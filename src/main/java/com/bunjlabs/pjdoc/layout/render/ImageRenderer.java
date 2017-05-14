package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Image;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ImageRenderer extends Renderer<Image> {

    private static final float IMAGE_PPI = 96;

    private PDImageXObject image;

    public ImageRenderer(Image modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBoundingBox = layoutContext.getBoundingBox();

        Rectangle boundingBox = layoutContext.getBoundingBox();

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), 0, 0));

        float width = getAttribute(Attribute.WIDTH, 0f);
        float heigth = getAttribute(Attribute.HEIGHT, 0f);

        image = null;

        try {
            image = LosslessFactory.createFromImage(layoutContext.getDocumentRenderer().getPDDocument(), modelElement.getImage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        float aspectRatio = ((float) image.getWidth()) / ((float) image.getHeight());

        if (width == 0f && heigth == 0f) {
            width = image.getWidth() / IMAGE_PPI;
            heigth = image.getHeight() / IMAGE_PPI;
        } else if (width == 0f) {
            width = heigth * aspectRatio;
        } else if (heigth == 0f) {
            heigth = width / aspectRatio;
        }

        occupiedArea.getBoundingBox().addRight(width).addBottom(heigth);

        return new LayoutResult(LayoutResult.FULL, occupiedArea);
    }

    @Override
    public void render(RenderContext renderContext) {
        try {
            PDPageContentStream stream = renderContext.getPageContentStream(occupiedArea.getPageNumber());
            Rectangle boundingBox = occupiedArea.getBoundingBox();

            stream.drawImage(image, boundingBox.getLeft(), boundingBox.getBottom(), boundingBox.getWidth(), boundingBox.getHeight());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Renderer getNextRenderer() {
        return new ImageRenderer(modelElement);
    }
}