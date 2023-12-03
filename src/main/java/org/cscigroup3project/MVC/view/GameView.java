/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:30 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.view
 * Class: GameView
 *
 * Description:
 * The View of our JavaFX application.
 * ****************************************
 */

package org.cscigroup3project.MVC.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.*;
import org.cscigroup3project.MVC.model.gameObject.GameObject;
import org.cscigroup3project.MVC.model.gameObject.Key;
import org.cscigroup3project.MVC.model.room.Room;
import org.cscigroup3project.MVC.model.room.RoomManager;

import java.util.ArrayList;

/**
 * The view shown to the user of our JavaFX application.
 */
public class GameView {

    /** The size of our grid */
    private static final int GRID_SIZE = 16;
    public static int DIM = 16;

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** A {@link StackPane} root for the view */
    private StackPane root;

    private StackPane allRoomPanes;

    /** The {@link GridPane} for the rooms */
    private ArrayList<GridPane> roomPanes;

    /** A {@link javafx.scene.layout.BorderPane} for the overlays */
    private BorderPane overlayPane;

    /** A {@link javafx.scene.layout.StackPane} for the textbox */
    private StackPane textboxPane;

    /** A {@link javafx.scene.layout.VBox} for storing the inventory */
    private VBox inventoryPane;

    /** The {@link ImageView} representing the player, collected from player object*/
    private ImageView playerView;

    /** The {@link ImageView} views representing two keys, collected from {@link Key} object */
    private ImageView keyView1;
    private ImageView keyView2;

    /** The {@link ImageView} representing a textbox */
    private ImageView textView;

    /** Container for all ImageViews in the program */
    private ArrayList<ArrayList<ImageView>> allViews;

    /** The {@link javafx.scene.control.Label} representing textbox text */
    private Label textLabel;

    private final int INITIAL_ROOM = 0;


    /**
     * Constructs the view given a {@link GameModel}.
     *
     * @param theModel the GameModel for this view
     */
    public GameView(GameModel theModel){
        this.theModel = theModel;
        initSceneGraph();
        initStyling();
    }

    /**
     * Initialize the scene graph by creating and adding the GUI components.
     */
    public void initSceneGraph(){

        // Initialize a StackPane root
        this.root = new StackPane();

        allViews = new ArrayList<>();


        this.roomPanes = new ArrayList<>();


        // Initialize the room views
        this.allRoomPanes = new StackPane();

        // For each known room, draw it and add it the StackPane of rooms
        for (int i = 0; i < theModel.getRoomManager().getRooms().size(); i++) {
            allViews.add(new ArrayList<>());
            drawRoom(theModel.getRoomManager().getRooms().get(i));
            allRoomPanes.getChildren().add(roomPanes.get(i));
        }

        // Add the room panes to the root
        this.root.getChildren().add(allRoomPanes);


        // Initialize the object views for gameObjects
        for (GameObject currentObject : theModel.getAllObjectsArray()){
            ImageView currentObjectView = new ImageView();
            //TODO: ".get(0)" hard coded for now, will need to change later
            this.allViews.get(0).add(currentObjectView);
            this.root.getChildren().add(currentObjectView);
        }

        // Initialize a PlayerView, add it to the root
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);

        //Initialize the overlay pane
        this.overlayPane = new BorderPane();
        //Initialize textbox pane
        this.textboxPane = new StackPane();
        //Initialize a textbox image view and label
        this.textView = new ImageView(String.valueOf(GameMain.class.getResource("textBoxSprite.png")));
        this.textLabel = new Label("");

        this.textboxPane.getChildren().add(textView);
        this.textboxPane.getChildren().add(textLabel);

        //Set the textbox pane to be the bottom pane in the overlay
        this.overlayPane.setBottom(textboxPane);
        this.root.getChildren().add(overlayPane);

        // set up the inventory pane
        this.inventoryPane = new VBox();
        this.inventoryPane.setVisible(false);

        this.root.getChildren().add(inventoryPane);

        updateRoom(INITIAL_ROOM);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

        // Style the ImageView of the Player with its height and width, and set its image
        this.playerView.setFitHeight(theModel.getPlayer().getHeight());
        this.playerView.setFitWidth(theModel.getPlayer().getWidth());
        this.playerView.setImage(theModel.getPlayer().getImage());
        this.playerView.setTranslateX(theModel.getPlayer().getxPos());
        this.playerView.setTranslateY(theModel.getPlayer().getyPos());

