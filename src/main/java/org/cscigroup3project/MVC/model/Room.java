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

enum RoomState{
    PRESENT_PLAYER,
    NO_PLAYER,
}
public class Room {


    /** The {@link RoomState} state of the room, for rendering */
    private RoomState roomState;

    /** The {@link GameObject} list contained by this room */
    private ArrayList<GameObject> gameObjects;

    /**
     * Empty constructor for a room with nothing in it.
     * No player in the room by default
     */
    public Room(){
        gameObjects = new ArrayList<>();
        roomState = RoomState.NO_PLAYER;
    }

    /**
     * Full constructor for pre-created objects for this room.
     * No player in the room by default
     * @param objects the initial {@link Object} objects for the room
     */
    public Room(ArrayList<GameObject> objects){
        this.gameObjects = objects;
        roomState = RoomState.NO_PLAYER;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Add an object to the room
     */
    public void addObject(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    /**
     * Getter for all {@link Wall} objects of the room
     */
    public ArrayList<Wall> getWalls(){
        ArrayList<Wall> walls = new ArrayList<>();

        for (GameObject gameObject : gameObjects){
            if (gameObject instanceof Wall){
                walls.add((Wall)gameObject);
            }
        }

        return walls;
    }

    /**
     * Getter for all {@link Door} objects of the room
     */
    public ArrayList<Door> getDoors(){
        ArrayList<Door> doors = new ArrayList<>();

        for (GameObject gameObject : gameObjects){
            if (gameObject instanceof Door){
                doors.add((Door)gameObject);
            }
        }

        return doors;
    }

    /**
     * For a player to enter the Room
     */
    public void enterPlayer(){
        this.roomState = RoomState.PRESENT_PLAYER;
    }

    /**
     * For a player to exit the room
     */
    public void exitPlayer(){
        this.roomState = RoomState.NO_PLAYER;
    }

}
