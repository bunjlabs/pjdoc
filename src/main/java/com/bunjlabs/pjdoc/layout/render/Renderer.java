package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.IAttributeContainer;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class Renderer<E extends Element> implements IAttributeContainer {

    protected final List<Renderer> childRenderers = new LinkedList<>();
    protected final E modelElement;

    protected Renderer(E modelElement) {
        this.modelElement = modelElement;
    }

    public void add(Renderer renderer) {
        childRenderers.add(renderer);
    }

    public abstract LayoutResult render(RenderContext renderContext, LayoutContext layoutContext);

    @Override
    public void deleteAttribute(Attribute attr) {
        modelElement.deleteAttribute(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr) {
        return (T1) modelElement.<T1>getAttribute(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr, T1 defaultValue) {
        return (T1) modelElement.<T1>getAttribute(attr, defaultValue);
    }

    @Override
    public boolean hasAttribute(Attribute attr) {
        return modelElement.hasAttribute(attr);
    }

    @Override
    public void setAttribute(Attribute attr, Object value) {
        modelElement.setAttribute(attr, value);
    }
}