        // Style the all game objects (items in the allview container) with their sprite
        for (int i = 0; i < allViews.size(); i++) {
            //TODO: ".get(0)" hard coded for now, will need to change later
            //set sprite of view
            this.allViews.get(0).get(i).setImage(theModel.getAllObjectsArray().get(i).getSprite());

            //set the x and y coordinates of the view
            this.allViews.get(0).get(i).setTranslateX(theModel.getAllObjectsArray().get(i).getxPos());
            this.allViews.get(0).get(i).setTranslateY(theModel.getAllObjectsArray().get(i).getyPos());
        }

        /*
        this.keyView1.setImage(theModel.getKeys()[0].getSprite());
        this.keyView2.setImage(theModel.getKeys()[1].getSprite());


        this.keyView1.setTranslateX(theModel.getKeys()[0].getxPos());
        this.keyView1.setTranslateY(theModel.getKeys()[0].getyPos());

        this.keyView2.setTranslateX(theModel.getKeys()[1].getxPos());
        this.keyView2.setTranslateY(theModel.getKeys()[1].getyPos());

         */

        // TODO no more magic?
        // adjust for top row height
        this.allRoomPanes.setTranslateY(-26);

        this.inventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.7)));

        //Make textbox start out invisible
        this.setTextboxVisibility(false);
    }



    /**
     * Draws a {@link Room} to the {@link GameView#allRoomPanes}
     * @param room the {@link Room} to be drawn
     */
    private void drawRoom(Room room){

        GridPane roomPane = new GridPane();

        // draw every game object in the room
        int i = 0;
        for (ArrayList<GameObject> arrGO : room.getBaseObjects()) {
            int j = 0;
            for (GameObject gameObject : arrGO) {
                drawGameObject(roomPane, gameObject, i, j);
                j++;
            }
            i++;
        }

        // center the room
        roomPane.setAlignment(Pos.CENTER);
        roomPanes.add(roomPane);
    }

    /**
     * Draws a {@link GameObject} to the {@link GameView#allRoomPanes}
     *
     * @param roomPane
     * @param gameObject the {@link GameObject} to be drawn
     * @param i          the x position of the {@link GameObject}
     * @param j          the y position of the {@link GameObject}
     */
    private void drawGameObject(GridPane roomPane, GameObject gameObject, int i, int j){;

        // adjust for the top row
        int trueHeight = gameObject.getHeight();
        if (j == 0){
            trueHeight *= 5;
        }

        Rectangle objectRect = new Rectangle(
                gameObject.getxPos(),
                gameObject.getyPos(),
                gameObject.getWidth(),
                trueHeight
        );


        objectRect.setFill(new ImagePattern(gameObject.getSprite()));

        roomPane.add(objectRect, i%16+8, j%16+8);
    }

    public ImageView getKeyView1() {
        return keyView1;
    }

    public ImageView getKeyView2() {
        return keyView2;
    }

    /** Sets visibility of the textbox */
    public void setTextboxVisibility(boolean visibility){
        textboxPane.setVisible(visibility);
    }

    /** gets visibility of the textbox */
    public boolean isTextboxVisible(){
        boolean visbility = textboxPane.isVisible();
        return visbility;
    }

    public void updateRoom(int i){

        for (int j = 0; j < theModel.getRoomManager().getRooms().size(); j++) {
            if (j == i) {
                allRoomPanes.getChildren().get(j).setVisible(true);
                allRoomPanes.getChildren().get(j).setManaged(true);
                for (int k = 0; k < allViews.get(j).size(); k++) {
                    allViews.get(j).get(k).setVisible(true);
                    allViews.get(j).get(k).setManaged(true);
                }
            } else {
                allRoomPanes.getChildren().get(j).setVisible(false);
                allRoomPanes.getChildren().get(j).setManaged(false);
                for (int k = 0; k < allViews.get(j).size(); k++) {
                    allViews.get(j).get(k).setVisible(false);
                    allViews.get(j).get(k).setManaged(false);
                }
            }
        }
    }


    /**
     * @return the root
     */
    public StackPane getRoot() {
        return root;
    }

    /**
     * @return the player texture
     */
    public ImageView getPlayerView() {
        return playerView;
    }

    public VBox getInventoryPane() {
        return inventoryPane;
    }

    public ArrayList<ArrayList<ImageView>> getAllViews() {
        return allViews;
    }

    public void setTextboxText(String s) {
        textLabel.setText(s);
    }

}
