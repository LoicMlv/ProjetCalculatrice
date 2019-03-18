package projetcalculatrice.Model;

import projetcalculatrice.Model.MathsOperationList;

import java.util.LinkedList;
import java.util.Observable;
import java.util.stream.Stream;

public class Model extends Observable {

    private String currentTotal;
    private String currentInputString;

    public Model() {
        currentTotal = "0";
        currentInputString = "";
    }

    public void computeString() {
        LinkedList<String> operationsTokens = new LinkedList<>();
        LinkedList<String> opeTokens = new LinkedList<>();

        MathsOperationList possibleOperations = new MathsOperationList();
        operationsTokens = new StringParser(currentInputString).getTokens();
        String string = "";
        if(currentInputString.contains("(")) {

            for (int i=0; i<operationsTokens.size(); i++){
                char c = operationsTokens.get(i).charAt(0);

                if (!possibleOperations.contains(operationsTokens.get(i)) && !possibleOperations.contains(Character.toString(c)))  {
                    opeTokens = new StringParser(operationsTokens.get(i)).getTokens();
                    opeTokens = performMathInSequence(opeTokens, possibleOperations);
                    operationsTokens.set(i, opeTokens.get(0));
                }
                string = string + operationsTokens.get(i) ;
                System.out.println("string" + string);
            }
        }
        else{
            string = currentInputString;
        }
        for (String var : operationsTokens){
            System.out.println(var);
        }
        operationsTokens = new StringParser(string).getTokens();
        operationsTokens = performMathInSequence(operationsTokens, possibleOperations);


        boolean hasOnlyOneToken = (operationsTokens.size() == 1);

        if (hasOnlyOneToken) {
            setCurrentTotal(operationsTokens.get(0));
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
                double computationResult;

                // perform the relevant operation
                switch (operation) {
                    case "*":computationResult = Double.parseDouble(firstOperand)*Double.parseDouble(secondOperand);break;
                    case "/":computationResult = Double.parseDouble(firstOperand)/Double.parseDouble(secondOperand);break;
                    case "+":computationResult = Double.parseDouble(firstOperand)+Double.parseDouble(secondOperand);break;
                    case "-":computationResult = Double.parseDouble(firstOperand)-Double.parseDouble(secondOperand);break;
                    case "%":computationResult = Double.parseDouble(firstOperand)%Double.parseDouble(secondOperand);break;
                    default:computationResult =  69.69;
                        System.out.println("Cannot detect operation"); break;
                }

                // cast the operation back into a String
                String tokenizedComputation = Double.toString(computationResult);

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
        double doubleTotal = Double.parseDouble(newTotal);
        int intTotal = (int) doubleTotal;

        setCurrentTotalAsIntValueIfPossible(doubleTotal, intTotal);

        setChanged();

        CalcDisplayData update = new CalcDisplayData();
        update.setCurrentTotal(currentTotal);

        notifyObservers(update);

    }

    private void setCurrentTotalAsIntValueIfPossible(double doubleTotal, int intTotal) {
        if (doubleTotal == intTotal) {
            currentTotal = Integer.toString(intTotal);
        } else {
            currentTotal = Double.toString(doubleTotal);
        }
    }

}