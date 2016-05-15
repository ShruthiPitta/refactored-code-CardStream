package composemethod;

public class example2 {
    public static void main(String[] args) {

    }

    boolean pensionEligile(double age) {
        return ageGreaterThanSixty(age);
    }

    private boolean ageGreaterThanSixty(double age) {
        return age > 60;
    }



}
