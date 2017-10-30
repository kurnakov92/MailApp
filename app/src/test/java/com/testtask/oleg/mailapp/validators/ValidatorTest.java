package com.testtask.oleg.mailapp.validators;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Oleg on 27.10.2017.
 */
public class ValidatorTest {

    Validator validator = new Validator();

    private String value;

    @Test
    public void isPasswordValid() throws Exception {
        //Проверка на кол-во символов
        value = "ad1d";
        assertFalse(validator.isPasswordValid(value));

        //проверка на наличие цифр
        value = "adadass";
        assertFalse(validator.isPasswordValid(value));

        //проверка на наличие букв
        value = "123123123";
        assertFalse(validator.isPasswordValid(value));

        value = "a1sd2qwe";
        assertTrue(validator.isPasswordValid(value));
    }


    @Test
    public void isPhoneValid() throws Exception {

        value = "+79133841262";
        assertTrue(validator.isPhoneValid(value));

        value = "89133841262";
        assertTrue(validator.isPhoneValid(value));

        value ="923";
        assertFalse(validator.isPhoneValid(value));
    }

    @Test
    public void isEmailValid() throws Exception {
        value = "qweqweqwe";
        assertFalse(validator.isEmailValid(value));

        value = "android@gmail.com";
        assertTrue(validator.isEmailValid(value));

        value = "mymail@com";
        assertFalse(validator.isEmailValid(value));

        value = "mymail.com";
        assertFalse(validator.isEmailValid(value));
    }

}