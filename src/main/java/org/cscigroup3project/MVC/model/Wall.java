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

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

/**
 * Class that represents wall objects that the {@link Player} cannot walk through. Extends from
 * {@link BoundingBox} in order to help checking for collisions.
 */
public class Wall {

    /** The {@link BoundingBox} of the Wall */
    private BoundingBox bounds;

    /**
     * Constructs a new Wall using a position and dimensions.
     *
     * @param minX   the X coordinate of the upper-left corner
     * @param minY   the Y coordinate of the upper-left corner
     * @param width  the width of the {@code Bounds}
     * @param height the height of the {@code Bounds}
     */
    public Wall(double minX, double minY, double width, double height) {
        this.bounds = new BoundingBox(minX, minY, width, height);
    }

    /** Getter for the {@link BoundingBox} of this Wall */
    public BoundingBox getBounds() { return bounds; }
}
