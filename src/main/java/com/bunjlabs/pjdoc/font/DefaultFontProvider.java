package com.bunjlabs.pjdoc.font;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DefaultFontProvider implements FontProvider {

    private final Map<FontDescriptor, PDFont> registeredFonts = new HashMap<>();

    private final PDDocument pDDocument;
    private final String baseDir;

    public DefaultFontProvider(PDDocument pDDocument) {
        this(pDDocument, "./");
    }

    public DefaultFontProvider(PDDocument pDDocument, String baseDir) {
        this.pDDocument = pDDocument;
        this.baseDir = baseDir;
    }

    public void register(FontDescriptor descriptor, PDFont pdfont) {
        registeredFonts.put(descriptor, pdfont);
    }

    @Override
    public PDFont provide(FontFamily fontFamily, FontStyle fontStyle, FontWeight fontWeight) {
        FontDescriptor fontDescriptor = new FontDescriptor(fontFamily, fontStyle, fontWeight);

        PDFont pdfont = registeredFonts.get(fontDescriptor);

        if (pdfont == null) {
            return PDType1Font.HELVETICA;
        } else {
            return pdfont;
        }
    }

}
