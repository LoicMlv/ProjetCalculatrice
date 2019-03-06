package projetcalculatrice.Model;

import projetcalculatrice.Model.MathsOperationList;

import java.util.LinkedList;
import java.util.Observable;

public class Model extends Observable {

    private String currentTotal;
    private String currentInputString;

    public Model() {
        currentTotal = "0";
        currentInputString = "";
    }

    public void computeString() {

        LinkedList<String> operationTokens = new StringParser(currentInputString).getTokens();

        MathsOperationList possibleOperations = new MathsOperationList();

        operationTokens = performMathInSequence(operationTokens, possibleOperations);

        boolean hasOnlyOneToken = (operationTokens.size() == 1);

        if (hasOnlyOneToken) {
            setCurrentTotal(operationTokens.get(0));
        } else {
            System.out.println("uhh.. something went wrong? LOL!");
        }

    }

    private LinkedList<String> performMathInSequence(LinkedList<String> operationTokens, MathsOperationList possibleOperations) {
        for (String operation : possibleOperations) {
            operationTokens = performOperations(operation, operationTokens);
        }
        return operationTokens;
    }

    private LinkedList<String> performOperations(String operation, LinkedList<String> tokens) {

        boolean isOperationCompleted = false;

        while (isOperationCompleted == false) {
            if (tokens.contains(operation)) {
                int operatorIndex = tokens.indexOf(operation);
                int firstOperandIndex = operatorIndex - 1;
                int secondOperandIndex = operatorIndex + 1;

                String firstOperand = tokens.get(operatorIndex - 1);
                String secondOperand = tokens.get(operatorIndex + 1);
                float computationResult;

                // perform the relevant operation
                switch (operation) {
                    case "*":computationResult = Float.parseFloat(firstOperand)*Float.parseFloat(secondOperand);break;
                    case "/":computationResult = Float.parseFloat(firstOperand)/Float.parseFloat(secondOperand);break;
                    case "+":computationResult = Float.parseFloat(firstOperand)+Float.parseFloat(secondOperand);break;
                    case "-":computationResult = Float.parseFloat(firstOperand)-Float.parseFloat(secondOperand);break;
                    case "%":computationResult = Float.parseFloat(firstOperand)%Float.parseFloat(secondOperand);break;
                    default:computationResult = (float) 69.69;
                        System.out.println("Cannot detect operation"); break;
                }

                // cast the operation back into a String
                String tokenizedComputation = Float.toString(computationResult);

                // remove all relevant tokens
                tokens.remove(secondOperandIndex);
                tokens.remove(operatorIndex);
                tokens.remove(firstOperandIndex);

                // place relevant token into relevant position
                tokens.add(firstOperandIndex, tokenizedComputation);

            } else {

                isOperationCompleted = true;
                return tokens;

            }

        }

        return tokens;
    }

    public void Clear() {
        currentTotal = "0";
        currentInputString = "";

        setChanged();

        CalcDisplayData update = new CalcDisplayData();
        update.setComputationText(currentInputString);
        update.setCurrentTotal(currentTotal);

        notifyObservers(update);

    }
    public void Conversion(){
        // Récupération de tous les nombres un par un
        String[] numberTable = currentInputString.split("[+|/|%|*|-]");

        // Récupération du dernier nombre qui est donc le nombre à convertir
        String number2Convert = numberTable[numberTable.length-1];
        String symbol2Convert;
        String symbol;
        String postSymbol;
        boolean symbolCheck = false;
        boolean substractionCheck = false;
        if (currentInputString.length() > number2Convert.length()){
            // Récupération du symbole avant le dernier nombre pour savoir la conversion à faire
            symbol2Convert = currentInputString.substring(currentInputString.length() - number2Convert.length() - 1 , currentInputString.length() - number2Convert.length() );
            // Si le symbole à convertir appartient (%/*) alors on passe le boolean à vrai, ce qui permettra de garder ce symbole et d'y ajouter le caratère "-" après
            if (symbol2Convert.equals("%") || symbol2Convert.equals("*") || symbol2Convert.equals("/")){
                symbolCheck = true;
            }
            if(currentInputString.length() - number2Convert.length() > 2){
                // Récupération du caractère juste avant le symbole à convertir
                symbol = currentInputString.substring(currentInputString.length() - number2Convert.length() - 2, currentInputString.length() - number2Convert.length() - 1);
                // Si ce caractère appartient (%/*) alors on passe le boolean à vrai, ce qui permettra de garder ce symbole sans y ajouter le caractère "+" après
                if(symbol.equals("%") || symbol.equals("*") || symbol.equals("/")){
                    substractionCheck = true;
                }
            }

            // Récupération des nombres et des symboles qui se trouve avant le symbole du nombre à convertir
            postSymbol = currentInputString.substring(0,currentInputString.length() - number2Convert.length() - 1);

            // Si il n'y a pas de valeur avant le symbole et que le symbole à convertir est "+" alors le boolean passe à vrai
            // ce qui permettra de ne pas ajouter un +
            // c'est le cas quand la valeur entré est -9
            // il n'y a rien avant le -, et lors de la converion on veut 9 et pas +9
            if(postSymbol.length() == 0 && symbol2Convert.equals("-")){
                substractionCheck = true;
            }
        }else{
            symbol2Convert = "+";
            postSymbol ="";
        }


        // Modification de la valeur du symbole
        if(symbolCheck){
            symbol2Convert += "-";
        }else if(substractionCheck){
            symbol2Convert = "";
        }else{
            if(symbol2Convert.equals("-") ){
                symbol2Convert = "+";
            }else if(symbol2Convert.equals("+")){
                symbol2Convert = "-";
            }else{
                symbol2Convert += "-";
            }
        }



        // On recréer la chaine de calcul avec la conversion effectuée
        currentInputString = postSymbol + symbol2Convert + number2Convert;
        setChanged();
        CalcDisplayData update = new CalcDisplayData();
        update.setComputationText(currentInputString);

        notifyObservers(update);

    }

    public void Delete() {
        currentTotal = "0";
        StringBuilder str = new StringBuilder(currentInputString);
        try {
            str.deleteCharAt(currentInputString.length() - 1);
        }catch(Exception ex){
                ex.getMessage();
            }
        currentInputString = str.toString();

        setChanged();

        CalcDisplayData update = new CalcDisplayData();
        update.setComputationText(currentInputString);
        update.setCurrentTotal(currentTotal);

        notifyObservers(update);

    }



    public void setComputationText(String newInputString) {

        currentInputString = newInputString;

        setChanged();

        CalcDisplayData update = new CalcDisplayData();
        update.setComputationText(newInputString);

        notifyObservers(update);

    }

    public void setCurrentTotal(String newTotal) {
        float floatTotal = Float.parseFloat(newTotal);

        int intTotal = (int) floatTotal;

        setCurrentTotalAsIntValueIfPossible(floatTotal, intTotal);

        setChanged();

        CalcDisplayData update = new CalcDisplayData();
        update.setCurrentTotal(currentTotal);

        notifyObservers(update);

    }

    private void setCurrentTotalAsIntValueIfPossible(float floatTotal, int intTotal) {
        if (floatTotal == intTotal) {
            currentTotal = Integer.toString(intTotal);
        } else {
            currentTotal = Float.toString(floatTotal);
        }
    }

}