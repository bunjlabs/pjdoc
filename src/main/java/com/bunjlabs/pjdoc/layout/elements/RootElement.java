package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.Attribute;
import com.bunjlabs.pjdoc.layout.ElementAttributeContainer;
import com.bunjlabs.pjdoc.layout.IAttributeContainer;
import com.bunjlabs.pjdoc.layout.Style;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RootElement<T extends IAttributeContainer> extends ElementAttributeContainer<T> {

    protected List<Element> childElements = new ArrayList<>();
    protected Set<Style> styles;

    public T addStyle(Style style) {
        if (styles == null) {
            styles = new LinkedHashSet<>();
        }

        styles.add(style);

        return (T) this;
    }

    public List<Element> getChildren() {
        return childElements;
    }

    public boolean isEmpty() {
        return this.childElements.isEmpty();
    }

    @Override
    public boolean hasAttribute(Attribute attribute) {
        boolean hasAttribute = super.hasAttribute(attribute);
        if (!hasAttribute) {
            hasAttribute = hasStyleAttribute(attribute);
        }
        return hasAttribute;
    }

    public boolean hasStyleAttribute(Attribute attribute) {
        if (styles != null && styles.size() > 0) {
            for (Style style : styles) {
                if (style.hasAttribute(attribute)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <T1> T1 getAttribute(Attribute attribute) {
        T1 result = super.<T1>getAttribute(attribute);
        if (result == null && !super.hasAttribute(attribute)) {
            result = getStyleAttribute(attribute);
        }

        return result;
    }

    public <T1> T1 getStyleAttribute(Attribute attribute) {
        T1 result = null;
        if (styles != null && styles.size() > 0) {
            for (Style style : styles) {
                T1 foundInStyle = style.<T1>getAttribute(attribute);
                if (foundInStyle != null || style.hasAttribute(attribute)) {
                    result = foundInStyle;
                }
            }
        }

        return result;
    }

    public Class<? extends RootElement> getType() {
        return this.getClass();
    }
}
