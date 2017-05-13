package com.bunjlabs.pjdoc.font;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface FontProvider {

    public PDFont provide(String fontName) throws IOException;
}
