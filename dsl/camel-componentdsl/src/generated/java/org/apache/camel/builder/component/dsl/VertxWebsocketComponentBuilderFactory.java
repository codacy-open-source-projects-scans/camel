/* Generated by camel build tools - do NOT edit this file! */
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
package org.apache.camel.builder.component.dsl;

import javax.annotation.processing.Generated;
import org.apache.camel.Component;
import org.apache.camel.builder.component.AbstractComponentBuilder;
import org.apache.camel.builder.component.ComponentBuilder;
import org.apache.camel.component.vertx.websocket.VertxWebsocketComponent;

/**
 * Expose WebSocket endpoints and connect to remote WebSocket servers using
 * Vert.x
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.ComponentDslMojo")
public interface VertxWebsocketComponentBuilderFactory {

    /**
     * Vert.x WebSocket (camel-vertx-websocket)
     * Expose WebSocket endpoints and connect to remote WebSocket servers using
     * Vert.x
     * 
     * Category: http,networking
     * Since: 3.5
     * Maven coordinates: org.apache.camel:camel-vertx-websocket
     * 
     * @return the dsl builder
     */
    static VertxWebsocketComponentBuilder vertxWebsocket() {
        return new VertxWebsocketComponentBuilderImpl();
    }

    /**
     * Builder for the Vert.x WebSocket component.
     */
    interface VertxWebsocketComponentBuilder extends ComponentBuilder<VertxWebsocketComponent> {
    
        
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions (if possible) occurred while the Camel
         * consumer is trying to pickup incoming messages, or the likes, will
         * now be processed as a message and handled by the routing Error
         * Handler. Important: This is only possible if the 3rd party component
         * allows Camel to be alerted if an exception was thrown. Some
         * components handle this internally only, and therefore
         * bridgeErrorHandler is not possible. In other situations we may
         * improve the Camel component to hook into the 3rd party component and
         * make this possible for future releases. By default the consumer will
         * use the org.apache.camel.spi.ExceptionHandler to deal with
         * exceptions, that will be logged at WARN or ERROR level and ignored.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: consumer
         * 
         * @param bridgeErrorHandler the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder bridgeErrorHandler(boolean bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
    
        
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: producer
         * 
         * @param lazyStartProducer the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder lazyStartProducer(boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
    
        
        /**
         * Whether the WebSocket client should add the Origin header to the
         * WebSocket handshake request.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: true
         * Group: advanced
         * 
         * @param allowOriginHeader the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder allowOriginHeader(boolean allowOriginHeader) {
            doSetProperty("allowOriginHeader", allowOriginHeader);
            return this;
        }
    
        
        /**
         * Whether autowiring is enabled. This is used for automatic autowiring
         * options (the option must be marked as autowired) by looking up in the
         * registry to find if there is a single instance of matching type,
         * which then gets configured on the component. This can be used for
         * automatic configuring JDBC data sources, JMS connection factories,
         * AWS Clients, etc.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: true
         * Group: advanced
         * 
         * @param autowiredEnabled the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder autowiredEnabled(boolean autowiredEnabled) {
            doSetProperty("autowiredEnabled", autowiredEnabled);
            return this;
        }
    
        
        /**
         * Default value for host name that the WebSocket should bind to.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Default: 0.0.0.0
         * Group: advanced
         * 
         * @param defaultHost the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder defaultHost(java.lang.String defaultHost) {
            doSetProperty("defaultHost", defaultHost);
            return this;
        }
    
        /**
         * Default value for the port that the WebSocket should bind to.
         * 
         * The option is a: &lt;code&gt;int&lt;/code&gt; type.
         * 
         * Group: advanced
         * 
         * @param defaultPort the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder defaultPort(int defaultPort) {
            doSetProperty("defaultPort", defaultPort);
            return this;
        }
    
        /**
         * The value of the Origin header that the WebSocket client should use
         * on the WebSocket handshake request. When not specified, the WebSocket
         * client will automatically determine the value for the Origin from the
         * request URL.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: advanced
         * 
         * @param originHeaderUrl the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder originHeaderUrl(java.lang.String originHeaderUrl) {
            doSetProperty("originHeaderUrl", originHeaderUrl);
            return this;
        }
    
        /**
         * To provide a custom vertx router to use on the WebSocket server.
         * 
         * The option is a: &lt;code&gt;io.vertx.ext.web.Router&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param router the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder router(io.vertx.ext.web.Router router) {
            doSetProperty("router", router);
            return this;
        }
    
        /**
         * To use an existing vertx instead of creating a new instance.
         * 
         * The option is a: &lt;code&gt;io.vertx.core.Vertx&lt;/code&gt; type.
         * 
         * Group: advanced
         * 
         * @param vertx the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder vertx(io.vertx.core.Vertx vertx) {
            doSetProperty("vertx", vertx);
            return this;
        }
    
        /**
         * To provide a custom set of vertx options for configuring vertx.
         * 
         * The option is a: &lt;code&gt;io.vertx.core.VertxOptions&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param vertxOptions the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder vertxOptions(io.vertx.core.VertxOptions vertxOptions) {
            doSetProperty("vertxOptions", vertxOptions);
            return this;
        }
    
        
        /**
         * Enable usage of global SSL context parameters.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: security
         * 
         * @param useGlobalSslContextParameters the value to set
         * @return the dsl builder
         */
        default VertxWebsocketComponentBuilder useGlobalSslContextParameters(boolean useGlobalSslContextParameters) {
            doSetProperty("useGlobalSslContextParameters", useGlobalSslContextParameters);
            return this;
        }
    }

    class VertxWebsocketComponentBuilderImpl
            extends AbstractComponentBuilder<VertxWebsocketComponent>
            implements VertxWebsocketComponentBuilder {
        @Override
        protected VertxWebsocketComponent buildConcreteComponent() {
            return new VertxWebsocketComponent();
        }
        @Override
        protected boolean setPropertyOnComponent(
                Component component,
                String name,
                Object value) {
            switch (name) {
            case "bridgeErrorHandler": ((VertxWebsocketComponent) component).setBridgeErrorHandler((boolean) value); return true;
            case "lazyStartProducer": ((VertxWebsocketComponent) component).setLazyStartProducer((boolean) value); return true;
            case "allowOriginHeader": ((VertxWebsocketComponent) component).setAllowOriginHeader((boolean) value); return true;
            case "autowiredEnabled": ((VertxWebsocketComponent) component).setAutowiredEnabled((boolean) value); return true;
            case "defaultHost": ((VertxWebsocketComponent) component).setDefaultHost((java.lang.String) value); return true;
            case "defaultPort": ((VertxWebsocketComponent) component).setDefaultPort((int) value); return true;
            case "originHeaderUrl": ((VertxWebsocketComponent) component).setOriginHeaderUrl((java.lang.String) value); return true;
            case "router": ((VertxWebsocketComponent) component).setRouter((io.vertx.ext.web.Router) value); return true;
            case "vertx": ((VertxWebsocketComponent) component).setVertx((io.vertx.core.Vertx) value); return true;
            case "vertxOptions": ((VertxWebsocketComponent) component).setVertxOptions((io.vertx.core.VertxOptions) value); return true;
            case "useGlobalSslContextParameters": ((VertxWebsocketComponent) component).setUseGlobalSslContextParameters((boolean) value); return true;
            default: return false;
            }
        }
    }
}