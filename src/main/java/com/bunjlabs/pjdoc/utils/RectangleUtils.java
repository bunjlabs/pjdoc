package com.bunjlabs.pjdoc.utils;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RectangleUtils {

    public static PDRectangle clone(PDRectangle other) {
        return new PDRectangle(
                other.getLowerLeftX(),
                other.getLowerLeftY(),
                other.getWidth(),
                other.getHeight()
        );
    }
}
