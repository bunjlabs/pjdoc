package com.bunjlabs.pjdoc.pjx.parser;

import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.pjx.XmlParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DocumentParser {

    private final ContentParser contentParser;
    private final TemplateParser templateParser;
    private final Map<String, DocumentParserAdapter> parserAdapters;
    private final Map<String, String> parameters = new HashMap<>();

    private final Map<String, List<Element>> contents = new HashMap<>();

    public DocumentParser() {
        this.parserAdapters = getParserAdapters();
        this.contentParser = new ContentParser();
        this.templateParser = new TemplateParser();
    }

    private Map<String, DocumentParserAdapter> getParserAdapters() {
        HashMap<String, DocumentParserAdapter> map = new HashMap<>();

        map.put("content", (c, n) -> DocumentParser.this.parseContent(c, n));

        return map;
    }

    public Document parseDocument(DocumentParserContext context, Node node) throws XmlParseException {
        if (!node.getNodeName().equals("pj-document")) {
            throw new XmlParseException("Root element must be pj-document");
        }

        Document document = new Document();

        context.parseChildren(parserAdapters, node);

        Node templateNode = node.getAttributes().getNamedItem("template");

        if (templateNode != null) {
            String templateid = templateNode.getNodeValue();
            Node templateRoot = context.getTemplateProvider().provide(templateid);
            templateParser.parseTemplate(new TemplateParserContext(contents, parameters, document, templateid), templateRoot);
        }

        ParserUtils.parseDocumentAttributes(document, node.getAttributes());
        ParserUtils.parseStyles(document, node.getAttributes());

        List<Element> mainElementsTree = contents.get("main");

        if (mainElementsTree != null) {
            mainElementsTree.forEach((e) -> {
                if (e instanceof BlockElement) {
                    document.add((BlockElement) e);
                }
            });
        }

        return document;
    }

    private void parseContent(DocumentParserContext context, Node node) throws XmlParseException {
        Node idNode = node.getAttributes().getNamedItem("name");

        String id = idNode == null ? "" : idNode.getNodeValue();

        ContentParserContext contentParserContext = new ContentParserContext(id, parameters);

        List<Element> parseContent = contentParser.parseContent(contentParserContext, node);

        contents.put(id, parseContent);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

}
