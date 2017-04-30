package com.bunjlabs.pjdoc.layout.elements;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Div extends BlockElement<Div> {

    public Div add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }
}
