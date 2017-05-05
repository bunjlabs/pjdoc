package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.util.List;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TemplateParser {

    private final ContentParser contentParser;

    public TemplateParser() {
        this.contentParser = new ContentParser();
    }

    public void parseTemplate(TemplateParserContext context, Node root) throws XmlParseException {
        if (!root.getNodeName().equals("pj-template")) {
            throw new XmlParseException("Root element must be pj-template");
        }

        ParserUtils.parseDocumentAttributes(context.getDocument(), root.getAttributes());
        ParserUtils.parseStyles(context.getDocument(), root.getAttributes());

        List<Element> templateContent = contentParser.parseContent(
                new ContentParserContext("@template-" + context.getTemplateId(), context.getContents()), root);

        context.getContents().put("main", templateContent);
    }
}
