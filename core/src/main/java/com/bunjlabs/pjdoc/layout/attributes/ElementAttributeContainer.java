package com.bunjlabs.pjdoc.layout.attributes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class ElementAttributeContainer<T extends IAttributeContainer> implements IAttributeContainer {

    protected final Map<Attribute, Object> attributes = new HashMap<>();

    @Override
    public void deleteAttribute(Attribute attr) {
        attributes.remove(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr) {
        return (T1) attributes.get(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr, T1 defaultValue) {
        T1 value = this.<T1>getAttribute(attr);
        return value == null ? defaultValue : value;
    }

    @Override
    public boolean hasAttribute(Attribute attr) {
        return attributes.containsKey(attr);
    }

    @Override
    public void setAttribute(Attribute attr, Object value) {
        attributes.put(attr, value);
    }

}
