package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.ParagraphRenderer;
import com.bunjlabs.pjdoc.layout.render.Renderer;
import java.util.Collection;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Paragraph extends BlockElement<Paragraph> {

    public Paragraph() {
    }

    public Paragraph(String text) {
        this(new Text(text));
    }

    public Paragraph(Text text) {
        add(text);
    }

    public Paragraph add(String text) {
        Text element = new Text(text);
        element.parentElement = this;
        return add(element);
    }

    public Paragraph add(Element element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public Paragraph addAll(Collection<Element> elements) {
        childElements.addAll(elements);
        elements.forEach((e) -> e.parentElement = this);
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new ParagraphRenderer(this);
    }
}
