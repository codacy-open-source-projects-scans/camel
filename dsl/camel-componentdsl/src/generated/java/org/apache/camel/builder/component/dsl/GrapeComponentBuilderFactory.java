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
import org.apache.camel.component.grape.GrapeComponent;

/**
 * Fetch, load and manage additional jars dynamically after Camel Context was
 * started.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.ComponentDslMojo")
public interface GrapeComponentBuilderFactory {

    /**
     * Grape (camel-grape)
     * Fetch, load and manage additional jars dynamically after Camel Context
     * was started.
     * 
     * Category: management
     * Since: 2.16
     * Maven coordinates: org.apache.camel:camel-grape
     * 
     * @return the dsl builder
     */
    static GrapeComponentBuilder grape() {
        return new GrapeComponentBuilderImpl();
    }

    /**
     * Builder for the Grape component.
     */
    interface GrapeComponentBuilder extends ComponentBuilder<GrapeComponent> {
    
        
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
        default GrapeComponentBuilder lazyStartProducer(boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
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
        default GrapeComponentBuilder autowiredEnabled(boolean autowiredEnabled) {
            doSetProperty("autowiredEnabled", autowiredEnabled);
            return this;
        }
    
        /**
         * Implementation of org.apache.camel.component.grape.PatchesRepository,
         * by default: FilePatchesRepository.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.component.grape.PatchesRepository&lt;/code&gt; type.
         * 
         * Group: advanced
         * 
         * @param patchesRepository the value to set
         * @return the dsl builder
         */
        default GrapeComponentBuilder patchesRepository(org.apache.camel.component.grape.PatchesRepository patchesRepository) {
            doSetProperty("patchesRepository", patchesRepository);
            return this;
        }
    }

    class GrapeComponentBuilderImpl
            extends AbstractComponentBuilder<GrapeComponent>
            implements GrapeComponentBuilder {
        @Override
        protected GrapeComponent buildConcreteComponent() {
            return new GrapeComponent();
        }
        @Override
        protected boolean setPropertyOnComponent(
                Component component,
                String name,
                Object value) {
            switch (name) {
            case "lazyStartProducer": ((GrapeComponent) component).setLazyStartProducer((boolean) value); return true;
            case "autowiredEnabled": ((GrapeComponent) component).setAutowiredEnabled((boolean) value); return true;
            case "patchesRepository": ((GrapeComponent) component).setPatchesRepository((org.apache.camel.component.grape.PatchesRepository) value); return true;
            default: return false;
            }
        }
    }
}