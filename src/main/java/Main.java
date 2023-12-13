/**
 * The Currency Conversion Application provides a way to convert from a currency
 * to multiple other currencies
 * 
 * @author Billy Stanton
 * @since 12/13/23
 * @version 1.0
 */

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main {
    
    public static Currencies currencyAPI = new Currencies();
    public static ImageIcon icon = new ImageIcon("logo.png");

    public static void main(String[] args) {
        // Option Dialog Elements
        JLabel title = new JLabel("Currency Conversion Application");
        title.setFont(new Font("Arial", Font.BOLD, 14));
         
        
        
        
        
        // Display Array
        Object[] display = {
            
        };
        
        // Display UI
        JOptionPane.showOptionDialog(null, display, "Currency Conversion Application", 0, -1, icon, new Object[]{}, null);
    }
}
