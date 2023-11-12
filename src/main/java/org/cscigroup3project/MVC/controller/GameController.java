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
 * The Controller of our JavaFX application.
 * ****************************************
 */

package org.cscigroup3project.MVC.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import org.cscigroup3project.MVC.model.Direction;
import org.cscigroup3project.MVC.model.GameModel;
import org.cscigroup3project.MVC.view.GameView;

/**
 * The class implementing the logic and user interactions for the created game.
 */
public class GameController {

    /** The {@link GameModel} for our app */
    private final GameModel theModel;

    /** The {@link GameView} for our app */
    private final GameView theView;

    /** The {@link Scene} for our app */
    private final Scene theScene;

    /**
     * Constructor for the GameController.
     *
     * @param theModel the game's model
     * @param theView the game's view
     * @param theScene the JavaFX scene
     */
    public GameController(GameModel theModel, GameView theView, Scene theScene) {
        this.theModel = theModel;
        this.theView = theView;
        this.theScene = theScene;

        initBindings();
        initEventHandlers();
    }

    /**
     * Initialize event handlers for keyboard input.
     */
    private void initEventHandlers() {
        this.theScene.setOnKeyPressed(this::handleKeyPress);
    }

    /**
     * Initialize property bindings between the player's position and the image view's position.
     */
    private void initBindings() {

        // Bind the PlayerView's position and image to the Player's x, y, and image properties
        theView.getPlayerView().translateXProperty().bind(theModel.getPlayer().getxProperty());
        theView.getPlayerView().translateYProperty().bind(theModel.getPlayer().getyProperty());
        theView.getPlayerView().imageProperty().bind(theModel.getPlayer().playerImageProperty());
    }

    /**
     * Handle key press events for player movement.
     *
     * @param event The {@link KeyEvent} generated by a key press.
     */
    public void handleKeyPress(KeyEvent event) {

        // Match the arrow key input, move the Player in the corresponding direction.
        // Check for collisions after each movement, if there is, move in the opposite direction
            switch (event.getCode()) {
                case DOWN -> { theModel.getPlayer().move(Direction.DOWN); }
                case LEFT -> { theModel.getPlayer().move(Direction.LEFT); }
                case UP -> { theModel.getPlayer().move(Direction.UP); }
                case RIGHT -> { theModel.getPlayer().move(Direction.RIGHT); }
            }
        if (theModel.getPlayer().getBounds().getBoundsInLocal().intersects(
                theModel.getWall().getBounds().getBoundsInLocal())){
            switch (event.getCode()) {
                case DOWN -> { theModel.getPlayer().silentMove(Direction.UP);}
                case LEFT -> { theModel.getPlayer().silentMove(Direction.RIGHT); }
                case UP -> { theModel.getPlayer().silentMove(Direction.DOWN); }
                case RIGHT -> { theModel.getPlayer().silentMove(Direction.LEFT); }
            }
        }
    }
}

