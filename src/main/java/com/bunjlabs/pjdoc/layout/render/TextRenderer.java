package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.Font;
import com.bunjlabs.pjdoc.layout.elements.Text;
import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TextRenderer extends Renderer<Text> {

    private PDFont font;
    private float fontSize;
    private float leading;
    private Color textColor;
    private String text;
    private String placedText;

    public TextRenderer(Text modelElement) {
        this(modelElement, modelElement.getText());
    }

    public TextRenderer(Text modelElement, String text) {
        super(modelElement);
        this.text = text;
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        Font pfont = getAttribute(Attribute.FONT);
        font = PDType1Font.HELVETICA;
        fontSize = getAttribute(Attribute.FONT_SIZE, 14f);
        leading = getAttribute(Attribute.LEADING, 1.5f) * fontSize;
        textColor = getAttribute(Attribute.COLOR, Color.BLACK);

        text = text.trim().replace("\n", "").replace("\r", "");

        float width = boundingBox.getWidth();

        int lastIndex = 0;
        while (true) {
            int spaceIndex = text.indexOf(' ', lastIndex + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }

            String subString = text.substring(0, spaceIndex);

            if (calcStringWidth(subString) > width) {
                placedText = text.substring(0, lastIndex);
                break;
            } else if (spaceIndex == text.length()) {
                placedText = text;
                break;
            } else {
                lastIndex = spaceIndex;
            }
        }

        float placedTextWidth = calcStringWidth(placedText);

        occupiedArea = new LayoutArea(
                layoutContext.getMediaArea().getPageNumber(),
                new Rectangle(boundingBox.getLeft(), boundingBox.getTop() - leading, placedTextWidth, leading)
        );

        if (text.length() > placedText.length()) {
            TextRenderer leftRenderer = createLeftRenderer();

            TextRenderer rightRenderer = createRightRenderer();
            rightRenderer.setText(text.substring(lastIndex));

            return new LayoutResult(LayoutResult.PARTIAL_OVERFLOW, occupiedArea, leftRenderer, rightRenderer);
        } else {
            return new LayoutResult(LayoutResult.FULL, occupiedArea);
        }
    }

    @Override
    public void render(RenderContext renderContext) {
        PDPageContentStream stream = renderContext.getPageContentStream(occupiedArea.getPageNumber());

        float startX = occupiedArea.getBoundingBox().getLeft();
        float startY = occupiedArea.getBoundingBox().getBottom();

        try {
            stream.beginText();
            stream.setRenderingMode(RenderingMode.FILL);
            stream.setFont(font, fontSize);
            stream.setNonStrokingColor(textColor);
            stream.newLineAtOffset(startX, startY);

            stream.showText(placedText);

            stream.endText();
            stream.restoreGraphicsState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    private float calcStringWidth(String str) {
        try {
            return fontSize * font.getStringWidth(str) / 1000f;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0f;
    }

    protected TextRenderer createLeftRenderer() {
        TextRenderer renderer = (TextRenderer) getNextRenderer();
        renderer.occupiedArea = occupiedArea;
        renderer.modelElement = modelElement;
        renderer.placedText = placedText;
        return renderer;
    }

    protected TextRenderer createRightRenderer() {
        TextRenderer renderer = (TextRenderer) getNextRenderer();
        renderer.modelElement = modelElement;
        return renderer;
    }

    @Override
    public Renderer getNextRenderer() {
        return new TextRenderer(modelElement);
    }

}
