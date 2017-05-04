package com.bunjlabs.pjdoc.xml;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class XmlParseException extends Exception {

    public XmlParseException() {
    }

    public XmlParseException(String msg) {
        super(msg);
    }

    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParseException(Throwable cause) {
        super(cause);
    }

}
