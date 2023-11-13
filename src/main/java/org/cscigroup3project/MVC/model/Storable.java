package org.cscigroup3project.MVC.model;

import javafx.scene.shape.Rectangle;

public interface Storable {

    public Rectangle getBounds();
    public void store();
    public void deploy(int x, int y);
}
