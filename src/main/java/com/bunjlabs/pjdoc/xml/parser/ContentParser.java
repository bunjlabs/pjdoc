package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.layout.elements.Flex;
import com.bunjlabs.pjdoc.layout.elements.Image;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.layout.elements.Text;
import com.bunjlabs.pjdoc.layout.elements.barcode.Barcode;
import com.bunjlabs.pjdoc.layout.elements.barcode.Code128;
import com.bunjlabs.pjdoc.layout.elements.barcode.Code39;
import com.bunjlabs.pjdoc.layout.elements.barcode.EAN13;
import com.bunjlabs.pjdoc.layout.elements.barcode.PDF417;
import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.w3c.dom.Node;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ContentParser {

    private Map<String, ContentParserAdapter> tagParserAdapters;

    public ContentParser() {
        this.tagParserAdapters = getParserAdapters();
    }

    private Map<String, ContentParserAdapter> getParserAdapters() {
        HashMap<String, ContentParserAdapter> map = new HashMap<>();

        map.put("div", (c, n) -> ContentParser.this.parseDiv(c, n));
        map.put("flex", (c, n) -> ContentParser.this.parseFlex(c, n));
        map.put("p", (c, n) -> ContentParser.this.parseParagraph(c, n));
        map.put("t", (c, n) -> ContentParser.this.parseText(c, n));
        map.put("", (c, n) -> ContentParser.this.parseText(c, n));
        map.put("image", (c, n) -> ContentParser.this.parseImage(c, n));
        map.put("barcode", (c, n) -> ContentParser.this.parseBarcode(c, n));
        map.put("place-content", (c, n) -> ContentParser.this.parsePlaceContent(c, n));

        return map;
    }

    public List<Element> parseContent(ContentParserContext context, Node node) throws XmlParseException {
        return context.parseChildren(tagParserAdapters, node);
    }

    private List<Element> parseDiv(ContentParserContext context, Node node) throws XmlParseException {
        Div d = new Div();

        ParserUtils.parseStyles(d, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof BlockElement) {
                d.add((BlockElement) e);
            }
        });

        return Arrays.asList(d);
    }

    private List<Element> parseFlex(ContentParserContext context, Node node) throws XmlParseException {
        Flex f = new Flex();

        ParserUtils.parseStyles(f, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof BlockElement) {
                f.add((BlockElement) e);
            }
        });

        return Arrays.asList(f);
    }

    private List<Element> parseParagraph(ContentParserContext context, Node node) throws XmlParseException {
        Paragraph p = new Paragraph();

        ParserUtils.parseStyles(p, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof Text) {
                p.add((Text) e);
            }
        });

        return Arrays.asList(p);
    }

    private List<Element> parseText(ContentParserContext context, Node node) throws XmlParseException {
        Text t = new Text(node.getTextContent());

        ParserUtils.parseStyles(t, node.getAttributes());

        return Arrays.asList(t);
    }

    private List<Element> parseImage(ContentParserContext context, Node node) throws XmlParseException {
        Node srcNode = node.getAttributes().getNamedItem("src");

        if (srcNode == null) {
            return Collections.EMPTY_LIST;
        }

        Image image;

        try {
            image = new Image(ImageIO.read(new File(srcNode.getNodeValue())));
        } catch (IOException ex) {
            throw new XmlParseException("Unable to insert image", ex);
        }

        return Arrays.asList(image);
    }

    private List<Element> parseBarcode(ContentParserContext context, Node node) throws XmlParseException {
        Barcode barcode = null;

        Node typeNode = node.getAttributes().getNamedItem("type");
        Node valueNode = node.getAttributes().getNamedItem("value");

        if (typeNode != null && valueNode != null) {
            String type = typeNode.getNodeValue();
            String value = valueNode.getNodeValue();

            try {
                switch (type.toLowerCase()) {
                    case "code128": {
                        barcode = new Code128(value);
                        break;
                    }
                    case "code39": {
                        barcode = new Code39(value);
                        break;
                    }
                    case "pdf417": {
                        barcode = new PDF417(value);
                        break;
                    }
                    default:
                    case "ean13": {
                        barcode = new EAN13(value);
                        break;
                    }
                }
            } catch (IOException ex) {
                throw new XmlParseException("Unable to create barcode instance", ex);
            }
        }

        ParserUtils.parseStyles(barcode, node.getAttributes());

        return barcode == null ? Collections.EMPTY_LIST : Arrays.asList(barcode);
    }

    private List<Element> parsePlaceContent(ContentParserContext context, Node node) throws XmlParseException {
        Node contentNode = node.getAttributes().getNamedItem("content");

        if (contentNode != null) {
            String contentId = contentNode.getNodeValue();
            List<Element> elements = context.getContents().get(contentId);

            if (elements != null) {
                return elements;
            }
        }

        return Collections.EMPTY_LIST;
    }
}
