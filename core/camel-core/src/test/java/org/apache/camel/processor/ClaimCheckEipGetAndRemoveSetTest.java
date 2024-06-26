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
package org.apache.camel.processor;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ClaimCheckOperation;
import org.junit.jupiter.api.Test;

public class ClaimCheckEipGetAndRemoveSetTest extends ContextTestSupport {

    @Test
    public void testGetAndRemoveSet() throws Exception {
        getMockEndpoint("mock:a").expectedBodiesReceived("Hello World");
        getMockEndpoint("mock:b").expectedBodiesReceived("Bye World");
        getMockEndpoint("mock:c").expectedBodiesReceived("Hello World");
        getMockEndpoint("mock:d").expectedBodiesReceived("Hi World");
        // it was removed so the data is not changed
        getMockEndpoint("mock:e").expectedBodiesReceived("Hi World");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").to("mock:a").claimCheck(ClaimCheckOperation.Set, "foo").transform().constant("Bye World")
                        .to("mock:b")
                        .claimCheck(ClaimCheckOperation.GetAndRemove, "foo").to("mock:c").transform().constant("Hi World")
                        .to("mock:d")
                        .claimCheck(ClaimCheckOperation.GetAndRemove, "foo").to("mock:e");
            }
        };
    }
}
