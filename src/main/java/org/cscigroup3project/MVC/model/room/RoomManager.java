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
import org.cscigroup3project.MVC.model.gameObject.*;

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

    /** ArrayList for wall images, for now */
    private ArrayList<Image> wallSprites;

    /** Single image for floor sprite */
    private ArrayList<Image> floorSprites;

    /** ArrayList for door images, for now */
    private ArrayList<Image> doorSprites;

    private static final int DOOR_SIZE = 4;

    /**
     * Creates a RoomManager object.
     */
    public RoomManager() {

        // add the sprites for the walls
        wallSprites = new ArrayList<>();
        addWallSprites();

        // add the sprites for the door
        doorSprites = new ArrayList<>();
        addDoorSprites();

        // add the sprite for the floor
        floorSprites = new ArrayList<>();
        floorSprites.add(new Image("cscigroup3project/roomTiles/Floor.png"));

        // generate the rooms for the game
        rooms = new ArrayList<>();

        // Door sizes for room 1 here
        ArrayList<Integer> doorXs = new ArrayList<>();
        doorXs.add(5);
        doorXs.add(DIM-1);

        ArrayList<Integer> doorYs = new ArrayList<>();
        doorYs.add(DIM-1);
        doorYs.add(8);

        generateRooms(doorXs, doorYs);
    }

    /**
     * Adds the door sprites to the {@link ArrayList} of door sprites
     */
    private void addDoorSprites() {
        File dir;
        File[] directoryListing;
        dir = new File("src/main/resources/cscigroup3project/roomTiles/doorTiles");
        directoryListing = dir.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing
            ) {
                if (child.getName().contains("Door")){
                    doorSprites.add(new Image("cscigroup3project/roomTiles/doorTiles/" + child.getName()));
                }
            }
        }
    }

    /**
     * Adds the wall sprites to the {@link ArrayList} of wall sprites
     */
    private void addWallSprites() {
        File dir = new File("src/main/resources/cscigroup3project/roomTiles/wallTiles/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing
            ) {
                if (child.getName().contains("Wall")){
                    wallSprites.add(new Image("cscigroup3project/roomTiles/wallTiles/" + child.getName()));
                }
            }
        }
    }

    /**
     * Generates {@link Room} objects
     */
    private void generateRooms(ArrayList<Integer> doorXs, ArrayList<Integer> doorYs) {

        // Generate the first Room code
        ArrayList<ArrayList<GameObject>> code1 = new ArrayList<>();
        initializeCode(code1);

        generateComponents(code1, doorXs, doorYs);

        Room room1 = new Room(code1); // pass in code1 once Room constructor is updated
        rooms.add(room1);

        //room1 active room by default
        activeRoom = room1;
    }


    private void initializeCode(ArrayList<ArrayList<GameObject>> code1) {
        for (int i = 0; i < DIM; i++) {
            code1.add(new ArrayList<>());
        }
    }

    /**
     * Generates the components of the room
     * @param code1 the code for the room
     */
    private void generateComponents(ArrayList<ArrayList<GameObject>> code1, ArrayList<Integer> doorXs, ArrayList<Integer> doorYs){

        ArrayList<Integer> doorXEnds = new ArrayList<>();
        ArrayList<Integer> doorYEnds = new ArrayList<>();
        ArrayList<Door> theseDoors = new ArrayList<>();

        // TODO: temporary hardcode
        for (int i = 0; i < doorXs.size(); i++) {
            if (doorXs.get(i) == 0 || doorXs.get(i) == DIM-1){
                doorXEnds.add(doorXs.get(i));
                doorYEnds.add(doorYs.get(i) + DOOR_SIZE);
            }
            else{
                doorXEnds.add(doorXs.get(i) + DOOR_SIZE);
                doorYEnds.add(doorYs.get(i));
            }
        }


        // Loop through every position in the Room code
        // Looping through y-positions
        for (int i = 0; i < DIM; i++) {
            // Looping through x-positions
            for (int j = 0; j < DIM; j++) {

                // reset whether the position is a door
                boolean isDoor = false;

                // iterate through the door starting positions
                for (int k = 0; k < doorXs.size(); k++) {

                    int doorX = doorXs.get(k);
                    int doorY = doorYs.get(k);

                    // Add a Door frame if at right coordinates
                    if (j == doorY && i == doorX) {
                        Door thisDoor = getDoor(i, j);
                        theseDoors.add(thisDoor);
                        code1.get(j).add(thisDoor.getTopDoorFrame());

                        isDoor = true;
                    }

                }
                for (int k = 0; k < doorXEnds.size(); k++) {


                    // Add a Door frame if at right coordinates
                    if (j == doorYEnds.get(k) && i == doorXEnds.get(k)){
                        code1.get(j).add(theseDoors.get(k).getBottomDoorFrame());
                        isDoor = true;
                    }

                    // Add a Door fade if at right coordinates
                    else if (j == doorYEnds.get(k) && doorXEnds.get(k) != 0 && i > doorXs.get(k) && i < doorXEnds.get(k)) {
                        code1.get(j).add(theseDoors.get(k).getDoorways().get(i - doorXs.get(k) - 1));
                        isDoor = true;
                    }

                    // Add a Door fade if at right coordinates
                    else if (i == doorXEnds.get(k) && doorYEnds.get(k) != 0 && j > doorYs.get(k) && j < doorYEnds.get(k)) {
                        code1.get(j).add(theseDoors.get(k).getDoorways().get(j - doorYs.get(k) - 1));
                        System.out.println("ADDING: " + theseDoors.get(k).getDoorways().get(j - doorYs.get(k) - 1).getBounds());
                        isDoor = true;
                    }

                }

                if (!isDoor) {
                    // Add a Wall around the square border otherwise
                    if ((j==0) || (j==DIM-1) || (i==0) || (i==DIM-1)) {
                        Wall thisWall = getWall(i, j);
                        code1.get(j).add(thisWall);
                    }
                    // Add a Floor to the center
                    else {
                        code1.get(j).add(new GameObject((int) (GRID_SIZE*(i-DIM/2.0)),(int) (GRID_SIZE*(j-DIM/2.0)),
                                GRID_SIZE, GRID_SIZE, floorSprites)); // floor.png
                    };
                }
            }
        }
    }

    /**
     * Gets the wall for the room
     * @param j the y position
     * @param i the x position
     * @return the wall
     */
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

    /**
     * Gets the door for the room
     * @param j the y position
     * @param i the x position
     * @return the door
     */
    private Door getDoor(int j, int i){
        DoorType doorType;
        if (i == 0 || i == DIM-1){
            doorType = DoorType.VERTICAL;
        }
        else {
            doorType = DoorType.HORIZONTAL;
        }
        Door thisDoor = new Door((int) (GRID_SIZE*(i -DIM/2.0)),(int) (GRID_SIZE*(j -DIM/2.0)),
                GRID_SIZE, GRID_SIZE, doorSprites, DOOR_SIZE, doorType);

        if (j ==0){
            thisDoor.getTopDoorFrame().setSprite(SpriteType.BACK_RIGHT);

            for (Doorway doorway : thisDoor.getDoorways()) {
                doorway.setSprite(SpriteType.BACK);
            }

            thisDoor.getBottomDoorFrame().setSprite(SpriteType.BACK_LEFT);
        }

        else if (j == DIM-1){
            thisDoor.getTopDoorFrame().setSprite(SpriteType.FRONT_LEFT);

            for (Doorway doorway : thisDoor.getDoorways()) {
                doorway.setSprite(SpriteType.FRONT);
            }

            thisDoor.getBottomDoorFrame().setSprite(SpriteType.FRONT_RIGHT);
        }

        else if (i == 0){
            thisDoor.getTopDoorFrame().setSprite(SpriteType.BACK_RIGHT);

            for (Doorway doorway : thisDoor.getDoorways()) {
                doorway.setSprite(SpriteType.LEFT);
            }

            thisDoor.getBottomDoorFrame().setSprite(SpriteType.FRONT_LEFT);
        }

        else {
            thisDoor.getTopDoorFrame().setSprite(SpriteType.BACK_LEFT);

            for (Doorway doorway : thisDoor.getDoorways()) {
                doorway.setSprite(SpriteType.RIGHT);
            }

            thisDoor.getBottomDoorFrame().setSprite(SpriteType.FRONT_RIGHT);
        }
        return thisDoor;
    }

    /**
     * Getter for the {@link ArrayList} of {@link Room}s
     * @return the {@link ArrayList} of {@link Room}s
     */
    public Room getActiveRoom() {
        return activeRoom;
    }
}