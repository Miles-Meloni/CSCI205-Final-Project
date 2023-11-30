/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Abigail Motter
 * Section: 9am MWF
 * Date: 11/29/2023
 * Time: 8:53 PM
 *
 * Project: csci205_final_project
 * Package: org.cscigroup3project.MVC.model.puzzles
 * Class: Jukebox
 *
 * Description:
 *
 * ****************************************
 */
package org.cscigroup3project.MVC.model.puzzles;

import org.cscigroup3project.MVC.model.gameObject.Jukebox;

public class JukeboxPuzzle implements Puzzle{
    /**the name of the disk that will open the door*/
    String name;
    /** represents whether the puzzle is solved*/
    boolean isSolved = false;
    /**jukebox that will hold the disks*/
    Jukebox jukebox;

    /**
     * Contructs a Jukebox puzzle
     * @param j - the jukebox that will be checked to have the correct disk
     */
    public JukeboxPuzzle(Jukebox j){
        jukebox = j;
    }
    /**
     * @return the boolean that represents if the puzzle has been solved
     */
    @Override
    public boolean getIsSolved() {
        solve();
        return isSolved;
    }

    public void solve(){

    }
}