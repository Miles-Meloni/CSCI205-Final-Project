/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/4/2023
 * Time: 10:05 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Player
 *
 * Description: Basic class containing a player object
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class representing a player object in the game.
 */
public class Player {

    /** Store the position of the player */
    private double xPos;
    private double yPos;
    private SimpleDoubleProperty xProperty;
    private SimpleDoubleProperty yProperty;

    /** The texture of the player */
    private Texture texture;
    /** The current state of the player */
    private PlayerState state;
    /** The name of the player */
    private String name;
    /** Inventory for the player */
    private Inventory inventory;

    /** Direction of the player*/
    private Direction direction;

    /**Player sprites array */
    private ArrayList<Texture> textures;

    /** player move speed constant*/
    private final int MOVE_SPEED = 3;

    /** current sprite for the player */
    private int curSprite;

    /**position in the array for left idle sprites */
    private int POS_IDLE_LEFT = 0;

    /**position in the array for right idle sprites */
    private int POS_IDLE_RIGHT = 4;

    /**position in the array for left move sprites */
    private int POS_MOVE_LEFT = 8;

    /**position in the array for right move sprites */
    private int POS_MOVE_RIGHT = 12;

    /**position in the array for down idle sprites */
    private int POS_IDLE_DOWN = 16;

    /**position in the array for down moving sprites */
    private int POS_MOVE_DOWN = 20;

    /**position in the array for down idle sprites */
    private int POS_IDLE_UP = 24;

    /**position in the array for down moving sprites */
    private int POS_MOVE_UP = 28;


    /**
     * Default constructor for a player.
     */
    public Player() {
        xPos = 0;
        yPos = 0;
        xProperty = new SimpleDoubleProperty(xPos);
        yProperty = new SimpleDoubleProperty(yPos);
        texture = null;
        state = PlayerState.IDLE;
        name = "";
        inventory = new Inventory();
        direction = Direction.DOWN;

        generateSprites();

        changeSpriteTo(0);
        //updateSpriteInMs(100);


    }

    /**
     * Constructor for a player with specified initial position, texture, and name.
     *
     * @param position The initial position of the player.
     * @param name     The name of the player.
     */
    public Player(double[] position, String name) {
        xPos = position[0];
        yPos = position[1];
        xProperty = new SimpleDoubleProperty(xPos);
        yProperty = new SimpleDoubleProperty(yPos);
        this.texture = texture;
        state = PlayerState.IDLE;
        this.name = name;
        inventory = new Inventory();

        generateSprites();

        changeSpriteTo(0);
        //updateSpriteInMs(100);
    }

    private void generateSprites() {
        //Sprite setup:
        textures = new ArrayList<>();
        for (int i = 1; i < 32; i++){
            String imageURL = "cscigroup3project/Sprites/PS" + i +".png";
            Image newSprite = new Image(imageURL);
            Texture newTexture = new Texture(newSprite);
            textures.add(newTexture);
        }

    }

    /**
     * Have the player enter an object or inventory (changing the sprite).
     */
    public void enterInterface() {
        this.state = PlayerState.EXPLORING;
    }

    /**
     * Move the player in the specified direction.
     *
     * @param movementDirection The direction in which to move.
     */
    public void move(Direction movementDirection) {
        state = PlayerState.MOVING;
        if (movementDirection == Direction.UP) {
            moveUp();
        } else if (movementDirection == Direction.DOWN) {
            moveDown();
        } else if (movementDirection == Direction.LEFT) {
            moveLeft();
        } else {
            moveRight();
        }
    }

