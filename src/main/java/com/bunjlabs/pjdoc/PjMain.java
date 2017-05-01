package com.bunjlabs.pjdoc;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.attributes.Style;
import com.bunjlabs.pjdoc.utils.UnitUtils;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.layout.render.DocumentRenderer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.script.ScriptException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class PjMain {

    public static void test01(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);
        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));

        Div div = new Div();
        div.addStyle(new Style()
                .setFont(font)
                .setLeading(1)
        );

        for (int i = 0; i < 26; i++) {
            Paragraph paragraph = new Paragraph();
            paragraph.add(UnitUtils.pangram(26));
            paragraph.add(UnitUtils.pangram(26));
            paragraph.addStyle(new Style()
                    .setFontSize(8 + i)
                    .setPaddingLeft(UnitUtils.mm(i))
                    .setMarginRight(UnitUtils.mm(50))
            );
            div.add(paragraph);
        }

        document.add(div);
    }

    public static void test02(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);

        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));
        Div div = new Div();
        div.addStyle(new Style().setFont(font));

        for (int i = 0; i < 10; i++) {
            Paragraph paragraph = new Paragraph();
            paragraph.add(UnitUtils.pangram());

            div.add(paragraph);
        }

        document.add(div);

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();

    }

    public static void main(String[] args) throws FileNotFoundException, ScriptException, IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.getDocumentInformation().setProducer("pjDoc");
        pDDocument.setVersion(1.5f);

        //test01(pDDocument);
        test02(pDDocument);

        pDDocument.save("./example.pdf");
        pDDocument.close();
    }

}
