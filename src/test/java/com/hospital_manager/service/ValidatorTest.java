package com.hospital_manager.service;

import com.hospital_manager.services.validation.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = { "123Asd", "uSer23", "veryHardPassword22" })
    void isPasswordValidTrueTest(String password){
        assertTrue(Validator.isPasswordValid(password));
    }

    @ParameterizedTest
    @ValueSource(strings = { "qwerty", "MyPassword", "12345jery" })
    void isPasswordValidFalseTest(String password){
        assertFalse(Validator.isPasswordValid(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"_doctor","main_ACCOUNT","user889"})
    void isLoginValidTrueTest(String login){
        assertTrue(Validator.isLoginValid(login));
    }

    @ParameterizedTest
    @ValueSource(strings = {"{}riten","last@mal","this is login"})
    void isLoginValidFalseTest(String login){
        assertFalse(Validator.isLoginValid(login));
    }
}
