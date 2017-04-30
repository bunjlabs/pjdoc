package com.bunjlabs.pjdoc.layout.render;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutState {

    private float blockContentStartX;
    private float blockContentStartY;
    private float blockContentWidth;
    private float blockContentHeigth;

    public LayoutState() {
        blockContentWidth = 0;
        blockContentHeigth = 0;
    }

    public LayoutState(LayoutState layoutState) {
        this.blockContentStartX = layoutState.blockContentStartX;
        this.blockContentStartY = layoutState.blockContentStartY;
        this.blockContentWidth = layoutState.blockContentWidth;
        this.blockContentHeigth = layoutState.blockContentHeigth;
    }

    public float getBlockContentWidth() {
        return blockContentWidth;
    }

    public void setBlockContentWidth(float blockContentWidth) {
        this.blockContentWidth = blockContentWidth;
    }

    public void addBlockContentWidth(float blockContentWidth) {
        this.blockContentWidth += blockContentWidth;
    }

    public float getBlockContentHeigth() {
        return blockContentHeigth;
    }

    public void setBlockContentHeigth(float blockContentHeigth) {
        this.blockContentHeigth = blockContentHeigth;
    }

    public void addBlockContentHeigth(float blockContentHeigth) {
        this.blockContentHeigth += blockContentHeigth;
    }

    public float getBlockContentStartX() {
        return blockContentStartX;
    }

    public void setBlockContentStartX(float blockContentStartX) {
        this.blockContentStartX = blockContentStartX;
    }

    public float getBlockContentStartY() {
        return blockContentStartY;
    }

    public void setBlockContentStartY(float blockContentStartY) {
        this.blockContentStartY = blockContentStartY;
    }
}
