/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunjlabs.pjdoc.md;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.vladsch.flexmark.ast.Node;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class MarkdownRenderer implements NodeRenderingContext {

    private final Document document;
    private final Map<Class<?>, NodeRenderingHandler> renderers;

    private Element currentElement;

    public MarkdownRenderer(Document document) {
        this.renderers = new HashMap<Class<?>, NodeRenderingHandler>(32);

        CoreNodeRenderer nodeRenderer = new CoreNodeRenderer();
        for (NodeRenderingHandler renderingHandler : nodeRenderer.getNodeRenderingHandlers()) {
            renderers.put(renderingHandler.getNodeType(), renderingHandler);
        }

        this.document = document;
    }

    @Override
    public void render(Node node) {
        renderNode(node, this);
    }

    @Override
    public void renderChildren(Node parent) {
        renderChildrenNode(parent, this);
    }

    private void renderChildrenNode(Node parent, NodeRenderingContext subContext) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();
            renderNode(node, subContext);
            node = next;
        }
    }

    private void renderNode(Node node, NodeRenderingContext context) {
        NodeRenderingHandler nodeRenderer = renderers.get(node.getClass());

        if (nodeRenderer == null) {
            throw new UnsupportedOperationException("Unsupported node type " + node.getClass().getName());
        }

        nodeRenderer.render(node, context);
    }

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public void setCurrentElement(Element element) {
        this.currentElement = element;
    }

    @Override
    public Element getCurrentElement() {
        return currentElement;
    }
}
