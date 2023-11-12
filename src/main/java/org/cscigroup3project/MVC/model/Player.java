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
 * Description:
 * Basic class containing a Player object
 * ****************************************
 */

package org.cscigroup3project.MVC.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 * A class representing a player object in the game.
 */
public class Player {

    /** The dimensions of the Player */
    private final int WIDTH = 37;
    private final int HEIGHT = 33;

    /** The reach of the Player */
    private final int REACH = 10;

    /** Store the position of the Player */
    private int xPos;
    private int yPos;
    private SimpleIntegerProperty xProperty;
    private SimpleIntegerProperty yProperty;

    /** The texture of the Player */
    private Image image;

    /** Observable value for the Player's image/ sprite */
    private ObjectProperty<Image> playerImage;

    /** The current state of the Player */
    private PlayerState state;

    /** The name of the Player */
    private String name;

    /** Inventory for the Player */
    private Inventory inventory;

    /** Collision {@link Rectangle} of the Player */
    private Rectangle bounds;

    /** Interacting {@link Rectangle} of the Player */
    private Rectangle reach;

    /** Direction of the Player */
    private Direction direction;

    /** Player sprites array */
    private ArrayList<Image> images;

    /** Player move speed constant*/
    private final int MOVE_SPEED = 3;

    /** Current sprite for the player */
    private int curSprite = 0;

    /** Position in the array for left idle sprites */
    private final int POS_IDLE_LEFT = 0;

    /** Position in the array for right idle sprites */
    private final int POS_IDLE_RIGHT = 4;

    /** Position in the array for left move sprites */
    private final int POS_MOVE_LEFT = 8;

    /** Position in the array for right move sprites */
    private final int POS_MOVE_RIGHT = 12;

    /** Position in the array for down idle sprites */
    private final int POS_IDLE_DOWN = 16;

    /** Position in the array for down moving sprites */
    private final int POS_MOVE_DOWN = 20;

    /** Position in the array for down idle sprites */
    private final int POS_IDLE_UP = 24;

    /** Position in the array for down moving sprites */
    private final int POS_MOVE_UP = 28;

    /** Total number of sprites */
    private final int NUM_SPRITES = 32;

    /**
     * Constructor for a player with specified initial position, texture, and name.
     *
     * @param position The initial position of the Player.
     * @param name     The name of the Player.
     * @param imageURL The String representing the Player sprite.
     */
    public Player(int[] position, String name, String imageURL) {
        this.xPos = position[0];
        this.yPos = position[1];
        this.xProperty = new SimpleIntegerProperty(xPos);
        this.yProperty = new SimpleIntegerProperty(yPos);
        this.state = PlayerState.IDLE;
        this.name = name;
        this.inventory = new Inventory();

        //this.playerImage = new PlayerObjectBinding<Image>(image);
        this.playerImage = new SimpleObjectProperty<>(image);
        generateSprites(imageURL);
        changeSpriteTo(0);

        // Initialize Player collision and reach bounds. Shift left and down by half of width floored
        int startX = WIDTH * -1 / 2;
        int startY = HEIGHT * -1 / 2;
        this.bounds = new Rectangle(startX, startY, WIDTH, HEIGHT); // this appears to be pixel-tight to sprite
        this.reach = new Rectangle(startX, startY, WIDTH + REACH, HEIGHT + REACH);
    }

    /**
     * Loop through all .png images used for the sprites
     * @param imageURL the URL (without image number and extension)
     *                 to be used for all the sprites
     */
    private void generateSprites(String imageURL) {
        //Sprite setup:
        images = new ArrayList<>();
        // loop through all 32 sprites
        for (int i = 1; i <= NUM_SPRITES; i++){
            Image newSprite = new Image(imageURL + i + ".png");
            images.add(newSprite);
        }
    }

    /**
     * Have the player enter an object or inventory (changing the sprite).
     */
    public void enterInterface() {
        this.state = PlayerState.EXPLORING;
    }

    /**
     * Move the player in the specified direction, and change its sprite.
     *
     * @param movementDirection The direction in which to move.
     */
    public void move(Direction movementDirection) {

        // Set the PlayerState to MOVING
        state = PlayerState.MOVING;

        // Match the direction the Player is moving, move them and update the sprite
        switch (movementDirection) {
            case UP -> { moveUp(); changeSpriteTo(POS_MOVE_UP); }
            case DOWN -> { moveDown(); changeSpriteTo(POS_MOVE_DOWN); }
            case LEFT -> { moveLeft(); changeSpriteTo(POS_MOVE_LEFT); }
            case RIGHT -> { moveRight(); changeSpriteTo(POS_MOVE_RIGHT); }
        }
    }

