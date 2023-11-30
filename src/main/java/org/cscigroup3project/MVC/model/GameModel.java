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
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.gameObject.*;
import org.cscigroup3project.MVC.model.player.Player;
import org.cscigroup3project.MVC.model.room.Room;
import org.cscigroup3project.MVC.model.room.RoomManager;

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

    /** Our lock for the key puzzle */
    private Lock lock;

    /** The {@link RoomManager} for the game */
    private RoomManager roomManager;

    /** Array which keeps track of the room objects */
    private ArrayList<GameObject> allObjectsArray;

    /**
     * Create a new GameModel object with any number of {@link Room} objects (and their contained
     * objects), a {@link Player} for the game, and {ADD ANYTHING ELSE HERE}
     */
    public GameModel(){

        // currently empty arraylist of rooms
        this.rooms = new ArrayList<>();

        // current Player for the game with position and name
        this.player = new Player(new int[]{0,0}, "DemoPlayer", "Sprites/PS");

        // Create the roomManager for the application
        this.roomManager = new RoomManager();

        //Create the objects array for the application
        this.allObjectsArray = new ArrayList<>();

        // NOTE: separate key sprites used to avoid confusion in GameController image removal
        ArrayList<Image> keySprite = new ArrayList<Image>();
        keySprite.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Key.png")));
        this.key1 = new Key(50,50, keySprite, "key1", 0);

        //add key to object array and room
        allObjectsArray.add(key1);
        this.roomManager.getActiveRoom().addObject(key1);

        // Create the key for the application
        keySprite.add(0, new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Key.png")));
        this.key2 = new Key(-50,-50, keySprite, "key2",1);

        //add key to object array and room
        allObjectsArray.add(key2);
        this.roomManager.getActiveRoom().addObject(key2);

        //create the lock in a similar manner to the keys
        ArrayList<Image> lockSprites = new ArrayList<Image>();
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_0.png")));
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_1.png")));
        lockSprites.add(new Image(GameMain.class.getResourceAsStream("keyAndLockSprites/Lock_2.png")));

        //add key to object array and room
        this.lock = new Lock(0, -120, lockSprites);
        allObjectsArray.add(lock);

        this.roomManager.getActiveRoom().addObject(lock);
    }

    /** Getters for the {@link Player}, {@link RoomManager}, {@link Key} objects */
    public Player getPlayer() { return player; }

    public Key[] getKeys() {
        return new Key[]{key1,key2};
    }
    public RoomManager getRoomManager(){
        return roomManager;
    }

    public ArrayList<GameObject> getAllObjectsArray() {
        return allObjectsArray;
    }
}
