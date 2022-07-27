package by.grits.news.service.impl;

import by.grits.news.dao.UserDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.entities.User;
import by.grits.news.service.UserService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.util.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.PASSWORD_SESSION;
import static by.grits.news.command.SessionAttribute.USER_EMAIL_SESSION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @Mock
    private User mockedUser;
    UserService userService;
    Map<String, String> userData = new HashMap<>();
    PasswordEncoder passwordEncoder = new PasswordEncoder();
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    void setup() {
        userService = UserServiceImpl.getInstance();
        userService.init(userDao);

        userData.put(USER_EMAIL_SESSION, "email@gmail.com");
        userData.put(PASSWORD_SESSION, "password");
    }

    @DisplayName("should add user")
    @Test
    void signup() throws ServiceException, DaoException, NoSuchAlgorithmException {
        userService.signup(userData);
        verify(userDao).findByEmail(userData.get(USER_EMAIL_SESSION));
        verify(userDao).insert(userArgumentCaptor.capture());
        verifyNoMoreInteractions(userDao);

        User capturedUser = userArgumentCaptor.getValue();
        String encodedPassword = passwordEncoder.encode(userData.get(PASSWORD_SESSION));

        assertEquals(capturedUser.getEmailAddress(), userData.get(USER_EMAIL_SESSION));
        assertEquals(capturedUser.getPassword(), encodedPassword);
    }

    @DisplayName("should find user by email")
    @Test
    void findByEmail() throws DaoException, ServiceException {
        when(userDao.findByEmail("email@gmail.com")).thenReturn(mockedUser);

        User result = userService.findUserByEmail("email@gmail.com");

        assertEquals(mockedUser, result);
        verify(userDao).findByEmail("email@gmail.com");
        verifyNoMoreInteractions(userDao);
    }

    @DisplayName("should login user")
    @Test
    void login() throws DaoException, ServiceException, NoSuchAlgorithmException {
        String encodedPassword = passwordEncoder.encode(userData.get(PASSWORD_SESSION));

        when(userDao.logIn(userData.get(USER_EMAIL_SESSION), encodedPassword)).thenReturn(mockedUser);

        User result = userService.login(userData);

        assertEquals(mockedUser, result);
        verify(userDao).logIn(userData.get(USER_EMAIL_SESSION), encodedPassword);
        verifyNoMoreInteractions(userDao);

    }

}