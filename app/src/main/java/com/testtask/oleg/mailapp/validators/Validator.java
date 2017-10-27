package com.testtask.oleg.mailapp.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleg on 27.10.2017.
 */

public class Validator {

    public boolean isEmailValid(String value){

        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public boolean isPhoneValid(String value){

        Pattern pattern = Pattern.compile("^\\+\\d{1}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

}
