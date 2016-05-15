package simplifying_conditional_expressions;

public class DecoomposeConditional {

}

class AirconditionCost {
    int winterStartMonth;
    int winterEndMonth;
    int winterStartDate;
    int winterEndDate;
    int summerStartMonth;
    int summerEndMonth;
    int summerStartDate;
    int summerEndDate;

    float basePrice;
    float winterRate;
    float summerRate;
    float summerServiceCharge;
    float installationCharge;

    AirconditionCost() {
        winterStartMonth = 12;
        winterStartDate = 01;

        winterEndMonth = 01;
        winterEndDate = 31;

        summerStartMonth = 04;
        summerStartDate = 01;

        summerEndMonth = 05;
        summerEndDate = 31;

        basePrice = 25000;
        winterRate = 0.8f;
        summerRate = 1.3f;
        summerServiceCharge = 0.2f;

        installationCharge = 1000;

    }
    float cost(int buyMonth, int buyDate, int quantity) throws Exception {
        boolean invalidDate = false;
        if (!(buyMonth >= 1 && buyMonth <= 12)) {
            invalidDate = true;
        }else {
            if (!(buyDate >= 1 && buyDate <= 31)) {
                invalidDate = true;
            } else {
                if (buyMonth == 2 && buyDate > 29) {
                    invalidDate = true;
                }
            }
        }


        if(invalidDate) {
            throw new Exception("invalid date");
        }

        float totalCost = 0;
        if(buyMonth >= winterStartMonth && buyMonth <= winterEndMonth && buyDate >= winterStartDate && buyDate <= winterEndDate) {
            totalCost = (basePrice + basePrice * winterRate) * quantity;
            totalCost += installationCharge;
        }else if(buyMonth >= summerStartMonth && buyMonth <= summerEndMonth && buyDate >= summerStartDate && buyDate <= summerEndDate) {
            totalCost = basePrice + basePrice * summerRate;
            totalCost += (totalCost * summerServiceCharge) *  quantity;
            totalCost += installationCharge;
        }else{
            totalCost = basePrice * quantity;
            totalCost += installationCharge;
        }

        return totalCost;
    }
}


/*
    decompose conditional - replace conditios with isWinter and isSummer methods
    consolidate conditional expressions - series of conditions to one extract method - get invalid date checks into single function like validDate
    consolidate duplicate conditional fragments - the same fragment of code is in all branches. Bring installationCharge out of all the if ladder.
    Replace nested conditional with guard clauses - method has conditional behavior that does not make clear the normal path of execution. Replace date check with guard clauses.
    remove control flag - remove the invalid date condition. Boolean checks makes the code complex. Use continue, break, return, ... etc exit points if they are applicable.
    replace conditional with polymorphisim - get cost hierarchy outside. Basically we are talking about normal price, winter price and summer price.
    introduce assertion - this programm is aussmed to work only for +ve quantity. Other part of the code do heavy checks for positive quantity. Add Assert.check(quantity > 0);

    Moving Features between classes
    Extract class - extract Date logic to seperate class
    Move Method - move date related logic to Date class step by step.

    organizing data
    Replace Data value with object - change cost from float to money class.

    replace magic numbers

 */