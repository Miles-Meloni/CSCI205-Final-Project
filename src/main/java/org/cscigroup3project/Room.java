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
package org.cscigroup3project;

import java.util.ArrayList;

public class Room {
    private ArrayList<Wall> walls;
    private ArrayList<Door> doors;
    private ArrayList<Object> objects;

    public Room(){
        walls = new ArrayList<Wall>();
        doors = new ArrayList<Door>();
        objects = new ArrayList<>();
    }

    public Room(ArrayList<Wall> walls, ArrayList<Door> doors, ArrayList<Object> objects){
        this.walls = walls;
        this.doors = doors;
        this.objects = objects;
    }

    //TODO the rest of the class

}
