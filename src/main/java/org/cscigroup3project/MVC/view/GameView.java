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

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.*;

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


        // Initialize the key view
        this.keyView1 = new ImageView();
        this.keyView2 = new ImageView();

        this.allViews.add(keyView1);
        this.allViews.add(keyView2);


        this.root.getChildren().add(keyView1);
        this.root.getChildren().add(keyView2);


        // Initialize a PlayerView, add it to the root
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);
        this.allViews.add(playerView);

        //Initialize the overlay pane
        this.overlayPane = new BorderPane();

        //Initialize textbox pane
        this.textboxPane = new StackPane();

        //Initialize a textbox image view and label
        this.textView = new ImageView("cscigroup3project/textBoxSprite.png");
        // TODO make the label get text from a different source
        this.textLabel = new Label("The Fitnessgram pacer test is a multi stage aerobic");

        this.textboxPane.getChildren().add(textView);
        this.textboxPane.getChildren().add(textLabel);
        this.allViews.add(textView);

        //Set the textbox pane to be the bottom pane in the overlay
        this.overlayPane.setBottom(textboxPane);
        this.root.getChildren().add(overlayPane);

        // set up the inventory pane
        this.inventoryPane = new VBox();
        this.inventoryPane.setVisible(false);

        this.root.getChildren().add(inventoryPane);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

        // Style the ImageView of the Player with its height and width, and set its image
        this.playerView.setFitHeight(theModel.getPlayer().getBounds().getHeight()+theModel.getPlayer().getDIFF());
        this.playerView.setFitWidth(theModel.getPlayer().getBounds().getWidth());
        this.playerView.setImage(theModel.getPlayer().getImage());
        this.playerView.setTranslateX(theModel.getPlayer().getxPos());
        this.playerView.setTranslateY(theModel.getPlayer().getyPos());


        // Style the imageview of the key view
        this.keyView1.setImage(theModel.getKeys()[0].getSprite());
        this.keyView2.setImage(theModel.getKeys()[1].getSprite());

        this.keyView2.setTranslateX(theModel.getKeys()[1].getxPos());
        this.keyView2.setTranslateY(theModel.getKeys()[1].getyPos());

        // TODO no more magic?
        // adjust for top row height
        this.roomPane.setTranslateY(-26);

        this.inventoryPane.setBackground(Background.fill(new Color(0, 0, 0, 0.7)));

        //Make textbox start out invisible
        this.setTextboxVisibility(false);
    }

    private void drawActiveRoom(RoomManager roomManager){
        Room activeRoom = roomManager.getActiveRoom();
        if (activeRoom != null){
            drawRoom(activeRoom);
        }
    }

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

    public ArrayList<ImageView> getAllViews() {
        return allViews;
    }

    public void setTextboxText(String s) {
        textLabel.setText(s);
    }

}
