/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/5/2023
 * Time: 6:32 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Walls
 *
 * Description:
 * Basic wall class for the game
 * ****************************************
 */

package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Collidable;
import org.cscigroup3project.MVC.model.player.Player;

import java.util.ArrayList;

/**
 * Class that represents wall objects that the {@link Player} cannot walk through. Uses a
 * {@link Rectangle} in order to help checking for collisions.
 */
public class Wall extends GameObject implements Collidable {

    private Rectangle bounds;

    /**
     * Constructs a new Wall using a position and dimensions.
     *
     * @param minX   the X coordinate of the upper-left corner
     * @param minY   the Y coordinate of the upper-left corner
     * @param w  the width of the {@code bounds}
     * @param h the height of the {@code bounds}
     */
    public Wall(int minX, int minY, int w, int h, ArrayList<Image> sprites) {
        super(minX, minY, w, h, sprites);
        this.bounds = new Rectangle(minX, minY, w, h);
    }

    /** Getter for the {@link Rectangle} of this Wall */
    public Rectangle getBounds() { return bounds; }
}
