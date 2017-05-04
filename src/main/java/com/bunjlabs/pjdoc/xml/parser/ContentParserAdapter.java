package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.util.List;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface ContentParserAdapter {

    public List<Element> parse(ContentParserContext context, Node node) throws XmlParseException;
}
