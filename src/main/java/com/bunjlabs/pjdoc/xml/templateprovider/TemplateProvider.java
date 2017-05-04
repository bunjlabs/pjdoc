package com.bunjlabs.pjdoc.xml.templateprovider;

import com.bunjlabs.pjdoc.xml.XmlParseException;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface TemplateProvider {

    public Node provide(String name) throws XmlParseException;
}
