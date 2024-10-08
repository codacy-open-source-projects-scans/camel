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
package org.apache.camel.component.cxf.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.apache.camel.StreamCache;
import org.apache.camel.component.cxf.common.CxfPayload;
import org.apache.camel.test.junit5.ExchangeTestSupport;
import org.apache.cxf.staxutils.StaxUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CxfPayloadConverterTest extends ExchangeTestSupport {
    private Document document;
    private CxfPayload<String[]> payload;
    private CxfPayload<String[]> emptyPayload;
    private CxfPayload<String[]> staxpayload;
    private FileInputStream inputStream;

    @Override
    public void setupResources() throws Exception {
        File file = new File("src/test/resources/org/apache/camel/component/cxf/converter/test.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        List<Source> body = new ArrayList<>();
        body.add(new DOMSource(document.getDocumentElement()));
        List<Source> staxbody = new ArrayList<>();
        staxbody.add(new StAXSource(StaxUtils.createXMLStreamReader(new FileInputStream(file), "utf-8")));
        payload = new CxfPayload<>(new ArrayList<String[]>(), body, null);
        emptyPayload = new CxfPayload<>(new ArrayList<String[]>(), new ArrayList<Source>(), null);
        staxpayload = new CxfPayload<>(new ArrayList<String[]>(), staxbody, null);
        inputStream = new FileInputStream(file);
    }

    @Test
    public void testDocumentToCxfPayload() {
        CxfPayload<String[]> payload = CxfPayloadConverter.documentToCxfPayload(document, exchange);
        assertNotNull(payload);
        assertEquals(1, payload.getBody().size(), "Get a wrong size of body");
    }

    @Test
    public void testNodeListToCxfPayload() {
        NodeList nodeList = document.getChildNodes();
        CxfPayload<String[]> payload = CxfPayloadConverter.nodeListToCxfPayload(nodeList, exchange);
        assertNotNull(payload);
        assertEquals(1, payload.getBody().size(), "Get a wrong size of body");
    }

    @Test
    public void testCxfPayloadToNodeList() {
        NodeList nodeList = CxfPayloadConverter.cxfPayloadToNodeList(payload, exchange);
        assertNotNull(nodeList);
        assertEquals(1, nodeList.getLength(), "Get a worng size of nodeList");
    }

    @Test
    public void testCxfPayloadToStreamCache() {
        StreamCache streamCache = CxfPayloadConverter.cxfPayLoadToStreamCache(payload, exchange);
        assertNotNull(streamCache);
        assertTrue(streamCache instanceof CxfPayload);
    }

    @Test
    public void testToCxfPayload() {
        // use default type converter
        exchange.getIn().setBody(inputStream);
        CxfPayload<?> payload = exchange.getIn().getBody(CxfPayload.class);
        assertTrue(payload instanceof CxfPayload);
        assertEquals(1, payload.getBodySources().size(), "Get a wrong size of body");
        assertEquals(1, payload.getBody().size(), "Get a wrong size of body");
        assertEquals("streamsource", payload.getBodySources().get(0).getClass().getSimpleName().toLowerCase(),
                "expects stream source");
    }

    @Test
    public void testByteArrayToCxfPayload() {
        // convert to byte array
        exchange.getIn().setBody(inputStream);
        byte[] bytes = exchange.getIn().getBody(byte[].class);
        assertNotNull(bytes);
        exchange.getIn().setBody(bytes);
        // use default type converter
        CxfPayload<?> payload = exchange.getIn().getBody(CxfPayload.class);
        assertTrue(payload instanceof CxfPayload);
        assertEquals(1, payload.getBodySources().size(), "Get a wrong size of body");
        assertEquals(1, payload.getBody().size(), "Get a wrong size of body");
    }

    @Test
    public void testInvalidByteArrayToCxfPayload() {
        exchange.getIn().setBody("NON-XML-Payload".getBytes());
        CxfPayload<?> payload = exchange.getIn().getBody(CxfPayload.class);
        assertNull(payload);
    }

    @Test
    public void testFromCxfPayload() {
        exchange.getIn().setBody(payload);
        InputStream inputStream = exchange.getIn().getBody(InputStream.class);
        assertTrue(inputStream instanceof InputStream);
    }

    @Test
    public void testFromCxfStAXPayload() {
        exchange.getIn().setBody(staxpayload);
        InputStream inputStream = exchange.getIn().getBody(InputStream.class);
        assertTrue(inputStream instanceof InputStream);
    }

    @Test
    public void testCxfPayloadToNode() {
        // call the payload conversion that works
        exchange.getIn().setBody(payload);
        Node node = exchange.getIn().getBody(Node.class);
        assertNotNull(node);

        // do the empty conversion
        exchange.getIn().setBody(emptyPayload);
        node = exchange.getIn().getBody(Node.class);
        assertNull(node);

        // do the same one that worked before
        exchange.getIn().setBody(payload);
        node = exchange.getIn().getBody(Node.class);
        assertNotNull(node);

        // To make sure we always get the element here
        Element root = (Element) node;
        assertEquals("root", root.getNodeName(), "root element name");
        assertEquals("http://www.test.org/foo", root.getNamespaceURI(), "root element namespace");
        Element bar = (Element) root.getElementsByTagName("bar").item(0);
        assertEquals("bar", bar.getNodeName(), "child element name");
        assertEquals("http://www.test.org/foo", bar.getNamespaceURI(), "child element namespace");
    }

    @Test
    public void testEmptySaxPayload() {
        exchange.getIn().setBody(emptyPayload);
        Object out = exchange.getIn().getBody(SAXSource.class);
        assertNull(out, "Should not be able to convert an empty payload");
    }

    @Test
    public void testEmptySaxAgainPayload() {
        // do the empty
        exchange.getIn().setBody(emptyPayload);
        Object out = exchange.getIn().getBody(SAXSource.class);
        assertNull(out, "Should not be able to convert an empty payload");

        // do the working
        exchange.getIn().setBody(payload);
        out = exchange.getIn().getBody(SAXSource.class);
        assertNotNull(out, "Should be able to convert a non-empty payload");

        // do the empty one again
        exchange.getIn().setBody(emptyPayload);
        out = exchange.getIn().getBody(SAXSource.class);
        assertNull(out, "Should not be able to convert an empty payload");

        // do the working
        exchange.getIn().setBody(payload);
        out = exchange.getIn().getBody(SAXSource.class);
        assertNotNull(out, "Should be able to convert a non-empty payload");
    }

}
