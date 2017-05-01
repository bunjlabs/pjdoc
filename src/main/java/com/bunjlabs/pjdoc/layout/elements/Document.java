package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.utils.UnitUtils;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Document extends RootElement<Document> {

    private final PDRectangle pageSize;
    private float marginTop = UnitUtils.mm(20);
    private float marginRight = UnitUtils.mm(20);
    private float marginBottom = UnitUtils.mm(20);
    private float marginLeft = UnitUtils.mm(20);

    public Document() {
        this.pageSize = PDRectangle.A4;
    }

    public Document(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    public Document add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(float marginTop) {
        this.marginTop = marginTop;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMargin(float marginTop, float marginRight, float marginBottom, float marginLeft) {
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
    }

    public PDRectangle getPageSize() {
        return new PDRectangle(
                this.pageSize.getLowerLeftX(),
                this.pageSize.getLowerLeftY(),
                this.pageSize.getWidth(),
                this.pageSize.getHeight()
        );
    }

    public PDRectangle getEffectiveArea() {
        return new PDRectangle(
                this.pageSize.getLowerLeftX() + this.marginLeft,
                this.pageSize.getLowerLeftY() + this.marginBottom,
                this.pageSize.getWidth() - this.marginLeft - this.marginRight,
                this.pageSize.getHeight() - this.marginBottom - this.marginTop
        );
    }

}
