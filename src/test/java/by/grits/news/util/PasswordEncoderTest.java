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
    void setup(){
        passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    void shouldMatch() throws NoSuchAlgorithmException {
        String password = "password";
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        String encodedPassword = passwordEncoder.encode(password);
        assertEquals("encodedPassword", encodedPassword);
    }

    @Test
    void shouldNotMatch() throws NoSuchAlgorithmException {
        String password = "password";
        when(passwordEncoder.encode(password)).thenReturn("encoded1");

        String encodedPassword = passwordEncoder.encode(password);
        assertNotEquals(password, encodedPassword);
    }

}