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
    boolean isLastCharacterPi;

    public StringInfo(String computationText) {

        readStringValue(computationText);

    }

    private void readStringValue(String computationText) {

        if (computationText.equals("")) {
            isEmpty = true;
        } else {
            lastCharIndex = computationText.length() - 1;
            previousCharIndex = computationText.length() - 2;
            String lastChar = computationText.substring(lastCharIndex);
            char lc = lastChar.charAt(0);
            String previousChar = computationText.substring(lastCharIndex);
            if (previousChar.equals("(")) {
                isPreviousCharOBracket = true;
            }

            if (!Character.isLetter(lc)) {
                switch (lastChar) {
                    case "0":
                        isLastCharacterNumber = true;
                        break;
                    case "1":
                        isLastCharacterNumber = true;
                        break;
                    case "2":
                        isLastCharacterNumber = true;
                        break;
                    case "3":
                        isLastCharacterNumber = true;
                        break;
                    case "4":
                        isLastCharacterNumber = true;
                        break;
                    case "5":
                        isLastCharacterNumber = true;
                        break;
                    case "6":
                        isLastCharacterNumber = true;
                        break;
                    case "7":
                        isLastCharacterNumber = true;
                        break;
                    case "8":
                        isLastCharacterNumber = true;
                        break;
                    case "9":
                        isLastCharacterNumber = true;
                        break;

                    case "(":
                        isLastCharacterOBracket = true;
                        break;
                    case ")":
                        isLastCharacterCBracket = true;
                        break;

                    case "+":
                        isLastCharacterOperator = true;
                        break;
                    case "-":
                        isLastCharacterOperator = true;
                        break;
                    case "%":
                        isLastCharacterOperator = true;
                        break;
                    case "*":
                        isLastCharacterOperator = true;
                        break;
                    case "/":
                        isLastCharacterOperator = true;
                        break;
                    case "^":
                        isLastCharacterOperator = true;
                        break;
                    case "!":
                        isLastCharacterOperator = true;
                        break;


                    case ".":
                        isLastCharacterDot = true;
                        break;

                    default:
                        System.out.println("Cannot parse last character!");

                }
            }
            if (computationText.length() >= 4) {
                if (computationText.substring(computationText.length() - 4).equals("Alea")) {
                    isLastCharacterNumber = true;
                }
                if (computationText.length() >= 3) {
                    String possibleOperande = computationText.substring(computationText.length() - 3);
                    switch (possibleOperande) {
                        case "SIN":
                            isLastCharacterOperator = true;
                            break;
                        case "COS":
                            isLastCharacterOperator = true;
                            break;
                        case "TAN":
                            isLastCharacterOperator = true;
                            break;
                        case "ASI":
                            isLastCharacterOperator = true;
                            break;
                        case "ACO":
                            isLastCharacterOperator = true;
                            break;
                        case "ATA":
                            isLastCharacterOperator = true;
                            break;
                        case "log":
                            isLastCharacterOperator = true;
                            break;
                        case "div":
                            isLastCharacterOperator = true;
                            break;
                    }
                }
                if (computationText.length() >= 2) {
                    String possibleOperande = computationText.substring(computationText.length() - 2);
                    switch (possibleOperande) {
                        case "PI":
                            isLastCharacterPi = true;
                            break;
                        case "ln":
                            isLastCharacterOperator = true;
                            break;

                    }
                }
                if (computationText.length() >= 1) {
                    String possibleOperande = computationText.substring(computationText.length() - 1);
                    switch (possibleOperande) {
                        case "e":
                            isLastCharacterOperator = true;
                            break;
                    }
                }
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

    public boolean isLastCharacterPi() {
        return isLastCharacterPi;
    }

    public boolean isLastCharacterCBracket() {
        return isLastCharacterCBracket;
    }

    public int getLastCharIndex() {
        return lastCharIndex;
    }

}