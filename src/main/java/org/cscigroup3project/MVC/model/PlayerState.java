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

public enum PlayerState {
    // when the player IS NOT moving
    IDLE,

    // when the player IS moving
    MOVING,

    // when the player is INTERACTING
    // WITH an interface of something
    EXPLORING,
}
