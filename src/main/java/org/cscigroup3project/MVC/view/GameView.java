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

    /** Model for the traffic light program */
    private GameModel theModel;

    /** Root note for the view */
    private StackPane root;

    /** Player representation ({@link javafx.scene.shape.Circle} for now) */
    private Player player;
    private ImageView playerView;



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
        Image playerImage = new Image("cscigroup3project/TestPlayer.png");
        this.player = new Player(new double[]{0,0}, new Texture(playerImage), "DemoPlayer");

        // set up the ImageView of the player with a hardcoded height and width, and set its image
        //TODO remove hardcoding from here
        this.playerView = new ImageView();
        this.playerView.setImage(playerImage);
        this.playerView.setFitHeight(100);
        this.playerView.setFitWidth(100);

        playerView.setTranslateX(player.getxPos());
        playerView.setTranslateY(player.getyPos());

        this.root.getChildren().add(playerView);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

    }

    public StackPane getRoot() {
        return root;
    }

    public ImageView getPlayerView() {
        return playerView;
    }

    public Player getPlayer() {
        return player;
    }
}
