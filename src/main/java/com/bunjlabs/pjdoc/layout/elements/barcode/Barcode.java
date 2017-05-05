package com.bunjlabs.pjdoc.layout.elements.barcode;

import com.bunjlabs.pjdoc.layout.elements.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class Barcode extends Image {

    private final int dpi = 1200;

    protected final void createBarcodeImage(AbstractBarcodeBean barcodeBean, String data) throws IOException {
        BitmapCanvasProvider provider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_GRAY, true, 0);
        barcodeBean.generateBarcode(provider, data);
        provider.finish();

        BufferedImage barcodeImage = provider.getBufferedImage();

        image = barcodeImage;
    }
}
