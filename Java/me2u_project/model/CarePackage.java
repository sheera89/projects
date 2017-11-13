/**
 * Sandeep Heera
 * CarePackage.java
 * This class is a representation of a care package in Me2U's inventory.
 */
package model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class represents pre-defined care packages. These packages have a 
 * pre-determined number of items and are intended to be updated in the 
 * Inventory class.
 * 
 * @author Sandeep Heera
 */
public class CarePackage implements java.io.Serializable{
    private static final long serialVersionUID = -8489812551772814702L;
    private String packageName;
    private ArrayList<Item> itemList;
    int quantity;
    int packageValue;
    
    /**
     * Default constructor.
     */
    public CarePackage(){
        this.packageName = new String();
        this.itemList = new ArrayList<>();
        this.quantity = 0;
        this.packageValue = 0;
    }
    
    /**
     * Parameterized constructor. Must call setPackageValue() and 
     * setPackageQuantity() after the CarePackage object has been 
     * initialized.
     * 
     * @param packageName name of the package
     * @param itemList items contained in the package
     */
    public CarePackage(String packageName, ArrayList<Item> itemList){
        this.packageName = packageName;
        this.itemList = new ArrayList<>(itemList);
        this.quantity = 0;
        this.packageValue = 0;
    }
    
    /**
     * Copy constructor.
     * 
     * @param carePackage care package to be copied
     */
    public CarePackage(CarePackage carePackage){
        this(carePackage.getPackageName(), carePackage.copyItemList());
    }
    
    /**
     * Returns the name of the package.
     * 
     * @return name of the package
     */
    public String getPackageName(){
        return this.packageName;
    }
    
    /**
     * Returns the items contained in the package.
     * @return an arraylist containing the items in the package
     */
    public ArrayList<Item> getItemList(){
        return this.itemList;
    }
    
    /**
     * Returns a deep copy of the item list.
     * 
     * @return deep copy of itemList
     */
    public ArrayList<Item> copyItemList(){
        ArrayList<Item> items = new ArrayList<>();
        
        for(int i = 0;i < this.itemList.size();i++){
            Item itemCopy = new Item(this.itemList.get(i));
            items.add(itemCopy);
        }
        return items;
    }
    
    /**
     * Returns the number of packages that are available.
     * 
     * @return quantity of the package
     */
    public int getQuantity(){
        return this.quantity;
    }
    
    /**
     * Returns the value of the package.
     * 
     * @return value of the package
     */
    public int getPackageValue(){
        return this.packageValue;
    }
    
    /**
     * Sets a new name for the package.
     * 
     * @param newName new name of the package
     */
    public void setPackageName(String newName){
        this.packageName = new String(newName);
    }
    
    /**
     * Sets a new item list for the package.
     * 
     * @param newList arraylist of new items
     */
    public void setItemList(ArrayList<Item> newList){
        this.itemList = newList;
    }
    
    /**
     * Sets the quantity of the care package manually. This should be called
     * when updating the quantity of care packages in the cart.
     * 
     * @param newQuantity new quantity of the cart
     */
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
    
    /**
     * Sets a new quantity for the package. Should be set by the Inventory 
     * class any time inventory changes or the item list for package changes.
     * 
     * @param inventory arraylist of inventory items
     */
    public void setQuantity(ArrayList<Item> inventory){
        if(!this.itemList.isEmpty() && !inventory.isEmpty()){
            int[] available = new int[this.itemList.size()];
            int min, i = 0;
            Iterator it = (Iterator) this.itemList.iterator();
            Iterator invIt;
            Item item, invItem;
            
            while(it.hasNext()){    //iterate through the item list
                item = (Item) it.next();
                invIt = (Iterator) inventory.iterator();
                while(invIt.hasNext()){ //iterate through inventory
                    invItem = (Item) invIt.next();
                    if(invItem.getItemName().equals(item.getItemName())){
                        available[i++] = invItem.getQuantity() /
                                         item.getQuantity();
                    }
                }
            }
            
            min = available[0]; //first element
            //determine the minimum
            for(int j = 1;j < i;j++){
                if(available[j] < min){
                    min = available[j];
                }
            }
            this.quantity = min;
        }
    }
    
    /**
     * Sets a new package value for the package. This method iterates through 
     * the item list and finds the summation of all of the items present to 
     * determine the value of the package. This should be called any time an
     * adjustment is made to the list of items in the package.
     */
    public void setPackageValue(){      
        if(!this.itemList.isEmpty()){
            int newPackageValue = 0;
            Iterator it = (Iterator) this.itemList.iterator();
            Item item;

            while(it.hasNext()){    //iterate through the list
                item = (Item) it.next();
                //add to the new value
                newPackageValue += item.getQuantity() * item.getValue();
            }
            this.packageValue = newPackageValue;
        }
    }
    
    /**
     * Checks to see if there is an item with the name itemName in the package.
     * Returns true if it exists or false otherwise.
     * 
     * @param itemName name of the item to search for
     * @return true if the item exists or false otherwise
     */
    public boolean itemExists(String itemName){
        Iterator it = (Iterator) this.itemList.iterator();
        Item item;
        
        while(it.hasNext()){    //iterate through the list
            item = (Item) it.next();
            if(item.getItemName().equals(itemName)){
                return true;
            }
        }
        return false;   //didn't find the item
    }
    
