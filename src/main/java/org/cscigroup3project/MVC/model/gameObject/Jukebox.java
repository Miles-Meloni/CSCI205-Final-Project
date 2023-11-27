/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Abigail Motter
 * Section: 9am MWF
 * Date: 11/27/2023
 * Time: 4:14 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: Jukebox
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;

import java.util.ArrayList;

public class Jukebox extends GameObject implements Interactible {
    public Jukebox(int x, int y, ArrayList<Image> sprites, String id){
        super(x, y, 16, 16, sprites, id);
    }
    public Rectangle getBounds(){
        Rectangle bounds = new Rectangle(super.xPos, super.yPos, super.width, super.height);
        return bounds;
    }

    public void playDisk(Disk disk){

        //TODO - change the ambient music to the music connected to the disk
    }
    public void react(){
    }

}