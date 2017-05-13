package com.bunjlabs.pjdoc.xml.templateprovider;

import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FileTemplateProvider implements TemplateProvider {

    private final String baseDir;

    public FileTemplateProvider() {
        this("./");
    }

    public FileTemplateProvider(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public Node provide(String name) throws XmlParseException {
        Node root;
        File templateFile = new File(baseDir, name);

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            root = documentBuilder.parse(templateFile).getDocumentElement();
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new XmlParseException("Unable to parse xml document", ex);
        }

        return root;
    }

}
