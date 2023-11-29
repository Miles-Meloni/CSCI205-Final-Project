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

package org.cscigroup3project.MVC.model.room;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.model.gameObject.Door;
import org.cscigroup3project.MVC.model.gameObject.GameObject;
import org.cscigroup3project.MVC.model.gameObject.Wall;

import java.io.File;
import java.util.ArrayList;

/**
 * Class that creates all the {@link Room}s our game will use and populates them with {@link GameObject}s.
 */
public class RoomManager {

    /** The constant for grid size */
    private final int GRID_SIZE = 16;

    /** The grid width & height of our example {@link Room} */
    private final int DIM = 16;

    /** The {@link ArrayList} of {@link Room} objects */
    private ArrayList<Room> rooms;

    /** The active {@link Room} to show the user */
    private Room activeRoom;

    //TODO discuss this
    /** ArrayList for wall images, for now */
    private ArrayList<Image> wallSprites;

    /** Single image for floor sprite */
    private ArrayList<Image> floorSprites;

    /** ArrayList for door images, for now */
    private ArrayList<Image> doorSprites;

    private static final int DOOR_SIZE = 3;

    /**
     * Creates a RoomManager object.
     */
    public RoomManager() {
        rooms = new ArrayList<>();

        //TODO maybe refactor elsewhere?
        wallSprites = new ArrayList<>();

        File dir = new File("src/main/resources/cscigroup3project/roomTiles/wallTiles/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing
            ) {
                if (child.getName().contains("indent") || child.getName().contains("Wall")){
                    wallSprites.add(new Image("cscigroup3project/roomTiles/wallTiles/" + child.getName()));
                }
            }
        }


        doorSprites = new ArrayList<>();

        dir = new File("src/main/resources/cscigroup3project/roomTiles/doorTiles");
        directoryListing = dir.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing
            ) {
                if (child.getName().contains("indent") || child.getName().contains("Door")){
                    doorSprites.add(new Image("cscigroup3project/roomTiles/doorTiles/" + child.getName()));
                }
            }
        }

        floorSprites = new ArrayList<>();
        floorSprites.add(new Image("cscigroup3project/roomTiles/Floor.png"));


        generateRooms();
    }

    /**
     * Generates {@link Room} objects
     */
    private void generateRooms() {

        // TODO: temporary hardcode
        int doorX = 8;
        int doorY = 0;

        int doorXEnd = -1;
        int doorYEnd = -1;

        Door thisDoor = null;

        // Generate the first Room code
        ArrayList<ArrayList<GameObject>> code1 = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            code1.add(new ArrayList<>());
        }

        //TODO clean up this nasty, messy, no good, very bad function / refactor

        // Loop through every position in the Room code
        // Looping through y-positions
        for (int j = 0; j < DIM; j++) {
            // Looping through x-positions
            for (int i = 0; i < DIM; i++) {


                // Add a Door frame if at right coordinates
                if (i == doorY && j == doorX) {
                    thisDoor = getDoor(j, i);
                    code1.get(i).add(thisDoor.getTopDoorFrame());

                    if (doorX == 0){
                        doorXEnd = 0;
                        doorYEnd = doorY + DOOR_SIZE;
                    }
                    else{
                        doorYEnd = 0;
                        doorXEnd = doorX + DOOR_SIZE;
                    }
                }

                // Add a Door frame if at right coordinates
                else if (i == doorYEnd && j == doorXEnd){
                    code1.get(i).add(thisDoor.getBottomDoorFrame());
                }

                // Add a Door fade if at right coordinates
                // TODO update with new files
                else if (i == doorY && doorX != 0 && j > doorX && j < doorXEnd){
                    code1.get(i).add(new GameObject((int) (GRID_SIZE*(j-DIM/2.0)),(int) (GRID_SIZE*(i-DIM/2.0)),
                            GRID_SIZE, GRID_SIZE, floorSprites));
                }

                // Add a Door fade if at right coordinates
                // TODO update with new files
                else if (j == doorX && doorY != 0 && i > doorY && i < doorYEnd){
                    code1.get(i).add(new GameObject((int) (GRID_SIZE*(j-DIM/2.0)),(int) (GRID_SIZE*(i-DIM/2.0)),
                            GRID_SIZE, GRID_SIZE, floorSprites));
                }

                // Add a Wall around the square border otherwise
                else if ((i==0) || (i==DIM-1) || (j==0) || (j==DIM-1)) {
                    Wall thisWall = getWall(j, i);
                    code1.get(i).add(thisWall);
                }
                // Add a Floor to the center
                else {
                    code1.get(i).add(new GameObject((int) (GRID_SIZE*(j-DIM/2.0)),(int) (GRID_SIZE*(i-DIM/2.0)),
                            GRID_SIZE, GRID_SIZE, floorSprites)); // floor.png
                }

            }
        }

        Room room1 = new Room(code1); // pass in code1 once Room constructor is updated
        rooms.add(room1);

        //TODO remove this once player position tracked, for testing View
        activeRoom = room1;
    }

    private Wall getWall(int j, int i) {
        Wall thisWall = new Wall((int) (GRID_SIZE*(i -DIM/2.0)),(int) (GRID_SIZE*(j -DIM/2.0)),
                GRID_SIZE, GRID_SIZE, wallSprites);
        ;

        if (j ==0){

            if (i ==0) {
                thisWall.setSprite(SpriteType.BACK_LEFT);
            }
            else if (i ==DIM-1) {
                thisWall.setSprite(SpriteType.BACK_RIGHT);
            }
            else { thisWall.setSprite(SpriteType.BACK); }
        }

        else if (j == DIM-1){
            if (i == 0){
                thisWall.setSprite(SpriteType.FRONT_LEFT);
            }
            else if (i ==DIM-1){
                thisWall.setSprite(SpriteType.FRONT_RIGHT);
            }
            else {thisWall.setSprite(SpriteType.FRONT); }
        }

        else if (i == 0){
            thisWall.setSprite(SpriteType.LEFT);
        }

        else {thisWall.setSprite(SpriteType.RIGHT);}
        return thisWall;
    }

    private Door getDoor(int j, int i){
        Door thisDoor = new Door((int) (GRID_SIZE*(i -DIM/2.0)),(int) (GRID_SIZE*(j -DIM/2.0)),
                GRID_SIZE, GRID_SIZE, doorSprites, DOOR_SIZE);

        if (j ==0){
            thisDoor.getTopDoorFrame().setSprite(SpriteType.BACK);
            thisDoor.getBottomDoorFrame().setSprite(SpriteType.FRONT);
        }

        else if (j == DIM-1){
            thisDoor.getTopDoorFrame().setSprite(SpriteType.LEFT);
            thisDoor.getBottomDoorFrame().setSprite(SpriteType.RIGHT);
        }

        else if (i == 0){
            //TODO fix: first "LEFT" to "BACK" when resized back door
            thisDoor.getTopDoorFrame().setSprite(SpriteType.LEFT);
            thisDoor.getBottomDoorFrame().setSprite(SpriteType.LEFT);
        }

        else {
            thisDoor.getTopDoorFrame().setSprite(SpriteType.FRONT);
            thisDoor.getBottomDoorFrame().setSprite(SpriteType.RIGHT);
        }
        return thisDoor;
    }

    public Room getActiveRoom() {
        return activeRoom;
    }
}