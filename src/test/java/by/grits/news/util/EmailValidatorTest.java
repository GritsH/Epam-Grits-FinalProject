package by.grits.news.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {


    @Test
    void validateEmailInput() {
        boolean result = EmailValidator.validateEmailInput("email@gmail.com");
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotValidateIfWrongEmailInput() {
        boolean result = EmailValidator.validateEmailInput("asdsad4548adw");
        Assertions.assertFalse(result);
    }

    @Test
    void shouldNotValidateIfInputIsEmpty() {
        boolean result = EmailValidator.validateEmailInput("");
        Assertions.assertFalse(result);
    }

}