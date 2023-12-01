/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:25 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: GameMain
 *
 * Description:
 * The main game class for the JavaFX application
 * ****************************************
 */

package org.cscigroup3project.MVC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cscigroup3project.MVC.controller.GameController;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.view.GameView;

/**
 * Our main game class for the JavaFX application.
 */
public class GameMain extends Application {

    /** The {@link GameModel} for the game */
    private GameModel theModel;

    /** The {@link GameView} for the game */
    private GameView theView;

    /** The {@link GameController} for the game */
    private GameController theController;

    /**
     * Launches the game
     *
     * @param args String[] to be launched
     */
    public static void main(String[] args) { launch(args); }

    /**
     * Creates a scene, initializes the controller, adds information to the stage,
     * sets the scene on the stage, and shows the scene
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {

        // Initialize the Scene and set its size
        Scene scene = new Scene(theView.getRoot(), 600, 400);
        scene.getStylesheets().add(String.valueOf(GameMain.class.getResource("gameStyling.css")));

        // Initialize the GameController
        this.theController = new GameController(this.theModel, this.theView, scene);

        // Set the title, Scene, and display it!
        primaryStage.setTitle("JUKE...BOX...HERO!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Initializes the {@link GameView} and the {@link GameModel}
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
        //Initialize the Model and View
        this.theModel = new GameModel();
        this.theView = new GameView(this.theModel);
    }
}
