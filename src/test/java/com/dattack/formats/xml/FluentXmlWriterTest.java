/*
 * Copyright (c) 2020, The Dattack team (http://www.dattack.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dattack.formats.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FluentXmlWriterTest {

    @Test
    public void testElementZeroChildren() {

        String xml = new FluentXmlWriter().writeStartDocument("1.1") //
            .writeStartElement("one").writeEndElement() //
            .writeEndDocument().getXml();
        String expected = "<?xml version=\"1.1\"?><one></one>";
        assertEquals(expected, xml);
    }

    @Test
    public void testEmptyDocumentWithVersionEncoding() {

        String xml = new FluentXmlWriter().writeStartDocument("UTF-8", "1.1").writeEndDocument().getXml();
        String expected = "<?xml version=\"1.1\" encoding=\"UTF-8\"?>";
        assertEquals(expected, xml);
    }

    @Test
    public void testEmptyElement() {

        String xml =
            new FluentXmlWriter().writeStartDocument("1.1").writeEmptyElement("one").writeEndDocument().getXml();
        String expected = "<?xml version=\"1.1\"?><one/>";
        assertEquals(expected, xml);
    }

    @Test
    public void testFullDocument() {

        String xml = new FluentXmlWriter().writeStartDocument() //
            .writeStartElement("one").writeAttribute("p1", 1) //
            .writeStartElement("two").writeAttribute("p2", "2") //
            .writeStartElement("three").writeAttribute("p3", "3").writeAttribute("p4", 4) //
            .writeCData("cdata text").writeEndElement() // // three
            .writeEndElement() // two
            .writeEndElement() // one
            .writeEndDocument().getXml();
        String expected = "<?xml version=\"1.0\" ?><one p1=\"1\"><two p2=\"2\"><three p3=\"3\" p4=\"4\">" //
            + "<![CDATA[cdata text]]></three></two></one>";
        assertEquals(expected, xml);
    }
}
