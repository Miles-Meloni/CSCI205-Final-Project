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

    /**
     * Create a new GameModel object with any number of {@link Room} objects (and their contained
     * objects), a {@link Player} for the game, and {ADD ANYTHING ELSE HERE}
     */
    public GameModel(){

        // currently empty arraylist of rooms
        this.rooms = new ArrayList<>();

        // current Player for the game with position and name
        this.player = new Player(new int[]{0,0}, "DemoPlayer", "cscigroup3project/Sprites/PS");

        // single Wall for implementing collisions
        // minX and minY define upper left corner position
        //TODO either uncomment or delete
        //this.wall = new Wall(100,50,50,50,new Image[1]);

    }

    /** Getter for the {@link Player}, {@link Wall} */
    public Player getPlayer() { return player; }
    public Wall getWall() { return wall; }
}
