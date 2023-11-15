package org.cscigroup3project.MVC.model;

import javafx.scene.shape.Rectangle;

/**
 * interface that defines how all objects that can be interacted with will act
 * example of an interactable object - a button that can be pressed
 */
public interface Interactible {
    /**
     * @returns the bounds of the interactable object
     */
    public Rectangle getBounds();

    /**
     * how the player interacts with the interactable
     */
    public void react();
}
