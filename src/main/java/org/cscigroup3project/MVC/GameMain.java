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
 * Description: Main view class for the game
 *
 * ****************************************
 */

package org.cscigroup3project.MVC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cscigroup3project.MVC.controller.GameController;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.view.GameView;

public class GameMain extends Application {

    private GameModel theModel;
    private GameView theView;
    private GameController theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // initialize the scene and its default size
        Scene scene = new Scene(theView.getRoot(), 600, 400);

        // initialize the controller
        this.theController = new GameController(this.theModel, this.theView, scene);

        // set the title, scene, and display it!
        primaryStage.setTitle("Basic Game Sim");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        //Initialize the Model and View
        this.theModel = new GameModel();
        this.theView = new GameView(this.theModel);
    }
}
