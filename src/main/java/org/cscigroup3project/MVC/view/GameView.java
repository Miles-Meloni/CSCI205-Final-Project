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

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** A {@link StackPane} root for the view */
    private StackPane root;

    /** A {@link GridPane} for the room */
    private GridPane roomPane;

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

    /** Container for all ImageViews in the program (save for the player and textboxes)*/
    private ArrayList<ImageView> allViews;

    /** The {@link javafx.scene.control.Label} representing textbox text */
    private Label textLabel;


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

        // Initialize the room display GridPane
        this.roomPane = new GridPane();
        this.roomPane.setAlignment(Pos.CENTER);

        drawActiveRoom(theModel.getRoomManager());
        this.root.getChildren().add(roomPane);


        // Initialize the object views for gameObjects
        for (GameObject currentObject :theModel.getAllObjectsArray()){
            ImageView currentObjectView = new ImageView();
            this.allViews.add(currentObjectView);
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
            //set sprite of view
            this.allViews.get(i).setImage(theModel.getAllObjectsArray().get(i).getSprite());

            //set the x and y coordinates of the view
            this.allViews.get(i).setTranslateX(theModel.getAllObjectsArray().get(i).getxPos());
            this.allViews.get(i).setTranslateY(theModel.getAllObjectsArray().get(i).getyPos());
        }

        // TODO no more magic?
        // adjust for top row height
        this.roomPane.setTranslateY(-26);

        //shade all inventory boxes
        this.doubleInventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.8)));
        this.singleInventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.8)));

        //stroke and shade the inner boxes
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
     * Draws the active {@link Room} to the {@link GameView#roomPane}
     * @param roomManager the {@link RoomManager} to get the active {@link Room} from
     */
    private void drawActiveRoom(RoomManager roomManager){
        Room activeRoom = roomManager.getActiveRoom();
        if (activeRoom != null){
            drawRoom(activeRoom);
        }
    }

    /**
     * Draws a {@link Room} to the {@link GameView#roomPane}
     * @param room the {@link Room} to be drawn
     */
    private void drawRoom(Room room){
        int i = 0;
        for (ArrayList<GameObject> arrGO : room.getBaseObjects()) {
            int j = 0;
            for (GameObject gameObject : arrGO) {
                drawGameObject(gameObject, i, j);
                j++;
            }
            i++;
        }
    }

    /**
     * Draws a {@link GameObject} to the {@link GameView#roomPane}
     * @param gameObject the {@link GameObject} to be drawn
     * @param i the x position of the {@link GameObject}
     * @param j the y position of the {@link GameObject}
     */
    private void drawGameObject(GameObject gameObject, int i, int j){;

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

    public ArrayList<ImageView> getAllViews() {
        return allViews;
    }

    public void setTextboxText(String s) {
        textLabel.setText(s);
    }

}
