/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/5/2023
 * Time: 6:33 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Door
 *
 * Description: Basic class for a door object in the game
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.GameObject;

import java.util.ArrayList;

public class Door extends GameObject{

    private DoorFrame topDoorFrame;
    private DoorFrame bottomDoorFrame;
    private ArrayList<Doorway> doorways;
    private static final int GRID_SIZE = 16;

    /**
     * Constructs a new Door using a position and dimensions.
     *
     * @param minX   the X coordinate of the upper-left corner
     * @param minY   the Y coordinate of the upper-left corner
     * @param w  the width of the {@code bounds}
     * @param h the height of the {@code bounds}
     */
    public Door(int minX, int minY, int w, int h, ArrayList<Image> sprites, int doorSize, DoorType doorType) {

        super(minX, minY, w, h, sprites);
        this.topDoorFrame = new DoorFrame(minX, minY, w, h, sprites);

        doorways = new ArrayList<>();

        generateDoor(minX, minY, w, h, sprites, doorSize, doorType);

    }

    private void generateDoor(int minX, int minY, int w, int h, ArrayList<Image> sprites, int doorSize, DoorType doorType) {
        if (doorType == DoorType.HORIZONTAL){
            this.bottomDoorFrame = new DoorFrame(minX + GRID_SIZE* doorSize, minY, w, h, sprites);
            for (int i = 1; i < doorSize; i++){
                Doorway doorway = new Doorway(minX + GRID_SIZE*(i), minY, w, h, sprites);
                doorways.add(doorway);
            }
        }
        else {
            this.bottomDoorFrame = new DoorFrame(minX, minY + GRID_SIZE* doorSize, w, h, sprites);
            for (int i = 1; i < doorSize; i++){
                Doorway doorway = new Doorway(minX, minY + GRID_SIZE*(i), w, h, sprites);
                doorways.add(doorway);
            }
        }
    }


    public void lockDoor(){
        for (Doorway doorway : doorways){
            doorway.setIsLocked(true);
        }
    }

    public void unlockDoor(){
        for (Doorway doorway : doorways){
            doorway.setIsLocked(false);
        }
    }

    public ArrayList<Doorway> getDoorways() {
        return doorways;
    }

    public DoorFrame[] getDoorFrames() { return new DoorFrame[]{topDoorFrame, bottomDoorFrame}; }

    public DoorFrame getTopDoorFrame() {
        return topDoorFrame;
    }

    public DoorFrame getBottomDoorFrame() {
        return bottomDoorFrame;
    }
}
