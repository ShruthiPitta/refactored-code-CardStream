package composemethod;

import java.util.HashMap;
import java.util.Map;

public class example1 {
    public static void main(String[] args) {

    }

    public Map<String, Double> breakGrossSalary() {
        double grossSalary = grossSalary();
        Map<String, Double> salaryBreak = new HashMap<String, Double>();
        double basic = grossSalary * 30f / 100f;
        salaryBreak.put("basic", basic);
        double hra = grossSalary * 20f / 100f;
        salaryBreak.put("hra", hra);
        double cityCompensatoryAll = grossSalary * 30f / 100f;
        salaryBreak.put("cityCompensatoryAll", cityCompensatoryAll);
        double conveyance = 800;
        salaryBreak.put("conveyance", conveyance);
        double specialBalance = grossSalary - basic - hra - cityCompensatoryAll - conveyance;
        salaryBreak.put("specialBalance", specialBalance);

        return salaryBreak;
    }

    public double grossSalary() {
        /*implementaiton to fetch gross salary from some where like ui, db*/
        return 2000;
    }
}
