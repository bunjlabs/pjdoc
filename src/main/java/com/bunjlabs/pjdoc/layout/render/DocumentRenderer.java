package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DocumentRenderer {

    private final LinkedList<PDPage> pages = new LinkedList<>();
    private final LinkedList<PDPageContentStream> pagesContentStream = new LinkedList<>();

    private final List<Renderer> childRenderers = new LinkedList<>();

    private final PDDocument pDDocument;
    private final Document document;

    private LayoutArea currentPageArea;

    public DocumentRenderer(PDDocument pDDocument, Document document) {
        this.pDDocument = pDDocument;
        this.document = document;

        this.currentPageArea = new LayoutArea(0, new Rectangle(document.getEffectiveArea()));

        this.document.getChildren().forEach((e) -> addChild(RendererFactory.createRendererSubtree(e)));
    }

    private void addChild(Renderer renderer) {
        childRenderers.add(renderer);

        LayoutContext layoutContext = new LayoutContext(currentPageArea.clone());

        List<Renderer> resultRenderers = new LinkedList<>();

        LayoutResult layoutResult;
        while ((layoutResult = renderer.layout(layoutContext)).getType() != LayoutResult.FULL) {
            if (layoutResult.getType() == LayoutResult.PARTIAL) {

                resultRenderers.add(layoutResult.getSplitRenderers()[0]);

                updateCurrentArea();

                layoutContext = new LayoutContext(currentPageArea.clone());
                renderer = layoutResult.getSplitRenderers()[1];
            }
        }

        childRenderers.addAll(resultRenderers);
    }

    public void render() throws IOException {
        RenderContext renderContext = new RenderContext(pDDocument, pagesContentStream);

        addPages();

        for (int childInx = 0; childInx < childRenderers.size(); childInx++) {
            Renderer renderer = childRenderers.get(childInx);

            renderer.render(renderContext);
        }

        for (PDPageContentStream contentStream : pagesContentStream) {
            contentStream.close();
        }

        for (PDPage page : pages) {
            pDDocument.addPage(page);
        }
    }

    private void updateCurrentArea() {
        currentPageArea = new LayoutArea(currentPageArea.getPageNumber() + 1, new Rectangle(document.getEffectiveArea()));
    }

    private void addPages() throws IOException {
        for (int i = 0; i <= currentPageArea.getPageNumber(); i++) {
            PDPage page = new PDPage(document.getPageSize());
            PDPageContentStream pageContentStream = new PDPageContentStream(pDDocument, page, PDPageContentStream.AppendMode.APPEND, true);

            pages.add(page);
            pagesContentStream.add(pageContentStream);
        }
    }
}
