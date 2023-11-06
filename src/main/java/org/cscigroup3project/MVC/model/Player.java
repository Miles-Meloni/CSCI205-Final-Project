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

public class Player {

    /** Store the position of the player */
    private int[] position;
    /** The texture of the player */
    private Texture texture;
    /** The current state of the player */
    private PlayerState state;
    /** The name of the player */
    private String name;
    /** Inventory for the player */
    private Inventory inventory;


    /**
     * Default constructor
     */
    public Player(){
        position = new int[]{0,0};
        texture = null;
        state = PlayerState.IDLE;
        name = "";
        inventory = new Inventory();
    }

    /**
     * Full constructor
     */
    public Player(int[] position, Texture texture, String name){
        this.position = position;
        this.texture = texture;
        state = PlayerState.IDLE;
        this.name = name;
        inventory = new Inventory();
    }

    /**
     * Have the player enter an object / inventory
     * (so its sprite is changed)
     */
    public void enterInterface(){
        this.state = PlayerState.EXPLORING;
    }

    /**
     * Have the player move
     */
    public void move(Direction movementDirection){
        state = PlayerState.MOVING;
        if (movementDirection == Direction.UP){
            moveUp();
        }
        else if (movementDirection == Direction.DOWN){
            moveDown();
        }
        else if (movementDirection == Direction.LEFT){
            moveLeft();
        }
        else {
            moveRight();
        }
    }

    public void moveUp(){
        this.position[1] -= 1;
    }

    public void moveDown(){
        this.position[1] += 1;
    }

    public void moveLeft(){
        this.position[0] -= 1;
    }

    public void moveRight(){
        this.position[0] += 1;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getState() {
        return state;
    }


    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }


    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }


    public void pickUpItem(Object item){
        inventory.addItem(item);
    }

    public boolean getRidOfItem(Object item){
        return inventory.removeItemByObject(item);
    }
}
