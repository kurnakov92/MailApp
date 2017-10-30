package com.testtask.oleg.mailapp.validators;

import android.telephony.PhoneNumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Oleg on 27.10.2017.
 */

public class Validator {

    private static final String GLOBAL_PHONE_NUMBER_PATTERN = "[\\+]?[0-9.-]+";

    public boolean isEmailValid(String value) {

        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public boolean isPhoneValid(String value) {

        boolean result = false;
        if (value.length() > 12 || value.length() < 11) {
            result = false;
        } else {
            Pattern pattern = Pattern.compile(GLOBAL_PHONE_NUMBER_PATTERN);
            Matcher matcher = pattern.matcher(value);
            result = matcher.matches();
        }

        return result;
    }

    public boolean isPasswordValid(String value) {

        Pattern pattern = Pattern.compile("((?=.*[0-9])(?=.*[a-z])[0-9a-z]{6,12})");
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();

    }

}
