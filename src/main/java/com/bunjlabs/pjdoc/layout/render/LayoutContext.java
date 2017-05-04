package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.utils.RectangleUtils;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutContext {

    private final PDRectangle mediaBox;
    private final PDRectangle boundingBox;
    private final ParagraphState paragraphState;

    public LayoutContext(PDRectangle mediaBox) {
        this.mediaBox = RectangleUtils.clone(mediaBox);
        this.boundingBox = RectangleUtils.clone(mediaBox);
        this.paragraphState = new ParagraphState();
    }

    public LayoutContext(PDRectangle mediaBox, PDRectangle boundingBox) {
        this.mediaBox = RectangleUtils.clone(mediaBox);
        this.boundingBox = boundingBox;
        this.paragraphState = new ParagraphState();
    }

    public LayoutContext(PDRectangle mediaBox, PDRectangle boundingBox, ParagraphState paragraphState) {
        this.mediaBox = RectangleUtils.clone(mediaBox);
        this.boundingBox = boundingBox;
        this.paragraphState = paragraphState;
    }

    public PDRectangle getMediaBox() {
        return mediaBox;
    }

    public PDRectangle getBoundingBox() {
        return boundingBox;
    }

    public ParagraphState getParagraphState() {
        return paragraphState;
    }

}
