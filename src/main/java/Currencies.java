
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Currencies class provides various methods for conversion of currencies
 * 
 * @author Billy Stanton
 * @since 12/13/23
 * @version 1.0
 */
public class Currencies {
    
    public double getConversion(double baseAmount, String baseCurrency, String targetCurrency) throws MalformedURLException, IOException {
        // GET request
        URL url = new URL("https://v6.exchangerate-api.com/v6/306aa222cfd43e8ff567e5f3/pair/" + baseCurrency + "/" + targetCurrency + "/" + baseAmount);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        System.out.println(request.toString());
        
        // Parse JSON Response
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        
        System.out.println(jsonobj.getAsJsonObject());
        
        return jsonobj.get("conversion_result").getAsDouble();
    }
    
//    public void getRate(String currency) throws MalformedURLException, IOException {
//        // GET request
//        URL url = new URL("https://v6.exchangerate-api.com/v6/306aa222cfd43e8ff567e5f3/latest/" + currency);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.connect();
//        System.out.println(request.toString());
//
//        // Parse JSON Response
//        JsonParser jp = new JsonParser();
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//        JsonObject jsonobj = root.getAsJsonObject();
//
//        System.out.println(jsonobj.get("conversion_rates").getAsJsonObject().get("EUR"));
//    }
}
