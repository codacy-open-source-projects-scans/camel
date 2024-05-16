/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.azure.servicebus;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.azure.core.util.BinaryData;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import org.apache.camel.*;
import org.apache.camel.component.azure.servicebus.client.ServiceBusSenderAsyncClientWrapper;
import org.apache.camel.component.azure.servicebus.operations.ServiceBusSenderOperations;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.support.DefaultAsyncProducer;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ServiceBusProducer extends DefaultAsyncProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceBusProducer.class);
    private final Map<ServiceBusProducerOperationDefinition, BiConsumer<Exchange, AsyncCallback>> operationsToExecute
            = new EnumMap<>(ServiceBusProducerOperationDefinition.class);
    private ServiceBusSenderAsyncClientWrapper senderClientWrapper;
    private ServiceBusConfigurationOptionsProxy configurationOptionsProxy;
    private ServiceBusSenderOperations serviceBusSenderOperations;

    {
        bind(ServiceBusProducerOperationDefinition.sendMessages, sendMessages());
        bind(ServiceBusProducerOperationDefinition.scheduleMessages, scheduleMessages());
    }

    public ServiceBusProducer(final Endpoint endpoint) {
        super(endpoint);
    }

    @Override
    protected void doInit() throws Exception {
        super.doInit();
        configurationOptionsProxy = new ServiceBusConfigurationOptionsProxy(getConfiguration());
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // create the senderClient
        final ServiceBusSenderAsyncClient senderClient = getConfiguration().getSenderAsyncClient() != null
                ? getConfiguration().getSenderAsyncClient()
                : getEndpoint().getServiceBusClientFactory().createServiceBusSenderAsyncClient(getConfiguration());

        // create the wrapper
        senderClientWrapper = new ServiceBusSenderAsyncClientWrapper(senderClient);

        // create the operations
        serviceBusSenderOperations = new ServiceBusSenderOperations(senderClientWrapper);
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {
        try {
            invokeOperation(configurationOptionsProxy.getServiceBusProducerOperationDefinition(exchange), exchange, callback);
            return false;
        } catch (Exception e) {
            exchange.setException(e);
            callback.done(true);
            return true;
        }

    }

    @Override
    protected void doStop() throws Exception {
        if (senderClientWrapper != null) {
            // shutdown async client
            senderClientWrapper.close();
        }

        super.doStop();
    }

    @Override
    public ServiceBusEndpoint getEndpoint() {
        return (ServiceBusEndpoint) super.getEndpoint();
    }

    public ServiceBusConfiguration getConfiguration() {
        return getEndpoint().getConfiguration();
    }

    private void bind(ServiceBusProducerOperationDefinition operation, BiConsumer<Exchange, AsyncCallback> fn) {
        operationsToExecute.put(operation, fn);
    }

    /**
     * Entry method that selects the appropriate ServiceBusProducerOperationDefinition operation and executes it
     */
    private void invokeOperation(
            final ServiceBusProducerOperationDefinition operation, final Exchange exchange, final AsyncCallback callback) {
        final ServiceBusProducerOperationDefinition operationsToInvoke;

        // we put sendMessage operation as default in case no operation has been selected
        if (ObjectHelper.isEmpty(operation)) {
            operationsToInvoke = ServiceBusProducerOperationDefinition.sendMessages;
        } else {
            operationsToInvoke = operation;
        }

        final BiConsumer<Exchange, AsyncCallback> fnToInvoke = operationsToExecute.get(operationsToInvoke);

        if (fnToInvoke != null) {
            fnToInvoke.accept(exchange, callback);
        } else {
            throw new RuntimeCamelException("Operation not supported. Value: " + operationsToInvoke);
        }
    }

    @SuppressWarnings("unchecked")
    private BiConsumer<Exchange, AsyncCallback> sendMessages() {
        return (exchange, callback) -> {
            final Object inputBody = exchange.getMessage().getBody();
            Map<String, Object> applicationProperties
                    = exchange.getMessage().getHeader(ServiceBusConstants.APPLICATION_PROPERTIES, Map.class);
            if (applicationProperties == null) {
                applicationProperties = new HashMap<>();
            }
            propagateHeaders(exchange, applicationProperties);
            final String correlationId = exchange.getMessage().getHeader(ServiceBusConstants.CORRELATION_ID, String.class);

            Mono<Void> sendMessageAsync;

            if (inputBody instanceof Iterable<?>) {
                sendMessageAsync
                        = serviceBusSenderOperations.sendMessages(convertBodyToList((Iterable<?>) inputBody),
                                configurationOptionsProxy.getServiceBusTransactionContext(exchange), applicationProperties,
                                correlationId);
            } else {
                Object convertedBody = inputBody instanceof BinaryData ? inputBody
                        : getConfiguration().isBinary() ? convertBodyToBinary(exchange)
                        : exchange.getMessage().getBody(String.class);

                sendMessageAsync = serviceBusSenderOperations.sendMessages(convertedBody,
                        configurationOptionsProxy.getServiceBusTransactionContext(exchange), applicationProperties,
                        correlationId);
            }

            subscribeToMono(sendMessageAsync, exchange, noop -> {
            }, callback);
        };
    }

    @SuppressWarnings("unchecked")
    private BiConsumer<Exchange, AsyncCallback> scheduleMessages() {
        return (exchange, callback) -> {
            final Object inputBody = exchange.getMessage().getBody();
            Map<String, Object> applicationProperties
                    = exchange.getMessage().getHeader(ServiceBusConstants.APPLICATION_PROPERTIES, Map.class);
            if (applicationProperties == null) {
                applicationProperties = new HashMap<>();
            }
            propagateHeaders(exchange, applicationProperties);
            final String correlationId = exchange.getMessage().getHeader(ServiceBusConstants.CORRELATION_ID, String.class);

            Mono<List<Long>> scheduleMessagesAsync;

            if (inputBody instanceof Iterable<?>) {
                scheduleMessagesAsync
                        = serviceBusSenderOperations.scheduleMessages(convertBodyToList((Iterable<?>) inputBody),
                                configurationOptionsProxy.getScheduledEnqueueTime(exchange),
                                configurationOptionsProxy.getServiceBusTransactionContext(exchange),
                                applicationProperties,
                                correlationId);
            } else {
                Object convertedBody = inputBody instanceof BinaryData ? inputBody
                        : getConfiguration().isBinary() ? convertBodyToBinary(exchange)
                        : exchange.getMessage().getBody(String.class);
                scheduleMessagesAsync
                        = serviceBusSenderOperations.scheduleMessages(convertedBody,
                                configurationOptionsProxy.getScheduledEnqueueTime(exchange),
                                configurationOptionsProxy.getServiceBusTransactionContext(exchange),
                                applicationProperties,
                                correlationId);
            }

            subscribeToMono(scheduleMessagesAsync, exchange,
                    sequenceNumbers -> exchange.getMessage().setBody(sequenceNumbers), callback);
        };
    }

    private List<?> convertBodyToList(final Iterable<?> inputBody) {
        return StreamSupport.stream(inputBody.spliterator(), false)
                .map(this::convertMessageBody)
                .toList();
    }

    private Object convertBodyToBinary(Exchange exchange) {
        Object body = exchange.getMessage().getBody();
        if (body instanceof InputStream) {
            return BinaryData.fromStream((InputStream) body);
        } else if (body instanceof Path) {
            return BinaryData.fromFile((Path) body);
        } else if (body instanceof File) {
            return BinaryData.fromFile(((File) body).toPath());
        } else {
            return BinaryData.fromBytes(exchange.getMessage().getBody(byte[].class));
        }
    }

    private Object convertMessageBody(Object inputBody) {
        TypeConverter typeConverter = getEndpoint().getCamelContext().getTypeConverter();
        if (inputBody instanceof BinaryData) {
            return inputBody;
        } else if (getConfiguration().isBinary()) {
            if (inputBody instanceof InputStream) {
                return BinaryData.fromStream((InputStream) inputBody);
            } else if (inputBody instanceof Path) {
                return BinaryData.fromFile((Path) inputBody);
            } else if (inputBody instanceof File) {
                return BinaryData.fromFile(((File) inputBody).toPath());
            } else {
                return typeConverter.convertTo(byte[].class, inputBody);
            }
        } else {
            return typeConverter.convertTo(String.class, inputBody);
        }
    }

    private void propagateHeaders(Exchange exchange, Map<String, Object> applicationProperties) {
        final HeaderFilterStrategy headerFilterStrategy = getConfiguration().getHeaderFilterStrategy();
        applicationProperties.putAll(
                exchange.getMessage().getHeaders().entrySet().stream()
                        .filter(entry -> !headerFilterStrategy.applyFilterToCamelHeaders(entry.getKey(), entry.getValue(),
                                exchange))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private <T> void subscribeToMono(
            final Mono<T> inputMono, final Exchange exchange, final Consumer<T> resultsCallback, final AsyncCallback callback) {
        inputMono
                .subscribe(resultsCallback, error -> {
                    // error but we continue
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Error processing async exchange with error: {}", error.getMessage());
                    }
                    exchange.setException(error);
                    callback.done(false);
                }, () -> {
                    // we are done from everything, so mark it as sync done
                    LOG.trace("All events with exchange have been sent successfully.");
                    callback.done(false);
                });
    }
}
