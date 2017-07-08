package com.bunjlabs.pjdoc.pjx.parser;

import com.bunjlabs.pjdoc.pjx.XmlParseException;
import com.bunjlabs.pjdoc.pjx.templateprovider.TemplateProvider;
import java.util.Map;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DocumentParserContext {

    private final TemplateProvider templateProvider;

    public DocumentParserContext(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    public void parseChildren(Map<String, DocumentParserAdapter> contentParserAdapters, Node subroot) throws XmlParseException {
        for (int i = 0; i < subroot.getChildNodes().getLength(); i++) {
            Node node = subroot.getChildNodes().item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            DocumentParserAdapter adapter = contentParserAdapters.get(node.getNodeName());
            if (adapter == null) {
                throw new UnsupportedOperationException("Tag '" + node.getNodeName() + "' not supported in document context");
            }

            adapter.parse(this, node);
        }
    }

    public TemplateProvider getTemplateProvider() {
        return templateProvider;
    }
}
