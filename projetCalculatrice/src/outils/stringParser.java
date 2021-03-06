package outils;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class stringParser {

    static public final String WITH_DELIMITERS = "((?<=%1$s)|(?=%1$s))";
    LinkedList<String> response;

    public stringParser(String string) {

        List<String> tokenList = extractTokens(string);
        response = transformToLinkedList(tokenList);

    }

    public LinkedList<String> getTokens() {
        return response;
    }

    private List<String> extractTokens(String string) {

        String[] tokens = string.split(String.format(WITH_DELIMITERS, "[*/+-]"));
        List<String> linkedTokens = Arrays.asList(tokens);

        return linkedTokens;
    }

    private LinkedList<String> transformToLinkedList(List<String> tokenList) {
        LinkedList<String> responses = new LinkedList<String>();
        responses.addAll(tokenList);

        return response;
    }

}
