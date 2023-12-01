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

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;
import org.cscigroup3project.MVC.model.player.Player;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Storable;

import java.util.ArrayList;

/**
 * Class that represents keys that can be picked up, equipped, and used by the {@link Player}.
 */
public class Key extends GameObject implements Storable, Interactible {

    /** key ID, used to stop keys from being conflated by the inventory system*/

    /**The key display text */
    private ArrayList<String> TEXT_ARRAY = new ArrayList<>();



    public Key(int x, int y, ArrayList<Image> sprites, String id, int duplicateID) {
        super(x, y, 16, 16, sprites, id);
        super.setDuplicateID(duplicateID);

        //Create the text to be displayed upon interaction
        TEXT_ARRAY.add(">A key of some sort.");
        TEXT_ARRAY.add(">It is made of copper and some sort of plastic.");
        TEXT_ARRAY.add(">You wonder if there's somewhere you can put it.");
        TEXT_ARRAY.add(">Somewhere other than the floor, that is.");
        super.setTextArray(TEXT_ARRAY);
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