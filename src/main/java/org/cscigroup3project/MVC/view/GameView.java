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

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.model.Player;

public class GameView {

    /** Model for the traffic light program */
    private GameModel theModel;

    /** Root note for the view */
    private StackPane root;

    /** Player representation ({@link javafx.scene.shape.Circle} for now) */
    private Circle player;



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

        this.player = new Circle(50);

        this.root.getChildren().add(player);
    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

    }

    public StackPane getRoot() {
        return root;
    }
}
