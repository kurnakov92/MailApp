package com.testtask.oleg.mailapp.validators;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Oleg on 27.10.2017.
 */
public class ValidatorTest {

    Validator validator = new Validator();

    @Test
    public void isPhoneValid() throws Exception {
//        String value1 = "89133841262";
//        assertTrue(validator.isPhoneValid(value1));
    }

    @Test
    public void isEmailValid() throws Exception {
        String value1 = "qweqweqwe";
        assertFalse(validator.isEmailValid(value1));

       String value2 = "android@gmail.com";
        assertTrue(validator.isEmailValid(value2));

        String value3 = "mymail@com";
        assertFalse(validator.isEmailValid(value3));

        String value4 = "mymail.com";
        assertFalse(validator.isEmailValid(value4));
    }

}