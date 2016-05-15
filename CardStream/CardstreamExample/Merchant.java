package CardstreamExample;

import java.util.HashMap;

public class Merchant {

    private String merchantId;
    private String merchantPassword;

    private HashMap<String,String> merchantData;

    public HashMap<String, String> getMerchantData() {
        return merchantData;
    }

    public Merchant(String merchantId, String merchantPassword, HashMap<String,String> merchantData) {
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void addMerchantData(String key, String value){
        merchantData.put(key, value);
    }

    public boolean isValid(){
        if (merchantId.isEmpty())
            return false;
        else
            return true;
    }
}
