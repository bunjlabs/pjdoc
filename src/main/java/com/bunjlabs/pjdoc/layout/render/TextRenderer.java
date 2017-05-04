package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Text;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TextRenderer extends Renderer<Text> {

    private final List<String> lines = new ArrayList<>();

    private int drawnLines = 0;

    public TextRenderer(Text modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult render(RenderContext renderContext, LayoutContext layoutContext) {
        PDRectangle boundingBox = layoutContext.getBoundingBox();
        PDPageContentStream stream = renderContext.getPageContentStream();

        PDFont font = getAttribute(Attribute.FONT, PDType1Font.HELVETICA);
        float fontSize = getAttribute(Attribute.FONT_SIZE, 14f);
        float leading = getAttribute(Attribute.LEADING, 1.5f) * fontSize;
        Color textColor = getAttribute(Attribute.COLOR, Color.BLACK);
        String text = modelElement.getText();

        text = text.trim().replace("\n", "").replace("\r", "");

        float width = boundingBox.getWidth();
        float startX = boundingBox.getLowerLeftX();
        float startY = boundingBox.getUpperRightY();

        float firstLineShift = 0;

        if (!text.isEmpty() && lines.isEmpty()) {
            lines.addAll(splitText(font, fontSize, width, text));
        }

        int linesToDraw = lines.size() - drawnLines;

        float textHeigth = linesToDraw * leading;

        boolean isPartial = false;
        if (textHeigth > boundingBox.getHeight()) {
            linesToDraw = (int) (boundingBox.getHeight() / leading);
            isPartial = true;
        }

        textHeigth = linesToDraw * leading;

        try {
            stream.beginText();
            stream.setRenderingMode(RenderingMode.FILL);
            stream.setFont(font, fontSize);
            stream.setLeading(leading);
            stream.setNonStrokingColor(textColor);
            stream.newLineAtOffset(startX, startY - leading);

            for (int i = drawnLines; i < lines.size() && i < drawnLines + linesToDraw; i++) {
                stream.showText(lines.get(i));
                stream.newLine();
            }

            stream.endText();
            stream.restoreGraphicsState();
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawnLines += linesToDraw;

        boundingBox.setUpperRightY(boundingBox.getUpperRightY() - textHeigth);

        if (isPartial) {
            return new LayoutResult(LayoutResult.PARTIAL);
        } else {
            return new LayoutResult(LayoutResult.FULL);
        }
    }

    private static List<String> splitText(PDFont font, float fontSize, float maxWidth, String text) {
        List<String> lines = new LinkedList<>();

        int lastSpace = -1;
        float lastLineWidth = 0;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }
            String subString = text.substring(0, spaceIndex);

            try {
                lastLineWidth = fontSize * font.getStringWidth(subString) / 1000f;
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (lastLineWidth > maxWidth) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();

                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }

        return lines;
    }

}
