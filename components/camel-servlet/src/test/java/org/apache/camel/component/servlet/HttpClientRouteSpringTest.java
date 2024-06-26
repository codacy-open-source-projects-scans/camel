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
package org.apache.camel.component.servlet;

import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import org.springframework.web.context.ContextLoaderListener;

public class HttpClientRouteSpringTest extends HttpClientRouteTest {

    @Override
    public void setupResources() throws Exception {
        super.setupResources();
        testConfiguration().withAutoStartContext(false);
    }

    @Override
    protected DeploymentInfo getDeploymentInfo() {
        return Servlets.deployment()
                .setClassLoader(getClass().getClassLoader())
                .setContextPath(CONTEXT)
                .setDeploymentName(getClass().getName())
                .addInitParameter("contextConfigLocation", "classpath:org/apache/camel/component/servlet/camelContext.xml")
                .addListener(Servlets.listener(ContextLoaderListener.class))
                .addServlet(Servlets.servlet("CamelServlet", CamelHttpTransportServlet.class)
                        .addInitParam("matchOnUriPrefix", "true")
                        .addMapping("/services/*"));
    }

}
