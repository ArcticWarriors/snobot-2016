package com.snobot.coordinate_gui.desktop_app.settings;

import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Arrays;

import javax.swing.border.MatteBorder;

import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

public class ImmutablesRepresenter extends Representer {

    public ImmutablesRepresenter() {
        super();
        this.representers.put(java.awt.Color.class, new RepresentColor());
        this.representers.put(Insets.class, new RepresentInsets());
        this.representers.put(MatteBorder.class, new RepresentMatteBorder());
        this.representers.put(Rectangle.class, new RepresentRectangle());
    }

    class RepresentInsets implements Represent {

        public Node representData(Object data) {
            Insets insets = (Insets) data;
            return representSequence(
                    getTag(data.getClass(), new Tag(data.getClass())),
                    Arrays.asList(new Object[] { insets.top, insets.left, insets.bottom,
                            insets.right }), true);
        }

    }

    class RepresentRectangle implements Represent {

        public Node representData(Object data) {
            Rectangle rect = (Rectangle) data;
            return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
                    Arrays.asList(new Object[] { rect.x, rect.y, rect.width, rect.height }), true);
        }

    }

    class RepresentMatteBorder implements Represent {

        public Node representData(Object data) {
            MatteBorder mb = (MatteBorder) data;
            return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
                    Arrays.asList(new Object[] { mb.getBorderInsets(), mb.getMatteColor() }), true);
        }

    }

    class RepresentColor implements Represent {

        public Node representData(Object data) {
            java.awt.Color color = (java.awt.Color) data;
            return representSequence(
                    getTag(data.getClass(), new Tag(data.getClass())),
                    Arrays.asList(new Integer[] { color.getRed(), color.getGreen(),
                            color.getBlue(), color.getAlpha() }), true);
        }

    }
}