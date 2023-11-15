/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Keeler Thomas
 * Section: 01 - 9:00am
 * Date: 11/15/2023
 * Time: 9:22 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: RoomManager
 *
 * Description:
 * Class that creates the Rooms for our game.
 * ****************************************
 */

package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Class that creates all the {@link Room}s our game will use and populates them with {@link GameObject}s.
 */
public class RoomManager {

    /** The constant for grid size */
    private final int GRID_SIZE = 16;

    /** The grid width & height of our example {@link Room} */
    private final int DIM = 5;

    /** The {@link ArrayList} of {@link Room} objects */
    private ArrayList<Room> rooms;

    /** The active {@link Room} to show the user */
    private Room activeRoom;

    /**
     * Creates a RoomManager object.
     */
    public RoomManager() {
        generateRooms();
    }

    /**
     * Generates {@link Room} objects
     */
    private void generateRooms() {

        // Generate the first Room code
        ArrayList<ArrayList<GameObject>> code1 = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            code1.add(new ArrayList<>());
        }
        // Loop through every position in the Room code
        // Looping through y-positions
        for (int i = 0; i < DIM; i++) {
            // Looping through x-positions
            for (int j = 0; j < DIM; j++) {
                // Add a Wall around the square border
                if ((i==0) || (i==DIM-1) || (j==0) || (j==DIM-1)) {
                    code1.get(i).add(new Wall((int) (GRID_SIZE*(j-DIM/2.0)),(int) (GRID_SIZE*(i-DIM/2.0)),
                            GRID_SIZE, GRID_SIZE, new Image[1])); // want this to be wall_front.png for now
                }
                // Add a Floor to the center
                else {
                    // TODO - Add floor tiles
                }
            }
        }
        Room room1 = new Room(); // pass in code1 once Room constructor is updated
        rooms.add(room1);
    }
}