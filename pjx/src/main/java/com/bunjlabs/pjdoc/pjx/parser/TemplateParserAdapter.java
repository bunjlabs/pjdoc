package com.bunjlabs.pjdoc.pjx.parser;

import com.bunjlabs.pjdoc.pjx.XmlParseException;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface TemplateParserAdapter {

    public void parse(TemplateParserContext context, Node node) throws XmlParseException;
}
