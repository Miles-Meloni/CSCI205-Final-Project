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
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import java.util.ArrayList;

public class GameModel {

    /**
     * Our player for the game
     */
    private Player player;

    /**
     * Our array of all rooms for the game
     */
    private ArrayList<Room> rooms;


    /**
     * Create a new GameModel object with any
     * number of {@link Room} objects (and their contained
     * objects), a {@link Player} for the game,
     * and {ADD ANYTHING ELSE HERE}
     */
    public GameModel(){

        // currently empty arraylist of rooms
        this.rooms = new ArrayList<>();

        // current player for the game
        // (for now, a circle)
        this.player = new Player();

    }

}
