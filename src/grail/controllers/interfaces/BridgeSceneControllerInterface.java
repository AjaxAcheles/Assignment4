package grail.controllers.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.BRIDGE_SCENE_CONTROLLER})
public interface BridgeSceneControllerInterface extends MouseListener, KeyListener,
        ActionListener, PropertyChangeListener {
    JButton getSay();
    JButton getPassed();
    JButton getFailed();
    JButton getApproach();
}
