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

import java.util.ArrayList;

public class Doorway extends GameObject{

        private boolean isLocked;

        public Doorway(int x, int y, int w, int h, ArrayList<Image> sprites) {
            super(x, y, w, h, sprites);
            this.isLocked = false;
        }

        void setIsLocked(boolean isLocked){
            this.isLocked = isLocked;
        }

        public Rectangle getBounds(){
            return super.getBounds();
        }

        public boolean isLocked() {
            return isLocked;
        }
}
