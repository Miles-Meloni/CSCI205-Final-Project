package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
import org.cscigroup3project.MVC.GameMain;
import org.cscigroup3project.MVC.model.gameObject.Key;
import org.cscigroup3project.MVC.model.player.Inventory;
import org.cscigroup3project.MVC.model.puzzles.KeyPuzzle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple tests to make sure that the puzzle works outside JavaFx
 */
class KeyPuzzleTest {
    //TODO fix this test
    private ArrayList<Image> sprites = new ArrayList<Image>();
    /** the first key to be added to the inventory **/
    private Key k1;
    /** the second key to be added to the inventory **/
    private Key k2;
    /** the first player's inventory **/
    private Inventory inventory1 = new Inventory();
    /** the second player's inventory **/
    private Inventory inventory2 = new Inventory();
    /** the first key puzzle that the test will be solving **/
    private KeyPuzzle kP1;
    /** the second key puzzle that the test will be solving **/
    private KeyPuzzle kP2;

    @BeforeEach
    void setUp() {
        Image sprite = null; // We're not testing sprites here
        sprites.add(sprite);
        k1 = new Key(1, 1, sprites, "key1",0);
        k2 = new Key(1, 1, sprites, "key2",1);
        inventory1.addItem(k1);
        inventory2.addItem(k2);
        kP1 = new KeyPuzzle(inventory1, k1, k2);
        kP2 = new KeyPuzzle(inventory2, k1, k2);
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