/*
 * Copyright (c) 2022, The Dattack team (http://www.dattack.com)
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

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * A utility class that provides a fluent interface for the creation of XML documents.
 *
 * @author cvarela
 * @since 0.4
 */
@SuppressWarnings({ "checkstyle:AbbreviationAsWordInName", "unused" })
public class FluentXmlWriter {

    private final ByteArrayOutputStream buffer;
    private XMLStreamWriter xmlStreamWriter;

    public FluentXmlWriter() {
        buffer = new ByteArrayOutputStream();
    }

    public FluentXmlWriter close() throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().close();
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter flush() throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().flush();
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public String getXml() {
        try {
            getXmlStreamWriter().flush();
            return buffer.toString(StandardCharsets.UTF_8.name());
        } catch (XMLStreamException | UnsupportedEncodingException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter reset() {
        buffer.reset();
        return this;
    }

    public FluentXmlWriter setDefaultNamespace(final String uri) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().setDefaultNamespace(uri);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter setNamespaceContext(final NamespaceContext context) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().setNamespaceContext(context);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter setPrefix(final String prefix, final String uri) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().setPrefix(prefix, uri);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeAttribute(String localName, int value) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeAttribute(localName, String.valueOf(value));
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeAttribute(String localName, boolean value) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeAttribute(localName, String.valueOf(value));
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeAttribute(final String localName, final String value) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeAttribute(localName, value);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeAttribute(final String prefix, final String namespaceURI, final String localName,
        final String value) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeAttribute(prefix, namespaceURI, localName, value);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeAttribute(final String namespaceURI, final String localName,
        final String value) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeAttribute(namespaceURI, localName, value);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeCData(final String data) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeCData(data);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeCharacters(final String text) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeCharacters(text);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeCharacters(final char[] text, final int start, final int len) //
        throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeCharacters(text, start, len);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeComment(final String data) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeComment(data);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeDTD(final String dtd) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeDTD(dtd);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeDefaultNamespace(final String namespaceURI) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeDefaultNamespace(namespaceURI);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEmptyElement(final String namespaceURI,
        final String localName) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeEmptyElement(namespaceURI, localName);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEmptyElement(final String prefix, final String localName,
        final String namespaceURI) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeEmptyElement(prefix, localName, namespaceURI);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEmptyElement(final String localName) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeEmptyElement(localName);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEndDocument() throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeEndDocument();
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEndElement() throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeEndElement();
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeEntityRef(final String name) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeEntityRef(name);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeNamespace(final String prefix,
        final String namespaceURI) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeNamespace(prefix, namespaceURI);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeProcessingInstruction(final String target) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeProcessingInstruction(target);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeProcessingInstruction(final String target, final String data) //
        throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeProcessingInstruction(target, data);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartDocument() throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeStartDocument();
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartDocument(final String version) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeStartDocument(version);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartDocument(final String encoding,
        final String version) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeStartDocument(encoding, version);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartElement(final String localName) throws FluentXmlWriterException {
        try {
            getXmlStreamWriter().writeStartElement(localName);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartElement(final String namespaceURI,
        final String localName) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeStartElement(namespaceURI, localName);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    public FluentXmlWriter writeStartElement(final String prefix, final String localName,
        final String namespaceURI) throws FluentXmlWriterException
    {
        try {
            getXmlStreamWriter().writeStartElement(prefix, localName, namespaceURI);
            return this;
        } catch (XMLStreamException e) {
            throw new FluentXmlWriterException(e);
        }
    }

    private synchronized XMLStreamWriter getXmlStreamWriter() throws XMLStreamException {
        if (xmlStreamWriter == null) {
            xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(buffer);
        }
        return xmlStreamWriter;
    }

}
