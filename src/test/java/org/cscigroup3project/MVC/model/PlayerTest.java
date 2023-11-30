package org.cscigroup3project.MVC.model;

import org.cscigroup3project.MVC.model.gameObject.GameObject;
import org.cscigroup3project.MVC.model.player.Direction;
import org.cscigroup3project.MVC.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {

    private Player player;
    private final double DELTA_DOUBLE = 0.001;

    @BeforeEach
    public void setUp() {
        // Initialize a new player before each test
        player = new Player(new int[]{0,0}, "Test", "Sprites/PS");
    }

    @Test
    public void testMove() {
        // Save the initial position
        int initialX = player.getxPos();
        int initialY = player.getyPos();

        // Move the player up
        player.move(Direction.UP);

        // Check if the Y position has decreased by the move speed
        assertEquals(initialX, player.getxPos(), DELTA_DOUBLE);
        assertEquals(initialY - player.getMOVE_SPEED(), player.getyPos(), DELTA_DOUBLE);

        // Move the player right
        player.move(Direction.RIGHT);

        // Check if the Y position has decreased by the move speed (same as previous)
        // AND the X position has increased by the move speed
        assertEquals(initialX + player.getMOVE_SPEED(), player.getxPos(), DELTA_DOUBLE);
        assertEquals(initialY - player.getMOVE_SPEED(), player.getyPos(), DELTA_DOUBLE);
    }

    @Test
    public void testPickUpItem() {
        // Create an item
        GameObject item = new GameObject(5,5,15,15,null);

        // Pick up the item
        player.pickUpItem(item);

        // Check if the item is in the player's inventory
        assertTrue(player.getInventory().containsItem(item));
    }

    @Test
    public void testGetRidOfItem() {
        // Create an item
        GameObject item = new GameObject(0,0,1,1,null);

        // Put the item in the player's inventory
        player.pickUpItem(item);

        // Try to get rid of the item
        GameObject removed = player.dropItem();

        // Check if the item was successfully removed
        assertEquals(removed,item);
        assertFalse(player.getInventory().containsItem(item));
    }
}
