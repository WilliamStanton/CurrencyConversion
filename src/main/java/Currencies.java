import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 * The Currencies class provides various methods for conversion of currencies
 * 
 * @author Billy Stanton
 * @since 12/13/23
 * @version 1.0
 */
public class Currencies {
    
    // Init Variables
    private String apiKey;
    private String[][] supportedCurrencies;
    
    // No-arg Constructor
    public Currencies() {
        // Attempt to load API Key from ENV
        try {
            // Load env
            Dotenv dotenv = Dotenv.load();
            apiKey = dotenv.get("API_KEY"); // grab API Key
            
            // Try api key
            try {
                URL url = new URL("https://v6.exchangerate-api.com/v6/" + apiKey + "/codes");
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                // If api key is invalid, exit
                if (request.getResponseCode() != 200) {
                    JOptionPane.showMessageDialog(null, "INVALID API KEY", "Currency Conversion Application", 0, null);
                    System.exit(0);
                }
            }
            catch(MalformedURLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Currency Conversion Application", 0, null);
                System.exit(0);
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Currency Conversion Application", 0, null);
                System.exit(0);
            }
        }
        catch(ExceptionInInitializerError e) {
                JOptionPane.showMessageDialog(null, "UNABLE TO LOAD API KEY", "Currency Conversion Application", 0, null);
                System.exit(0);
        }
        
        // Load supported currencies
        try {
            // GET request
            URL url = new URL("https://v6.exchangerate-api.com/v6/" + apiKey + "/codes");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            
            // Parse JSON Response
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Get nested JSON
            JsonArray results = jsonobj.getAsJsonArray("supported_codes");
            supportedCurrencies = new String[results.size()][2];
            
            // Dump to array
            for (int i = 0; i < results.size(); i++) {
                String currencyResults = results.get(i).toString().replaceAll("\\[|\\]|\"", "");
                int splitIndex = currencyResults.indexOf(",");
                supportedCurrencies[i][0] = currencyResults.substring(0, splitIndex); // currency code
                supportedCurrencies[i][1] = currencyResults.substring(splitIndex+1); // currency name
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * The getConversion method method accepts a base amount, base currency, and converts it to the amount
     * equivalent to the accepted target currency
     * 
     * @param baseAmount the base amount
     * @param baseCurrency the currency that the base amount is in
     * @param targetCurrency the target currency to convert the base to
     * 
     * @return the amount in the target currency
     */
    public double getConversion(double baseAmount, String baseCurrency, String targetCurrency) {
        // GET request
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + baseAmount);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            System.out.println(request.toString());

            // Parse JSON Response
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            
            // Return the result
            return jsonobj.get("conversion_result").getAsDouble();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return -1;
    }
    
    /**
     * The getSupportedCurrencies method returns a 2d array containing currency codes and relating currency names
     * 
     * @return 2d array containing the currency code and currency name
     */
    public String[][] getSupportedCurrencies() {
        return supportedCurrencies;
    }
}
