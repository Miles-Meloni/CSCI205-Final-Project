/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: MILES MELONI
 * Section: 9:00
 * Date: 12/1/23
 * Time: 12:22 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.gameObject
 * Class: StorageObject
 *
 * Description: Object that can store objects in an inventory.
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.gameObject;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.model.player.Inventory;

import java.util.ArrayList;

public class StorageObject extends GameObject{

    /** inventory of objects within the storage object */
    private Inventory storageObjectInventory;

    public StorageObject(int x, int y, int w, int h, ArrayList<Image> sprites, ArrayList<GameObject> startingInventory) {
        super(x, y, w, h, sprites);
        storageObjectInventory = new Inventory(startingInventory);
    }

    /**
     * The method for whether an object can be put in the inventory.
     * Default constructor accepts all objects.
     * @param object - the object that will be stored.
     * @return - whether the object can 'fit' int the storage object.
     */
    public boolean isAccepted(GameObject object){
        return true;
    }

    public Inventory getStorageObjectInventory() {
        return storageObjectInventory;
    }



}