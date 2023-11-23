package org.cscigroup3project.MVC.model.puzzles;

/**
 * interface for the puzzles that the game will hold
 */
public interface Puzzle {
    /**holds whether the puzzle has been solved**/
    public boolean isSolved = false;

    /**
     * @return the boolean that represents if the puzzle has been solved
     */
    public boolean getIsSolved();
}
