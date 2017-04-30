package com.bunjlabs.pjdoc.layout.elements;

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

    public <T1 extends Element> Paragraph addAll(java.util.List<T1> elements) {
        elements.forEach((e) -> e.parentElement = this);
        childElements.addAll(elements);
        return this;
    }
}
