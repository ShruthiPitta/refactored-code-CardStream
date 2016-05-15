package composemethod;

public class example3 {
    public static void main(String[] args) {

    }

    public int getTotalEmployees() {
        int totalCount = 0;
        int northCount = globalNorthCount();
        int southCount = globalSouthCount();
        totalCount = northCount + southCount;
        return totalCount;
    }

    public int globalNorthCount() {
        /*some implementation*/
        return 100;
    }

    public int globalSouthCount() {
        /*some implementation*/
        return 100;
    }

}
