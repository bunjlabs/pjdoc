package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.xml.XmlParseException;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface DocumentParserAdapter {

    public void parse(DocumentParserContext context, Node node) throws XmlParseException;
}
