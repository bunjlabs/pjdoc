package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.Attribute;
import com.bunjlabs.pjdoc.layout.elements.*;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class CoreRenderer {

    public Set<ElementRenderingHandler<?>> getElementRenderingHandlers() {
        return new HashSet<>(Arrays.asList(
                new ElementRenderingHandler<Paragraph>(Paragraph.class, (Paragraph e, ElementRenderContext c) -> CoreRenderer.this.render(e, c)),
                new ElementRenderingHandler<Text>(Text.class, (Text e, ElementRenderContext c) -> CoreRenderer.this.render(e, c)),
                new ElementRenderingHandler<Div>(Div.class, (Div e, ElementRenderContext c) -> CoreRenderer.this.render(e, c)),
                new ElementRenderingHandler<Flex>(Flex.class, (Flex e, ElementRenderContext c) -> CoreRenderer.this.render(e, c))
        ));
    }

    private void render(Paragraph e, ElementRenderContext c) throws IOException {
        LayoutState parentLayoutState = c.getLayoutStateStack().peek();

        LayoutState layoutState = new LayoutState();
        layoutState.setBlockContentStartX(parentLayoutState.getBlockContentStartX());
        layoutState.setBlockContentStartY(parentLayoutState.getBlockContentStartY() - parentLayoutState.getBlockContentHeigth());
        layoutState.setBlockContentWidth(parentLayoutState.getBlockContentWidth());
        layoutState.setBlockContentHeigth(0);
        c.getLayoutStateStack().push(layoutState);

        c.renderChildren(e);

        parentLayoutState.addBlockContentHeigth(layoutState.getBlockContentHeigth());

        c.getLayoutStateStack().pop();
    }

    private void render(Text e, ElementRenderContext c) throws IOException {
        PDPageContentStream stream = c.getCurrentPageContentStream();
        LayoutState parentLayoutState = c.getLayoutStateStack().peek();

        PDFont font = e.getAttribute(Attribute.FONT, PDType1Font.HELVETICA);
        float fontSize = e.getAttribute(Attribute.FONT_SIZE, 14f);
        float leading = e.getAttribute(Attribute.LEADING, 1.5f) * fontSize;
        String text = e.getText();

        float width = parentLayoutState.getBlockContentWidth();
        float startX = parentLayoutState.getBlockContentStartX();
        float startY = parentLayoutState.getBlockContentStartY() - parentLayoutState.getBlockContentHeigth();

        int lastSpace = -1;

        List<String> lines = new LinkedList<>();
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0) {
                spaceIndex = text.length();
            }
            String subString = text.substring(0, spaceIndex);

            float size = fontSize * font.getStringWidth(subString) / 1000f;
            if (size > width) {
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

        float textWidth = lines.size() * leading;

        parentLayoutState.addBlockContentHeigth(textWidth);

        stream.beginText();
        stream.setRenderingMode(RenderingMode.FILL);
        stream.setFont(font, fontSize);
        stream.setLeading(leading);
        stream.setNonStrokingColor(e.getAttribute(Attribute.COLOR, Color.BLACK));

        stream.newLineAtOffset(startX, startY - leading);
        for (String line : lines) {
            stream.showText(line);
            stream.newLine();
        }
        stream.endText();
    }

    private void render(Div e, ElementRenderContext c) throws IOException {
        LayoutState parentLayoutState = c.getLayoutStateStack().peek();

        LayoutState layoutState = new LayoutState();
        layoutState.setBlockContentStartX(parentLayoutState.getBlockContentStartX());
        layoutState.setBlockContentStartY(parentLayoutState.getBlockContentStartY() - parentLayoutState.getBlockContentHeigth());
        layoutState.setBlockContentWidth(parentLayoutState.getBlockContentWidth());
        layoutState.setBlockContentHeigth(0);
        c.getLayoutStateStack().push(layoutState);

        c.renderChildren(e);

        parentLayoutState.addBlockContentHeigth(layoutState.getBlockContentHeigth());

        c.getLayoutStateStack().pop();
    }

    private void render(Flex e, ElementRenderContext c) throws IOException {
        LayoutState parentLayoutState = c.getLayoutStateStack().peek();

        LayoutState layoutState = new LayoutState();
        layoutState.setBlockContentStartX(parentLayoutState.getBlockContentStartX());
        layoutState.setBlockContentStartY(parentLayoutState.getBlockContentStartY() - parentLayoutState.getBlockContentHeigth());
        layoutState.setBlockContentWidth(parentLayoutState.getBlockContentWidth());
        layoutState.setBlockContentHeigth(0);
        c.getLayoutStateStack().push(layoutState);

        c.renderChildren(e);

        parentLayoutState.addBlockContentHeigth(layoutState.getBlockContentHeigth());

        c.getLayoutStateStack().pop();
    }
}
