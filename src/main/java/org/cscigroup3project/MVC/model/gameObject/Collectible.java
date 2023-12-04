/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Keeler Thomas
 * Section: 01 - 9:00am
 * Date: 11/9/2023
 * Time: 10:44 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: Key
 *
 * Description:
 * Class that represents keys that can be used by the Player.
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Storable;
import org.cscigroup3project.MVC.model.player.Player;

import java.util.ArrayList;

/**
 * Class that represents items that can be picked up by the user {@link Player}.
 */
public class Collectible extends GameObject implements Storable, Interactible {


    public Collectible(int x, int y, ArrayList<Image> sprites, ArrayList<String> textArray) {
        super(x, y, 16, 16, sprites);
        super.setDuplicateID(duplicateID);

        //Create the text to be displayed upon interaction
        super.setTextArray(textArray);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle(super.xPos, super.yPos, super.width, super.height);
        return bounds;
    }

    @Override
    public void store() {

    }

    @Override
    public void deploy(int x, int y) {

    }

    @Override
    public void react() {

    }
}