package com.bunjlabs.pjdoc.layout.elements.barcode;

import java.io.IOException;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class PDF417 extends Barcode {

    private final int dpi = 300;

    public PDF417(String data) throws IOException {
        createBarcodeImage(new PDF417Bean(), data);
    }

}
