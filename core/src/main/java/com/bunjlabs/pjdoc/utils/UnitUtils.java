package com.bunjlabs.pjdoc.utils;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class UnitUtils {

    public static float mm(float mm) {
        return (mm / 25.4f) * 72f;
    }

    public static float cm(float cm) {
        return (cm / 2.54f) * 72f;
    }

    public static float m(float mm) {
        return (mm / 0.254f) * 72f;
    }

    public static float inch(float inch) {
        return inch * 72f;
    }
}
