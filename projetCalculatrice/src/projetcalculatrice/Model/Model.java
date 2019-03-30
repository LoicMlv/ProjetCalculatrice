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

        MathsOperationList possibleOperations = new MathsOperationList();
        MathsTrigoOperationsList possibleTrigoOperations = new MathsTrigoOperationsList();
        operationsTokens = new StringParser(currentInputString).getTokens();
        String string = "";
        // on verifie si le calcul comprend des parentheses
        if (currentInputString.contains("(")) {
            for (int i = 0; i < operationsTokens.size(); i++) {
                // si l'element de operationsTokens n'est pas un operateur (+,-,* ...) on rentre dans le if
                //rappel operationsTokens est du type 3+3
                if (!possibleOperations.contains(operationsTokens.get(i)) && !possibleTrigoOperations.contains((operationsTokens.get(i)))) {
                    LinkedList<String> opeTokens = new LinkedList<>();
                    System.out.printf("ok");
                    //on crée une liste de tokens à partir de la liste precedente pour differencier les nombres des operateurs
                    opeTokens = new StringParser(operationsTokens.get(i)).getTokens();
                    //si le premier ou le dernier element de opeTokens n'est pas un operateur on rentre dans le if
                    // rappel : opeTokens est du type 3 / + / 3
                    if (!possibleOperations.contains(opeTokens.get(0)) && !possibleOperations.contains(opeTokens.get(opeTokens.size() - 1))) {
                        // on effectue le calcul à l'interieur des parentheses pour que l'ordre des priorites des calculs
                        System.out.println(opeTokens);

                        opeTokens = performMathInSequence(opeTokens, possibleOperations, possibleTrigoOperations);
                        operationsTokens.set(i, opeTokens.get(0));
                    }
                }
                // on ajoute à une chaine de caractere en fonction des fonctions precedentes, un operateur ,un nombre
                // ou une ensemble d'operateur + nombre
                string = string + operationsTokens.get(i);
            }
        } else {
            string = currentInputString;
        }
        operationsTokens = new StringParser(string).getTokens();
        operationsTokens = performMathInSequence(operationsTokens, possibleOperations, possibleTrigoOperations);

        boolean hasOnlyOneToken = (operationsTokens.size() == 1);

        if (hasOnlyOneToken) {
            setCurrentTotal(operationsTokens.get(0));
        } else {
            System.out.println("uhh.. something went wrong? LOL!");
        }

    }

    private LinkedList<String> performMathInSequence(LinkedList<String> operationTokens, MathsOperationList possibleOperations, MathsTrigoOperationsList possibleTrigoOperations) {
        for (String operation : possibleTrigoOperations) {
            operationTokens = performTrigoOperations(operation, operationTokens);
        }
        for (String operation : possibleOperations) {
            operationTokens = performOperations(operation, operationTokens);
        }
        return operationTokens;
    }

    private LinkedList<String> performOperations(String operation, LinkedList<String> tokens) {

        boolean isOperationCompleted = false;
        while (isOperationCompleted == false) {

            if (tokens.contains(operation)) {
                System.out.println("tokens : " + tokens);
                int operatorIndex = tokens.indexOf(operation);
                int firstOperandIndex = operatorIndex - 1;
                int secondOperandIndex = operatorIndex + 1;
                String firstOperand = tokens.get(operatorIndex - 1);
                if (firstOperand.equals("PI")) firstOperand = Double.toString(Math.PI);
                String secondOperand = tokens.get(operatorIndex + 1);
                if (secondOperand.equals("PI")) secondOperand = Double.toString(Math.PI);

                double computationResult;
                // perform the relevant operation
                switch (operation) {
                    case "^":
                        computationResult = Math.pow(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand));
                        break;
                    case "*":
                        computationResult = Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
                        break;
                    case "/":
                        computationResult = Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
                        break;
                    case "+":
                        computationResult = Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
                        break;
                    case "-":
                        computationResult = Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
                        break;
                    case "%":
                        computationResult = Double.parseDouble(firstOperand) % Double.parseDouble(secondOperand);
                        break;
                    default:
                        computationResult = 69.69;
                        System.out.println("Cannot detect operation");
                        break;
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

    private LinkedList<String> performTrigoOperations(String operation, LinkedList<String> tokens) {

        boolean isOperationCompleted = false;
        while (isOperationCompleted == false) {

            if (tokens.contains(operation)) {
                System.out.println("tokens : " + tokens);
                int operatorIndex = tokens.indexOf(operation);
                int operandIndex = operatorIndex + 1;


                String operand = tokens.get(operatorIndex + 1);
                if (operand.equals("PI")) operand = Double.toString(Math.PI);
                double computationResult;
                // perform the relevant operation
                switch (operation) {
                    case "SIN":
                        computationResult = Math.sin(Double.parseDouble(operand));
                        break;
                    case "COS":
                        computationResult = Math.cos(Double.parseDouble(operand));
                        break;
                    case "TAN":
                        computationResult = Math.tan(Double.parseDouble(operand));
                        break;
                    case "ASI":
                        computationResult = Math.asin(Double.parseDouble(operand));
                        break;
                    case "ACO":
                        computationResult = Math.acos(Double.parseDouble(operand));
                        break;
                    case "ATA":
                        computationResult = Math.atan(Double.parseDouble(operand));
                        break;
                    case "!":
                        computationResult = 1;
                        if (Double.parseDouble(operand) >= 0 && Double.parseDouble(operand)%1 ==0) {
                            for (int i = 2; i <= Double.parseDouble(operand); i++) {
                                computationResult = computationResult * i;
                            }
                        }else{
                            computationResult = 69.69;
                        }

                        break;

                    default:
                        computationResult = 69.69;
                        System.out.println("Cannot detect operation");
                        break;
                }

                // cast the operation back into a String
                String tokenizedComputation = Double.toString(computationResult);
                // remove all relevant tokens
                tokens.remove(operandIndex);
                tokens.remove(operatorIndex);

                // place relevant token into relevant position

                System.out.println(operandIndex);
                tokens.add(operatorIndex, tokenizedComputation);
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
        } catch (Exception ex) {
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