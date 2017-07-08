package com.bunjlabs.pjdoc.utils;

import java.util.Scanner;
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

    public static PDRectangle pageSize(String value) {
        switch (value.toUpperCase()) {
            case "A0":
                return PDRectangle.A0;
            case "A1":
                return PDRectangle.A1;
            case "A2":
                return PDRectangle.A2;
            case "A3":
                return PDRectangle.A3;
            case "A4":
                return PDRectangle.A4;
            case "A5":
                return PDRectangle.A5;
            case "A6":
                return PDRectangle.A6;
            case "LEGAL":
                return PDRectangle.LEGAL;
            case "LETTER":
                return PDRectangle.LETTER;
        }

        Scanner s = new Scanner(value);

        PDRectangle page = new PDRectangle(StyleUtils.unit(s.next()), StyleUtils.unit(s.next()));

        s.close();

        return page;

    }
}
