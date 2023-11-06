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
    }

    /**
     * Constructor for a player with specified initial position, texture, and name.
     *
     * @param position The initial position of the player.
     * @param texture  The texture of the player.
     * @param name     The name of the player.
     */
    public Player(double[] position, Texture texture, String name) {
        xPos = position[0];
        yPos = position[1];
        xProperty = new SimpleDoubleProperty(xPos);
        yProperty = new SimpleDoubleProperty(yPos);
        this.texture = texture;
        state = PlayerState.IDLE;
        this.name = name;
        inventory = new Inventory();
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

    /**
     * Move the player up by one unit and update the corresponding property.
     */
    private void moveUp() {
        yPos -= 4;
        yProperty.set(yPos);
    }

    /**
     * Move the player down by one unit and update the corresponding property.
     */
    private void moveDown() {
        yPos += 4;
        yProperty.set(yPos);
    }

    /**
     * Move the player left by one unit and update the corresponding property.
     */
    private void moveLeft() {
        xPos -= 4;
        xProperty.set(xPos);
    }

    /**
     * Move the player right by one unit and update the corresponding property.
     */
    private void moveRight() {
        xPos += 4;
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
