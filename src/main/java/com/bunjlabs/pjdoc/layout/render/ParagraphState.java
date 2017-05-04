package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.TextAlign;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
class ParagraphState {

    private float lastLineWidth = 0f;
    private final TextAlign textAlign;
    private float startX;
    private float startY;

    public ParagraphState() {
        this.textAlign = TextAlign.LEFT;
    }

    public ParagraphState(TextAlign textAlign) {
        this.textAlign = textAlign;
    }

    public float getLastLineWidth() {
        return lastLineWidth;
    }

    public void setLastLineWidth(float lastLineWidth) {
        this.lastLineWidth = lastLineWidth;
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

}
