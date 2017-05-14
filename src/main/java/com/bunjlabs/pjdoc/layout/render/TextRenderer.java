package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.font.FontFamily;
import com.bunjlabs.pjdoc.font.FontStyle;
import com.bunjlabs.pjdoc.font.FontWeight;
import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Text;
import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TextRenderer extends Renderer<Text> {

    private FontFamily fontFamily;
    private float fontSize;
    private FontStyle fontStyle;
    private FontWeight fontWeight;
    private float leading;
    private Color textColor;
    private String text;

    private PDFont pdfont;
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

        fontFamily = getAttribute(Attribute.FONT_FAMILY);
        fontSize = getAttribute(Attribute.FONT_SIZE, 14f);
        fontStyle = getAttribute(Attribute.FONT_STYLE, FontStyle.NORMAL);
        fontWeight = getAttribute(Attribute.FONT_WEIGHT, FontWeight.REGULAR);
        leading = getAttribute(Attribute.LEADING, 1.5f) * fontSize;
        textColor = getAttribute(Attribute.COLOR, Color.BLACK);

        pdfont = layoutContext.getDocumentRenderer().getFontProvider().provide(fontFamily, fontStyle, fontWeight);

        text = text.replace("\n", "").replace("\r", "");

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

        if ((!text.isEmpty() && placedText.isEmpty()) || boundingBox.getHeight() < leading) {
            TextRenderer rightRenderer = createRightRenderer();

            return new LayoutResult(LayoutResult.NOTHING, occupiedArea, rightRenderer);
        } else if (text.length() > placedText.length()) {
            TextRenderer leftRenderer = createLeftRenderer();

            TextRenderer rightRenderer = createRightRenderer();
            rightRenderer.setText(text.substring(lastIndex + 1));

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
            stream.setFont(pdfont, fontSize);
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
            return fontSize * pdfont.getStringWidth(str) / 1000f;
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
