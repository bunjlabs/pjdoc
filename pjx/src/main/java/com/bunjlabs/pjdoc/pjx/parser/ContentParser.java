package com.bunjlabs.pjdoc.pjx.parser;

import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.layout.elements.Flex;
import com.bunjlabs.pjdoc.layout.elements.Image;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.layout.elements.Table;
import com.bunjlabs.pjdoc.layout.elements.TableData;
import com.bunjlabs.pjdoc.layout.elements.TableRow;
import com.bunjlabs.pjdoc.layout.elements.Text;
import com.bunjlabs.pjdoc.layout.elements.barcode.Barcode;
import com.bunjlabs.pjdoc.layout.elements.barcode.Code128;
import com.bunjlabs.pjdoc.layout.elements.barcode.Code39;
import com.bunjlabs.pjdoc.layout.elements.barcode.EAN13;
import com.bunjlabs.pjdoc.layout.elements.barcode.PDF417;
import com.bunjlabs.pjdoc.md.MarkdownWorker;
import com.bunjlabs.pjdoc.pjx.XmlParseException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.tools.ant.util.ReaderInputStream;
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
        map.put("table", (c, n) -> ContentParser.this.parseTable(c, n));
        map.put("tr", (c, n) -> ContentParser.this.parseTableRow(c, n));
        map.put("td", (c, n) -> ContentParser.this.parseTableData(c, n));
        map.put("", (c, n) -> ContentParser.this.parseRawText(c, n));
        map.put("image", (c, n) -> ContentParser.this.parseImage(c, n));
        map.put("barcode", (c, n) -> ContentParser.this.parseBarcode(c, n));
        map.put("place-content", (c, n) -> ContentParser.this.parsePlaceContent(c, n));
        map.put("worker-markdown", (c, n) -> ContentParser.this.parseWorkerMarkdown(c, n));

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
        String content = ParserUtils.parseContent(context, node);

        Text t = new Text(content);

        ParserUtils.parseStyles(t, node.getAttributes());

        return Arrays.asList(t);
    }

    private List<Element> parseRawText(ContentParserContext context, Node node) throws XmlParseException {
        String content = node.getTextContent().trim();

        if (content.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        content = content.replaceAll("\\n|\\r", "");

        Text t = new Text(content);

        return Arrays.asList(t);
    }

    private List<Element> parseTable(ContentParserContext context, Node node) throws XmlParseException {
        Table t = new Table();

        ParserUtils.parseStyles(t, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof TableRow) {
                t.add((TableRow) e);
            }
        });

        return Arrays.asList(t);
    }

    private List<Element> parseTableRow(ContentParserContext context, Node node) throws XmlParseException {
        TableRow tr = new TableRow();

        ParserUtils.parseStyles(tr, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof TableData) {
                tr.add((TableData) e);
            }
        });

        return Arrays.asList(tr);
    }

    private List<Element> parseTableData(ContentParserContext context, Node node) throws XmlParseException {
        TableData td = new TableData();

        ParserUtils.parseStyles(td, node.getAttributes());

        List<Element> elements = context.parseChildren(tagParserAdapters, node);

        elements.forEach((e) -> {
            if (e instanceof BlockElement) {
                td.add((BlockElement) e);
            }
        });

        return Arrays.asList(td);
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

        String content = ParserUtils.parseContent(context, node);

        Node typeNode = node.getAttributes().getNamedItem("type");

        if (typeNode != null) {
            String type = typeNode.getNodeValue();
            String value = content;

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
        Node contentNameNode = node.getAttributes().getNamedItem("name");

        if (contentNameNode != null) {
            String contentId = contentNameNode.getNodeValue();
            List<Element> elements = context.getContents().get(contentId);

            if (elements != null) {
                return elements;
            }
        }

        return Collections.EMPTY_LIST;
    }

    private List<Element> parseWorkerMarkdown(ContentParserContext context, Node node) throws XmlParseException {
        String content = ParserUtils.parseContent(context, node);

        try {
            MarkdownWorker markdownWorker = new MarkdownWorker();

            Document workDocument = markdownWorker.work(new ReaderInputStream(new StringReader(content)));

            return workDocument.getChildren();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }
}
