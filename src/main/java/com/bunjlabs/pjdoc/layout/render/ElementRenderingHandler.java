package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Element;
import java.io.IOException;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ElementRenderingHandler<E extends Element> extends ElementRenderAdapter<E, ElementRenderer<E>> {

    public ElementRenderingHandler(Class<? extends E> aClass, ElementRenderer<E> renderer) {
        super(aClass, renderer);
    }

    public void render(Element element, ElementRenderContext context) throws IOException {
        renderer.render((E) element, context);
    }

}
