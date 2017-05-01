package com.bunjlabs.pjdoc.render;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutResult {

    public static final int FULL = 1;
    public static final int PARTIAL = 2;

    private final int type;

    public LayoutResult(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
