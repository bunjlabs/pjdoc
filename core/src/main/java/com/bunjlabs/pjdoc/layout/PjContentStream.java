package com.bunjlabs.pjdoc.layout;

import com.bunjlabs.pjdoc.layout.attributes.LineStyle;
import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PjContentStream {

    private static final float[] DASH_PATTERN_DOTTED = {1};
    private static final float[] DASH_PATTERN_DASHED = {2, 1};

    private final PDPageContentStream stream;

    public PjContentStream(PDPageContentStream stream) {
        this.stream = stream;
    }

    public void drawLineStroked(Color color, LineStyle lineStyle, float x1, float y1, float x2, float y2, float width) throws IOException {
        if (lineStyle == LineStyle.DOTTED) {
            stream.setLineDashPattern(DASH_PATTERN_DOTTED, 0);
        } else if (lineStyle == LineStyle.DASHED) {
            stream.setLineDashPattern(DASH_PATTERN_DASHED, 0);
        }

        stream.setLineCapStyle(2);
        stream.setLineWidth(width);
        stream.setStrokingColor(color);
        stream.moveTo(x1, y1);
        stream.lineTo(x2, y2);
        stream.stroke();
        stream.restoreGraphicsState();
    }

    public void drawRectFilled(Color color, Rectangle rectangle) throws IOException {
        stream.setStrokingColor(color);
        stream.setNonStrokingColor(color);
        stream.addRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        stream.fill();
        stream.restoreGraphicsState();
    }

    public PDPageContentStream getPDPageContentStream() {
        return stream;
    }

}
