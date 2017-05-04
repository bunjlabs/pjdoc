package com.bunjlabs.pjdoc.layout.elements.barcode;

import java.io.IOException;
import org.krysalis.barcode4j.impl.code128.Code128Bean;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Code128 extends Barcode {

    public Code128(String data) throws IOException {
        Code128Bean bean = new Code128Bean();
        bean.setBarHeight(4);
        createBarcodeImage(bean, data);
    }

}
