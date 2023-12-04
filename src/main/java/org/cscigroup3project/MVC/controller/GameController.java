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
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.*;
import org.cscigroup3project.MVC.model.gameObject.*;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Collidable;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Storable;
import org.cscigroup3project.MVC.model.player.Direction;
import org.cscigroup3project.MVC.view.GameView;

import java.io.File;
import java.util.ArrayList;

import static org.cscigroup3project.MVC.view.GameView.DIM;

/**
 * The class implementing the logic and user interactions for the created game.
 */
public class GameController {

    private static final int GRID_SIZE = 16;
    /** The {@link GameModel} for our app */
    private final GameModel theModel;

    /** The {@link GameView} for our app */
    private final GameView theView;

    /** The {@link Scene} for our app */
    private final Scene theScene;

    /** An array that has hard coded textbox values.
     * #TODO remove hardcoding */
    private ArrayList<String> textArray;



    // TODO: likely needs moved elsewhere
    /** Unselected and selected rectangles for border purposes in the inventory */
    private Rectangle unselected, selected;

    /** Unselected and selected rectangles for border purposes in the dual inventory */
    private Rectangle unselected2, selected2;

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
        this.textArray.add(">What are you doing?");
        this.textArray.add(">Don't you want to get out of this dump?");
        this.textArray.add(">Well so do I.");
        this.textArray.add(">Let's get moving, shall we?");

        this.selected = new Rectangle(16,16);
        this.selected.setFill(null);
        this.selected.setStroke(Color.WHITE);

        this.unselected = new Rectangle(16,16);
        this.unselected.setFill(null);
        this.unselected.setStroke(Color.TRANSPARENT);

        this.selected2 = new Rectangle(16,16);
        this.selected2.setFill(null);
        this.selected2.setStroke(Color.WHITE);

        this.unselected2 = new Rectangle(16,16);
        this.unselected2.setFill(null);
        this.unselected2.setStroke(Color.TRANSPARENT);

        theView.setTextboxVisibility(true);

