package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Element;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class ElementRenderAdapter<E extends Element, A extends ElementRenderer<E>> {

    protected final Class<? extends E> myClass;
    protected final A renderer;

    public ElementRenderAdapter(Class<? extends E> aClass, A renderer) {
        this.myClass = aClass;
        this.renderer = renderer;
    }

    public Class<? extends E> getNodeType() {
        return myClass;
    }

    public A getElementRenderer() {
        return renderer;
    }
}
