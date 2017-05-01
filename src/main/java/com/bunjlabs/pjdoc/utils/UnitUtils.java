package com.bunjlabs.pjdoc.utils;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class UnitUtils {

    private static final String PANGRAM_STR = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public static float mm(float mm) {
        return (mm / 25.4f) * 72f;
    }

    public static float cm(float cm) {
        return (cm / 2.54f) * 72f;
    }

    public static String pangram() {
        return PANGRAM_STR.substring(0, PANGRAM_STR.length());
    }

    public static String pangram(int length) {
        return PANGRAM_STR.substring(0, length);
    }
}
