package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Element;
import com.bunjlabs.pjdoc.layout.elements.Flex;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.xml.XmlParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        String text = node.getTextContent();

        p.add(text != null ? text : "");

        return Arrays.asList(p);
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
