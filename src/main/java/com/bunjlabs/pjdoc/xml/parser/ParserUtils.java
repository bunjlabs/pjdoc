package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.font.Font;
import com.bunjlabs.pjdoc.layout.attributes.Style;
import com.bunjlabs.pjdoc.layout.attributes.TextAlign;
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
                case "font": {
                    style.setFont(new Font(attribute.getNodeValue()));
                    break;
                }
                case "font-size": {
                    style.setFontSize(Float.parseFloat(attribute.getNodeValue()));
                    break;
                }
                case "text-align": {
                    style.setTextAlign(TextAlign.valueOf(attribute.getNodeValue().toUpperCase()));
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
            }
        }
    }

}
