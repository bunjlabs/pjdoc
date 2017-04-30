package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface ElementRenderContext {

    public void addPage() throws IOException;

    public void render(Element element) throws IOException;

    public void renderChildren(Element parent) throws IOException;

    public PDPageContentStream getCurrentPageContentStream();

    public PDPage getCurrentPage();

    public Document getDocument();

    public LayoutStateStack getLayoutStateStack();

}
