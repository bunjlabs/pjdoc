package com.bunjlabs.pjdoc.layout.elements.barcode;

import java.io.IOException;
import org.krysalis.barcode4j.impl.code39.Code39Bean;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Code39 extends Barcode {

    public Code39(String data) throws IOException {
        Code39Bean bean = new Code39Bean();
        bean.setBarHeight(4);
        bean.setFontSize(2);
        createBarcodeImage(bean, data);
    }
}
