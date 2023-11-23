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

    /** Name of the key object */
    private String name;

    public Key(int x, int y, ArrayList<Image> sprites, String id) {
        super(x, y, 16, 16, sprites, id);
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