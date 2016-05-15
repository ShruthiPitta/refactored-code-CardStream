package CardstreamExample;

import java.util.HashMap;
import java.util.Map;
// divergent change
public class Cardstream {

    private Boolean httpSuccess = false;
    private Map<String, String> response = new HashMap<>();

    private int amount;
    private int type;
    private String action;
    private String orderRef;
    private int countryCode;
    private int currencyCode;
    private String uniqueIdentifier = "";
    // data clumps
    private Card card;
    Merchant merchant;
    private Customer customer;

    private String  callBackUrl = "";
    private String preSharedKey;


    public int getCountryCode() {
        return countryCode;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public int getAmount() {
        return amount;
    }


    public String getCallBackUrl() {
        return callBackUrl;
    }

    public String getPreSharedKey() {
        return preSharedKey;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Boolean Authorise() throws Exception {
        try {
            validate();
            PaymentGateway paymentGateway = PaymentGateway.getPaymentGateway();
            response = paymentGateway.pay(this);
            if (response == null) {
                return false;
            }
            httpSuccess=true;
            return httpSuccess;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    private void validate() throws Exception {
        customer.validate();
        card.validate();


        // Check the amount
        if (amount == 0) {
            throw new Exception("Amount not set");
        } else if (amount < 10) {
            throw new Exception("Amount must be not be lower than 10");
        }

        // Check the country code
        if (countryCode == 0) {
            throw new Exception("Country code not set");
        }

        // Check the currency code
        if (currencyCode == 0) {
            throw new Exception("Currency code not set");
        }

        // Check the unique identifier
        if (uniqueIdentifier == null || uniqueIdentifier.length() == 0) {
            throw new Exception("Unique identifier not set");
        }

    }

    public void setPreSharedKey(String preSharedKey) {
     
        this.preSharedKey = preSharedKey;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return this.card;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }


    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void addItem(String description, int quantity, int value) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public void setTaxValue(int i) {
//        formData.remove("discountValue");
//        formData.put("taxValue", i + "");
//    }
//
//    public void setDiscountValue(int i) {
//        formData.remove("taxValue");
//        formData.put("discountValue", i + "");
//    }



    public Boolean isHttpSuccess() {
        return this.httpSuccess;
    }

    public String getAuthResponseCode() {
        return this.response.get("responseCode");
    }

    public String getAuthMessage() {
        return this.response.get("responseMessage");
    }


    public void setType(int type) {
        this.type = type;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }
}
