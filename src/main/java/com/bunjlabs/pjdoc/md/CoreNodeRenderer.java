package com.bunjlabs.pjdoc.md;

import com.bunjlabs.pjdoc.font.FontStyle;
import com.bunjlabs.pjdoc.font.FontWeight;
import com.bunjlabs.pjdoc.layout.attributes.Style;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.vladsch.flexmark.ast.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class CoreNodeRenderer {

    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet<>(Arrays.asList(
                new NodeRenderingHandler<AutoLink>(AutoLink.class, (AutoLink node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Document>(Document.class, (Document node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Emphasis>(Emphasis.class, (Emphasis node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<StrongEmphasis>(StrongEmphasis.class, (StrongEmphasis node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Heading>(Heading.class, (Heading node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Image>(Image.class, (Image node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Link>(Link.class, (Link node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<LinkRef>(LinkRef.class, (LinkRef node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Paragraph>(Paragraph.class, (Paragraph node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<Text>(Text.class, (Text node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<TextBase>(TextBase.class, (TextBase node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                }),
                new NodeRenderingHandler<SoftLineBreak>(SoftLineBreak.class, (SoftLineBreak node, NodeRenderingContext context) -> {
                    CoreNodeRenderer.this.render(node, context);
                })
        ));
    }

    private void render(AutoLink node, NodeRenderingContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void render(Document node, NodeRenderingContext context) {
        context.renderChildren(node);
    }

    private void render(Emphasis node, NodeRenderingContext context) {
        context.renderChildren(node);
    }

    private void render(Heading node, NodeRenderingContext context) {
        com.bunjlabs.pjdoc.layout.elements.Paragraph p = new com.bunjlabs.pjdoc.layout.elements.Paragraph();

        p.addStyle(new Style().setFontSize(24 - (node.getLevel() * 4)));

        context.setCurrentElement(p);
        context.renderChildren(node);
        context.getDocument().add(p);
    }

    private void render(StrongEmphasis node, NodeRenderingContext context) {
        context.renderChildren(node);
    }

    private void render(Image node, NodeRenderingContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void render(Link node, NodeRenderingContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void render(LinkRef node, NodeRenderingContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void render(Paragraph node, NodeRenderingContext context) {
        com.bunjlabs.pjdoc.layout.elements.Paragraph p = new com.bunjlabs.pjdoc.layout.elements.Paragraph();

        context.setCurrentElement(p);
        context.renderChildren(node);
        context.getDocument().add(p);

    }

    private void render(Text node, NodeRenderingContext context) {
        Element currentElement = context.getCurrentElement();
        if (currentElement == null) {
            return;
        }

        if (currentElement instanceof com.bunjlabs.pjdoc.layout.elements.Paragraph) {
            com.bunjlabs.pjdoc.layout.elements.Paragraph p = (com.bunjlabs.pjdoc.layout.elements.Paragraph) currentElement;

            com.bunjlabs.pjdoc.layout.elements.Text text = new com.bunjlabs.pjdoc.layout.elements.Text(node.getChars().unescape());

            if (node.getParent() instanceof Emphasis) {
                text.addStyle(new Style().setFontStyle(FontStyle.ITALIC));
            }

            if (node.getParent() instanceof StrongEmphasis) {
                text.addStyle(new Style().setFontWeight(FontWeight.BOLD));
            }

            p.add(text);
        }
    }

    private void render(TextBase node, NodeRenderingContext context) {
        context.renderChildren(node);
    }

    private void render(SoftLineBreak node, NodeRenderingContext context) {

    }
}