        initBindings();
        initEventHandlers();
        initMusic();
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

    }

    /**
     * Initialize the music that will play in the background of our program indefinitely.
     */
    private void initMusic() {

        // TODO - Remove hard coding

        // Get the filepath from the resources folder
//        String defaultMusic = "src/main/resources/cscigroup3project/music/PinkDisk.mp3";
        String defaultMusic = ("music/PinkDisk.mp3");

        // Create a File object from the resources URL
        File fileURL = new File(defaultMusic);

        // Get the long URL needed for the Media object
        String longUrl = fileURL.toURI().toString();

        // Create the Media object
        Media sound = new Media(String.valueOf(GameMain.class.getResource("music/PinkDisk.mp3")));

        // Set the MediaPlayer with the music
        backgroundPlayer = new MediaPlayer(sound);

        // Cycle the song indefinitely, and play the song
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.play();
    }

    /**
     * Handle key press events for player movement.
     *
     * @param event The {@link KeyEvent} generated by a key press.
     */
    public void handleKeyPress(KeyEvent event) {

        // If the inventory is open, check for inventory actions
        if (theView.getSingleInventoryPane().isVisible()) { singleInventoryAction(event); }

        //if the double inventory is open, check for double inventory actions
        else if (theView.getDoubleInventoryPane().isVisible()) { doubleInventoryAction(event); }

        // If the inventory is not open
        else {

            // Check for environment actions
            environmentAction(event); }

        // Always check for collisions with walls
        checkCollisions(event);
    }

    /**
     * Check for collisions with walls
     * @param event the key event that triggered the collision check
     */
    private void checkCollisions(KeyEvent event) {
        checkWalls(event);
        checkDoors(event);
        checkObjects(event);
    }

    /**
     * Check for collisions with walls
     * @param event the key event that triggered the collision check
     */
    private void checkWalls(KeyEvent event) {
        for (Wall wall : theModel.getRoomManager().getActiveRoom().getWalls()) {
            // if the player intersects with a wall, correct the movement
            if (theModel.getPlayer().getBounds().getBoundsInLocal().intersects(
                    wall.getBounds().localToParent(wall.getBounds().getBoundsInLocal()))) {
                collisionCorrection(event);
            }
        }
    }

    /**
     * Check for collisions with objects with the collidable interface
     * @param event the key event that triggered the collision check
     */
    private void checkObjects(KeyEvent event) {
        // Set up a variable to contain the GameObject, if we find one
        GameObject foundObject = null;

        // Set up tracking variables to see if we need to exit the loop
        boolean found = false;
        int index = 0;

        // While loop because we only want to pick up a single item.
        // Continue while a GameObject is not found, and we still have items to search for
        while (!found && index < theModel.getRoomManager().getActiveRoom().getItemObjects().size()){

            // Set the current object and increment index
            GameObject currentObject = theModel.getRoomManager().getActiveRoom().getItemObjects().get(index);
            index++;

            // check for intersection; if it's there and it implements storable
            if (currentObject instanceof Collidable
                    &
                    theModel.getPlayer().getBounds().intersects(
                            currentObject.getBounds().localToParent(currentObject.getBounds().getBoundsInLocal())))
            {
                collisionCorrection(event);
            }
        }
    }

    /**
     * Check for collisions with door frames
     * @param event the key event that triggered the collision check
     */
    private void checkDoors(KeyEvent event) {
        // check for collisions with door frames
        for (DoorFrame doorFrame : theModel.getRoomManager().getActiveRoom().getDoorFrames()) {
            // if the player intersects with a door frame, correct the movement
            if (theModel.getPlayer().getBounds().getBoundsInLocal().intersects(
                    doorFrame.getBounds().localToParent(doorFrame.getBounds().getBoundsInLocal()))) {
                collisionCorrection(event);
            }
        }
        // check for collisions with LOCKED doorways ONLY
        for (Doorway doorway : theModel.getRoomManager().getActiveRoom().getDoorways()){

            if (theModel.getPlayer().getBounds().getBoundsInLocal().intersects(
                    doorway.getBounds().localToParent(doorway.getBounds().getBoundsInLocal()))) {

                // doorway locked, correct movement
                if (doorway.isLocked()){
                    collisionCorrection(event);
                }

                // doorway unlocked, move to next room
                else {
                    theModel.getRoomManager().moveToNextRoom(doorway);
                    theView.updateRoom(theModel.getRoomManager().getActiveRoomIndex());

                    // depending on where the player is coming from, set the player's position
                    if (doorway.getxPos() <= (DIM / 2 - 1)*-1*GRID_SIZE){
                        theModel.getPlayer().setxPos((DIM / 2) * GRID_SIZE - theModel.getPlayer().getWidth());
                    }
                    else if (doorway.getxPos() >= (DIM / 2 - 1)*GRID_SIZE){
                        theModel.getPlayer().setxPos((DIM / 2 - 2) * -1 * GRID_SIZE);
                    }
                    else if (doorway.getyPos() >= ((DIM / 2) - 1) * GRID_SIZE){
                        theModel.getPlayer().setyPos((DIM / 2 - 2) * -1 * GRID_SIZE);
                    }
                    else if (doorway.getyPos() <= ((DIM / 2) - 1) * -1 * GRID_SIZE){
                        theModel.getPlayer().setyPos((DIM / 2) * GRID_SIZE - theModel.getPlayer().getHeight());
                    }
                }

            }
        }
    }

    /**
     * Correct the player's movement if they collide with a wall
     * @param event the key event that triggered the collision correction
     */
    private void collisionCorrection(KeyEvent event) {
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

    /**
     * Handle environmental actions, such as movement, item pickup, and inventory toggling
     * @param event the key event that triggered the environmental action
     */
    private void environmentAction(KeyEvent event) {
        //if there's already text on screen, the only thing the player can do is move to the next screen
        if (theView.isTextboxVisible()){updateTextbox(null);}
        //otherwise, text acts as normal
        else{
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
                    GameObject foundObject = findStorableItem();
                    if (foundObject != null) {
                        pickUpItem(foundObject);
                    }
                }
                case E -> {
                    toggleSingleInventory();
                }
                case Q -> {

                    //select the nearby object (if applicable) and proceed to the update textbox method
                    GameObject foundObject = findInteractibleItem();
                    updateTextbox(foundObject);

                } //TODO for full functionality get boxes from user

                //TODO implement stable version of interactive inventory
                /*
                case Z ->{

                    //select nearby object if applicable and proceed to opening the dual inventory combo
                    StorageObject foundObject = findStorageItem();
                    if (foundObject != null) {
                        toggleDoubleInventory(foundObject);
                    }
                }
                 */
            }
        }
    }

    /**
     * Handle inventory actions, such as item selection, dropping, and toggling
     * @param event the key event that triggered the inventory action
     */
    private void singleInventoryAction(KeyEvent event) {
        // select item in inventory based on up and down arrows
        // highlight item with white box if selected, starting with first item
        switch (event.getCode()) {
            // toggle inventory with E
            case E -> {
                toggleSingleInventory();
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
                    updateItemSelection(selected);
                }
            }
        }
    }

    /**
     * Handle double inventory actions, such as item selection, switching, and toggling
     * @param event the key event that triggered the inventory action
     */
    private void doubleInventoryAction(KeyEvent event) {
        // select item in inventory based on up and down arrows
        // highlight item with white box if selected, starting with first item
        //if column is switched then it goes up to the top of the other inventory
        switch (event.getCode()) {
            // toggle inventory with E
            case Z -> {
                toggleDoubleInventory(null);
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
            // switch item's storage if C is pressed
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
        if (theModel.getPlayer().getInventory().isEmpty()) {
            return;
        }
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
    private void updateTextbox(GameObject foundObject) {

        //If there already is text, update it by displaying the next line
        if (!textArray.isEmpty()){
            theView.setTextboxVisibility(true);

            //Update text and remove from array
            theView.setTextboxText(textArray.get(0));
            textArray.remove(0);
        }
        // If there is no current text and foundObject is not null, show the text box, set the text from the foundObject
        else if (foundObject != null) {
            theView.setTextboxVisibility(true);
            textArray.addAll(foundObject.getTextArray());

            //Update text and remove from array
            theView.setTextboxText(textArray.get(0));
            textArray.remove(0);
        }
        //if there's no text, don't do anything
        else {
            theView.setTextboxVisibility(false);
        }
    }

    /**
     * Find an item in range that is storable, if it exists, among all items in the active room.
     *
     * @return foundObject, the item the Player can pick up
     */
    private GameObject findStorableItem(){
        // Set up a variable to contain the GameObject, if we find one
        GameObject foundObject = null;

        // Set up tracking variables to see if we need to exit the loop
        boolean found = false;
        int index = 0;

        // While loop because we only want to pick up a single item.
        // Continue while a GameObject is not found, and we still have items to search for
        while (!found && index < theModel.getRoomManager().getActiveRoom().getItemObjects().size()){

            // Set the current object and increment index
            GameObject currentObject = theModel.getRoomManager().getActiveRoom().getItemObjects().get(index);
            index++;

            // check for intersection; if it's there and it implements storable
            if (currentObject instanceof Storable
                    &
                    theModel.getPlayer().getReach().getBoundsInLocal().intersects(
                    currentObject.getBounds().localToParent(currentObject.getBounds().getBoundsInLocal()))) {

                foundObject = currentObject;
                found = true;
            }
        }

        // Return the found GameObject
        return foundObject;
    }

    /**
     * Find a storage item in range, if it exists, among all items in the active room.
     *
     * @return foundObject, the item the Player can pick up
     */
    private StorageObject findStorageItem(){
        // Set up a variable to contain the GameObject, if we find one
        GameObject foundObject = null;

        // Set up tracking variables to see if we need to exit the loop
        boolean found = false;
        int index = 0;

        // While loop because we only want to pick up a single item.
        // Continue while a GameObject is not found, and we still have items to search for
        while (!found && index < theModel.getRoomManager().getActiveRoom().getItemObjects().size()){

            // Set the current object and increment index
            GameObject currentObject = theModel.getRoomManager().getActiveRoom().getItemObjects().get(index);
            index++;

            // check for intersection; if it's there and it implements storable
            if (currentObject instanceof StorageObject
                    &
                    theModel.getPlayer().getReach().getBoundsInLocal().intersects(
                            currentObject.getBounds().localToParent(currentObject.getBounds().getBoundsInLocal()))) {

                foundObject = currentObject;
                found = true;
            }
        }

        // Return the found GameObject
        return (StorageObject) foundObject;
    }

    /**
     * Find an item in range that is interactible, if it exists, among all items in the active room.
     *
     * @return foundObject, the item the Player can talk to
     */
    private GameObject findInteractibleItem(){
        // Set up a variable to contain the GameObject, if we find one
        GameObject foundObject = null;

        // Set up tracking variables to see if we need to exit the loop
        boolean found = false;
        int index = 0;

        // While loop because we only want to pick up a single item.
        // Continue while a GameObject is not found, and we still have items to search for
        while (!found && index < theModel.getRoomManager().getActiveRoom().getItemObjects().size()){

            // Set the current object and increment index
            GameObject currentObject = theModel.getRoomManager().getActiveRoom().getItemObjects().get(index);
            index++;

            // check for intersection; if it's there and it implements storable
            if (currentObject != null && currentObject instanceof Interactible
                    &
                    theModel.getPlayer().getReach().getBoundsInLocal().intersects(
                            currentObject.getBounds().localToParent(currentObject.getBounds().getBoundsInLocal()))) {

                foundObject = currentObject;
                found = true;
            }
        }

        // Return the found GameObject
        return foundObject;
    }

    /**
     * Pick up the specified item by notifying Player and Room
     * @param object the object to be picked up
     */
    private void pickUpItem(GameObject object){
        // pick up the item and remove it from the room
        theModel.getPlayer().pickUpItem(object);
        theModel.getRoomManager().getActiveRoom().removeObject(object);

        // add the item to the inventory and the dual inventory, and add the image to the view
        ImageView objectView = new ImageView(object.getSprite());
        ImageView objectView2 = new ImageView(object.getSprite());
        StackPane imagePane = new StackPane();
        StackPane imagePane2 = new StackPane();

        imagePane.getChildren().add(objectView);
        imagePane2.getChildren().add(objectView2);


        if (theModel.getPlayer().getInventory().size() == 1) {
            imagePane.getChildren().add(selected);
            imagePane2.getChildren().add(selected2);
        }
        else{
            imagePane.getChildren().add(unselected);
            imagePane2.getChildren().add(unselected2);
        }

        theView.getInventoryPane().getChildren().add(imagePane);
        theView.getPlayerInventoryPane().getChildren().add(imagePane2);

        // if the image matches, turn off visibility
        for (ImageView imgView : theView.getAllViews().get(theModel.getRoomManager().getActiveRoomIndex())){
            if (imgView.getImage() == object.getSprite()){
                imgView.setVisible(false);
            }
        }

        //remove image of the object from the room it was assigned to
        int currRoom = theModel.getRoomManager().getActiveRoomIndex();
        theView.getAllViews().get(currRoom).remove(object.getImageOfObject());

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
        //do the same for the dual inventory
        theView.getPlayerInventoryPane().getChildren().remove(theModel.getPlayer().getInventoryTracker());

        // add the item to the room and add the image to the view
        theModel.getRoomManager().getActiveRoom().addObject(droppedItem);

        //set the visuals of the object to those of the current room
        int currRoom = theModel.getRoomManager().getActiveRoomIndex();
        theView.getAllViews().get(currRoom).add(droppedItem.getImageOfObject());

        // if the image matches, move the translation and set to visible
        for (ImageView imgView : theView.getAllViews().get(theModel.getRoomManager().getActiveRoomIndex())){
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
    private void toggleSingleInventory(){
        if (theView.getSingleInventoryPane().isVisible() || theView.getDoubleInventoryPane().isVisible()){
            theView.getSingleInventoryPane().setVisible(false);
        }
        else {
            theView.getSingleInventoryPane().setVisible(true);
        }
    }

    /**
     * Turn the inventory on or off, based on current state
     */
    private void toggleDoubleInventory(StorageObject storageObject){
        if (theView.getSingleInventoryPane().isVisible()|| theView.getDoubleInventoryPane().isVisible()){
            theView.getDoubleInventoryPane().setVisible(false);
            //remove all objects for the view of the other inventory
            theView.getDoubleInventoryPane().getChildren().removeAll();

        }
        else {
            theView.getDoubleInventoryPane().setVisible(true);
            //add the object's inventory to the view
            for(int i = 0; i < storageObject.getStorageObjectInventory().size(); i++){
                ImageView objectView = new ImageView(storageObject.getStorageObjectInventory().getItem(i).getSprite());
                StackPane imagePane = new StackPane();

                imagePane.getChildren().add(objectView);
                imagePane.getChildren().add(unselected);

                theView.getInventoryPane().getChildren().add(imagePane);
            }
        }
    }
}

