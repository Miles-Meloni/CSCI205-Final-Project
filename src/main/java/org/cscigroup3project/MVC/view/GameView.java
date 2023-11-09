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
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.model.Player;
import org.cscigroup3project.MVC.model.Texture;

public class GameView {

    /** Model for the game */
    private GameModel theModel;

    /** Root note for the view */
    private StackPane root;

    /** Player representation ({@link javafx.scene.shape.Circle} for now) */
    private Player player;
    /** the texture representing the player, collected from player object*/
    private ImageView playerView;
    /** png representing a wall */
    private ImageView wallView;


    public GameView(GameModel theModel){
        this.theModel = theModel;

        initSceneGraph();
        initStyling();
    }

    /**
     * Initialize the scene graph by creating the GUI componenets
     */
    public void initSceneGraph(){
        this.root = new StackPane();

        //TODO move a lot of the below code from the view to the model

        // get image from resources and create player
        this.player = new Player(new double[]{0,0}, "DemoPlayer");

        // set up the ImageView of the player with a hardcoded height and width, and set its image
        //TODO remove hardcoding from here
        this.playerView = new ImageView();
        this.playerView.setFitHeight(32);
        this.playerView.setFitWidth(37);
        this.playerView.setImage(player.getTexture().getTextureFile());

        this.playerView.setTranslateX(player.getxPos());
        this.playerView.setTranslateY(player.getyPos());

        //TEMPORARY wall rendering
        Image wallImage = new Image("cscigroup3project/TestWall.png");

        this.wallView = new ImageView();
        this.wallView.setImage(wallImage);
        this.wallView.setFitHeight(50);
        this.wallView.setFitWidth(400);
        this.wallView.setTranslateX(100);
        this.wallView.setTranslateY(100);



        this.root.getChildren().add(playerView);
        this.root.getChildren().add(wallView);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

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
