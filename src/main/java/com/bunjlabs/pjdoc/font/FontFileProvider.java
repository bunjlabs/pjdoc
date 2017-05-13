package com.bunjlabs.pjdoc.font;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FontFileProvider implements FontProvider {

    private final PDDocument pDDocument;
    private final String baseDir;

    public FontFileProvider(PDDocument pDDocument) {
        this(pDDocument, "./");
    }

    public FontFileProvider(PDDocument pDDocument, String baseDir) {
        this.pDDocument = pDDocument;
        this.baseDir = baseDir;
    }

    @Override
    public PDFont provide(String fontName) throws IOException {
        File fontFile = new File(baseDir, fontName);

        return PDType0Font.load(pDDocument, fontFile);
    }

}
