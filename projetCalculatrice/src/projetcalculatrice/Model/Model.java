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