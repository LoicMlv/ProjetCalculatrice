package  projetcalculatrice.Model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringParser {

    static public final String WITH_DELIMITERS = "((?<=%1$s)|(?=%1$s))";
    LinkedList<String> answers;

    public StringParser(String string) {

        List<String> tokenList = extractTokens(string);
        answers = transformToLinkedList(tokenList);

    }

    public LinkedList<String> getTokens() {
        return answers;
    }

    private List<String> extractTokens(String string) {

        String[] tokens = string
                .split(String.format(WITH_DELIMITERS, "[*/%+-]"));
        List<String> linkedTokens = Arrays.asList(tokens);

        return linkedTokens;
    }

    private LinkedList<String> transformToLinkedList(List<String> tokenList) {
        LinkedList<String> answers = new LinkedList<String>();
        answers.addAll(tokenList);

        return answers;
    }

}