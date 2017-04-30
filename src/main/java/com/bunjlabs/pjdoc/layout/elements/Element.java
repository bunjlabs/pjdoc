package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.Attribute;
import com.bunjlabs.pjdoc.layout.Style;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class Element<T extends Element> extends RootElement<T> {

    protected RootElement parentElement;

    public RootElement getParent() {
        return this.parentElement;
    }

    @Override
    public boolean hasAttribute(Attribute attribute) {
        boolean hasAttribute = super.hasAttribute(attribute);

        if (hasAttribute) {
            return hasAttribute;
        }

        if (parentElement != null && attribute.isInherited()) {
            hasAttribute = parentElement.hasStyleAttribute(attribute);
        }

        return hasAttribute;
    }

    @Override
    public boolean hasStyleAttribute(Attribute attribute) {
        boolean hasAttribute = super.hasStyleAttribute(attribute);

        if (hasAttribute) {
            return hasAttribute;
        }

        if (parentElement != null && attribute.isInherited()) {
            hasAttribute = parentElement.hasStyleAttribute(attribute);
        }

        return hasAttribute;
    }

    @Override
    public <T1> T1 getAttribute(Attribute attribute) {
        T1 result = super.<T1>getAttribute(attribute);

        if (result != null) {
            return result;
        }

        if (parentElement != null && attribute.isInherited()) {
            result = (T1) parentElement.<T1>getStyleAttribute(attribute);
        }

        return result;
    }

    public <T1> T1 getAttribute(Attribute attr, T1 defaultValue) {
        T1 value = this.<T1>getAttribute(attr);
        return value == null ? defaultValue : value;
    }

    @Override
    public <T1> T1 getStyleAttribute(Attribute attribute) {
        T1 result = super.<T1>getStyleAttribute(attribute);

        if (result != null) {
            return result;
        }

        if (parentElement != null && attribute.isInherited()) {
            result = (T1) parentElement.<T1>getStyleAttribute(attribute);
        }

        return result;
    }

    public <T1> T1 getStyleAttribute(Attribute attr, T1 defaultValue) {
        T1 value = this.<T1>getStyleAttribute(attr);
        return value == null ? defaultValue : value;
    }
}
