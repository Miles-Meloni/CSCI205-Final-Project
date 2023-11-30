package org.cscigroup3project.MVC.model;

import org.cscigroup3project.MVC.model.gameObject.GameObject;
import org.cscigroup3project.MVC.model.player.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    //TODO: fix test to use GameObjects now
    private Inventory inventory;
    private GameObject item1;
    private GameObject item2;

    @BeforeEach
    public void setUp() {
        // Initialize a new inventory and some test items before each test
        inventory = new Inventory();
        item1 = new GameObject(0,0,1,1,null);
        item2 = new GameObject(10,10,10,10,null);
    }

    @Test
    public void testAddItem() {
        // Add an item to the inventory
        inventory.addItem(item1);

        // Check if the item is in the inventory
        assertTrue(inventory.containsItem(item1));
    }

    @Test
    public void testContainsItem() {
        // Add items to the inventory
        inventory.addItem(item1);
        inventory.addItem(item2);

        // Check if the inventory contains both items
        assertTrue(inventory.containsItem(item1));
        assertTrue(inventory.containsItem(item2));
    }

    @Test
    public void testRemoveItemByObject() {
        // Add items to the inventory
        inventory.addItem(item1);
        inventory.addItem(item2);

        // Remove one of the items
        boolean removed = inventory.removeItemByObject(item1);

        // Check if the item was successfully removed
        assertTrue(removed);
        assertTrue(inventory.containsItem(item2));
        assertFalse(inventory.containsItem(item1));
    }

    @Test
    public void testRemoveItemByIndex() {
        // Add items to the inventory
        inventory.addItem(item1);
        inventory.addItem(item2);

        // Remove an item by index
        Object removedItem = inventory.removeItemByIndex(0);

        // Check if the correct item was removed
        assertEquals(item1, removedItem);
        assertFalse(inventory.containsItem(item1));
    }
}