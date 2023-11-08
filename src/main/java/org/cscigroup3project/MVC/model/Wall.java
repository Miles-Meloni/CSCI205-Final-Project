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
 * Description: Basic wall class for the game
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class Wall extends Bounds {


    /**
     * Creates a new instance of {@code Bounds} class.
     *
     * @param minX   the X coordinate of the upper-left corner
     * @param minY   the Y coordinate of the upper-left corner
     * @param minZ   the minimum z coordinate of the {@code Bounds}
     * @param width  the width of the {@code Bounds}
     * @param height the height of the {@code Bounds}
     * @param depth  the depth of the {@code Bounds}
     */
    protected Wall(double minX, double minY, double minZ, double width, double height, double depth) {
        super(minX, minY, minZ, width, height, depth);
    }

    public boolean isEmpty(){
        return false;
    }

    /**
     * Checks if the specified point is inside the boundary of {@code Bounds}.
     *
     * @param p the specified point to be tested
     * @return true if the specified point is inside the boundary of this
     * {@code Bounds}; false otherwise
     */
    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    /**
     * Checks if the specified point is inside the boundary of {@code Bounds}.
     *
     * @param p the specified 3D point to be tested
     * @return true if the specified point is inside the boundary of this
     * {@code Bounds}; false otherwise
     */
    @Override
    public boolean contains(Point3D p) {
        return false;
    }

    /**
     * Checks if the specified {@code (x, y)} coordinates are inside the boundary
     * of {@code Bounds}.
     *
     * @param x the specified x coordinate to be tested
     * @param y the specified y coordinate to be tested
     * @return true if the specified {@code (x, y)} coordinates are inside the
     * boundary of this {@code Bounds}; false otherwise
     */
    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    /**
     * Checks if the specified {@code (x, y, z)} coordinates are inside the boundary
     * of {@code Bounds}.
     *
     * @param x the specified x coordinate to be tested
     * @param y the specified y coordinate to be tested
     * @param z the specified z coordinate to be tested
     * @return true if the specified {@code (x, y)} coordinates are inside the
     * boundary of this {@code Bounds}; false otherwise
     */
    @Override
    public boolean contains(double x, double y, double z) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} entirely contains the
     * specified Bounds, {@code b}.
     *
     * @param b The specified Bounds
     * @return true if the specified Bounds, {@code b}, is inside the
     * boundary of this {@code Bounds}; false otherwise
     */
    @Override
    public boolean contains(Bounds b) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} entirely contains the
     * specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     *          rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     *          rectangular area
     * @param w the width of the specified rectangular area
     * @param h the height of the specified rectangular area
     * @return true if the interior of this {@code Bounds} entirely contains
     * the specified rectangular area; false otherwise
     */
    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} entirely contains the
     * specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param y the y coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param z the z coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param w the width of the specified rectangular volume
     * @param h the height of the specified rectangular volume
     * @param d the depth of the specified rectangular volume
     * @return true if the interior of this {@code Bounds} entirely contains
     * the specified rectangular area; false otherwise
     */
    @Override
    public boolean contains(double x, double y, double z, double w, double h, double d) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} intersects the interior
     * of a specified Bounds, {@code b}.
     *
     * @param b The specified Bounds
     * @return true if the interior of this {@code Bounds} and the interior
     * of the specified Bounds, {@code b}, intersect
     */
    @Override
    public boolean intersects(Bounds b) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} intersects the interior
     * of a specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     *          rectangular area
     * @param y the y coordinate of the upper-left corner of the specified
     *          rectangular area
     * @param w the width of the specified rectangular area
     * @param h the height of the specified rectangular area
     * @return true if the interior of this {@code Bounds} and the interior
     * of the rectangular area intersect
     */
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    /**
     * Checks if the interior of this {@code Bounds} intersects the interior
     * of a specified rectangular area.
     *
     * @param x the x coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param y the y coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param z the z coordinate of the upper-left corner of the specified
     *          rectangular volume
     * @param w the width of the specified rectangular volume
     * @param h the height of the specified rectangular volume
     * @param d the depth of the specified rectangular volume
     * @return true if the interior of this {@code Bounds} and the interior
     * of the rectangular area intersect
     */
    @Override
    public boolean intersects(double x, double y, double z, double w, double h, double d) {
        return false;
    }
}
