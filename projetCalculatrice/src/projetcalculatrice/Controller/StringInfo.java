package projetcalculatrice.Controller;

public class StringInfo {

    boolean isEmpty;
    boolean isLastCharacterNumber;
    boolean isLastCharacterOperator;
    boolean isLastCharacterDot;
    boolean isLastCharacterOBracket;
    boolean isLastCharacterCBracket;
    int lastCharIndex;
    int previousCharIndex;
    boolean isPreviousCharOBracket;

    public StringInfo(String computationText){

        readStringValue(computationText);

    }

    private void readStringValue(String computationText) {

        if (computationText.equals("")) {
            isEmpty = true;
        } else {
            lastCharIndex = computationText.length()-1;
            previousCharIndex = computationText.length()-2;
            String lastChar = computationText.substring(lastCharIndex);
            String previousChar = computationText.substring(lastCharIndex);
            if(previousChar.equals("(")){
                isPreviousCharOBracket = true;
            }

            switch(lastChar){
                case "0" : isLastCharacterNumber = true; break;
                case "1" : isLastCharacterNumber = true; break;
                case "2" : isLastCharacterNumber = true; break;
                case "3" : isLastCharacterNumber = true; break;
                case "4" : isLastCharacterNumber = true; break;
                case "5" : isLastCharacterNumber = true; break;
                case "6" : isLastCharacterNumber = true; break;
                case "7" : isLastCharacterNumber = true; break;
                case "8" : isLastCharacterNumber = true; break;
                case "9" : isLastCharacterNumber = true; break;

                case "(" : isLastCharacterOBracket = true; break;
                case ")" : isLastCharacterCBracket = true; break;

                case "+" : isLastCharacterOperator= true; break;
                case "-" : isLastCharacterOperator= true; break;
                case "%" : isLastCharacterOperator= true; break;
                case "*" : isLastCharacterOperator= true; break;
                case "/" : isLastCharacterOperator= true; break;

                case "." : isLastCharacterDot = true; break;



                default : System.out.println("Cannot parse last character!");

            }

        }


    }

    public boolean isComputationTextEmpty() {
        return isEmpty;
    }


    public boolean isLastCharacterNumber() {
        return isLastCharacterNumber;
    }

    public boolean isLastCharacterOperator() {
        return isLastCharacterOperator;
    }

    public boolean isLastCharacterDot() {
        return isLastCharacterDot;
    }

    public boolean isLastCharacterOBracket() {
        return isLastCharacterOBracket;
    }

    public boolean isLastCharacterCBracket() { return isLastCharacterCBracket; }

    public int getLastCharIndex() {
        return lastCharIndex;
    }

}