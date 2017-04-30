package com.bunjlabs.pjdoc.md;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ast.NodeAdaptingVisitHandler;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class NodeRenderingHandler<N extends Node> extends NodeAdaptingVisitHandler<N, NodeRenderer<N>> implements NodeRenderer<N> {

    public NodeRenderingHandler(Class<? extends N> aClass, NodeRenderer<N> adapter) {
        super(aClass, adapter);
    }

    @Override
    public void render(Node node, NodeRenderingContext context) {
        myAdapter.render((N) node, context);
    }
}
