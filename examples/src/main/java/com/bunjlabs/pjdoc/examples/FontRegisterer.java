package com.bunjlabs.pjdoc.examples;

import com.bunjlabs.pjdoc.font.DefaultFontProvider;
import com.bunjlabs.pjdoc.font.FontDescriptor;
import com.bunjlabs.pjdoc.font.FontFamily;
import com.bunjlabs.pjdoc.font.FontStyle;
import com.bunjlabs.pjdoc.font.FontWeight;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FontRegisterer {

    private static final String BASE_FONT_DIR = "./fonts/";

    public static void registerFontRoboto(PDDocument pDDocument, DefaultFontProvider fontProvider) {
        try {
            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.NORMAL, FontWeight.REGULAR),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Regular.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.ITALIC, FontWeight.REGULAR),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Italic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.NORMAL, FontWeight.BOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Bold.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.ITALIC, FontWeight.BOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-BoldItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.NORMAL, FontWeight.BLACK),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Black.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.ITALIC, FontWeight.BLACK),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-BlackItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.NORMAL, FontWeight.LIGHT),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Light.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.ITALIC, FontWeight.LIGHT),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-LightItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.NORMAL, FontWeight.THIN),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-Thin.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Roboto"),
                            FontStyle.ITALIC, FontWeight.THIN),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "roboto/Roboto-ThinItalic.ttf"))
            );

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void registerFontLato(PDDocument pDDocument, DefaultFontProvider fontProvider) {
        try {
            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.HAIRLINE),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Hairline.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.HAIRLINE),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-HairlineItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.THIN),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Thin.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.THIN),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-ThinItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.LIGHT),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Light.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.LIGHT),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-LightItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.REGULAR),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Regular.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.REGULAR),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Italic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.MEDIUM),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Medium.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.MEDIUM),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-MediumItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.SEMIBOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Semibold.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.SEMIBOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-SemiboldItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.BOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Bold.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.BOLD),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-BoldItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.HEAVY),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Heavy.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.HEAVY),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-HeavyItalic.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.NORMAL, FontWeight.BLACK),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-Black.ttf"))
            );

            fontProvider.register(
                    new FontDescriptor(new FontFamily("Lato"),
                            FontStyle.ITALIC, FontWeight.BLACK),
                    PDType0Font.load(pDDocument, new File(BASE_FONT_DIR, "lato/Lato-BlackItalic.ttf"))
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
