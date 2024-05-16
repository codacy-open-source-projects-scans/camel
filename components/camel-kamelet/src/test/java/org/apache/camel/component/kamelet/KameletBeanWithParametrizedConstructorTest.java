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
package org.apache.camel.component.kamelet;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class KameletBeanWithParametrizedConstructorTest extends CamelTestSupport {

    protected final String name = "Ada";

    protected final String message = "Welcome";

    protected final String out = "mock:out";

    protected final String in = "direct:start";

    @Test
    public void testKamelet() throws Exception {

        getMockEndpoint("mock:out").expectedBodiesReceived("Hi " + name + "! " + message);

        template.sendBody(in, "Hello");

        MockEndpoint.assertIsSatisfied(context);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                routeTemplate("salute")
                        .templateParameter("name")
                        .templateParameter("message")
                        .templateBean("myBean", "#class:org.apache.camel.component.kamelet.MyBean({{message}}, {{name}})")
                        .from("kamelet:source")
                        .to("bean:{{myBean}}?method=salute");

                routeTemplate("myTemplate")
                        .templateParameter("uri")
                        .templateParameter("in")
                        .templateParameter("out")
                        .from("{{in}}")
                        .to("{{uri}}")
                        .to("{{out}}");

                templatedRoute("myTemplate")
                        .parameter("in", in)
                        .parameter("uri", "kamelet:salute/test?name=" + name + "&message=" + message)
                        .parameter("out", out);
            }
        };
    }
}
