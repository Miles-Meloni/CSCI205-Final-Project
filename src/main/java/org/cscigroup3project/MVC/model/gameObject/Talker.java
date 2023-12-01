/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: MILES MELONI
 * Section: 9:00
 * Date: 11/30/23
 * Time: 7:30 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: Talker
 *
 * Description: A game object which is interactive but cannot be picked up. Eg: NPCs, some decorations
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;

import java.util.ArrayList;

public class Talker extends GameObject implements Interactible {
    public Talker(int x, int y, int w, int h, ArrayList<Image> sprites, ArrayList<String> textArray) {
        super(x, y, w, h, sprites);
        super.setTextArray(textArray);
    }

    /**
     * how the player interacts with the interactable
     */
    @Override
    public void react() {

    }
}