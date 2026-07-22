package grail.views.interfaces;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.PAINT_LISTENER)
public interface PaintListener extends PropertyChangeListener {
    void paint(Graphics2D graphics);

    void repaint();

    @Override
    default void propertyChange(PropertyChangeEvent event) {
        repaint();
    }
}