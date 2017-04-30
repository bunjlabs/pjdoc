package com.bunjlabs.pjdoc.layout.elements;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Text extends Element<Text> {

    protected String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
