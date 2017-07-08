package com.bunjlabs.pjdoc.font;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface FontProvider {

    public PDFont provide(FontFamily fontFamily, FontStyle fontStyle, FontWeight fontWeight);
}
