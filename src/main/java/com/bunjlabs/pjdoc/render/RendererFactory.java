package com.bunjlabs.pjdoc.render;

import com.bunjlabs.pjdoc.layout.elements.Element;
import java.util.List;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RendererFactory {

    public static Renderer createRendererSubtree(Element element) {
        Renderer subroot = element.createRenderer();
        List<Element> children = element.getChildren();

        children.forEach((e) -> subroot.add(createRendererSubtree(e)));

        return subroot;
    }
}
