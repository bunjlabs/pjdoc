package com.bunjlabs.pjdoc.layout;

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
    public boolean hasAttribute(Attribute attr) {
        return attributes.containsKey(attr);
    }

    @Override
    public void setAttribute(Attribute attr, Object value) {
        attributes.put(attr, value);
    }

}
