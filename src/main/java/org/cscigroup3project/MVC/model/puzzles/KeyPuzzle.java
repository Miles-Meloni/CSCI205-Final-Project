/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Abigail Motter
 * Section: 9am MWF
 * Date: 11/15/2023
 * Time: 9:01 AM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model
 * Class: DoorPuzzle
 *
 * Description: A simple puzzle that requires two keys to unlock a door
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.puzzles;

import org.cscigroup3project.MVC.model.gameObject.Key;
import org.cscigroup3project.MVC.model.player.Inventory;

/**
 * Takes in the player inventory and two key objects, checks if the keys are in the inventory
 * If they are, the puzzle is set to true/solved
 */
public class KeyPuzzle implements Puzzle {
    /** true if the puzzle is solved, false if not **/
    public boolean isSolved;
    /** the inventory that holds the players game objects **/
    public Inventory inventory;
    /** a key that is needed to solve the puzzle **/
    public Key key1;
    /** the second key that is needed to solve the puzzle **/
    public Key key2;

    /**
     * takes in the inventory from the player, and the two keys from the Room manager
     *
     * @param i - player's current inventory
     * @param k1 - the first key needed to solve the puzzle
     * @param k2 - the second key needed to solve the puzzle
     */
    public KeyPuzzle(Inventory i, Key k1, Key k2){
        inventory = i;
        key1 = k1;
        key2 = k2;
    }

    /**
     * Checks if the inventory has both key1 and key2, and if so, the puzzle is solved
     */
    public void solve(){
        //check if BOTH keys are in the inventory
        if(inventory.containsItem(key1) && inventory.containsItem(key2)){
            // remove keys from inventory
            inventory.removeItemByObject(key1);
            inventory.removeItemByObject(key2);
            // set the puzzle to "solved"
            isSolved = true;
        }
    }

    /**
     * @return true if the puzzle is solved, false otherwise
     */
    public boolean getIsSolved(){
        //double check if it is solved
        this.solve();
        return isSolved;
    }
}