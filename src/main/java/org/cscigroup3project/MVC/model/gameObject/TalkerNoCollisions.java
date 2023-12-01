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
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Collidable;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;

import java.util.ArrayList;

public class TalkerNoCollisions extends GameObject implements Interactible{
    public TalkerNoCollisions(int x, int y, int w, int h, ArrayList<Image> sprites, ArrayList<String> textArray) {
        super(x, y, w, h, sprites);
        super.setTextArray(textArray);
    }

    /**
     * how the player interacts with the interactable
     */
    @Override
    public void react() {

    }

    @Override
    public Rectangle getBounds() {
        //the x and y coordinates are shifted to give the collisions an accurate bounding box.
        //the width and height are shrunken to give easier movement for the player.
        //height is shifted upward more than the width to stop the player from standing on top of things.
        Rectangle bounds = new Rectangle(super.xPos - super.width*0.4, super.yPos - super.height*0.6, super.width*0.8, super.height*0.8);
        return bounds;
    }
}