    public void updateSpriteInMs(long timeInMs){
        //repeat update over and over
        do {
            Runnable r = () -> {
                try {
                    int moveBy = 1;
                    //first we check if it is the 4th sprite,
                    // if we do we go back 3 instead of moving forward 1
                    if (curSprite % 4 == 3) {
                        moveBy = -3;
                    }
                    if (state == PlayerState.MOVING) {
                        if (direction == Direction.LEFT) {
                            if (curSprite >= POS_MOVE_LEFT & curSprite <= POS_MOVE_LEFT + 3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_MOVE_LEFT);
                        }
                        if (direction == Direction.RIGHT) {
                            if (curSprite >= POS_MOVE_RIGHT & curSprite <= POS_MOVE_RIGHT+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_MOVE_RIGHT);
                        }
                        if (direction == Direction.DOWN) {
                            if (curSprite >= POS_MOVE_DOWN & curSprite <= POS_MOVE_DOWN + 3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_MOVE_DOWN);
                        }
                        if (direction == Direction.UP) {
                            if (curSprite >= POS_MOVE_UP & curSprite <= POS_MOVE_UP+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_MOVE_UP);
                        }
                    } else {
                        if (direction == Direction.LEFT) {
                            if (curSprite >= POS_IDLE_LEFT & curSprite <= POS_IDLE_LEFT+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_IDLE_LEFT);
                        }
                        if (direction == Direction.RIGHT) {
                            if (curSprite >= POS_IDLE_RIGHT & curSprite <= POS_IDLE_RIGHT+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_IDLE_RIGHT);
                        }
                        if (direction == Direction.DOWN) {
                            if (curSprite >= POS_IDLE_DOWN & curSprite <= POS_IDLE_DOWN+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_IDLE_DOWN);
                        }
                        if (direction == Direction.UP) {
                            if (curSprite >= POS_IDLE_UP & curSprite <= POS_IDLE_UP+3) {
                                changeSpriteTo(curSprite + moveBy);
                            } else changeSpriteTo(POS_IDLE_UP);
                        }
                    }

                    Thread.sleep(timeInMs);
                } catch (InterruptedException e) {
                }
            };

            //encapsulate thread
            Thread t = new Thread(r);
            t.start();

        }while(true);
    }

    /**
     * Changes the player texture and updates the current sprite value simultaneously.
     * @param spriteVal - the index in the sprite array of the new sprite assigned.
     */
    public void changeSpriteTo(int spriteVal){
        curSprite = spriteVal;
        setTexture(textures.get(spriteVal));
    }

    /**
     * Move the player up by one unit and update the corresponding property.
     */
    private void moveUp() {
        yPos -= MOVE_SPEED ;
        yProperty.set(yPos);
    }

    /**
     * Move the player down by one unit and update the corresponding property.
     */
    private void moveDown() {
        yPos += MOVE_SPEED ;
        yProperty.set(yPos);
    }

    /**
     * Move the player left by one unit and update the corresponding property.
     */
    private void moveLeft() {
        xPos -= MOVE_SPEED ;
        xProperty.set(xPos);
    }

    /**
     * Move the player right by one unit and update the corresponding property.
     */
    private void moveRight() {
        xPos += MOVE_SPEED ;
        xProperty.set(xPos);
    }

    /**
     * Set the name of the player.
     *
     * @param name The name to set for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the state of the player.
     *
     * @param state The state to set for the player.
     */
    public void setState(PlayerState state) {
        this.state = state;
    }

    /**
     * Get the current state of the player.
     *
     * @return The current state of the player.
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Set the texture of the player.
     *
     * @param texture The texture to set for the player.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Get the texture of the player.
     *
     * @return The texture of the player.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Set the position of the player using an array of coordinates.
     *
     * @param position An array containing the x and y coordinates to set for the player.
     */
    public void setPosition(double[] position) {
        xPos = position[0];
        yPos = position[1];
    }

    /**
     * Get the current position of the player as an array of coordinates.
     *
     * @return An array of coordinates representing the player's position.
     */
    public double[] getPosition() {
        return new double[]{xPos, yPos};
    }

    /**
     * Get the x-coordinate of the player's position.
     *
     * @return The x-coordinate of the player's position.
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Get the y-coordinate of the player's position.
     *
     * @return The y-coordinate of the player's position.
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Get the x-coordinate property for binding to JavaFX elements.
     *
     * @return The x-coordinate property.
     */
    public SimpleDoubleProperty getxProperty() {
        return xProperty;
    }

    /**
     * Get the y-coordinate property for binding to JavaFX elements.
     *
     * @return The y-coordinate property.
     */
    public SimpleDoubleProperty getyProperty() {
        return yProperty;
    }


    /**
     * Get the inventory of the player
     * @return the inventory arraylist of the player
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Have the player pick up an item and add it to their inventory.
     *
     * @param item The item to be added to the player's inventory.
     */
    public void pickUpItem(Object item) {
        inventory.addItem(item);
    }

    /**
     * Remove an item from the player's inventory.
     *
     * @param item The item to be removed from the player's inventory.
     * @return True if the item was successfully removed, false otherwise.
     */
    public boolean getRidOfItem(Object item) {
        return inventory.removeItemByObject(item);
    }
}
