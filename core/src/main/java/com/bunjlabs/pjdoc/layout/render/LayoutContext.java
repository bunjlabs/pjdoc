package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutContext {

    private final DocumentRenderer documentRenderer;

    private final LayoutArea mediaArea;
    private final Rectangle boundingBox;

    public LayoutContext(DocumentRenderer documentRenderer, LayoutArea mediaArea) {
        this.documentRenderer = documentRenderer;
        this.mediaArea = mediaArea;
        this.boundingBox = mediaArea.getBoundingBox().clone();
    }

    public LayoutContext(DocumentRenderer documentRenderer, LayoutArea mediaArea, Rectangle boundingBox) {
        this.documentRenderer = documentRenderer;
        this.mediaArea = mediaArea;
        this.boundingBox = boundingBox.clone();
    }

    public LayoutArea getMediaArea() {
        return mediaArea;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public LayoutContext extend() {
        return new LayoutContext(documentRenderer, mediaArea, boundingBox);
    }

    public LayoutContext extend(Rectangle boundingBox) {
        return new LayoutContext(documentRenderer, mediaArea, boundingBox);
    }

    public DocumentRenderer getDocumentRenderer() {
        return documentRenderer;
    }
}
