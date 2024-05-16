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
package org.apache.camel.jsonpath;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class JsonPathChoicePrimitiveTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                        .choice()
                            .when().jsonpath("$[?(@.counter>0)]")
                                .to("mock:positive")
                            .otherwise()
                                .to("mock:zero")
                        .end();
            }
        };
    }

    @Test
    public void testCounter() throws Exception {
        getMockEndpoint("mock:positive").expectedBodiesReceived("{\"counter\": 1}", "{\"counter\": 2}");
        getMockEndpoint("mock:zero").expectedBodiesReceived("{\"counter\": 0}");

        template.sendBody("direct:start", "{\"counter\": 1}");
        template.sendBody("direct:start", "{\"counter\": 0}");
        template.sendBody("direct:start", "{\"counter\": 2}");

        MockEndpoint.assertIsSatisfied(context);
    }

}
