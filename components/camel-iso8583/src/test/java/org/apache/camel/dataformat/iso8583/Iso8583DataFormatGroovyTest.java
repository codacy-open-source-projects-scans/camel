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
package org.apache.camel.dataformat.iso8583;

import java.io.File;
import java.util.Map;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class Iso8583DataFormatGroovyTest extends CamelTestSupport {

    @Test
    public void testUnmarshal() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(1);
        getMockEndpoint("mock:result").message(0).body().isInstanceOf(Map.class);
        getMockEndpoint("mock:result").message(0).body().simple("${body[op]}").isEqualTo("650000");
        getMockEndpoint("mock:result").message(0).body().simple("${body[amount]}").isEqualTo("30.00");
        getMockEndpoint("mock:result").message(0).body().simple("${body[ref]}").isEqualTo("001234425791");
        getMockEndpoint("mock:result").message(0).body().simple("${body[response]}").isEqualTo("00");
        getMockEndpoint("mock:result").message(0).body().simple("${body[terminal]}").isEqualTo("614209027600TéST");
        getMockEndpoint("mock:result").message(0).body().simple("${body[currency]}").isEqualTo("484");

        template.sendBody("direct:unmarshal", new File("src/test/resources/parse1.txt"));

        MockEndpoint.assertIsSatisfied(context);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:unmarshal").unmarshal().iso8583("0210")
                        .transform().groovy(
                                """
                                          [
                                            "op": body[3].value,
                                            "amount": body[4].value,
                                            "ref": body[37].value,
                                            "response": body[39].value,
                                            "terminal": body[41].value,
                                            "currency": body[49].value
                                          ]
                                        """)
                        .log("${body}")
                        .to("mock:result");
            }
        };
    }
}
