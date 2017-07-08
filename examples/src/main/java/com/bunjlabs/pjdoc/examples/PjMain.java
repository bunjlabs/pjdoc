package com.bunjlabs.pjdoc.examples;

import com.bunjlabs.pjdoc.font.DefaultFontProvider;
import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.render.DocumentRenderer;
import com.bunjlabs.pjdoc.pjx.XmlWorker;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class PjMain {

    public static void test01(PDDocument pDDocument) throws Exception {
        XmlWorker xmlWorker = new XmlWorker();
        xmlWorker.getParameters().put("document.barcode", "123456789");
        xmlWorker.getParameters().put("document.typename", "Договор");
        xmlWorker.getParameters().put("document.description", "на разработку сайта");
        xmlWorker.getParameters().put("document.number", "5243/2017");
        xmlWorker.getParameters().put("document.barcode", "123456789");
        xmlWorker.getParameters().put("document.type", "314");
        xmlWorker.getParameters().put("document.body", "2.1. abc\n\n"
                + "**2.2.** abc\n\n"
                + "Some centered body text"
                + "Some centered body text"
                + "2.3. abc\n\n"
                + "2.4. abc\n\n");
        
        xmlWorker.getParameters().put("document.contacts", "Общество с ограниченной ответственностью «Банжлабс»\n\n"
                + "ОГРН 1172536015904\n\n"
                + "ИНН/КПП 2536302984/253601001");

        Document document = xmlWorker.work(new File("./example.xml"));

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);

        DefaultFontProvider fontProvider = (DefaultFontProvider) documentRenderer.getFontProvider();
        FontRegisterer.registerFontRoboto(pDDocument, fontProvider);
        FontRegisterer.registerFontLato(pDDocument, fontProvider);
        documentRenderer.render();
    }

    public static void test02(PDDocument pDDocument) throws Exception {
        XmlWorker xmlWorker = new XmlWorker();
        xmlWorker.getParameters().put("contract.barcode", "123456789");

        Document document = xmlWorker.work(new File("./example2.xml"));

        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);

        DefaultFontProvider fontProvider = (DefaultFontProvider) documentRenderer.getFontProvider();
        FontRegisterer.registerFontRoboto(pDDocument, fontProvider);
        FontRegisterer.registerFontLato(pDDocument, fontProvider);
        documentRenderer.render();
    }

    public static void main(String[] args) throws Exception {
        PDDocument pDDocument = new PDDocument();
        pDDocument.getDocumentInformation().setProducer("pjDoc");

        test01(pDDocument);

        pDDocument.save("./example.pdf");
        pDDocument.close();
    }

}
