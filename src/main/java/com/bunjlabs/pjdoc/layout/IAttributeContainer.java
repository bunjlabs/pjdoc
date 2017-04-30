package com.bunjlabs.pjdoc.layout;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface IAttributeContainer {

    public void deleteAttribute(Attribute attr);

    public <T1> T1 getAttribute(Attribute attr);

    public boolean hasAttribute(Attribute attr);

    public void setAttribute(Attribute attr, Object value);
}
