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
package org.apache.camel.util;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.PredicateAssertHelper;
import org.junit.jupiter.api.Test;

import static org.apache.camel.builder.Builder.constant;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PredicateAssertHelperTest extends ContextTestSupport {

    @Test
    public void testPredicateAssertHelper() {
        Exchange exchange = new DefaultExchange(context);
        Predicate notNull = PredicateBuilder.isNotNull(constant("foo"));

        PredicateAssertHelper.assertMatches(notNull, "foo is not null", exchange);
        PredicateAssertHelper.assertMatches(notNull, null, exchange);
    }

    @Test
    public void testPredicateAssertHelperFailed() {
        Exchange exchange = new DefaultExchange(context);
        Predicate notNull = PredicateBuilder.isNotNull(constant(null));

        assertThrows(AssertionError.class, () -> PredicateAssertHelper.assertMatches(notNull, "foo is not null", exchange));
        assertThrows(AssertionError.class, () -> PredicateAssertHelper.assertMatches(notNull, null, exchange));
    }
}
