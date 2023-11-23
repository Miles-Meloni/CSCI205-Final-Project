package org.cscigroup3project.MVC.model.gameObject.objectInterface;


import javafx.scene.shape.Rectangle;

/**
 * The interface that defines how objects that can be collided with ought to act
 * example - a wall
 */
public interface Collidable {
    /**
     * @return the bounds of the collidable object
     */
    public Rectangle getBounds();
}
