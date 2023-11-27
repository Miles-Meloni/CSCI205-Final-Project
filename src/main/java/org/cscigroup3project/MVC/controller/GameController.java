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
import javafx.scene.media.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.*;
import org.cscigroup3project.MVC.model.gameObject.GameObject;
import org.cscigroup3project.MVC.model.gameObject.Wall;
import org.cscigroup3project.MVC.model.player.Direction;
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

    // TODO: likely needs moved elsewhere
    /** Unselected and selected rectangles for border purposes */
    private Rectangle unselected, selected;

    // TODO: remove hardcoding
    /** Background audio that plays when game is started*/
    private Media DEFAULT_SONG;

    /** Audio track for music/background audio */
    private MediaPlayer backgroundPlayer;

    /** Audio track for sound effects */
    private MediaPlayer sfxPlayer;

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

        this.selected = new Rectangle(16,16);
        this.selected.setFill(null);
        this.selected.setStroke(Color.WHITE);

        this.unselected = new Rectangle(16,16);
        this.unselected.setFill(null);
        this.unselected.setStroke(Color.TRANSPARENT);

        initBindings();
        initEventHandlers();

        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePass = "cscigroup3project/music/PinkDisk.mp3";
        String resourceURL = classLoader.getResource(resourcePass).toExternalForm();
        DEFAULT_SONG = new Media (resourceURL);
        backgroundPlayer = new MediaPlayer(DEFAULT_SONG);
        backgroundPlayer.setAutoPlay(true);
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

        //TODO fix the bounding box error for non-player objects (keys, for now)

        // Bind the PlayerView's position and image to the Player's x, y, and image properties
        theView.getPlayerView().translateXProperty().bind(theModel.getPlayer().getxProperty());
        theView.getPlayerView().translateYProperty().bind(theModel.getPlayer().getyProperty());
        theView.getPlayerView().imageProperty().bind(theModel.getPlayer().playerImageProperty());

    }

    /**
     * Handle key press events for player movement.
     *
     * @param event The {@link KeyEvent} generated by a key press.
     */
    public void handleKeyPress(KeyEvent event) {

        // if the textbox is not visible, check for inventory or environmental actions
        if(!theView.isTextboxVisible()) {

            // if the inventory is currently opened
            if (theView.getInventoryPane().isVisible()){
                inventoryAction(event);
            }
            // if the inventory is not opened, check for environment actions
            else {
                environmentAction(event);
            }
        }
        // otherwise, any keypress updates the text box
        else{ updateTextbox();}

        // check for collisions with walls
        checkCollisions(event);
    }

    /**
     * Check for collisions with walls
     * @param event the key event that triggered the collision check
     */
    private void checkCollisions(KeyEvent event) {
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

    /**
     * Handle environmental actions, such as movement, item pickup, and inventory toggling
     * @param event the key event that triggered the environmental action
     */
    private void environmentAction(KeyEvent event) {
        switch (event.getCode()) {
            case DOWN -> {
                theModel.getPlayer().move(Direction.DOWN);
            }
            case LEFT -> {
                theModel.getPlayer().move(Direction.LEFT);
            }
            case UP -> {
                theModel.getPlayer().move(Direction.UP);
            }
            case RIGHT -> {
                theModel.getPlayer().move(Direction.RIGHT);
            }
            case C -> {
                findItem();
            }
            case E -> {
                toggleInventory();
            }
            case Q -> {
                localArray = new ArrayList<>(textArray);
                updateTextbox();
            } //TODO for full functionality get boxes from user
        }
    }

    /**
     * Handle inventory actions, such as item selection, dropping, and toggling
     * @param event the key event that triggered the inventory action
     */
    private void inventoryAction(KeyEvent event) {
        // select item in inventory based on up and down arrows
        // highlight item with white box if selected, starting with first item
        switch (event.getCode()) {
            // toggle inventory with E
            case E -> {
                toggleInventory();
            }
            // select item in inventory with down arrow
            case DOWN -> {
                if (!theModel.getPlayer().getInventory().isEmpty()) {

                    updateItemSelection(unselected);

                    theModel.getPlayer().setInventoryTracker(theModel.getPlayer().getInventoryTracker() + 1);
                    if (theModel.getPlayer().getInventoryTracker() >= theModel.getPlayer().getInventory().size()) {
                        theModel.getPlayer().setInventoryTracker(0);
                    }

                    updateItemSelection(selected);

                }
            }
            // select item in inventory based on up arrow
            case UP -> {
                if (!theModel.getPlayer().getInventory().isEmpty()) {
                    updateItemSelection(unselected);
                    theModel.getPlayer().setInventoryTracker(theModel.getPlayer().getInventoryTracker() - 1);
                    if (theModel.getPlayer().getInventoryTracker() < 0) {
                        theModel.getPlayer().setInventoryTracker(theModel.getPlayer().getInventory().size() - 1);
                    }
                    updateItemSelection(selected);
                }
            }
            // drop item if C is pressed
            case C -> {
                // only execute if the inventory is not empty
                if (!theModel.getPlayer().getInventory().isEmpty()){

                    updateItemSelection(unselected);

                    // drop an item and adjust the inventory tracker
                    dropItem();
                    theModel.getPlayer().setInventoryTracker(theModel.getPlayer().getInventoryTracker() - 1);
                    if (theModel.getPlayer().getInventoryTracker() < 0) {
                        theModel.getPlayer().setInventoryTracker(0);
                    }
                    else {
                        updateItemSelection(selected);
                    }
                }
            }
        }
    }

    /**
     * Update the selection of the item in the inventory
     * @param selection the selection to be changed in the inventory
     */
    private void updateItemSelection(Rectangle selection) {
        StackPane pane = (StackPane) theView.getInventoryPane().getChildren().get(theModel.getPlayer().getInventoryTracker());

        pane.getChildren().remove(unselected);
        pane.getChildren().remove(selected);

        pane.getChildren().add(selection);

        theView.getInventoryPane().getChildren().remove(theModel.getPlayer().getInventoryTracker());
        theView.getInventoryPane().getChildren().add(theModel.getPlayer().getInventoryTracker(), pane);
    }

    /**
     * Update the textbox with the next text in the array
     */
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
        // pick up the item and remove it from the room
        theModel.getPlayer().pickUpItem(object);
        theModel.getRoomManager().getActiveRoom().removeObject(object);

        // add the item to the inventory and add the image to the view
        ImageView objectView = new ImageView(object.getSprite());
        StackPane imagePane = new StackPane();

        imagePane.getChildren().add(objectView);


        if (theModel.getPlayer().getInventory().size() == 1) {
            imagePane.getChildren().add(selected);
        }
        else{
            imagePane.getChildren().add(unselected);
        }

        theView.getInventoryPane().getChildren().add(imagePane);

        // if the image matches, turn off visibility
        for (ImageView imgView : theView.getAllViews()){
            if (imgView.getImage() == object.getSprite()){
                imgView.setVisible(false);
            }
        }

    }

    /**
     * drop and return the current item selected by the player's inventory
     */
    private GameObject dropItem(){
        // get the item that the player is dropping
        GameObject droppedItem = theModel.getPlayer().dropItem();
        droppedItem.setxPos(theModel.getPlayer().getxPos());
        droppedItem.setyPos(theModel.getPlayer().getyPos());

        // remove the item from the inventory and remove the image from the view
        theView.getInventoryPane().getChildren().remove(theModel.getPlayer().getInventoryTracker());

        // add the item to the room and add the image to the view
        theModel.getRoomManager().getActiveRoom().addObject(droppedItem);

        // if the image matches, move the translation and set to visible
        for (ImageView imgView : theView.getAllViews()){
            if (imgView.getImage() == droppedItem.getSprite()){
                imgView.setTranslateX(droppedItem.getxPos());
                imgView.setTranslateY(droppedItem.getyPos());
                imgView.setVisible(true);
            }
        }

        // return the item
        return droppedItem;
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

