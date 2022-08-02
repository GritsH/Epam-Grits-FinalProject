package by.grits.news.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDataInputValidator {
    private static final String INPUT_PATTERN = "[A-zА-яЁё\\d !@?.&():,;-]+";
    private static final Pattern PATTERN = Pattern.compile(INPUT_PATTERN);
    public static boolean validateInput(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return matcher.matches();
    }

    public static String makeValid(String input){
        return input.replaceAll(INPUT_PATTERN, " ");
    }
}
