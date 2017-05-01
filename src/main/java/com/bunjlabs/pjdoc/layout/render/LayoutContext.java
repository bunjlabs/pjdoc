package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.utils.RectangleUtils;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutContext {

    private final PDRectangle mediaBox;
    private PDRectangle boundingBox;

    public LayoutContext(PDRectangle mediaBox) {
        this.mediaBox = RectangleUtils.clone(mediaBox);
        this.boundingBox = RectangleUtils.clone(mediaBox);
    }

    public LayoutContext(PDRectangle mediaBox, PDRectangle boundingBox) {
        this.mediaBox = RectangleUtils.clone(mediaBox);
        this.boundingBox = boundingBox;
    }

    public PDRectangle getMediaBox() {
        return mediaBox;
    }

    public PDRectangle getBoundingBox() {
        return boundingBox;
    }

}
