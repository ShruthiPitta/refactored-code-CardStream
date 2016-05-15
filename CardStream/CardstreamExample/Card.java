package CardstreamExample;

public class Card {


    private String cardIssueNumber = "";
    private String cardExpiryMM = "";
    private String cardExpiryYY = "";
    private String cardStartYear;
    private String cardStartMonth;
    private String cardNumber = "";
    private String cardCVV;

    public Card(){

    }

    public Card(String cardCVV, String cardExpiryMM ,String cardExpiryYY, String cardIssueNumber, String cardStartMonth, String cardStartYear){

        this.cardCVV = cardCVV;
        this.cardExpiryMM = cardExpiryMM;
        this.cardExpiryYY = cardExpiryYY;
        this.cardIssueNumber = cardIssueNumber;
        this.cardStartMonth = cardStartMonth;
        this.cardStartYear = cardStartYear;
    }

    public String getCardIssueNumber() {
        return cardIssueNumber;
    }

    public void setCardIssueNumber(String cardIssueNumber) {
        this.cardIssueNumber = cardIssueNumber;
    }

    public String getCardExpiryMM() {
        return cardExpiryMM;
    }

    public void setCardExpiryMM(String cardExpiryMM) {
        this.cardExpiryMM = cardExpiryMM;
    }

    public String getCardExpiryYY() {
        return cardExpiryYY;
    }

    public void setCardExpiryYY(String cardExpiryYY) {
        this.cardExpiryYY = cardExpiryYY;
    }

    public String getCardStartMonth() {
        return cardStartMonth;
    }

    public void setCardStartMonth(String cardStartMonth) {
        this.cardStartMonth = cardStartMonth;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardStartYear() {
        return cardStartYear;
    }

    public void setCardStartYear(String cardStartYear) {
        this.cardStartYear = cardStartYear;
    }

    public Card(String cardIssueNumber, String cardExpiryMM, String cardExpiryYY, String cardStartYear, String cardStartMonth, String cardNumber, String cardCVV) {
        this.cardIssueNumber = cardIssueNumber;
        this.cardExpiryMM = cardExpiryMM;
        this.cardExpiryYY = cardExpiryYY;
        this.cardStartYear = cardStartYear;
        this.cardStartMonth = cardStartMonth;
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
    }

    public void validate() throws Exception {
        validateMonth();
        validateYear();
        validateCardNumber();

    }

    private void validateYear() throws Exception {
        // Check the card expiry year
        String cardExpiryYY = this.getCardExpiryYY();
        if (cardExpiryYY == null || cardExpiryYY.length() == 0) {
            throw new Exception("Card expiry year not set");
        } else if (cardExpiryYY.length() != 2) {
            throw new Exception("Card expiry year must be 2 characters");
        }
    }

    private void validateMonth() throws Exception {
        // Check the card expiry month
        String cardExpiryMM=this.getCardExpiryMM();
        if (cardExpiryMM == null || cardExpiryMM.length() == 0) {
            throw new Exception("Card expiry month not set");
        } else if (cardExpiryMM.length() != 2) {
            throw new Exception("Card expiry month must be 2 characters");
        }
    }

    private void validateCardNumber() throws Exception {
        // Check the card number
        if (cardNumber == null || cardNumber.length() == 0) {
            throw new Exception("Card number not set");
        } else if (cardNumber.length() < 16) {
            throw new Exception("Card number must not be lower than 16 digits");
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
