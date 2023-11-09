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
import org.cscigroup3project.MVC.model.Texture;

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
    private Player player;

    /** The {@link ImageView} representing the player, collected from player object*/
    private ImageView playerView;

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

        //TODO move select code from the view to the model

        // Initialize a StackPane root
        this.root = new StackPane();

        // Initialize a Player with position and name, add it to the root
        this.player = theModel.getPlayer();
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);

        // Initialize a wall Image, add it to the root
        Image wallImage = new Image("cscigroup3project/TestWall.png"); // TODO - hardcoding image file?
        this.wallView = new ImageView();
        this.wallView.setImage(wallImage);
        this.root.getChildren().add(wallView);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

        // Style the ImageView of the player with a field constant height and width, and set its image
        this.playerView.setFitHeight(PLAYER_HEIGHT);
        this.playerView.setFitWidth(PLAYER_WIDTH);
        this.playerView.setImage(player.getTexture().getTextureFile());
        this.playerView.setTranslateX(player.getxPos());
        this.playerView.setTranslateY(player.getyPos());

        // Style the wallView of the wall with magic number height, width, and translated position
        this.wallView.setFitHeight(50);
        this.wallView.setFitWidth(400);
        this.wallView.setTranslateX(100);
        this.wallView.setTranslateY(100);
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
    public Player getPlayer() {
        return player;
    }
}
