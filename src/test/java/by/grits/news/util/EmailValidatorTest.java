package by.grits.news.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    private EmailValidator validator;

    @BeforeEach
    void setup() {
        validator = new EmailValidator();
    }

    @Test
    void validateEmailInput() {
        boolean result = validator.validateEmailInput("email@gmail.com");
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotValidateIfWrongEmailInput() {
        boolean result = validator.validateEmailInput("asdsad4548adw");
        Assertions.assertFalse(result);
    }

    @Test
    void shouldNotValidateIfInputIsNull() {
        boolean result = validator.validateEmailInput("");
        Assertions.assertFalse(result);
    }

}