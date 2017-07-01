package com.bunjlabs.pjdoc.utils;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TextUtils {

    private static final String PANGRAM_STR = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public static int countMatches(CharSequence str, char ch) {
        if (str.length() <= 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (ch == str.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static String pangram() {
        return PANGRAM_STR.substring(0, PANGRAM_STR.length());
    }

    public static String pangram(int length) {
        return PANGRAM_STR.substring(0, length);
    }

    public static float lineWidth(String line, PDFont font, float fontSize) throws IOException {
        return fontSize * font.getStringWidth(line) / 1000f;
    }
}
