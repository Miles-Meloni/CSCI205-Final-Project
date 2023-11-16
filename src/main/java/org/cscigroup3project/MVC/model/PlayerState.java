/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/4/2023
 * Time: 10:12 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Texture
 *
 * Description: Class to store the texture of an object
 *
 * ****************************************
 */

package org.cscigroup3project.MVC.model;

/**
 * Enum describing the current state of the {@link Player}.
 */
public enum PlayerState {

    /** The {@link Player} is not moving */
    IDLE,

    /** The {@link Player} is moving */
    MOVING,

    /** The {@link Player} is interacting with an object or interface */
    EXPLORING,
}
