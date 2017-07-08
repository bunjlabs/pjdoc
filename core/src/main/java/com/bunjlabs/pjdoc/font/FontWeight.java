package com.bunjlabs.pjdoc.font;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public enum FontWeight {

    HAIRLINE(100),
    THIN(200),
    LIGHT(300),
    REGULAR(400),
    NORMAL(400),
    MEDIUM(500),
    SEMIBOLD(600),
    BOLD(700),
    HEAVY(800),
    BLACK(900);

    private final int weight;

    private FontWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
