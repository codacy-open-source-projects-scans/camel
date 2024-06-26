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
package org.apache.camel.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.cache.DefaultConsumerCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisabledOnOs(OS.AIX)
public class ManagedConsumerCacheTest extends ManagementTestSupport {

    @Test
    public void testManageConsumerCache() throws Exception {
        // always register services in JMX so we can enlist our consumer template/cache
        context.getManagementStrategy().getManagementAgent().setRegisterAlways(true);

        DefaultConsumerCache cache = new DefaultConsumerCache(this, context, 0);
        context.addService(cache);

        template.sendBody("direct:start", "Hello World");

        Endpoint endpoint = context.getEndpoint("seda:queue");
        PollingConsumer consumer = cache.acquirePollingConsumer(endpoint);
        Exchange out = consumer.receive(3000);
        assertNotNull(out, "Should got an exchange");
        assertEquals("Hello World", out.getIn().getBody());

        // get the stats for the route
        MBeanServer mbeanServer = getMBeanServer();
        Set<ObjectName> set = mbeanServer.queryNames(new ObjectName("*:type=services,*"), null);
        List<ObjectName> list = new ArrayList<>(set);
        ObjectName on = null;
        for (ObjectName name : list) {
            if (name.getCanonicalName().contains("ConsumerCache")) {
                on = name;
                break;
            }
        }

        assertNotNull(on, "Should have found ConsumerCache");

        Integer max = (Integer) mbeanServer.getAttribute(on, "MaximumCacheSize");
        assertEquals(1000, max.intValue());

        Integer current = (Integer) mbeanServer.getAttribute(on, "Size");
        assertEquals(1, current.intValue());

        String source = (String) mbeanServer.getAttribute(on, "Source");
        assertNotNull(source);
        assertTrue(source.contains("testManageConsumerCache"));

        // purge
        mbeanServer.invoke(on, "purge", null, null);

        current = (Integer) mbeanServer.getAttribute(on, "Size");
        assertEquals(0, current.intValue());

        // stop the consumer as it was purged from the cache
        // so we need to manually stop it
        consumer.stop();
        cache.stop();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("seda:queue");
            }
        };
    }

}
