package org.cscigroup3project.MVC.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple tests to make sure that the puzzle works outside JavaFx
 */
class KeyPuzzleTest {
    /** the first key to be added to the inventory **/
    private Key k1 = new Key("key1");
    /** the second key to be added to the inventory **/
    private Key k2 = new Key("key2");
    /** the first player's inventory **/
    private Inventory inventory1 = new Inventory();
    /** the second player's inventory **/
    private Inventory inventory2 = new Inventory();
    /** the first key puzzle that the test will be solving **/
    private KeyPuzzle kP1 = new KeyPuzzle(inventory1, k1, k2);
    /** the second key puzzle that the test will be solving **/
    private KeyPuzzle kP2 = new KeyPuzzle(inventory2, k1, k2);

    @BeforeEach
    void setUp() {
        inventory1.addItem(k1);
        inventory2.addItem(k2);
    }

    @AfterEach
    void tearDown() {
    }


    /**
     * Assesses if the initial key will solve the puzzle (it will not)
     * adds the remaining key, and checks if the puzzle is solved (it will be)
     */
    @Test
    void getIsSolved() {
        assertFalse(kP1.getIsSolved());
        inventory1.addItem(k2);
        assertTrue(kP1.getIsSolved());
    }

    /**
     * Checks if the solve method will solve the puzzle
     */
    @Test
    void solve() {
        assertFalse(kP2.isSolved);
        inventory2.addItem(k1);
        kP2.solve();
        assertTrue(kP2.isSolved);
    }
}