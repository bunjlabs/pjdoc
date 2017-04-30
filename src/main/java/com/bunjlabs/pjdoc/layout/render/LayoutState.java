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
    private float blockContentHeight;
    private float blockWidth;
    private float blockHeight;

    public LayoutState() {
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

    public float getBlockContentHeight() {
        return blockContentHeight;
    }

    public void setBlockContentHeight(float blockContentHeight) {
        this.blockContentHeight = blockContentHeight;
    }

    public void addBlockContentHeight(float blockContentHeight) {
        this.blockContentHeight += blockContentHeight;
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

    public float getBlockWidth() {
        return blockWidth;
    }

    public void addBlockWidth(float blockWidth) {
        this.blockWidth += blockWidth;
    }

    public void setBlockWidth(float blockWidth) {
        this.blockWidth = blockWidth;
    }

    public float getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(float blockHeight) {
        this.blockHeight = blockHeight;
    }

    public void addBlockHeight(float blockHeight) {
        this.blockHeight += blockHeight;
    }
}
