package com.bunjlabs.pjdoc.font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public enum FontWeight {

    LIGHTER(100),
    LIGHT(300),
    NORMAL(400),
    BOLD(700),
    BOLDER(900);

    private final int weight;

    private FontWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
