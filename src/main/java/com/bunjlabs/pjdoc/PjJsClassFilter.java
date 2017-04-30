package com.bunjlabs.pjdoc;

import jdk.nashorn.api.scripting.ClassFilter;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class PjJsClassFilter implements ClassFilter {

    @Override
    public boolean exposeToScripts(String s) {
        return s.startsWith("com.itextpdf.layout.element")
                || s.startsWith("com.itextpdf.barcodes");
    }

}
