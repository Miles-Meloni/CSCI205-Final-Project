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
package org.cscigroup3project;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Object> items;

    /**
     * Empty constructor
     */
    public Inventory(){
        items = new ArrayList<Object>();
    }

    /**
     * Constructor with initial items
     * @param initialItems the initial items in the inventory
     */
    public Inventory(ArrayList<Object> initialItems){
        items = initialItems;
    }

    public void addItem(Object item){
        items.add(item);
    }

    public boolean removeItemByObject(Object item){
        return items.remove(item);
    }

    public Object removeItemByIndex(int index){
        return items.remove(index);
    }




}
