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
package org.apache.camel.component.kafka.serde;

import java.nio.ByteBuffer;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultKafkaHeaderSerializer implements KafkaHeaderSerializer, CamelContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultKafkaHeaderSerializer.class);
    private CamelContext camelContext;

    @Override
    public byte[] serialize(final String key, final Object value) {
        if (value instanceof String string) {
            return string.getBytes();
        } else if (value instanceof Long aLong) {
            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            buffer.putLong(aLong);
            return buffer.array();
        } else if (value instanceof Integer integer) {
            ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
            buffer.putInt(integer);
            return buffer.array();
        } else if (value instanceof Double aDouble) {
            ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
            buffer.putDouble(aDouble);
            return buffer.array();
        } else if (value instanceof Boolean b) {
            return b.toString().getBytes();
        } else if (value instanceof byte[] bytes) {
            return bytes;
        }
        if (camelContext != null) {
            byte[] converted = camelContext.getTypeConverter().tryConvertTo(byte[].class, value);
            if (converted != null) {
                return converted;
            }
        }

        LOG.debug("Cannot propagate header value of type[{}], skipping... "
                  + "Supported types: String, Integer, Long, Double, byte[].",
                value != null ? value.getClass() : "null");
        return null;
    }

    @Override
    public CamelContext getCamelContext() {
        return camelContext;
    }

    @Override
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }
}
