package model;

/**
 * Sandeep Heera
 * Item.java
 * This class is a representation of an item in physical inventory.
 * In addition, it is also a representation of an item in a predefined
 * package.
 */
import java.util.Objects;

/**
 * Item class which stores the item's name, type, quantity in inventory,
 * optimal quantity (desired quantity in inventory), and value.
 * 
 * @author Sandeep Heera
 */
public class Item implements java.io.Serializable{
    private static final long serialVersionUID = -7696538378418102993L;
    private String itemName;
    private String itemType;
    private int quantity;
    private int optimalQuantity;
    private int value;
    
    /**
     * Default constructor.
     */
    public Item(){
        this.itemName = new String();
        this.itemType = new String();
        this.quantity = 0;
        this.optimalQuantity = 0;
        this.value = 0;
    }
    
    /**
     * Parameterized constructor.
     * 
     * @param itemName name of the item
     * @param itemType type of the item
     * @param quantity quantity of the item
     * @param optimalQuantity optimal quantity of the item
     * @param value value of the item
     */
    public Item(String itemName, String itemType, int quantity, 
                int optimalQuantity, int value){
        this.itemName = new String(itemName);
        this.itemType = new String(itemType);
        this.quantity = quantity;
        this.optimalQuantity = optimalQuantity;
        this.value = value;
    }
    
    /**
     * Copy constructor.
     * 
     * @param item item to be copied
     */
    public Item(Item item){
        this(item.getItemName(), item.getItemType(), item.getQuantity(), 
             item.getOptimalQuantity(), item.getValue());
    }
    
    /**
     * Returns the name of the item.
     * 
     * @return name of the item
     */
    public String getItemName(){
        return itemName;
    }
    
    /**
     * Returns the type of the item.
     * 
     * @return type of the item
     */
    public String getItemType(){
        return itemType;
    }
    
    /**
     * Returns the quantity of the item.
     * 
     * @return quantity of the item
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * Returns the optimal quantity of the item.
     * 
     * @return optimal quantity of the item
     */
    public int getOptimalQuantity(){
        return optimalQuantity;
    }
    
    /**
     * Returns the value of the item.
     * 
     * @return value of the item
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Sets the item's name to the new name.
     * 
     * @param newName new name of the item
     */
    public void setItemName(String newName){
        itemName = new String(newName);
    }
    
    /**
     * Sets a new type for the item.
     * 
     * @param newType new type of the item
     */
    public void setItemType(String newType){
        itemType = new String(newType);
    }
    
    /**
     * Sets the quantity of the item to the new quantity.
     * 
     * @param newQuantity new quantity of the item
     */
    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
    
    /**
     * Sets the optimal quantity of the item to the new optimal quantity.
     * 
     * @param newOptimalQuantity new optimal quantity of the item
     */
    public void setOptimalQuantity(int newOptimalQuantity){
        optimalQuantity = newOptimalQuantity;
    }
    
    /**
     * Sets the value of the item to the new value.
     * 
     * @param newValue new value of the item
     */
    public void setValue(int newValue){
        value = newValue;
    }
    
    /**
     * Compares the input object to this item and determines if they are the 
     * same.
     * 
     * @param toCompare object to compare with
     * @return true if the items are the same or false otherwise
     */
    @Override
    public boolean equals(Object toCompare){
        if(!(toCompare instanceof Item)){
            return false;
        }
        else{
            Item item = (Item) toCompare;
            if(this.itemName.equals(item.getItemName()) &&
               this.itemType.equals(item.getItemType()) &&
               this.quantity == item.getQuantity() &&
               this.optimalQuantity == item.getOptimalQuantity() &&
               this.value == item.getValue()){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    /**
     * Returns a hash code for this item.
     * 
     * @return hash code for this object
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.itemName, this.itemType, this.quantity,
                            this.optimalQuantity, this.value);
    }
    
    /**
     * Returns a string representation of the Item object. The string will
     * store the fields of the item.
     * 
     * @return string representation of this item
     */
    @Override
    public String toString(){
        String item = "Item Name: " + this.getItemName() + ", Item Type: " + 
                      this.getItemType() + ", Item Quantity: " +
                      this.getQuantity() + ", Optimal Quantity: " + 
                      this.getOptimalQuantity() + ", Item Value: " + 
                      this.getValue() + "\n";
        return item;
    }
}