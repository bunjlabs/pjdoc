package com.bunjlabs.pjdoc;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.attributes.Style;
import com.bunjlabs.pjdoc.utils.UnitUtils;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Flex;
import com.bunjlabs.pjdoc.layout.elements.Image;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.layout.elements.barcode.Code128;
import com.bunjlabs.pjdoc.layout.render.DocumentRenderer;
import com.bunjlabs.pjdoc.xml.XmlWorker;
import java.io.File;
import java.io.IOException;
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
        document.addStyle(new Style().setFont(font));

        for (int i = 0; i < 32; i++) {
            Paragraph paragraph = new Paragraph();
            paragraph.add(UnitUtils.pangram(26));
            paragraph.addStyle(new Style()
                    .setFontSize(8 + i)
                    .setPaddingLeft(UnitUtils.mm(i))
            );
            document.add(paragraph);
        }

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
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

    public static void test03(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);

        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));
        Div div = new Div();
        div.addStyle(new Style().setFont(font));

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(UnitUtils.pangram());
        div.add(paragraph1);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(UnitUtils.pangram());
        div.add(paragraph2);

        Paragraph paragraph3 = new Paragraph();
        paragraph3.add(UnitUtils.pangram());
        div.add(paragraph3);

        document.add(div);

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
    }

    public static void test04(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);

        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));
        Div div = new Div();
        div.addStyle(new Style().setFont(font));

        Paragraph paragraph = new Paragraph();
        paragraph.add("Lorem ");
        paragraph.add("ipsum ");
        paragraph.add("dolor ");
        paragraph.add("sit ");
        paragraph.add("amet. ");
        div.add(paragraph);

        document.add(div);

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
    }

    public static void test05(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);

        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));
        document.addStyle(new Style().setFont(font));

        Flex flex = new Flex();
        flex.addStyle(new Style().setMarginBottom(UnitUtils.mm(20)));

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(UnitUtils.pangram(20));
        flex.add(paragraph1);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(UnitUtils.pangram(20));
        flex.add(paragraph2);

        Paragraph paragraph3 = new Paragraph();
        paragraph3.add(UnitUtils.pangram(20));
        flex.add(paragraph3);

        Div body = new Div();
        body.addStyle(new Style().setMarginBottom(UnitUtils.mm(20)));
        body.add(new Paragraph(UnitUtils.pangram()));

        document.add(flex);
        document.add(body);

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
    }

    public static void test06(PDDocument pDDocument) throws IOException {
        Document document = new Document(PDRectangle.A4);

        Image barcode = new Code128("3assgasdhg7has");
        barcode.addStyle(new Style().setHeight(UnitUtils.mm(30)));

        document.add(barcode);

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
    }

    public static void test07(PDDocument pDDocument) throws Exception {
        XmlWorker xmlWorker = new XmlWorker();

        Document document = xmlWorker.work(new File("example.xml"));

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.render();
    }

    public static void main(String[] args) throws Exception {
        PDDocument pDDocument = new PDDocument();
        pDDocument.getDocumentInformation().setProducer("pjDoc");
        pDDocument.setVersion(1.5f);

        test07(pDDocument);

        pDDocument.save("./example.pdf");
        pDDocument.close();
    }

}
