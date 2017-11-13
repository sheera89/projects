/**
 * Sandeep Heera
 * ItemTest.java
 * J-Unit tests for the Item class.
 */
package model;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * J-Unit tests for the Item class.
 * 
 * @author Sandeep Heera
 */
public class ItemTest {   
    Item instance;
    
    public ItemTest() {
        instance = new Item("FILA", "Shoes", 1, 4, 50);
    }

    /**
     * Test of getItemName method, of class Item.
     */
    @Test
    public void testGetItemName() {
        System.out.println("getItemName");
        String expResult = "FILA";
        String result = instance.getItemName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getItemType method, of class Item.
     */
    @Test
    public void testGetItemType() {
        System.out.println("getItemType");
        String expResult = "Shoes";
        String result = instance.getItemType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class Item.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        int expResult = 1;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOptimalQuantity method, of class Item.
     */
    @Test
    public void testGetOptimalQuantity() {
        System.out.println("getOptimalQuantity");
        int expResult = 4;
        int result = instance.getOptimalQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Item.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int expResult = 50;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setItemName method, of class Item.
     */
    @Test
    public void testSetItemName() {
        System.out.println("setItemName");
        String newName = "Pumas";
        instance.setItemName(newName);
        assertEquals(newName, instance.getItemName());
    }

    /**
     * Test of setItemType method, of class Item.
     */
    @Test
    public void testSetItemType() {
        System.out.println("setItemType");
        String newType = "Sandals";
        instance.setItemType(newType);
        assertEquals(newType, instance.getItemType());
    }

    /**
     * Test of setQuantity method, of class Item.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int newQuantity = 5;
        instance.setQuantity(newQuantity);
        assertEquals(newQuantity, instance.getQuantity());
    }

    /**
     * Test of setOptimalQuantity method, of class Item.
     */
    @Test
    public void testSetOptimalQuantity() {
        System.out.println("setOptimalQuantity");
        int newOptimalQuantity = 10;
        instance.setOptimalQuantity(newOptimalQuantity);
        assertEquals(newOptimalQuantity, instance.getOptimalQuantity());
    }

    /**
     * Test of setValue method, of class Item.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int newValue = 25;
        instance.setValue(newValue);
        assertEquals(newValue, instance.getValue());
    }  
    
    /**
     * Test of equals method, of class Item.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Item compareTo = new Item("Air Force Ones", "Shoes", 4, 3, 120);
        
        assertEquals(false, instance.equals(compareTo));
        
        compareTo.setItemName("FILA");
        compareTo.setItemType("Shoes");
        compareTo.setQuantity(1);
        compareTo.setOptimalQuantity(4);
        compareTo.setValue(50);
        
        assertEquals(true, instance.equals(compareTo));
    }  
    
     /**
     * Test of hashCode method, of class Item.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int hashCodeOne = instance.hashCode();
        
        Item compareTo = new Item("Air Force Ones", "Shoes", 4, 3, 120);
        int hashCodeTwo = compareTo.hashCode();
        assertEquals(false, hashCodeOne == hashCodeTwo);
        
        compareTo.setItemName("FILA");
        compareTo.setItemType("Shoes");
        compareTo.setQuantity(1);
        compareTo.setOptimalQuantity(4);
        compareTo.setValue(50);
        
        hashCodeTwo = compareTo.hashCode();
        assertEquals(true, hashCodeOne == hashCodeTwo);
    }  
}
