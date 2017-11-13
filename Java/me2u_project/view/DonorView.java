/**
 * Sandeep Heera
 * DonorView.java
 * This is the view for the donation page.
 */
package view;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 * This class represents the view that donors will see when they want to
 * donate items through the Me2U application.
 * 
 * @author Sandeep Heera
 */
public class DonorView extends javax.swing.JPanel {
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel cartLabel;
    private javax.swing.JList<String> cartList;
    private javax.swing.JLabel cartPointsLabel;
    private javax.swing.JTextField cartPointsTextField;
    private javax.swing.JScrollPane cartQuantityScrollPane;
    private javax.swing.JScrollPane categoryItemsScrollPane;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JList<String> categoryList;
    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JButton donateNowButton;
    private javax.swing.JList<String> itemList;
    private javax.swing.JLabel itemsLabel;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JDialog packagesPopUp;
    private javax.swing.JScrollPane packagesScrollPane;
    private javax.swing.JTextArea packagesTextArea;
    private javax.swing.JLabel pageNameLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JList<String> quantityList;
    private javax.swing.JTextField quantityTextArea;
    private javax.swing.JButton removeItemFromCartButton;   
    
    /**
     * Default Constructor
     */
    public DonorView() {
        initComponents();
        setCategoryList();
    }

    /**
     * This method initializes all of the swing gui elements.
     */
    @SuppressWarnings("unchecked")                       
    private void initComponents() {
        packagesPopUp = new javax.swing.JDialog();
        packagesScrollPane = new javax.swing.JScrollPane();
        packagesTextArea = new javax.swing.JTextArea();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<String>((new DefaultListModel<String>()));
        categoryItemsScrollPane = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<String>((new DefaultListModel<String>()));
        jScrollPane3 = new javax.swing.JScrollPane();
        cartList = new javax.swing.JList<String>((new DefaultListModel<String>()));
        cartQuantityScrollPane = new javax.swing.JScrollPane();
        quantityList = new javax.swing.JList<String>((new DefaultListModel<String>()));
        removeItemFromCartButton = new javax.swing.JButton();
        donateNowButton = new javax.swing.JButton();
        quantityTextArea = new javax.swing.JTextField();
        addItemButton = new javax.swing.JButton();
        categoryLabel = new javax.swing.JLabel();
        itemsLabel = new javax.swing.JLabel();
        cartLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        pageNameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        cartPointsTextField = new javax.swing.JTextField();
        cartPointsLabel = new javax.swing.JLabel();

        packagesPopUp.setResizable(false);

        packagesTextArea.setEditable(false);
        packagesTextArea.setColumns(20);
        DefaultCaret caret = (DefaultCaret)packagesTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        packagesScrollPane.setViewportView(packagesTextArea);

        javax.swing.GroupLayout packagesPopUpLayout = new javax.swing.GroupLayout(packagesPopUp.getContentPane());
        packagesPopUp.getContentPane().setLayout(packagesPopUpLayout);
        packagesPopUpLayout.setHorizontalGroup(
            packagesPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(packagesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        packagesPopUpLayout.setVerticalGroup(
            packagesPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(packagesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );

        categoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoryScrollPane.setViewportView(categoryList);

        itemList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoryItemsScrollPane.setViewportView(itemList);

        cartList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(cartList);

        cartQuantityScrollPane.setViewportView(quantityList);

        removeItemFromCartButton.setText("Remove Item");

        donateNowButton.setText("Donate Now");

        quantityTextArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        addItemButton.setText("Add");

        categoryLabel.setText("Categories");

        itemsLabel.setText("Items");

        cartLabel.setText("Cart");

        quantityLabel.setText("Quantity");

        pageNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pageNameLabel.setText("Me2U Donation Page");

        logoutButton.setText("Logout");

        backButton.setText("Back");

        cartPointsTextField.setEditable(false);
        cartPointsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cartPointsLabel.setText("Cart Points");

        javax.swing.GroupLayout donationPagePanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(donationPagePanelLayout);
        this.setPreferredSize(new Dimension(1225, 800));
        donationPagePanelLayout.setHorizontalGroup(
            donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donationPagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, donationPagePanelLayout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(405, 405, 405)
                        .addComponent(pageNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeItemFromCartButton))
                        .addGap(112, 112, 112))
                    .addGroup(donationPagePanelLayout.createSequentialGroup()
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(donateNowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(donationPagePanelLayout.createSequentialGroup()
                                .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, donationPagePanelLayout.createSequentialGroup()
                                        .addComponent(categoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(categoryItemsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addItemButton)
                                            .addComponent(quantityTextArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(donationPagePanelLayout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(categoryLabel)
                                        .addGap(235, 235, 235)
                                        .addComponent(itemsLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cartLabel)
                                        .addGap(113, 113, 113)))
                                .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartQuantityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(donationPagePanelLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(quantityLabel)))))
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(donationPagePanelLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(cartPointsLabel))
                            .addGroup(donationPagePanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cartPointsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(120, 120, 120))))
        );
        donationPagePanelLayout.setVerticalGroup(
            donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donationPagePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pageNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(donationPagePanelLayout.createSequentialGroup()
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityLabel)
                            .addComponent(cartLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(donationPagePanelLayout.createSequentialGroup()
                                .addComponent(quantityTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(addItemButton))
                            .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(donationPagePanelLayout.createSequentialGroup()
                                    .addComponent(removeItemFromCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cartPointsLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cartPointsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(categoryItemsScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cartQuantityScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))))
                    .addGroup(donationPagePanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(donationPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryLabel)
                            .addComponent(itemsLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(donateNowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
    }                  

    /**
     * Sets the category JList.
     */
    private void setCategoryList(){
        ((DefaultListModel)categoryList.getModel()).addElement("Packages");
        ((DefaultListModel)categoryList.getModel()).addElement("Needed Items");
        ((DefaultListModel)categoryList.getModel()).addElement("Food");
        ((DefaultListModel)categoryList.getModel()).addElement("Clothing");
        ((DefaultListModel)categoryList.getModel()).addElement("Furniture");
        ((DefaultListModel)categoryList.getModel()).addElement("Other");
    }
    
    /**
     * Returns the category JList.
     * 
     * @return category JList
     */
    public JList<String> getCategoryList(){
        return this.categoryList;
    }
    
    /**
     * Returns the cart JList.
     * 
     * @return cart JList
     */
    public JList<String> getCartList(){
        return this.cartList;
    }
    
    /**
     * Returns the category item JList.
     * 
     * @return category item JList
     */
    public JList<String> getItemList(){
        return this.itemList;
    }
    
    /**
     * Returns the quantity JList.
     * 
     * @return quantity JList
     */
    public JList<String> getQuantityList(){
        return this.quantityList;
    }
    
    /**
     * Returns the add item JButton.
     * 
     * @return add item JButton
     */
    public JButton getAddItemButton(){
        return this.addItemButton;
    }
    
    /**
     * Returns the donate now JButton.
     * 
     * @return donate now JButton
     */
    public JButton getDonateNowButton(){
        return this.donateNowButton;
    }
    
    /**
     * Returns the logout JButton.
     * 
     * @return logout JButton
     */
    public JButton getLogoutButton(){
        return this.logoutButton;
    }
    
    /**
     * Returns the remove item JButton.
     * 
     * @return remove item JButton
     */
    public JButton getRemoveItemButton(){
        return this.removeItemFromCartButton;
    }
    
    /**
     * Returns the back JButton.
     * 
     * @return back JButton
     */
    public JButton getBackButton(){
        return this.backButton;
    }
    
    /**
     * Returns the quantity JTextField.
     * 
     * @return quantity JTextField
     */
    public JTextField getQuantityTextField(){
        return this.quantityTextArea;
    }
    
    /**
     * Returns the cart points JTextField.
     * 
     * @return cart points JTextField
     */
    public JTextField getCartPointsTextField(){
        return this.cartPointsTextField;
    }
    
    /**
     * Returns the packages JTextArea that appears in the packages pop-up 
     * window.
     * 
     * @return packages JTextArea
     */
    public JTextArea getPackagesTextArea(){
        return this.packagesTextArea;
    }
    
    /**
     * Returns the packages JDialog pop-up.
     * 
     * @return packages JDialog pop-up
     */
    public JDialog getPackagesPopUp(){
        return this.packagesPopUp;
    }               
}
