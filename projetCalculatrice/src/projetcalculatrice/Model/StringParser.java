package projetcalculatrice.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringParser {

    static public final String WITH_DELIMITERS = "((?<=%1$s)|(?=%1$s))";
    LinkedList<String> answers;

    public StringParser(String string) {
        List<String> tokenList = new ArrayList<>();

        if (string.contains("(")) {
            tokenList.addAll(extractTokens2(string));
            answers = transformToLinkedList(tokenList);
        } else {
            tokenList.addAll(extractTokens(string));
            answers = transformToLinkedList(tokenList);
        }

    }


    public LinkedList<String> getTokens() {
        return answers;
    }


    private List<String> extractTokens2(String string) {
        List<String> linkedTokens = new ArrayList<>();
        String[] tokensWithBracket = string
                .split(String.format(WITH_DELIMITERS, "[()]"));
        for (String token : tokensWithBracket) {
            if (!(token.equals("(") || token.equals(")"))) {
                // System.out.println("tokens : " + token);
                linkedTokens.add(token);
            }
        }
        return linkedTokens;
    }

    private List<String> extractTokens(String string) {
        String[] tokens = string
                .split(String.format(WITH_DELIMITERS, "PI|ASI|ACO|ATA|SIN|COS|TAN|[*^!/%+-]"));
        List<String> linkedTokens = new ArrayList<>();
        MathsOperationList possibleOperations = new MathsOperationList();
        // on regarde dans la liste des tokens si un "-" est utilsié dans un calcul ou pour ne nombre negatif
        for (int j = 0; j < tokens.length; j++) {
            if(j!=0){
                if (tokens[j].equals("-") && !Character.isDigit(tokens[j-1].charAt(0))) {
                    linkedTokens.add(tokens[j].toString() + tokens[j + 1].toString());
                    j+=1;
                } else {
                    linkedTokens.add(tokens[j]);
                }
            }else{

                if (tokens[j].equals("-")) {
                    linkedTokens.add(tokens[j].toString() + tokens[j + 1].toString());
                    j+=1;
                } else {
                    linkedTokens.add(tokens[j]);
                }
            }
        }
        return linkedTokens;
    }

    private LinkedList<String> transformToLinkedList(List<String> tokenList) {
        LinkedList<String> answers = new LinkedList<>();
        answers.addAll(tokenList);

        return answers;
    }

}