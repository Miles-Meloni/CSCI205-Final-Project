package org.cscigroup3project.MVC.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Object item1;
    private Object item2;

    @BeforeEach
    public void setUp() {
        // Initialize a new inventory and some test items before each test
        inventory = new Inventory();
        item1 = new Object();
        item2 = new Object();
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