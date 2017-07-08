package com.bunjlabs.pjdoc.layout.attributes;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface IAttributeContainer {

    public void deleteAttribute(Attribute attr);

    public <T1> T1 getAttribute(Attribute attr);
    
    public <T1> T1 getAttribute(Attribute attr, T1 defaultValue);

    public boolean hasAttribute(Attribute attr);

    public void setAttribute(Attribute attr, Object value);
}
