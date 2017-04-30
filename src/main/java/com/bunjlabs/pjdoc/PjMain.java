package com.bunjlabs.pjdoc;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.bunjlabs.pjdoc.layout.Style;
import com.bunjlabs.pjdoc.layout.UnitUtils;
import com.bunjlabs.pjdoc.layout.elements.Div;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import com.bunjlabs.pjdoc.layout.render.DocumentRenderer;
import java.awt.Color;
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
    
    public static void main(String[] args) throws FileNotFoundException, ScriptException, IOException {
        PDDocument pDDocument = new PDDocument();
        //pDDocument.getDocumentInformation().setProducer("pjDoc");
        //pDDocument.setVersion(1.5f);

        PDFont font = PDType0Font.load(pDDocument, new File("./fonts/roboto/Roboto-Thin.ttf"));
        
        Div div = new Div();
        div.addStyle(new Style().setFont(font).setLeading(1));
        
        for (int i = 0; i < 26; i++) {
            Paragraph paragraph = new Paragraph();
            paragraph.add(UnitUtils.pangram(26));
            paragraph.addStyle(new Style().setFontSize(8 + i));
            div.add(paragraph);
        }
        
        Document document = new Document(PDRectangle.A4);
        document.add(div);
        
        DocumentRenderer documentRenderer = new DocumentRenderer(pDDocument, document);
        documentRenderer.renderDocument();
        
        pDDocument.save("./example.pdf");
        pDDocument.close();
    }
    
}
