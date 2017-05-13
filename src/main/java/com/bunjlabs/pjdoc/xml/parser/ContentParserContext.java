package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ContentParserContext {

    private final String contentId;
    private final Map<String, List<Element>> contents;

    public ContentParserContext(String contentId) {
        this.contentId = contentId;
        this.contents = new HashMap<>();
    }

    public ContentParserContext(String contentId, Map<String, List<Element>> contents) {
        this.contentId = contentId;
        this.contents = contents;
    }

    public List<Element> parseChildren(Map<String, ContentParserAdapter> contentParserAdapters, Node subroot) throws XmlParseException {
        List<Element> elements = new LinkedList<>();

        for (int i = 0; i < subroot.getChildNodes().getLength(); i++) {
            Node node = subroot.getChildNodes().item(i);
            ContentParserAdapter adapter;
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE:
                    adapter = contentParserAdapters.get(node.getNodeName());
                    break;
                case Node.TEXT_NODE:
                    adapter = contentParserAdapters.get("");
                    break;
                default:
                    continue;
            }

            if (adapter == null) {
                throw new UnsupportedOperationException("Tag '" + node.getNodeName() + "' not supported in content context");
            }

            elements.addAll(adapter.parse(this, node));
        }
        return elements;
    }

    public String getContentId() {
        return contentId;
    }

    public Map<String, List<Element>> getContents() {
        return contents;
    }
}
