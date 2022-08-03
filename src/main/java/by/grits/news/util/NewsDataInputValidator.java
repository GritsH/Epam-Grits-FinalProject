package by.grits.news.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDataInputValidator {
    private static final String INPUT_PATTERN = "[A-zА-яЁё\\d \\r\\n\\t!@?.&():,;-]+";
    private static final Pattern PATTERN = Pattern.compile(INPUT_PATTERN);
    public static boolean validateInput(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return matcher.matches();
    }
}
