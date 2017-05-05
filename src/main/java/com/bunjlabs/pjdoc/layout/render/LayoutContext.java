package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutContext {

    private final LayoutArea mediaArea;
    private Rectangle boundingBox;

    public LayoutContext(LayoutArea mediaArea) {
        this.mediaArea = mediaArea;
        this.boundingBox = mediaArea.getBoundingBox().clone();
    }

    public LayoutContext(LayoutArea mediaArea, Rectangle mediaBox) {
        this.mediaArea = mediaArea;
        this.boundingBox = mediaBox.clone();
    }

    public LayoutArea getMediaArea() {
        return mediaArea;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
