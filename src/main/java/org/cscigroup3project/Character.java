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
 * Class: Character
 *
 * Description: Basic class containing a character object
 *
 * ****************************************
 */
package org.cscigroup3project;

public class Character {

    /** Store the position of the character */
    private int[] position;
    /** The texture of the character */
    private Texture texture;
    /** The current state of the character */
    private CharacterState state;
    /** The name of the character */
    private String name;
    /** Inventory for the character */
    private Inventory inventory;


    /**
     * Default constructor
     */
    public Character(){
        position = new int[]{0,0};
        texture = null;
        state = CharacterState.IDLE;
        name = "";
    }

    /**
     * Full constructor
     */
    public Character(int[] position, Texture texture, String name){
        this.position = position;
        this.texture = texture;
        state = CharacterState.IDLE;
        this.name = name;
    }

    /**
     * Have the character enter a puzzle
     * (so its sprite is changed)
     */
    public void enterPuzzle(){
        this.state = CharacterState.PUZZLING;
    }

    /**
     * Have the character move
     */
    public void move(Direction movementDirection){
        state = CharacterState.MOVING;
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

    public void setState(CharacterState state) {
        this.state = state;
    }

    public CharacterState getState() {
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
}
