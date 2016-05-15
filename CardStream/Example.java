
import CardstreamExample.Card;
import CardstreamExample.Cardstream;
import CardstreamExample.Customer;
import CardstreamExample.Merchant;

import java.util.HashMap;

public class Example {

    public static void main(String args[]) {

        // Initialise the Cardstream object
        Cardstream cs = new Cardstream();

        cs.setPreSharedKey("cCircle4Take40Idea");
        cs.setAmount(10);
        cs.setType(1);
        cs.setAction("PREAUTH");
        cs.setCountryCode(826);
        cs.setCurrencyCode(826);
        cs.setUniqueIdentifier("26482843");
        cs.setOrderRef("ref");

        //card fields
       // cs.setXref(""); 
        Card card = new Card();
//        card.setCardNumber("4929421234600821");
        card.setCardCVV("356");
        card.setCardStartYear("07");
        card.setCardStartMonth("04");
        card.setCardExpiryMM("09");
        card.setCardExpiryYY("14");
        card.setCardIssueNumber("1");
        cs.setCard(card);
        //verification
       cs.setCallBackUrl("callback");

        //customer details
        Customer customer = new Customer();
        customer.setCustomerName("A Nother");
        customer.setCustomerAddress("Flat 6, Primrose Rise, 347 Lavender Road, Northampton");

        customer.setCustomerPostcode("NN17 8YG");
        customer.setCustomerEmail("a.nother@email.com");
        customer.setCustomerPhone("01234 567890");
        cs.setCustomer(customer);
        //american express and diners club
        cs.addItem("Description", 5, 25);
//        cs.setDiscountValue(20);
        //or
        //cs.setTaxValue(20);
        
        //merchant data
        HashMap merchantData = new HashMap<>();
        merchantData.put("key", "value");
        Merchant merchant = new Merchant("100001",null,merchantData);
        cs.setMerchant(merchant);

        try {

            // Authorise the payment
            cs.Authorise();

            // Ensure the request was sent
            if (!cs.isHttpSuccess()) {
                System.out.println("Request failed");
                return;
            }

            // Check the authorisation response
            if (cs.getAuthResponseCode().equals("0")) {
                System.out.println("Card authorised successfully");
            } else if (cs.getAuthResponseCode().equals("2")) {
                System.out.println("Card areferred");
            } else if (cs.getAuthResponseCode().equals("4")) {
                System.out.println("Card decline - keep card");
            } else if (cs.getAuthResponseCode().equals("5")) {
                System.out.println("Card declined");
            } else if (cs.getAuthResponseCode().equals("30")) {
                System.out.println("Authorisation failed: " + cs.getAuthMessage());
            } else {
                System.out.println("Unknown Cardstream response: "+cs.getAuthResponseCode()+": " + cs.getAuthMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
