/**
 * Sandeep Heera
 * CarePackageTest.java
 * J-Unit tests for the CarePackage class.
 */
package model;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * J-Unit tests for the CarePackage class.
 * 
 * @author Sandeep Heera
 */
public class CarePackageTest {
    CarePackage instance;
    ArrayList<Item> itemList;
    ArrayList<Item> inventoryList;
    
    public CarePackageTest() {
        inventoryList = new ArrayList<>();
        Item tomatoSoupI = new Item("Campbell's Tomato Soup", "Food", 90, 5, 4);
        Item bandAidsI = new Item("Band-Aids", "Medical", 400, 50, 2);
        Item beefJerkyI = new Item("Beef Jerky", "Food", 53, 40, 1);
        Item flashLightI = new Item("Flash Light", "Other", 19, 10, 15);
        inventoryList.add(tomatoSoupI);
        inventoryList.add(bandAidsI);
        inventoryList.add(beefJerkyI);
        inventoryList.add(flashLightI);
        
        itemList = new ArrayList<>();
        Item tomatoSoup = new Item("Campbell's Tomato Soup", "Food", 10, 0, 4);
        Item bandAids = new Item("Band-Aids", "Medical", 10, 0, 2);
        Item beefJerky = new Item("Beef Jerky", "Food", 10, 0, 1);
        Item flashLight = new Item("Flash Light", "Other", 1, 0, 15);
        
        itemList.add(tomatoSoup);
        itemList.add(bandAids);
        itemList.add(beefJerky);
        itemList.add(flashLight);
        
        instance = new CarePackage("One Week Survival", itemList);
        
        assertEquals(false, instance == null);
    }

    /**
     * Test of getPackageName method, of class CarePackage.
     */
    @Test
    public void testGetPackageName() {
        System.out.println("getPackageName");
        String expResult = "One Week Survival";
        String result = instance.getPackageName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getItemList method, of class CarePackage.
     */
    @Test
    public void testGetItemList() {
        System.out.println("getItemList");
        ArrayList<Item> expResult = itemList;
        ArrayList<Item> result = instance.getItemList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class CarePackage.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPackageValue method, of class CarePackage.
     */
    @Test
    public void testGetPackageValue() {
        System.out.println("getPackageValue");
        int expResult = 0;
        int result = instance.getPackageValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPackageName method, of class CarePackage.
     */
    @Test
    public void testSetPackageName() {
        System.out.println("setPackageName");
        String newName = "One Week Survival lol";
        instance.setPackageName(newName);
        assertEquals(newName, instance.getPackageName());
    }

    /**
     * Test of setItemList method, of class CarePackage.
     */
    @Test
    public void testSetItemList() {
        System.out.println("setItemList");
        ArrayList<Item> newList = null;
        instance.setItemList(newList);
        assertEquals(null, instance.getItemList());
        instance.setItemList(itemList);
    }

    /**
     * Test of setQuantity method, of class CarePackage.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int newQuantity = 5;
        instance.setQuantity(inventoryList);
        assertEquals(newQuantity, instance.getQuantity());
    }

    /**
     * Test of setPackageValue method, of class CarePackage.
     */
    @Test
    public void testSetPackageValue() {
        System.out.println("setPackageValue");
        instance.setPackageValue();
        int expResult = 85;
        assertEquals(expResult, instance.getPackageValue());
    }

    /**
     * Test of itemExists method, of class CarePackage.
     */
    @Test
    public void testItemExists() {
        System.out.println("itemExists");
        String itemName = "Campbell's Tomato Soup";
        boolean expResult = true;
        boolean result = instance.itemExists(itemName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getItem method, of class CarePackage.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");
        Item tomatoSoup1 = new Item("Campbell's Tomato Soup", "Food", 10, 0, 4);
        String itemName = "Campbell's Tomato Soup";
        Item expResult = tomatoSoup1;
        Item result = instance.getItem(itemName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addItem method, of class CarePackage.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        String itemName = "Jello";
        String itemType = "Food";
        int quantity = 19;
        int itemValue = 1;
        instance.addItem(itemName, itemType, quantity, itemValue,
                         inventoryList);
        assertEquals(true, instance.itemExists(itemName));
        instance.deleteItem("Jello", inventoryList);
    }

    /**
     * Test of getItemQuantity method, of class CarePackage.
     */
    @Test
    public void testGetItemQuantity() {
        System.out.println("getItemQuantity");
        String itemName = "Campbell's Tomato Soup";
        int expResult = 10;
        int result = instance.getItemQuantity(itemName);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteItem method, of class CarePackage.
     */
    @Test
    public void testDeleteItem() {
        System.out.println("deleteItem");
        String itemName = "Flash Light";
        instance.deleteItem(itemName, inventoryList);
        assertEquals(false, instance.itemExists(itemName));
        Item flashLight = new Item("Flash Light", "Other", 1, 0, 15);
        instance.addItem(flashLight.getItemName(), flashLight.getItemType(),
                         flashLight.getQuantity(), flashLight.getValue(),
                         inventoryList);
    }

    /**
     * Test of updateItemName method, of class CarePackage.
     */
    @Test
    public void testUpdateItemName() {
        System.out.println("updateItemName");
        String itemName = "Campbell's Tomato Soup";
        String newName = "Tomato Soup";
        instance.updateItemName(itemName, newName);
        assertEquals(newName, instance.getItem(newName).getItemName());
        instance.updateItemName(newName, itemName);
    }

    /**
     * Test of updateItemType method, of class CarePackage.
     */
    @Test
    public void testUpdateItemType() {
        System.out.println("updateItemType");
        String itemName = "Beef Jerky";
        String newType = "Meat";
        instance.updateItemType(itemName, newType);
        assertEquals(newType, instance.getItem(itemName).getItemType());
    }

    /**
     * Test of updateItemQuantity method, of class CarePackage.
     */
    @Test
    public void testUpdateItemQuantity() {
        System.out.println("updateItemQuantity");
        String itemName = "Beef Jerky";
        int newQuantity = 9;
        instance.updateItemQuantity(itemName, newQuantity, inventoryList);
        assertEquals(newQuantity, instance.getItem(itemName).getQuantity());
    }

    /**
     * Test of updateItemValue method, of class CarePackage.
     */
    @Test
    public void testUpdateItemValue() {
        System.out.println("updateItemValue");
        String itemName = "Band-Aids";
        int newValue = 35;
        instance.updateItemValue(itemName, newValue);
        assertEquals(newValue, instance.getItem(itemName).getValue());
    }

    /**
     * Test of equals method, of class CarePackage.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object toCompare = new CarePackage("Test", itemList);
        instance.setItemList(itemList);
        instance.setPackageName("Test");
        boolean expResult = true;
        boolean result = instance.equals(toCompare);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CarePackage.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CarePackage test = new CarePackage(instance.getPackageName(),
                                           instance.getItemList());
        assertEquals(test.hashCode(), instance.hashCode());
    }
}
