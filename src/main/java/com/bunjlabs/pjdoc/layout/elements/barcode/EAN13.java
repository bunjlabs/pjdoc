package com.bunjlabs.pjdoc.layout.elements.barcode;

import java.io.IOException;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class EAN13 extends Barcode {

    public EAN13(String data) throws IOException {
        createBarcodeImage(new EAN13Bean(), data);
    }

}
