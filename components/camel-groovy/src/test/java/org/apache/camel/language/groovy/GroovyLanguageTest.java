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
package org.apache.camel.language.groovy;

import org.apache.camel.test.junit5.LanguageTestSupport;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GroovyLanguageTest extends LanguageTestSupport {

    @Test
    public void testGroovyExpressions() {
        assertExpression("exchange.in.headers.foo", "abc");
        assertExpression("request.headers.foo", "abc");
        assertExpression("headers.foo", "abc");
        assertExpression("header.foo", "abc");
    }

    @Test
    public void testGroovyExchangeProperty() {
        exchange.setProperty("myProp1", "myValue");
        exchange.setProperty("myProp2", 123);

        assertExpression("exchange.properties.myProp1", "myValue");
        assertExpression("exchange.properties.myProp2", 123);

        assertExpression("exchangeProperties.myProp1", "myValue");
        assertExpression("exchangeProperties.myProp2", 123);
        assertExpression("exchangeProperty.myProp1", "myValue");
        assertExpression("exchangeProperty.myProp2", 123);
    }

    @Test
    public void testValidateExpression() throws Exception {
        GroovyLanguage g = new GroovyLanguage();
        g.setCamelContext(context);

        assertTrue(g.validateExpression("2 * 3"));
        assertTrue(g.validateExpression("exchange.getExchangeId()"));
        assertTrue(g.validatePredicate("2 * 3 > 4"));

        try {
            g.validateExpression("""
                    var a = 123;
                    println a */ 2;
                    """);
            fail("Should throw error");
        } catch (GroovyValidationException e) {
            assertEquals(23, e.getIndex());
            assertInstanceOf(MultipleCompilationErrorsException.class, e.getCause());
        }
    }

    @Override
    protected String getLanguageName() {
        return "groovy";
    }
}
