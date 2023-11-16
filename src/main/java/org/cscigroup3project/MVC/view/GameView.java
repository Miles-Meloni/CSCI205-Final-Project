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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.*;

import java.util.ArrayList;

/**
 * The view of our JavaFX application
 */
public class GameView {

    private static final int TILE_SIZE = 16;

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** A {@link StackPane} root for the view */
    private StackPane root;

    /** A {@link javafx.scene.layout.Pane} for the room */
    private GridPane roomPane;

    /** A {@link javafx.scene.layout.Pane} for the overlays */
    private BorderPane overlayPane;

    /** A {@link javafx.scene.layout.Pane} for the textbox */
    private StackPane textboxPane;

    /** The {@link ImageView} representing the player, collected from player object*/
    private ImageView playerView;

    /** The {@link ImageView} representing a textbox */
    private ImageView textView;

    /** The {@link ImageView} png representing a wall */
    private ImageView wallView;

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
     * Initialize the scene graph by creating and adding the GUI components
     */
    public void initSceneGraph(){

        // Initialize a StackPane root
        this.root = new StackPane();

        // Initialize the room display Pane
        this.roomPane = new GridPane();
        this.roomPane.setAlignment(Pos.CENTER);

        // Initialize a wall ImageView, add it to the root
        this.wallView = new ImageView();
        //this.root.getChildren().add(wallView);

        drawActiveRoom(theModel.getRoomManager());

        this.root.getChildren().add(roomPane);



        // Initialize a PlayerView, add it to the root
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);

        //Initialize the overlay pane
        this.overlayPane = new BorderPane();

        //Initialize textbox pane
        this.textboxPane = new StackPane();

        //Initialize a textbox image view, add it to the pane
        this.textView = new ImageView();
        this.textboxPane.getChildren().add(textView);

        //Set the textbox pane to be the bottom pane in the overlay
        this.overlayPane.setBottom(textboxPane);

    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

        //Make root bgcolor black
        root.setBackground(Background.fill(Color.BLACK));

        // Style the ImageView of the Player with its height and width, and set its image
        this.playerView.setFitHeight(theModel.getPlayer().getBounds().getHeight()+theModel.getPlayer().getDIFF());
        this.playerView.setFitWidth(theModel.getPlayer().getBounds().getWidth());
        this.playerView.setImage(theModel.getPlayer().getImage());
        this.playerView.setTranslateX(theModel.getPlayer().getxPos());
        this.playerView.setTranslateY(theModel.getPlayer().getyPos());

        // adjust for top row height
        this.roomPane.setTranslateY(-26);

        // Style the ImageView of the Wall with its model height, width, translated position, and set its Image
        //Image image = new Image("cscigroup3project/roomTiles/Wall_front.png");
        //this.wallView.setImage(image); // TODO - hardcoding image file?
        //this.wallView.setFitHeight(theModel.getWall().getBounds().getHeight());
        //this.wallView.setFitWidth(theModel.getWall().getBounds().getWidth());

        //this.wallView.setTranslateX(theModel.getWall().getBounds().getX()+theModel.getWall().getBounds().getWidth()/2);
        //this.wallView.setTranslateY(theModel.getWall().getBounds().getY()+theModel.getWall().getBounds().getHeight()/2);
    }

    private void drawActiveRoom(RoomManager roomManager){
        Room activeRoom = roomManager.getActiveRoom();
        if (activeRoom != null){
            drawRoom(activeRoom);
        }
    }

    private void drawRoom(Room room){
        int i = 0;
        for (ArrayList<GameObject> arrGO : room.getGameObjects()) {
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
}
