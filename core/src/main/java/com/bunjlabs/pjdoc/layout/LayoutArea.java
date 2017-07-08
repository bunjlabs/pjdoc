package com.bunjlabs.pjdoc.layout;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LayoutArea {

    private final int pageNumber;
    private Rectangle boundingBox;

    public LayoutArea(int pageNumber, Rectangle boundingBox) {
        this.pageNumber = pageNumber;
        this.boundingBox = boundingBox;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    public LayoutArea clone() {
        return new LayoutArea(pageNumber, boundingBox.clone());
    }

}
