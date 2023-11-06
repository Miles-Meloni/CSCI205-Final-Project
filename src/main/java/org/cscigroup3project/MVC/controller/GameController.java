/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/6/2023
 * Time: 9:31 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.controller
 * Class: GameController
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.view.GameView;


/**
 * The class implementing the logic and user interactions
 * for our created game
 */
public class GameController {

    private GameModel theModel;
    private GameView theView;
    private Scene theScene;

    /**
     * Constructor for the GameController
     *
     * @param theModel the game's model
     * @param theView the game's view
     */
    public GameController(GameModel theModel, GameView theView, Scene theScene){
        this.theModel = theModel;
        this.theView = theView;
        this.theScene = theScene;

        initBindings();
        initEventHandlers();
    }

    private void initEventHandlers() {
        this.theScene.setOnKeyPressed(this::handleKeyPress);
    }

    private void initBindings() {
        //TODO all
    }

    public void handleKeyPress(KeyEvent event){
        if (event.getCode() == KeyCode.DOWN){
            System.out.println("DOWN ARROW PRESSED");
        }
    }

}
