package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class DocumentRenderer implements ElementRenderContext {

    private final LinkedList<PDPage> pages = new LinkedList<>();
    private final LinkedList<PDPageContentStream> pagesContentStream = new LinkedList<>();
    private final LayoutStateStack layoutStateStack = new LayoutStateStack();
    private final HashMap<Class<?>, ElementRenderingHandler> renderers;

    private final PDDocument pDDocument;
    private final Document document;

    public DocumentRenderer(PDDocument pDDocument, Document document) throws IOException {
        this.pDDocument = pDDocument;
        this.document = document;

        this.renderers = new HashMap<Class<?>, ElementRenderingHandler>(32);

        CoreRenderer coreRenderer = new CoreRenderer();
        for (ElementRenderingHandler renderingHandler : coreRenderer.getElementRenderingHandlers()) {
            this.renderers.put(renderingHandler.getNodeType(), renderingHandler);
        }

        PDRectangle effectiveArea = document.getEffectiveArea();

        LayoutState layoutState = new LayoutState();
        layoutState.setBlockContentStartX(effectiveArea.getLowerLeftX());
        layoutState.setBlockContentStartY(effectiveArea.getUpperRightY());
        layoutState.setBlockContentWidth(effectiveArea.getWidth());
        layoutState.setBlockContentHeigth(0);

        layoutStateStack.push(layoutState);

        this.addPage();
    }

    @Override
    public void addPage() throws IOException {
        PDPage page = new PDPage(document.getPageSize());

        PDPageContentStream pageContentStream = new PDPageContentStream(pDDocument, page, PDPageContentStream.AppendMode.APPEND, true);

        pages.add(page);
        pagesContentStream.add(pageContentStream);
    }

    public void renderDocument() throws IOException {
        List<Element> childs = document.getChildren();
        for (Element child : childs) {
            render(child);
        }

        for (PDPageContentStream contentStream : pagesContentStream) {
            contentStream.close();
        }

        for (PDPage page : pages) {
            pDDocument.addPage(page);
        }

    }

    @Override
    public void render(Element element) throws IOException {
        ElementRenderingHandler renderingHandler = renderers.get(element.getClass());

        if (renderingHandler == null) {
            throw new UnsupportedOperationException("Unsupported element type: " + element.getClass().getTypeName());
        }

        renderingHandler.render(element, this);
    }

    @Override
    public void renderChildren(Element parent) throws IOException {
        List<Element> childs = ((Element) parent).getChildren();
        for (Element child : childs) {
            render(child);
        }
    }

    @Override
    public PDPageContentStream getCurrentPageContentStream() {
        return pagesContentStream.isEmpty() ? null : pagesContentStream.getLast();
    }

    @Override
    public PDPage getCurrentPage() {
        return pages.isEmpty() ? null : pages.getLast();
    }

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public LayoutStateStack getLayoutStateStack() {
        return layoutStateStack;
    }

}
