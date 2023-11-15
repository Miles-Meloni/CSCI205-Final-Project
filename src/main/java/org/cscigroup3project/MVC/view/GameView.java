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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.model.Player;
import org.cscigroup3project.MVC.model.Wall;

/**
 * The view of our JavaFX application
 */
public class GameView {

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** A {@link StackPane} root for the view */
    private StackPane root;

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

        // Initialize a StackPane root
        this.root = new StackPane();

        // Initialize a wall ImageView, add it to the root
        this.wallView = new ImageView();
        this.root.getChildren().add(wallView);

        // Initialize a PlayerView, add it to the root
        this.playerView = new ImageView();
        this.root.getChildren().add(playerView);
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

        // Style the ImageView of the Wall with its model height, width, translated position, and set its Image
        Image image = new Image("cscigroup3project/roomTiles/Wall_front.png");
        this.wallView.setImage(image); // TODO - hardcoding image file?
        this.wallView.setFitHeight(theModel.getWall().getBounds().getHeight());
        this.wallView.setFitWidth(theModel.getWall().getBounds().getWidth());

        this.wallView.setTranslateX(theModel.getWall().getBounds().getX()+theModel.getWall().getBounds().getWidth()/2);
        this.wallView.setTranslateY(theModel.getWall().getBounds().getY()+theModel.getWall().getBounds().getHeight()/2);
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
