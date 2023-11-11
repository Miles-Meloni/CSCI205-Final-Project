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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.model.Player;
import org.cscigroup3project.MVC.model.Wall;

/**
 * The view of our JavaFX application
 */
public class GameView {

    /** The {@link Player} height and width */
    private final int PLAYER_HEIGHT = 32;
    private final int PLAYER_WIDTH = 37;

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** A {@link StackPane} root for the view */
    private StackPane root;

    /** The {@link Player} representation using Sprite resources */
//    private Player player;

    /** The {@link ImageView} representing the player, collected from player object*/
    private ImageView playerView;

    /** The {@link Wall} in order to collide */
    private Wall wall;

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

        // Initialize our Player from the model
//        this.player = theModel.getPlayer();

        // Initialize our Wall from the model
        this.wall = theModel.getWall();

        // Initialize a PlayerView, add it to the root
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);

        // Initialize a wall ImageView, add it to the root
        this.wallView = new ImageView();
        this.root.getChildren().add(wallView);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

        // Style the ImageView of the Player with a field constant height and width, and set its image
        this.playerView.setFitHeight(PLAYER_HEIGHT);
        this.playerView.setFitWidth(PLAYER_WIDTH);
        this.playerView.setImage(theModel.getPlayer().getImage());
        this.playerView.setTranslateX(theModel.getPlayer().getxPos());
        this.playerView.setTranslateY(theModel.getPlayer().getyPos());

        // Style the ImageView of the Wall with its model height, width, translated position, and set its Image
        this.wallView.setImage(new Image("cscigroup3project/TestWall.png")); // TODO - hardcoding image file?
        this.wallView.setFitHeight(this.wall.getBounds().getHeight());
        this.wallView.setFitWidth(this.wall.getBounds().getWidth());
        this.wallView.setTranslateX(this.wall.getBounds().getMinX());
        this.wallView.setTranslateY(this.wall.getBounds().getMinY());
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

    /**
     * @return the player texture
     */
//    public Player getPlayer() {
//        return player;
//    }
}