    /**
     * Move the player in the specified direction, and DO NOT CHANGE its sprite.
     *
     * @param movementDirection The direction in which to move.
     */
    public void silentMove(Direction movementDirection) {

        // Set the PlayerState to MOVING
        state = PlayerState.MOVING;

        // Match the direction the Player is moving, move them and update the sprite
        switch (movementDirection) {
            case UP -> { moveUp();  }
            case DOWN -> { moveDown(); }
            case LEFT -> { moveLeft(); }
            case RIGHT -> { moveRight(); }
        }
    }

    //TODO This needs redone
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
        setImage(images.get(spriteVal)); }


    /**
     * Move the player up by one unit and update the corresponding property.
     */
    private void moveUp() {
        yPos -= MOVE_SPEED ;
        yProperty.set(yPos);
        bounds.setY(bounds.getY() - MOVE_SPEED);
    }

    /**
     * Move the player down by one unit and update the corresponding property.
     */
    private void moveDown() {
        yPos += MOVE_SPEED ;
        yProperty.set(yPos);
        bounds.setY(bounds.getY() + MOVE_SPEED);
    }

    /**
     * Move the player left by one unit and update the corresponding property.
     */
    private void moveLeft() {
        xPos -= MOVE_SPEED ;
        xProperty.set(xPos);
        bounds.setX(bounds.getX() - MOVE_SPEED);
    }

    /**
     * Move the player right by one unit and update the corresponding property.
     */
    private void moveRight() {
        xPos += MOVE_SPEED ;
        xProperty.set(xPos);
        bounds.setX(bounds.getX() + MOVE_SPEED);
    }

    /**
     * Set the name of the player.
     *
     * @param name The name to set for the player.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Get the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() { return name; }

    /**
     * Set the state of the player.
     *
     * @param state The state to set for the player.
     */
    public void setState(PlayerState state) { this.state = state; }

    /**
     * Get the current state of the player.
     *
     * @return The current state of the player.
     */
    public PlayerState getState() { return state; }

    /**
     * Set the texture of the player.
     *
     * @param image The texture to set for the player.
     */
    public void setImage(Image image) { this.image = image; this.playerImage.setValue(image); }

    /**
     * Get the texture of the player.
     *
     * @return The texture of the player.
     */
    public Image getImage() { return image; }

    /**
     * Set the position of the player using an array of coordinates.
     *
     * @param position An array containing the x and y coordinates to set for the player.
     */
    public void setPosition(int[] position) { xPos = position[0]; yPos = position[1]; }

    /**
     * Get the current position of the player as an array of coordinates.
     *
     * @return An array of coordinates representing the player's position.
     */
    public int[] getPosition() { return new int[]{xPos, yPos}; }

    /**
     * Get the x-coordinate of the player's position.
     *
     * @return The x-coordinate of the player's position.
     */
    public int getxPos() { return xPos; }

    /**
     * Get the y-coordinate of the player's position.
     *
     * @return The y-coordinate of the player's position.
     */
    public int getyPos() { return yPos; }

    /**
     * Get the x-coordinate property for binding to JavaFX elements.
     *
     * @return The x-coordinate property.
     */
    public SimpleIntegerProperty getxProperty() { return xProperty; }

    /**
     * Get the y-coordinate property for binding to JavaFX elements.
     *
     * @return The y-coordinate property.
     */
    public SimpleIntegerProperty getyProperty() { return yProperty; }

    /**
     * Have the player pick up an item and add it to their inventory.
     *
     * @param item The item to be added to the player's inventory.
     */
    public void pickUpItem(Object item) { inventory.addItem(item); }

    /**
     * Remove an item from the player's inventory.
     *
     * @param item The item to be removed from the player's inventory.
     * @return True if the item was successfully removed, false otherwise.
     */
    public boolean getRidOfItem(Object item) { return inventory.removeItemByObject(item); }

    public Inventory getInventory() { return inventory; }

    public Image getPlayerImage() { return playerImage.get(); }

    public ObjectProperty<Image> playerImageProperty() { return playerImage; }

    /** Getter for the {@link Rectangle} representing the Player collision bounds */
    public Rectangle getBounds() { return bounds; }

    /** Getter for the {@link Rectangle} representing the Player reach bounds */
    public Rectangle getReach() { return reach; }

    /** Getter for the Player's movement speed */
    public int getMOVE_SPEED() { return MOVE_SPEED; }
}
