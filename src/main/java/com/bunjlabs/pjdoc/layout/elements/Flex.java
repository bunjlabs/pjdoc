package com.bunjlabs.pjdoc.layout.elements;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Flex extends BlockElement<Flex> {

    public Flex add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }
}
