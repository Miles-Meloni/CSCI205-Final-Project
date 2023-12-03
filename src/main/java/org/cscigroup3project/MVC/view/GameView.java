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

import javafx.geometry.Insets;
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

    /** A {@link javafx.scene.layout.HBox} for storing the single inventory view */
    private HBox singleInventoryPane;

    /** A {@link javafx.scene.layout.HBox} for storing the double inventory view */
    private HBox doubleInventoryPane;

    /** A {@link javafx.scene.layout.VBox} for storing the player inventory on its own*/
    private VBox inventoryPane;

    /** A {@link javafx.scene.layout.VBox} for storing the player inventory with another inventory*/
    private VBox playerInventoryPane;


    /** A {@link javafx.scene.layout.VBox} for storing the other inventory in the 2 inventory view */
    private VBox objectInventoryPane;


     /** The {@link ImageView} representing the player, collected from player object*/
    private ImageView playerView;


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

        this.allViews = new ArrayList<>();

        this.roomPanes = new ArrayList<>();

        this.allRoomPanes = new StackPane();

        //Create an arraylist for each room
        for (int i = 0; i < theModel.getRoomManager().getRooms().size(); i++) {
            allViews.add(new ArrayList<>());
            drawRoom(theModel.getRoomManager().getRooms().get(i));
            allRoomPanes.getChildren().add(roomPanes.get(i));
        }

        //add the rooms to the view
        this.root.getChildren().add(allRoomPanes);


        // Initialize the object views for gameObjects
        for (int i = 0; i < theModel.getAllObjectsArray().size(); i++) {
            ArrayList<GameObject> currentRoomArray = theModel.getAllObjectsArray().get(i);
            if (currentRoomArray.size() != 0){
                for (GameObject currentObject : currentRoomArray) {
                    //for each object: create a view, add it to the list of views,
                    // assign it to the object, and add the object to the root
                    ImageView currentObjectView = new ImageView();
                    currentObject.setImageOfObject(currentObjectView);
                    this.allViews.get(i).add(currentObjectView);
                    this.root.getChildren().add(currentObjectView);
                }
            }
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

        // set up the solitary player inventory pane
        this.inventoryPane = new VBox();

        // set up the partnered player inventory pane
        this.playerInventoryPane = new VBox();

        //set up the object inventory pane
        this.objectInventoryPane = new VBox();

        //set up the single inventory, and add the player inventory
        this.singleInventoryPane = new HBox();
        this.singleInventoryPane.getChildren().add(inventoryPane);
        this.singleInventoryPane.setVisible(false);
        root.getChildren().add(singleInventoryPane);

        //set up the double inventory, add both inventories
        this.doubleInventoryPane = new HBox();
        this.doubleInventoryPane.getChildren().add(playerInventoryPane);
        this.doubleInventoryPane.getChildren().add(objectInventoryPane);
        this.doubleInventoryPane.setVisible(false);
        root.getChildren().add(doubleInventoryPane);

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
            ArrayList roomView = allViews.get(i);
            if (roomView.size() != 0){
                for (int j = 0; j < roomView.size(); j++) {
                    //TODO: ".get(0)" hard coded for now, will need to change later
                    //set sprite of view
                    this.allViews.get(i).get(j).setImage(theModel.getAllObjectsArray().get(i).get(j).getSprite());

                    //set the x and y coordinates of the view
                    this.allViews.get(i).get(j).setTranslateX(theModel.getAllObjectsArray().get(i).get(j).getxPos());
                    this.allViews.get(i).get(j).setTranslateY(theModel.getAllObjectsArray().get(i).get(j).getyPos());
                }
            }
        }

        // TODO no more magic?
        // adjust for top row height
        this.allRoomPanes.setTranslateY(-26);

        //shade all inventory boxes
        this.doubleInventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.8)));
        this.singleInventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.8)));

        //stroke and shade the inner boxes with corresponding colors
        Color invPink = new Color(1,0,0.5,1.0);
        Color invGreen = new Color(0, 1, 0.5, 1);
        this.inventoryPane.setBorder(new Border(new BorderStroke(invPink, invPink, invPink, invPink,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        this.inventoryPane.setBackground(Background.fill(new Color(1, 0, 0.5, 0.1)));

        this.playerInventoryPane.setBorder(new Border(new BorderStroke(invPink, invPink, invPink, invPink,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        this.playerInventoryPane.setBackground(Background.fill(new Color(1, 0, 0.5, 0.1)));

        this.objectInventoryPane.setBorder(new Border(new BorderStroke(invGreen, invGreen, invGreen, invGreen,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        this.objectInventoryPane.setBackground(Background.fill(new Color(0, 1, 0.5, 0.1)));

        //center all inventory boxes
        this.inventoryPane.setAlignment(Pos.CENTER);
        this.playerInventoryPane.setAlignment(Pos.CENTER);
        this.singleInventoryPane.setAlignment(Pos.CENTER);
        this.doubleInventoryPane.setAlignment(Pos.CENTER);
        this.objectInventoryPane.setAlignment(Pos.CENTER);

        //Add spacing between inventory boxes
        Insets inventoryPadding = new Insets(10);
        this.inventoryPane.setPadding(inventoryPadding);
        this.playerInventoryPane.setPadding(inventoryPadding);
        this.objectInventoryPane.setPadding(inventoryPadding);

        this.inventoryPane.setSpacing(4);
        this.playerInventoryPane.setSpacing(4);
        this.objectInventoryPane.setSpacing(4);

        //Adds spacing between inventories
        this.doubleInventoryPane.setSpacing(8);
        this.singleInventoryPane.setSpacing(8);
    }



    /**
     * Draws a {@link Room} to the {@link GameView#allRoomPanes}
     * @param room the {@link Room} to be drawn
     */
    private void drawRoom(Room room){

        GridPane roomPane = new GridPane();

        // draw every game object in the room
        int i = 0;
        for (ArrayList<GameObject> arrayGameObject : room.getBaseObjects()) {
            int j = 0;
            for (GameObject gameObject : arrayGameObject) {
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

    public VBox getPlayerInventoryPane() {
        return playerInventoryPane;
    }

    public VBox getObjectInventoryPane() {
        return objectInventoryPane;
    }

    public HBox getSingleInventoryPane() {
        return singleInventoryPane;
    }

    public HBox getDoubleInventoryPane() {
        return doubleInventoryPane;
    }

    public ArrayList<ArrayList<ImageView>> getAllViews() {
        return allViews;
    }

    public void setTextboxText(String s) {
        textLabel.setText(s);
    }

}