    /**
     * Returns the item with name itemName if it exists in the item list for
     * this package or null if it is not present.
     * 
     * @param itemName name of the item to search for
     * @return the item with name itemName if it exists or null otherwise
     */
    public Item getItem(String itemName){
        Iterator it = (Iterator) this.itemList.iterator();
        Item item;
        
        while(it.hasNext()){    //iterate through the list
            item = (Item) it.next();
            //if the item has the same name as the parameter, return it
            if(item.getItemName().equals(itemName)){
                return item;
            }
        }
        return null;    //item was not found
    }
    
    /**
     * Adds a new item with the specified parameters and adds it to the item
     * list for this package provided an item with that name does not already 
     * exist.
     * 
     * @param itemName name of the item
     * @param itemType type of the item
     * @param quantity quantity of the item in this package
     * @param itemValue value of the item
     * @param inventory current inventory
     */
    public void addItem(String itemName, String itemType, int quantity,
                        int itemValue, ArrayList<Item> inventory){
        if(!itemExists(itemName)){
            //create new item
            Item item = new Item(itemName, itemType, quantity, 0,
                                 itemValue);
            //add it to the item list
            this.itemList.add(item);
            
            //update quantity
            setQuantity(inventory);
        }
    }
    
    /**
     * Returns the quantity of the item in the item list of the package 
     * provided it exists.
     * 
     * @param itemName name of the item to search for
     * @return quantity of the item or -1 if it is not found
     */
    public int getItemQuantity(String itemName){
        if(itemExists(itemName)){
            return getItem(itemName).getQuantity();
        }
        else{
            return -1;
        }
    }
    
    /**
     * Deletes the item with the same name as the parameter provided it exists.
     * 
     * @param itemName name of the item to be deleted
     * @param inventory current inventory
     */
    public void deleteItem(String itemName, ArrayList<Item> inventory){
        if(itemExists(itemName)){
            Iterator it = (Iterator) this.itemList.iterator();
            Item item;
            
            while(it.hasNext()){    //iterate through the list to find the item
                item = (Item) it.next();
                
                if(item.getItemName().equals(itemName)){
                    it.remove();    //remove item
                    setQuantity(inventory);
                }
            }
        }
    }
    
    /**
     * Updates the name of the item with the name itemName provided
     * it exists.
     * 
     * @param itemName name of the item to be updated in the package list
     * @param newName new name of the item
     */
    public void updateItemName(String itemName, String newName){
        if(itemExists(itemName)){   //if the item exists
            Item item = getItem(itemName);
            
            item.setItemName(newName);  //update the name
        }
    }
    
    /**
     * Updates the type of the item with the name itemName provided
     * it exists.
     * 
     * @param itemName name of the item to be updated in the package
     * @param newType new type of the item
     */
    public void updateItemType(String itemName, String newType){
        if(itemExists(itemName)){   //if the item exists
            Item item = getItem(itemName);
            
            item.setItemType(newType);  //update the type
        }
    }
    
    /**
     * Updates the quantity of the item with the name itemName provided
     * it exists.
     * 
     * @param itemName name of the item to be updated in the package
     * @param newQuantity new quantity of the item
     * @param inventory current inventory
     */
    public void updateItemQuantity(String itemName, int newQuantity,
                                   ArrayList<Item> inventory){
        if(itemExists(itemName)){   //if the item exists
            Item item = getItem(itemName);
            
            item.setQuantity(newQuantity);  //update the quantity
            setQuantity(inventory);
        }
    }
    
    /**
     * Updates the value of the item with the name itemName provided
     * it exists.
     * 
     * @param itemName name of the item to be updated in the package
     * @param newValue new value of the item
     */
    public void updateItemValue(String itemName, int newValue){
        if(itemExists(itemName)){   //if the item exists
            Item item = getItem(itemName);
            
            item.setValue(newValue);  //update the value
        }
    }
    
    /**
     * Compares the input object to this care package and determines if they 
     * are the same.
     * 
     * @param toCompare object to compare with
     * @return true if the care packages are the same or false otherwise
     */
    @Override
    public boolean equals(Object toCompare){
        if(!(toCompare instanceof CarePackage)){
            return false;
        }
        else{
            CarePackage carePackage = (CarePackage) toCompare;
            if(this.packageName.equals(carePackage.getPackageName()) &&
               this.itemList.equals(carePackage.getItemList()) &&
               this.quantity == carePackage.getQuantity() &&
               this.packageValue == carePackage.getPackageValue()){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    /**
     * Returns a hash code for this care package.
     * 
     * @return hash code for this object
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.packageName, this.itemList, this.quantity,
                            this.packageValue);
    }
    
    /**
     * Returns a string representation of the CarePackage object. The string
     * will store the fields of the CarePackage.
     * 
     * @return string representation of this care package
     */
    @Override
    public String toString(){
        String carePackage = "Package Name: " + this.getPackageName();
        carePackage += "\nPackage Quantity: " + this.getQuantity();
        if(!this.itemList.isEmpty()){
            int i = 1;
            Iterator it = (Iterator) this.itemList.iterator();
            Item item;
            carePackage += "\n\tItems in this package: \n";
            
            while(it.hasNext()){    //traverse through the item list
                item = (Item) it.next();
                carePackage += "\tItem " + i++ + ": \n\tItem Name: " + 
                               item.getItemName() + "\n\tItem Type: " +
                               item.getItemType() + "\n\tItem Quantity: " +
                               item.getQuantity() + "\n\n";
            }
        }
        carePackage += "\n";
        
        return carePackage;
    }
}