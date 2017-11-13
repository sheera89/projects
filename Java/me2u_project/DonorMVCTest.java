/*
 * Sandeep Heera
 * DonorMVCTest.java
 * This class runs the donor user story of the Me2U application.
 */
import controller.DonorController;
import javax.swing.JFrame;

/**
 * Runs the donor user story of the Me2U application.
 * @author Sandeep Heera
 */
public class DonorMVCTest {
    public static void main(String[] args){        
        DonorController controller = new DonorController();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Donations Page");
                frame.add(controller.getDonorView());
                frame.setSize(controller.getDonorView().getPreferredSize());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}
