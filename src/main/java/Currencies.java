/**
 * The Currencies class provides various methods for conversion of currencies
 * 
 * @author Billy Stanton
 * @since 12/13/23
 * @version 1.0
 */
public class Currencies {
    
    /**
     * The getUSDRate method returns the usd rate for the specified currency
     * @param baseCurrency the currency selected
     * @return the usd rate for the currency selected, or 0.00 if currency not found
     */
    public double getUSDRate(String baseCurrency) {
        // initialize usd rate
        double rate = 0.00;
        
        // find usd rate
        switch(baseCurrency) {
            case "USD" -> rate = 1.00;
            case "EUR" -> rate = 1.08;
            case "JPY" -> rate = 0.0069;
            case "GBP" -> rate = 1.25;
            case "AUD" -> rate = 0.66;
            case "CAD" -> rate = 0.74;
            case "CNY" -> rate = 0.14;
            case "CNH" -> rate = 7.1898;
            case "HKD" -> rate = 0.13;
            case "NZD" -> rate = 0.61;
            default -> rate = 0.00;
        }
        
        // return usd rate
        return rate;
    }
    
    /**
     * The getConversion method takes the base amount and base currency and converts it to the target currency
     * @param baseAmount the base amount of money
     * @param baseCurrency the base currency
     * @param targetCurrency the target currency to convert the base to
     * @return the amount of money equivalent to the base amount, in the target currency
     */
    public double getConversion(double baseAmount, String baseCurrency, String targetCurrency) {
        // Get the usd rate of the base currency
        double usdRate = getUSDRate(baseCurrency);
        
        // Convert the base amount in base currency to usd amount
        double usdAmount = baseAmount * usdRate;
        
        // Get the usd rate of the target currency
        double targetRate = getUSDRate(targetCurrency);
        
        // Convert the base amount (in usd) to the target currency amount
        return usdAmount / targetRate;
        
    }
}
