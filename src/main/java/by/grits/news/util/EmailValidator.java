package by.grits.news.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public static boolean validateEmailInput(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
