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
package org.apache.camel.component.file;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spi.Registry;
import org.junit.jupiter.api.Test;

/**
 * For documentation how to write files using the FileProducer.
 */
public class ToFileRouteTest extends ContextTestSupport {

    // START SNIPPET: e1
    @Test
    public void testToFile() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedFileExists(testFile("report.txt"));

        template.sendBody("direct:reports", "This is a great report");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected Registry createCamelRegistry() throws Exception {
        // bind our processor in the registry with the given id
        Registry reg = super.createCamelRegistry();
        reg.bind("processReport", new ProcessReport());
        return reg;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                // the reports from the seda queue is processed by our processor
                // before they are written to files in the test directory
                from("direct:reports").process("processReport")
                        .to(fileUri(), "mock:result");
            }
        };
    }

    private static class ProcessReport implements Processor {

        @Override
        public void process(Exchange exchange) {
            String body = exchange.getIn().getBody(String.class);
            // do some business logic here

            // set the output to the file
            exchange.getMessage().setBody(body);

            // set the output filename using java code logic, notice that this
            // is done by setting
            // a special header property of the out exchange
            exchange.getMessage().setHeader(Exchange.FILE_NAME, "report.txt");
        }

    }
    // END SNIPPET: e1

}
