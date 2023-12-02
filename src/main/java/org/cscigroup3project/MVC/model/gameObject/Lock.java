/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: MILES MELONI
 * Section: 9:00
 * Date: 11/30/23
 * Time: 1:08 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: Lock
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.model.gameObject.objectInterface.Interactible;

import java.util.ArrayList;

public class Lock extends StorageObject implements Interactible {

    /**The display text when there is no keys, one key, and two keys, respectively*/
    private ArrayList<String> TEXT_ARRAY_0 = new ArrayList<>();
    private ArrayList<String> TEXT_ARRAY_1 = new ArrayList<>();
    private ArrayList<String> TEXT_ARRAY_2 = new ArrayList<>();


    public Lock(int x, int y, ArrayList<Image> sprites) {
        super(x, y, 16, 16, sprites, null);

        //Create the text to be displayed upon interaction
        TEXT_ARRAY_0.add(">It appears to be a lock of some sort.");
        TEXT_ARRAY_0.add(">If only there was a conveniently shaped object to put in it.");
        TEXT_ARRAY_0.add(">Or two conveniently shaped objects.");
        super.setTextArray(TEXT_ARRAY_0);

        //Create text for other interactions
        TEXT_ARRAY_1.add(">It still is not opening.");
        TEXT_ARRAY_1.add(">Maybe it needs another key.");

        TEXT_ARRAY_2.add(">The lock is unlocked.");
        TEXT_ARRAY_2.add(">You can leave it alone now.");
    }

    /**
     * how the player interacts with the interactable
     */

    @Override
    public void react() {

    }

    /**
     * Checks to make sure only a key can be put in the inventory
     * @param object - the object that will be stored.
     * @return - whether that object is a key or not
     */
    @Override
    public boolean isAccepted(GameObject object){
        return object instanceof Key;
    }
}