package org.cscigroup3project.MVC.model;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {

    private Player player;
    private final double DELTA_DOUBLE = 0.001;

    @BeforeEach
    public void setUp() {
        // Initialize a new player before each test
        player = new Player(new double[]{0,0}, "Test", "src/test/resources/cscigroup3project/Sprites/PS");
    }

    @Test
    public void testMove() {
        // Save the initial position
        double initialX = player.getxPos();
        double initialY = player.getyPos();

        // Move the player up
        player.move(Direction.UP);

        // Check if the Y position has decreased by 4
        assertEquals(initialX, player.getxPos(), DELTA_DOUBLE);
        assertEquals(initialY - 4, player.getyPos(), DELTA_DOUBLE);

        // Move the player right
        player.move(Direction.RIGHT);

        // Check if the Y position has decreased by 4 (same as previous)
        // AND the X position has increased by 4
        assertEquals(initialX + 4, player.getxPos(), DELTA_DOUBLE);
        assertEquals(initialY - 4, player.getyPos(), DELTA_DOUBLE);
    }

    @Test
    public void testPickUpItem() {
        // Create an item
        Object item = new Object();

        // Pick up the item
        player.pickUpItem(item);

        // Check if the item is in the player's inventory
        assertTrue(player.getInventory().containsItem(item));
    }

    @Test
    public void testGetRidOfItem() {
        // Create an item
        Object item = new Object();

        // Put the item in the player's inventory
        player.pickUpItem(item);

        // Try to get rid of the item
        boolean removed = player.getRidOfItem(item);

        // Check if the item was successfully removed
        assertTrue(removed);
        assertFalse(player.getInventory().containsItem(item));
    }
}
