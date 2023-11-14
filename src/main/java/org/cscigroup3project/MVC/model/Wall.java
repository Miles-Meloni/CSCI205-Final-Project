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

package org.cscigroup3project.MVC.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Class that represents wall objects that the {@link Player} cannot walk through. Uses a
 * {@link Rectangle} in order to help checking for collisions.
 */
public class Wall implements Collidable{

    /** The {@link Rectangle} of the Wall */
    private Rectangle bounds;

    /**
     * Constructs a new Wall using a position and dimensions.
     *
     * @param minX   the X coordinate of the upper-left corner
     * @param minY   the Y coordinate of the upper-left corner
     * @param width  the width of the {@code bounds}
     * @param height the height of the {@code bounds}
     */
    public Wall(int minX, int minY, int width, int height) {
        this.bounds = new Rectangle(minX, minY, width, height);
    }

    /** Getter for the {@link Rectangle} of this Wall */
    public Rectangle getBounds() { return bounds; }
}
