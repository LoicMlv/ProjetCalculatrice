package projetcalculatrice.Controller;

public class ButtonInfo {

    boolean isOperator;
    boolean isEquals;
    boolean isDot;
    boolean isNumber;
    boolean isClear;
    boolean isDelete;
    boolean isConversion;
    boolean isOBracket;
    boolean isCBracket;
    boolean isSubstract;
    boolean isOperation;
    boolean isPi;

    public ButtonInfo (String buttonText){

        readButtonValue(buttonText);

    }

    private void readButtonValue(String buttonText) {
        switch (buttonText){
            case "C" : isClear = true; break;

            case "DEL" : isDelete = true; break;

            case "1" : isNumber = true; break;
            case "2" : isNumber = true; break;
            case "3" : isNumber = true; break;
            case "4" : isNumber = true; break;
            case "5" : isNumber = true; break;
            case "6" : isNumber = true; break;
            case "7" : isNumber = true; break;
            case "8" : isNumber = true; break;
            case "9" : isNumber = true; break;
            case "0" : isNumber = true; break;
            case "PI" : isPi = true; break;

            case "." :  isDot = true; break;

            case "(" :  isOBracket = true; break;
            case ")" :  isCBracket = true; break;

            case "+" : isOperator = true; break;
            case "-" :  isOperator = true; isSubstract = true; break;
            case "*" : isOperator = true; break;
            case "/" :  isOperator = true; break;
            case "%" : isOperator = true; break;
            case "^(2)" : isOperator = true; break;
            case "^(3)" : isOperator = true; break;
            case "^(10)" : isOperator = true; break;
            case "^(" : isOperator = true; break;
            case  "^(1/2)" : isOperator = true; break;
            case  "^(1/3)" : isOperator = true; break;
            case  "^(1/" : isOperator = true; break;
            case  "*10^" : isOperator = true; break;

            case "SIN" : isOperation = true; break;
            case "COS" : isOperation = true; break;
            case "TAN" : isOperation = true; break;
            case "ASI" : isOperation = true; break;
            case "ACO" : isOperation = true; break;
            case "ATA" : isOperation = true; break;
            case "!" : isOperation = true; break;
            case "e" : isOperation = true; break;
            case "log" : isOperation = true; break;
            case "ln" : isOperation = true; break;



            case "+/-": isConversion = true; break;

            case "=" : isEquals = true; break;

            default : System.out.println("BUTTON TYPE NOT FOUND");
        }

    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isOperation() { return isOperation; }

    public boolean isOperator() {
        return isOperator;
    }

    public boolean isEquals() {
        return isEquals;
    }

    public boolean isDot() {
        return isDot;
    }

    public boolean isClear() {
        return isClear;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public boolean isConversion() { return isConversion; }

    public boolean isOBracket() {
        return isOBracket;
    }

    public boolean isCBracket() { return isCBracket; }

    public boolean isPi() { return isPi; }


}