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
package org.apache.camel.component.google.drive;

import java.util.Collection;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import org.apache.camel.CamelContext;

public class MyClientFactory implements GoogleDriveClientFactory {

    public MyClientFactory() {
    }

    @Override
    public Drive makeClient(
            String clientId, String clientSecret, Collection<String> scopes, String applicationName, String refreshToken,
            String accessToken) {
        return new Drive(new NetHttpTransport(), new JacksonFactory(), null);
    }

    @Override
    public Drive makeClient(
            CamelContext camelContext, String serviceAccountKey, Collection<String> scopes, String applicationName,
            String delegate) {
        throw new IllegalArgumentException("Not implemented");
    }
}
