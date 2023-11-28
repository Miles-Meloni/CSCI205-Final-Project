/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/27/2023
 * Time: 6:14 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: DoorFrame
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Collidable;
import org.cscigroup3project.MVC.model.room.SpriteType;

import java.util.ArrayList;

public class DoorFrame extends GameObject implements Collidable {

    /** The {@link Rectangle} of the DoorFrame */
    private Rectangle bounds;

    /**
     * Creates a new DoorFrame object
     * @param minX the x position of the door frame
     * @param minY the y position of the door frame
     * @param w the width of the door frame
     * @param h the height of the door frame
     * @param sprites the sprites for the door frame
     */
    public DoorFrame(int minX, int minY, int w, int h, ArrayList<Image> sprites){
        super(minX, minY, w, h, sprites);
        this.bounds = new Rectangle(minX, minY, w, h);
    }

    /**
     * Sets the sprites for the door frame
     * @param spriteType1 the sprite for the first frame
     */
    public void setSprite(SpriteType spriteType1){
        super.setSprite(spriteType1);
    }

    /** Getter for the {@link Rectangle} of this Door */
    public Rectangle getBounds() {
        return bounds;
    }
}
