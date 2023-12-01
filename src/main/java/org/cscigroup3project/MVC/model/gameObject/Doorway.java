/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/29/2023
 * Time: 6:05 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: Doorway
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.cscigroup3project.MVC.model.room.Room;

import java.util.ArrayList;

public class Doorway extends GameObject{

        private boolean isLocked;
        private int nextRoom;

        public Doorway(int x, int y, int w, int h, ArrayList<Image> sprites) {
            super(x, y, w, h, sprites);
            this.isLocked = false;
            this.nextRoom = -1;
        }

        public void setIsLocked(boolean isLocked){
            this.isLocked = isLocked;
        }

        public boolean isLocked() {
        return isLocked;
    }

        public void setNextRoom(int nextRoom) {
            this.nextRoom = nextRoom;
        }

        public int getNextRoom() {
            return nextRoom;
        }


        public Rectangle getBounds(){
        return super.getBounds();
    }
}
