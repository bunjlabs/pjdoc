package com.bunjlabs.pjdoc.xml.parser;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TemplateParserContext {

    private final Map<String, List<Element>> contents;
    private final Document document;
    private final String templateId;

    TemplateParserContext(Map<String, List<Element>> contents, Document document, String templateId) {
        this.contents = contents;
        this.document = document;
        this.templateId = templateId;
    }

    public Map<String, List<Element>> getContents() {
        return contents;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Document getDocument() {
        return document;
    }

}
