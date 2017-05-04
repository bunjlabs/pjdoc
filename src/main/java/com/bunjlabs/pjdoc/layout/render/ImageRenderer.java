package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Image;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
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
    public LayoutResult render(RenderContext renderContext, LayoutContext layoutContext) {
        PDPageContentStream stream = renderContext.getPageContentStream();
        PDRectangle boundingBox = layoutContext.getBoundingBox();

        try {
            float width = getAttribute(Attribute.WIDTH, 0f);
            float heigth = getAttribute(Attribute.HEIGHT, 0f);

            PDImageXObject createFromImage = LosslessFactory.createFromImage(renderContext.getPDDocument(), modelElement.getImage());

            float aspectRatio = ((float) createFromImage.getWidth()) / ((float) createFromImage.getHeight());

            if (width == 0f && heigth == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getLowerLeftY());
            } else if (width == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getLowerLeftY(), heigth * aspectRatio, heigth);
            } else if (heigth == 0f) {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getLowerLeftY(), width, width / aspectRatio);
            } else {
                stream.drawImage(createFromImage, boundingBox.getLowerLeftX(), boundingBox.getLowerLeftY(), width, heigth);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new LayoutResult(LayoutResult.FULL);
    }

}
