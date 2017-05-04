package com.bunjlabs.pjdoc.xml;

import com.bunjlabs.pjdoc.xml.parser.DocumentParser;
import com.bunjlabs.pjdoc.xml.templateprovider.TemplateProvider;
import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.xml.parser.DocumentParserContext;
import com.bunjlabs.pjdoc.xml.templateprovider.FileTemplateProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class XmlWorker {

    private final TemplateProvider templateProvider;
    private final DocumentParser documentParser;

    private Document document;

    public XmlWorker() {
        this.templateProvider = new FileTemplateProvider();
        this.documentParser = new DocumentParser();
    }

    public XmlWorker(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
        this.documentParser = new DocumentParser();
    }

    public Document work(File f) throws XmlParseException, FileNotFoundException {
        return work(new FileInputStream(f));
    }

    public Document work(InputStream is) throws XmlParseException {
        Node root;

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            root = documentBuilder.parse(is).getDocumentElement();
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new XmlParseException("Unable to parse xml document", ex);
        }

        document = documentParser.parseDocument(new DocumentParserContext(templateProvider), root);

        return document;
    }
}
