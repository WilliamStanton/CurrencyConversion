/**
 * The Currency Conversion Application provides a way to convert from a currency
 * to multiple other currencies
 * 
 * @author Billy Stanton
 * @since 12/13/23
 * @version 1.0
 */

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main {
    
    public static Currencies currencyAPI = new Currencies();
    public static ImageIcon icon = new ImageIcon("icon.png");

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        // Load currencies
        String[][] supportedCurrencies = currencyAPI.getSupportedCurrencies();
        String[] supportedCurrenciesNames = new String[supportedCurrencies.length];
        for (int i = 0; i < supportedCurrencies.length; i++) {
            supportedCurrenciesNames[i] = supportedCurrencies[i][0] + ", " + supportedCurrencies[i][1];
        }
        
        // Option Dialog Elements
        // App Title
        JLabel appTitle = new JLabel("Currency Conversion Application");
        appTitle.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Statistics Title
        JLabel statTitle = new JLabel(supportedCurrencies.length + " currencies");
        statTitle.setFont(new Font("Arial", Font.BOLD, 20));
         
        // Base Currency
        // Base Currency Title
        JLabel baseCurrencyTitle = new JLabel("Original currency");
        baseCurrencyTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Base Currency Textbox
        JTextField baseCurrencyTextbox = new JTextField();
        
        // Base Currency Dropdown
        JComboBox baseCurrencyDropdown = new JComboBox(supportedCurrenciesNames);
        
        // Target Currency
        // Target Currency Title
        JLabel targetCurrencyTitle = new JLabel("Target currency");
        targetCurrencyTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Target Currency Dropdown
        JComboBox targetCurrencyDropdown = new JComboBox(supportedCurrenciesNames);

        // Convert the currency
        JButton convert = new JButton("Convert Currencies");
        ActionListener goConvert = (ActionEvent ae) -> {
            // Get results
            try {
                double results = currencyAPI.getConversion(Double.parseDouble(
                      baseCurrencyTextbox.getText()), // base currency
                        supportedCurrenciesNames[baseCurrencyDropdown.getSelectedIndex()].replaceAll(",.*", ""), // base currency amount
                        supportedCurrenciesNames[targetCurrencyDropdown.getSelectedIndex()].replaceAll(",.*", ""));   // target currency

                // Display results
                JOptionPane.showMessageDialog(convert, new DecimalFormat("#.00").format(Double.parseDouble(baseCurrencyTextbox.getText())) + " " + supportedCurrenciesNames[baseCurrencyDropdown.getSelectedIndex()].replaceAll(",.*", "") + " is " + new DecimalFormat("#.00").format(results) + " " + supportedCurrenciesNames[targetCurrencyDropdown.getSelectedIndex()].replaceAll(",.*", ""), "Currency Conversion Application", 0, icon);
            }
            catch(HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(convert, "Invalid Input \n" + e.toString(), "Currency Conversion Application", 0, icon);
            }
        };
        convert.addActionListener(goConvert);
        
        // Display Array
        Object[] display = {
            // App Title
            appTitle,
            statTitle,
            
            // Base Currency
            baseCurrencyTitle,
            baseCurrencyTextbox, baseCurrencyDropdown,
            
            // Target Currency
            targetCurrencyTitle,
            targetCurrencyDropdown,
            
            // Convert the currency
            convert
        };
        
        // Display UI
        JOptionPane.showOptionDialog(null, display, "Currency Conversion Application", 0, -1, icon, new Object[]{}, null);
        
        // Safely exit
        System.exit(0);
    }
}
