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
package org.cscigroup3project.MVC.model.room;

import org.cscigroup3project.MVC.model.gameObject.*;
import org.cscigroup3project.MVC.model.player.Player;

import java.util.ArrayList;

/**
 * Enum representing the state of the Room.
 */
enum RoomState{

    /** The {@link Player} is in the Room */
    PRESENT_PLAYER,

    /** The {@link Player} is not in the Room */
    NO_PLAYER,
}

/**
 * Class containing a 2D {@link ArrayList} coding for {@link Wall}, floor, and {@link GameObject}s.
 */
public class Room {

    /** The {@link RoomState} state of the room, for rendering */
    private RoomState roomState;

    /** The {@link GameObject} list of lists contained by this room */
    private ArrayList<ArrayList<GameObject>> baseObjects;

    /** The {@link GameObject} list containing non-environmental objects */
    private ArrayList<GameObject> itemObjects;

    private ArrayList<Doorway> doorways;
    private ArrayList<DoorFrame> doorFrames;

    /**
     * Empty constructor for a room with nothing in it.
     * No player in the room by default
     */
    public Room(){
        baseObjects = new ArrayList<>();
        itemObjects = new ArrayList<>();
        roomState = RoomState.NO_PLAYER;
    }

    /**
     * Full constructor for pre-created objects for this room.
     * No player in the room by default
     * @param objects the initial {@link Object} objects for the room
     */
    public Room(ArrayList<ArrayList<GameObject>> objects){
        this.baseObjects = objects;
        this.itemObjects = new ArrayList<>();

        this.doorways = new ArrayList<>();
        this.doorFrames = new ArrayList<>();

        genDoorways();
        genDoorFrames();

        roomState = RoomState.NO_PLAYER;
    }

    public ArrayList<ArrayList<GameObject>> getBaseObjects() {
        return baseObjects;
    }

    public ArrayList<GameObject> getItemObjects() {
        return itemObjects;
    }


    /**
     * Getter for all {@link Wall} objects of the room
     */
    public ArrayList<Wall> getWalls(){
        ArrayList<Wall> walls = new ArrayList<>();

        for (ArrayList<GameObject> arrGO : baseObjects) {
            for (GameObject gameObject : arrGO) {
                if (gameObject instanceof Wall) {
                    walls.add((Wall) gameObject);
                }
            }
        }

        return walls;
    }

    /**
     * Getter for all {@link DoorFrame} objects of the room
     */
    private void genDoorFrames(){

        for (ArrayList<GameObject> arrGO : baseObjects) {
            for (GameObject gameObject : arrGO) {
                if (gameObject instanceof DoorFrame) {
                    this.doorFrames.add((DoorFrame) gameObject);
                }
            }
        }
    }

    public ArrayList<DoorFrame> getDoorFrames() {
        return doorFrames;
    }

    /**
     * Getter for all {@link org.cscigroup3project.MVC.model.gameObject.Doorway} objects of the room
     */
    private void genDoorways() {
        ArrayList<Doorway> doorways = new ArrayList<>();

        for (ArrayList<GameObject> arrGO : baseObjects) {
            for (GameObject gameObject : arrGO) {
                if (gameObject instanceof Doorway) {
                    this.doorways.add((Doorway) gameObject);
                }
            }
        }
    }
    public ArrayList<Doorway> getDoorways() {
        return doorways;
    }


    public void addObject(GameObject addition){
        itemObjects.add(addition);
    }


    /**
     * Method to remove an item from the {@link Room#baseObjects} list
     * @param removal the {@link GameObject} to be removed
     * @return the removed object, if found; otherwise, null
     */
    public GameObject removeObject(GameObject removal){
        if (itemObjects.remove(removal)){
            return removal;
        }
        else {return null;}
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
