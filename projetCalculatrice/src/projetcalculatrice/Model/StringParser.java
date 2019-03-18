package  projetcalculatrice.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringParser {

    static public final String WITH_DELIMITERS = "((?<=%1$s)|(?=%1$s))";
    LinkedList<String> answers;

    public StringParser(String string) {
        List<String> tokenList= new ArrayList<>();

        if (string.contains("(")){
            tokenList.addAll(extractTokens2(string));
            answers = transformToLinkedList(tokenList);
        }else{
            tokenList.addAll(extractTokens(string));
            answers = transformToLinkedList(tokenList);
        }

    }


    public LinkedList<String> getTokens() {
        return answers;
    }

//    private List<List<String>> extractTokens2(String string){
//        String[] tokens ;
//
//        String str = "";
//
//
//
//        List<List<String>> linkedTokens = Arrays.asList(tokens);
//        return linkedTokens;
//
//    }
//    private List<List<String>> calcWithBrackets(String str ){
//        List<List<String>> calcs = new ArrayList<>();
//        char[] charArray = str.toCharArray();
//
//        for (int i=charArray.length; i>=0; i--){
//            if (charArray[i] == ')') {
//                while (charArray[i] == '(' || charArray[i] == ')'){
//                    // recuperer en utilisant extractTokens la partie apres la parenthese fermante
//                    // tant qu'on ne rencontre pas une autre parenthese
//
//                }
//                char[] array2 = Arrays.copyOfRange(charArray, 0, i - 1);
//                char[] array3 = new char[0];
//                if (charArray.length>=i+1) {
//                    array3 = Arrays.copyOfRange(charArray, i + 1, charArray.length);
//                }
//                String newCalc = String.valueOf(array2) + String.valueOf(array3);
//                calcWithBrackets(newCalc);
//
//            }else if (charArray[i] == '('){
//                char[] array2 = Arrays.copyOfRange(charArray, i+1, charArray.length);
//                List<String> tokenList = extractTokens(String.valueOf(array2));
//                calcs.add(tokenList);
//            }
//        }
//        return calcs;
//    }

    private List<String> extractTokens2(String string) {
        List<String> linkedTokens = new ArrayList<>();
        String[] tokensWithBracket = string
                .split(String.format(WITH_DELIMITERS, "[()]"));
        for(String token : tokensWithBracket){
            if(!(token.equals("(") || token.equals(")"))){
               // System.out.println("tokens : " + token);
                linkedTokens.add(token) ;
            }
        }

        return linkedTokens;
    }

    private List<String> extractTokens(String string) {

        String[] tokens = string
                .split(String.format(WITH_DELIMITERS, "[*/%+-]"));
        List<String> linkedTokens = Arrays.asList(tokens);
        for (String token: tokens) {
            // System.out.println("token" + token);
        }
        return linkedTokens;
    }

    private LinkedList<String> transformToLinkedList(List<String> tokenList) {
        LinkedList<String> answers = new LinkedList<>();
        answers.addAll(tokenList);

        return answers;
    }

}