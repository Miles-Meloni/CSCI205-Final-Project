/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:28 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: GameModel
 *
 * Description:
 * The Model representing our JavaFX application.
 * ****************************************
 */

package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * The model representing our JavaFX application.
 */
public class GameModel {

    /** Our player for the game */
    private Player player;

    /** Our array of all rooms for the game */
    private ArrayList<Room> rooms;

    /** Our {@link Wall} for the game */
    private Wall wall;

    /**Our {@link Disk} for testing music */
    private Disk disk;

    /** Our two {@link Key} objects for the key puzzle */
    private Key key1;
    private Key key2;

    /** The {@link org.cscigroup3project.MVC.model.RoomManager} for the game */
    private RoomManager roomManager;

    /**
     * Create a new GameModel object with any number of {@link Room} objects (and their contained
     * objects), a {@link Player} for the game, and {ADD ANYTHING ELSE HERE}
     */
    public GameModel(){

        // currently empty arraylist of rooms
        this.rooms = new ArrayList<>();

        // current Player for the game with position and name
        this.player = new Player(new int[]{0,0}, "DemoPlayer", "cscigroup3project/Sprites/PS");

        // Create the roomManager for the application
        this.roomManager = new RoomManager();

        // Add the single key sprite, to be passed in Key constructors
        ArrayList<Image> keySprite = new ArrayList<Image>();
        keySprite.add(new Image("cscigroup3project/keyAndLockSprites/Key.png"));

        // Create the key for the application
        this.key1 = new Key(0,0, keySprite, "key1");
        this.key2 = new Key(50,50, keySprite, "key2");

        this.roomManager.getActiveRoom().addObject(key1);
        this.roomManager.getActiveRoom().addObject(key2);

    }

    /** Getters for the {@link Player}, {@link RoomManager}, {@link Key} objects */
    public Player getPlayer() { return player; }

    public Key[] getKeys() {
        return new Key[]{key1,key2};
    }
    public RoomManager getRoomManager(){
        return roomManager;
    }

}
