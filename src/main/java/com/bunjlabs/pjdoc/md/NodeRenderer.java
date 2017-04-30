package com.bunjlabs.pjdoc.md;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ast.NodeAdaptingVisitor;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface NodeRenderer<N extends Node> extends NodeAdaptingVisitor<N> {

    void render(N node, NodeRenderingContext context);
}
