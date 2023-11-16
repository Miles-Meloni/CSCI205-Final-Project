/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:31 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.controller
 * Class: GameController
 *
 * Description:
 * The Controller of our JavaFX application.
 * ****************************************
 */

package org.cscigroup3project.MVC.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import org.cscigroup3project.MVC.model.*;
import org.cscigroup3project.MVC.view.GameView;

import java.util.ArrayList;

/**
 * The class implementing the logic and user interactions for the created game.
 */
public class GameController {

    /** The {@link GameModel} for our app */
    private final GameModel theModel;

    /** The {@link GameView} for our app */
    private final GameView theView;

    /** The {@link Scene} for our app */
    private final Scene theScene;

    /** An array that has hard coded textbox values.
     * #TODO remove hardcoding */
    private ArrayList<String> textArray;

    /** The arraylist that looks at our player data, and displays it. */
    private ArrayList<String> localArray;

    /**
     * Constructor for the GameController.
     *
     * @param theModel the game's model
     * @param theView the game's view
     * @param theScene the JavaFX scene
     */
    public GameController(GameModel theModel, GameView theView, Scene theScene) {
        this.theModel = theModel;
        this.theView = theView;
        this.theScene = theScene;
        this.textArray = new ArrayList<>();
        this.textArray.add("Have some text");
        this.textArray.add("Have some more");

        initBindings();
        initEventHandlers();
    }

    /**
     * Initialize event handlers for keyboard input.
     */
    private void initEventHandlers() {
        this.theScene.setOnKeyPressed(this::handleKeyPress);
    }

    /**
     * Initialize property bindings between the player's position and the image view's position.
     */
    private void initBindings() {

        // Bind the PlayerView's position and image to the Player's x, y, and image properties
        theView.getPlayerView().translateXProperty().bind(theModel.getPlayer().getxProperty());
        theView.getPlayerView().translateYProperty().bind(theModel.getPlayer().getyProperty());
        theView.getPlayerView().imageProperty().bind(theModel.getPlayer().playerImageProperty());

        //TODO remove; debugging only

    }

    /**
     * Handle key press events for player movement.
     *
     * @param event The {@link KeyEvent} generated by a key press.
     */
    public void handleKeyPress(KeyEvent event) {

        // Match the arrow key input, move the Player in the corresponding direction.
        // Check for collisions after each movement, if there is, move in the opposite direction
        if(!theView.isTextboxVisible()) {
            switch (event.getCode()) {
                case DOWN -> { theModel.getPlayer().move(Direction.DOWN); }
                case LEFT -> { theModel.getPlayer().move(Direction.LEFT); }
                case UP -> { theModel.getPlayer().move(Direction.UP); }
                case RIGHT -> { theModel.getPlayer().move(Direction.RIGHT); }
                case C -> { findItem(); }
                case E -> { toggleInventory(); }
                case Q -> {
                    localArray = new ArrayList<>(textArray);
                    updateTextbox();
                } //TODO for full functionality get boxes from user
            }

        }
        else{ updateTextbox();}

        for (Wall wall : theModel.getRoomManager().getActiveRoom().getWalls()) {
            if (theModel.getPlayer().getBounds().getBoundsInLocal().intersects(
                    wall.getBounds().localToParent(wall.getBounds().getBoundsInLocal()))) {
                switch (event.getCode()) {
                    case DOWN -> {
                        theModel.getPlayer().silentMove(Direction.UP);
                    }
                    case LEFT -> {
                        theModel.getPlayer().silentMove(Direction.RIGHT);
                    }
                    case UP -> {
                        theModel.getPlayer().silentMove(Direction.DOWN);
                    }
                    case RIGHT -> {
                        theModel.getPlayer().silentMove(Direction.LEFT);
                    }
                }
            }
        }
    }

    private void updateTextbox() {
        if (localArray.isEmpty()){
            theView.setTextboxVisibility(false);
        }
        else{
            theView.setTextboxVisibility(true);
            theView.setTextboxText(localArray.get(0));
            localArray.remove(0);

        }
    }

    /**
     * Find an item in range, if it exists, among all items in the active room
     */
    private void findItem(){

        boolean found = false;
        int index = 0;

        // while loop because we only want to pick up a single item
        while (!found && index < theModel.getRoomManager().getActiveRoom().getItemObjects().size()){

            // set the current object and increment index
            GameObject object = theModel.getRoomManager().getActiveRoom().getItemObjects().get(index);
            index++;

            // check for intersection; if there, pick it up!
            if (theModel.getPlayer().getReach().getBoundsInLocal().intersects(
                    object.getBounds().localToParent(object.getBounds().getBoundsInLocal()))) {

                pickUpItem(object);
                found = true;

            }
        }

    }

    /**
     * Pick up the specified item by notifying Player and Room
     * @param object the object to be picked up
     */
    private void pickUpItem(GameObject object){

        theModel.getPlayer().pickUpItem(object);
        theModel.getRoomManager().getActiveRoom().removeObject(object);

        ImageView objectView = new ImageView(object.getSprite());

        theView.getInventoryPane().add(objectView,0, theModel.getPlayer().getInventory().size());

        for (ImageView imgView : theView.getAllViews()){
            if (imgView.getImage() == object.getSprite()){
                imgView.setVisible(false);
            }
        }

    }

    /**
     * Turn the inventory on or off, based on current state
     */
    private void toggleInventory(){
        if (theView.getInventoryPane().isVisible()){
            theView.getInventoryPane().setVisible(false);
        }
        else {
            theView.getInventoryPane().setVisible(true);
        }
    }
}

