package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Div;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DivRenderer extends BlockRenderer<Div> {

    public DivRenderer(Div modelElement) {
        super(modelElement);
    }

    @Override
    public Renderer getNextRenderer() {
        return new DivRenderer(modelElement);
    }

}
