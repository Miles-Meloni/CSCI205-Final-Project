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

import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.GameModel;

public class GameView {

    /** Model for the traffic light program */
    private GameModel theModel;

    public GameView(GameModel theModel){
        this.theModel = theModel;

        initSceneGraph();
        initStyling();
    }

    /**
     * Initialize the scene graph by creating the GUI componenets
     */
    public void initSceneGraph(){

    }

    /**
     * Initialize styling for the view
     */
    public void initStyling(){

    }

}
