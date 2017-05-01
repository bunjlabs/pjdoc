package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.Renderer;
import com.bunjlabs.pjdoc.layout.render.TextRenderer;

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

    @Override
    public Renderer createRenderer() {
        return new TextRenderer(this);
    }

}
