package CardstreamExample;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PaymentGateway {
    private final TreeMap<String, String> paymentDetailMap = new TreeMap<>();
    private final HashMap<String, String> responseMap = new HashMap<>();
    private static PaymentGateway paymentGateway = new PaymentGateway();
    private String response;

    private PaymentGateway(){
    }

    public static PaymentGateway getPaymentGateway(){
        return paymentGateway;
    }

    public Map pay(Cardstream cs) throws UnsupportedEncodingException {
        buildPaymentDetailsMap(cs);
        Set keySet = paymentDetailMap.keySet();
        Iterator iterator = keySet.iterator();
        boolean first = true;
        String reqString = new String();
        while (iterator.hasNext()) {
            if (!first) {
                reqString += "&";
            }
            first = false;
            String propertyKey = (String) iterator.next();

                reqString += URLEncoder.encode(propertyKey, "ISO-8859-1") + "=" + URLEncoder.encode(paymentDetailMap.get(propertyKey), "ISO-8859-1");
          }
        reqString += "&signature=" + hashFormData(reqString + cs.getPreSharedKey());
        response = HttpService.postRequest(reqString);

        return parseResponse();
    }

    private Map parseResponse() {
        if (response.length() > 0) {
            for (String kvPairStr : response.split("&")) {
                String[] kvPair = kvPairStr.split("=");

                if (kvPair.length == 2) {
                    responseMap.put(kvPair[0], kvPair[1]);
                }
            }
        }
        return responseMap;
    }

    private void buildPaymentDetailsMap(Cardstream cs) {
        Card card = cs.getCard();
        Customer customer = cs.getCustomer();
        Merchant merchant = cs.getMerchant();

        addCardDetailsToPaymentDetailMap(card);
        addCustomerDetailToPaymentDetailMap(customer);
        addMrchantDetailsToPaymentDetailMap(merchant);


        paymentDetailMap.put("countryCode", "" + cs.getCountryCode());
        paymentDetailMap.put("amount", "" + cs.getAmount());
        paymentDetailMap.put("currencyCode", "" + cs.getCurrencyCode());
        paymentDetailMap.put("uniqueIdentifier", cs.getUniqueIdentifier());


    }

    private void addMrchantDetailsToPaymentDetailMap(Merchant merchant) {
        HashMap<String,String> merchantData = merchant.getMerchantData();
        if(merchantData!=null) {
            for (String key : merchantData.keySet()) {
                paymentDetailMap.put("merchantData[" + key + "]", merchantData.get(key));

            }
        }
    }

    private void addCustomerDetailToPaymentDetailMap(Customer customer) {
        paymentDetailMap.put("customerName", customer.getCardName());
        if(customer.getCustomerAddress()!=null)
        paymentDetailMap.put("customerAddress", customer.getCustomerAddress());
        if(customer.getCustomerPostcode()!=null)
        paymentDetailMap.put("customerPostcode ", customer.getCustomerPostcode());
        if(customer.getCustomerEmail()!=null)
        paymentDetailMap.put("customerEmail",customer.getCustomerEmail());
        if(customer.getCustomerPhone()!=null)
        paymentDetailMap.put("customerPhone",customer.getCustomerPhone());
    }

    private void addCardDetailsToPaymentDetailMap(Card card) {
        paymentDetailMap.put("cardExpiryMM", card.getCardExpiryMM());
        paymentDetailMap.put("cardExpiryYY", card.getCardExpiryYY());
        paymentDetailMap.put("cardNumber", card.getCardNumber());
        if(card.getCardIssueNumber()!=null) {
            paymentDetailMap.put("cardIssueNumber", card.getCardIssueNumber());
        }
        if(card.getCardStartMonth()!=null) {
            paymentDetailMap.put("cardStartMonth", card.getCardStartMonth());
        }
        if(card.getCardStartYear()!=null) {
            paymentDetailMap.put("cardStartYear", card.getCardStartYear());
        }
        if(card.getCardCVV()!=null) {
            paymentDetailMap.put("cardCVV", card.getCardCVV());
        }
    }


    private String hashFormData(String Data) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(Data.getBytes());

            byte[] mb = md.digest();
            String out = "";
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
            //System.out.println(out.length());
            //System.out.println("CRYPTO: " + out);

            return out;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;

    }

}
