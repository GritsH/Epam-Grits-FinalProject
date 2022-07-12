package by.grits.news.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        passwordEncoder = new PasswordEncoder();
    }

    @Test
    void shouldMatch() throws NoSuchAlgorithmException {
        String password = "password";
        String password2 = "password";

        String encodedPassword = passwordEncoder.encode(password);
        String encodedPassword2 = passwordEncoder.encode(password2);
        assertEquals(encodedPassword, encodedPassword2);
    }

    @Test
    void shouldNotMatch() throws NoSuchAlgorithmException {
        String password = "password";
        String password2 = "notPassword";

        String encodedPassword = passwordEncoder.encode(password);
        String encodedPassword2 = passwordEncoder.encode(password2);
        assertNotEquals(encodedPassword, encodedPassword2);
    }

}