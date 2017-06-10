package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.font.FontFamily;
import com.bunjlabs.pjdoc.font.FontStyle;
import com.bunjlabs.pjdoc.font.FontWeight;
import com.bunjlabs.pjdoc.layout.attributes.HorizontalAlign;
import com.bunjlabs.pjdoc.layout.attributes.Style;
import com.bunjlabs.pjdoc.layout.attributes.TextAlign;
import com.bunjlabs.pjdoc.layout.attributes.VerticalAlign;
import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.RootElement;
import com.bunjlabs.pjdoc.utils.RectangleUtils;
import com.bunjlabs.pjdoc.utils.UnitUtils;
import java.awt.Color;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ParserUtils {

    public static void parseStyles(RootElement element, NamedNodeMap attributes) {
        if (attributes == null || attributes.getLength() <= 0) {
            return;
        }

        Style style = new Style();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);

            if (attribute.getNodeType() != Node.ATTRIBUTE_NODE) {
                continue;
            }

            switch (attribute.getNodeName()) {
                case "background-color": {
                    style.setBackgroundColor(Color.decode(attribute.getNodeValue()));
                    break;
                }
                case "color": {
                    style.setColor(Color.decode(attribute.getNodeValue()));
                    break;
                }
                case "font-family": {
                    style.setFont(new FontFamily(attribute.getNodeValue()));
                    break;
                }
                case "font-size": {
                    style.setFontSize(Float.parseFloat(attribute.getNodeValue()));
                    break;
                }
                case "font-style": {
                    style.setFontStyle(FontStyle.valueOf(attribute.getNodeValue().toUpperCase()));
                    break;
                }
                case "font-weight": {
                    style.setFontWeight(FontWeight.valueOf(attribute.getNodeValue().toUpperCase()));
                    break;
                }
                case "text-align": {
                    style.setTextAlign(TextAlign.valueOf(attribute.getNodeValue().toUpperCase()));
                    break;
                }
                case "horizontal-align": {
                    style.setHorizontalAlign(HorizontalAlign.valueOf(attribute.getNodeValue().toUpperCase()));
                    break;
                }
                case "vertical-align": {
                    style.setVerticalAlign(VerticalAlign.valueOf(attribute.getNodeValue().toUpperCase()));
                    break;
                }
                case "leading": {
                    style.setLeading(Float.parseFloat(attribute.getNodeValue()));
                    break;
                }
                case "width": {
                    style.setWidth(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "height": {
                    style.setHeight(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin": {
                    float[] indents = UnitUtils.unitArrayIndent(attribute.getNodeValue());
                    style.setMargin(indents[0], indents[1], indents[2], indents[3]);
                    break;
                }
                case "margin-top": {
                    style.setMarginTop(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-right": {
                    style.setMarginRight(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-bottom": {
                    style.setMarginBottom(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-left": {
                    style.setMarginLeft(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "adding": {
                    float[] indents = UnitUtils.unitArrayIndent(attribute.getNodeValue());
                    style.setPadding(indents[0], indents[1], indents[2], indents[3]);
                    break;
                }
                case "padding-top": {
                    style.setPaddingTop(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "padding-right": {
                    style.setPaddingRight(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "padding-bottom": {
                    style.setPaddingBottom(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "padding-left": {
                    style.setPaddingLeft(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
            }
        }

        element.addStyle(style);
    }

    public static void parseDocumentAttributes(Document document, NamedNodeMap attributes) {
        if (attributes == null || attributes.getLength() <= 0) {
            return;
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);

            if (attribute.getNodeType() != Node.ATTRIBUTE_NODE) {
                continue;
            }

            switch (attribute.getNodeName()) {
                case "page-size": {
                    document.setPageSize(RectangleUtils.pageSize(attribute.getNodeValue()));
                    break;
                }
                case "margin": {
                    float[] indents = UnitUtils.unitArrayIndent(attribute.getNodeValue());
                    document.setMargin(indents[0], indents[1], indents[2], indents[3]);
                    break;
                }
                case "margin-top": {
                    document.setMarginTop(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-right": {
                    document.setMarginRight(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-bottom": {
                    document.setMarginBottom(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
                case "margin-left": {
                    document.setMarginLeft(UnitUtils.unit(attribute.getNodeValue()));
                    break;
                }
            }
        }
    }

    public static String parseContent(ContentParserContext contentParserContext, Node node) {

        if (node.getNodeType() == Node.TEXT_NODE) {
            return node.getTextContent();
        }

        String content;

        Node contentNode = node.getAttributes().getNamedItem("content");

        if (contentNode != null) {
            content = contentNode.getNodeValue();
        } else {
            content = node.getTextContent();
        }

        if (content.startsWith("@")) {
            content = contentParserContext.getParameter(content.substring(1));
        }

        if (content == null) {
            content = "";
        }

        return content;
    }

}
