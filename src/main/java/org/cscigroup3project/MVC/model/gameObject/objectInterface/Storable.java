package org.cscigroup3project.MVC.model.gameObject.objectInterface;

import javafx.scene.shape.Rectangle;

/**
 * Interface that defines how items that can be kept in the inventory will act
 */
public interface Storable {
    /**
     * @return the Rectangle representation of the bounds of the storable object
     */
    public Rectangle getBounds();

    /**
     * stores the object into the inventory
     */
    public void store();

    /**
     *  the location in the room of the storable object
     */
    public void deploy(int x, int y);
}
