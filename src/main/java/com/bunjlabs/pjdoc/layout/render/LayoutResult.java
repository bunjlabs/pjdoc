package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutResult {

    public static final int NOTHING = 0;
    public static final int FULL = 1;
    public static final int PARTIAL = 2;
    public static final int PARTIAL_OVERFLOW = 3;

    private final int type;
    private final LayoutArea occupiedArea;
    private final Renderer[] splitRenderers;

    public LayoutResult(int type, LayoutArea occupiedArea) {
        this.type = type;
        this.occupiedArea = occupiedArea;
        this.splitRenderers = new Renderer[0];
    }

    public LayoutResult(int type, LayoutArea occupiedArea, Renderer... renderers) {
        this.type = type;
        this.occupiedArea = occupiedArea;

        this.splitRenderers = renderers;
    }

    public int getType() {
        return type;
    }

    public LayoutArea getOccupiedArea() {
        return occupiedArea;
    }

    public Renderer[] getSplitRenderers() {
        return splitRenderers;
    }

}
