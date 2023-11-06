/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/5/2023
 * Time: 6:23 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Room
 *
 * Description: Basic room class
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import java.util.ArrayList;

public class Room {
    /** The {@link Wall} objects contained by this room */
    private ArrayList<Wall> walls;

    /** The {@link Door} objects contained by this room */
    private ArrayList<Door> doors;

    /** The {@link Object} list contained by this room */
    private ArrayList<Object> objects;

    /**
     * Empty constructor for a room with nothing in it
     */
    public Room(){
        walls = new ArrayList<Wall>();
        doors = new ArrayList<Door>();
        objects = new ArrayList<>();
    }

    /**
     * Full constructor for pre-created walls, doors,
     * and objects for this room
     * @param walls the initial {@link Wall} objects for the room
     * @param doors the initial {@link Door} objects for the room
     * @param objects the initial {@link Object} objects for the room
     */
    public Room(ArrayList<Wall> walls, ArrayList<Door> doors, ArrayList<Object> objects){
        this.walls = walls;
        this.doors = doors;
        this.objects = objects;
    }

    //TODO the rest of the class

}
