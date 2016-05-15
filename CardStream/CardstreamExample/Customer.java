package CardstreamExample;

public class Customer {
    private String customerName;
    private String customerAddress;
    private String customerPostcode;
    private String customerEmail;
    private String customerPhone;


    public Customer(){

    }


    public Customer(String customerName,String customerAddress,String customerEmail,String customerPostcode,String customerPhone){
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPostcode = customerPostcode;
        this.customerPhone = customerPhone;
        this.customerName = customerName;
    }


    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostcode() {
        return customerPostcode;
    }

    public void setCustomerPostcode(String customerPostcode) {
        this.customerPostcode = customerPostcode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCardName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void validate() throws Exception {
        // Check the card number
        if (customerName == null || customerName.length() == 0) {
            throw new Exception("Customer Name not set");
        }

    }
}
