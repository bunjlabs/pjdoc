package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.ImageRenderer;
import com.bunjlabs.pjdoc.layout.render.Renderer;
import java.awt.image.BufferedImage;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Image extends BlockElement<Image> {

    protected BufferedImage image;

    public Image() {
    }

    public Image(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Renderer createRenderer() {
        return new ImageRenderer(this);
    }

    public BufferedImage getImage() {
        return image;
    }

}
