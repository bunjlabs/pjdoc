package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Image;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ImageRenderer extends Renderer<Image> {

    public ImageRenderer(Image modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBoundingBox = layoutContext.getBoundingBox();

        Rectangle boundingBox = layoutContext.getBoundingBox();

        float width = getAttribute(Attribute.WIDTH, 0f);
        float heigth = getAttribute(Attribute.HEIGHT, 0f);

        PDImageXObject createFromImage = null;// = LosslessFactory.createFromImage(renderContext.getPDDocument(), modelElement.getImage());

        float aspectRatio = ((float) createFromImage.getWidth()) / ((float) createFromImage.getHeight());

        if (width == 0f && heigth == 0f) {
            width = createFromImage.getWidth() / 96;
            heigth = createFromImage.getHeight() / 96;
        } else if (width == 0f) {
            width = heigth * aspectRatio;
        } else if (heigth == 0f) {
            heigth = width / aspectRatio;
        }

        //stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getUpperRightY() - heigth, width, heigth);

        /*if (width == 0f && heigth == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getUpperRightY());
            } else if (width == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getUpperRightY(), heigth * aspectRatio, heigth);
            } else if (heigth == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getUpperRightY(), width, width / aspectRatio);
            } else {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getUpperRightY(), width, heigth);
            }*/
        parentBoundingBox.addHeight(heigth);

        return new LayoutResult(LayoutResult.FULL, null);
    }

    @Override
    public void render(RenderContext renderContext) {
    }

    @Override
    public Renderer getNextRenderer() {
        return new ImageRenderer(modelElement);
    }

}
