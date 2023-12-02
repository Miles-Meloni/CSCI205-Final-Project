/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hunter Gehman
 * Section: 02 - 10 am
 * Date: 11/4/2023
 * Time: 11:05 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project
 * Class: Inventory
 *
 * Description: Class for the inventory of a player
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.player;

import org.cscigroup3project.MVC.model.gameObject.GameObject;

import java.util.ArrayList;

public class Inventory {

    /** All items contained by the inventory */
    private ArrayList<GameObject> items;

    /**
     * Empty constructor
     */
    public Inventory(){
        items = new ArrayList<GameObject>();
    }

    /**
     * Constructor with initial items
     * @param initialItems the initial items in the inventory
     */
    public Inventory(ArrayList<GameObject> initialItems){
        items = initialItems;
    }

    /**
     * adds an item to the player inventory
     * @param item - the object to be added to the inventory
     */
    public void addItem(GameObject item){
        items.add(item);
    }

    /**
     * Checks if the player's inventory contains a specific object
     * @param item - the item that the inventory possible contains
     * @return true if the item is in the inventory, false if it does not
     */
    public boolean containsItem(Object item){return items.contains(item);}

    /**
     * Removes an object from the inventory based on the specific object
     * @param item - the object that is being removed
     * @return true if the item was successfully removed
     */
    public boolean removeItemByObject(Object item){
        return items.remove(item);
    }

    /**
     * Removes an object from the inventory based on its index in the inventory
     * @param index the index of the object to be removed
     * @return the object that was removed
     */
    public GameObject removeItemByIndex(int index){
        return items.remove(index);
    }

    /**
     * Get the current size of the inventory
     * @return the number of elements in the inventory
     */
    public int size(){
        return items.size();
    }


    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Get object without removing it from the inventory
     * @param index the index of the object to be removed
     * @return the object that was selected
     */
    public GameObject getItem(int index) {return items.get(index);}
}
