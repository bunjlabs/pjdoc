package com.bunjlabs.pjdoc.md;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.vladsch.flexmark.ast.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface NodeRenderingContext {

    public void render(Node node);

    public void renderChildren(Node parent);

    public Document getDocument();

    public void setCurrentElement(Element element);

    public Element getCurrentElement();

}
