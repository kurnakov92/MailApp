package com.testtask.oleg.mailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.testtask.oleg.mailapp.validators.Validator;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btnChangePassVisibility;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etPassword;
    private TextView tvCounter;
    private Intent intent;

    //Ключи для обмена данными между Activities
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    private boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        etPhone.addTextChangedListener(new CounterTextWatcher());
        intent = new Intent(this, SendDataActivity.class);
    }

    public void openSendDataActivity(View view) {

        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String pass = etPassword.getText().toString();
        validateFields(email, phone, pass);
        if (result) {
            intent.putExtra(MainActivity.EMAIL, email);
            intent.putExtra(MainActivity.PHONE_NUMBER, phone);
            intent.putExtra(MainActivity.PASSWORD, pass);
            startActivity(intent);
        }

    }

    private void findViews() {
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createNonTerminated(PredefinedSlots.RUS_PHONE_NUMBER));

        etEmail = (EditText) findViewById(R.id.input_email);
        etPhone = (EditText) findViewById(R.id.input_phone);
        formatWatcher.installOn(etPhone);
        etPassword = (EditText) findViewById(R.id.input_password);
        tvCounter = (TextView) findViewById(R.id.phone_number_counter);
    }

    private class CounterTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String value = String.valueOf(s) + "";
            value = toNumericOnlyFormat(value);
            // +1 нужен для того чтобы "+" тоже считался как введенный символ
            tvCounter.setText(value.length() + 1 + "/12");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private String toNumericOnlyFormat(String pNumber) {

        pNumber = pNumber.replaceAll(" ", "");
        pNumber = pNumber.replaceAll("\\+", "");
        pNumber = pNumber.replaceAll("\\(", "");
        pNumber = pNumber.replaceAll("\\)", "");
        pNumber = pNumber.replaceAll("\\-", "");

        return pNumber;
    }

    private void validateFields(String email, String phone, String pass) {
        Validator validator = new Validator();
        Toast toast;

        boolean mailValid = false;
        boolean phoneValid = false;
        boolean passValid = false;

        if (!validator.isEmailValid(email)) {
            toast = Toast.makeText(this, "Введите правильный Email", Toast.LENGTH_SHORT);
            toast.show();
            result = false;
            return;
        } else {
            mailValid = true;
        }

        if (!validator.isPhoneValid(toNumericOnlyFormat(phone))) {
            toast = Toast.makeText(this, "Введите правильный номер телефона", Toast.LENGTH_SHORT);
            toast.show();
            result = false;
            return;
        } else {
            phoneValid = true;
        }

        if (!validator.isPasswordValid(pass)) {
            toast = Toast.makeText(this, "Введите правильный пароль", Toast.LENGTH_SHORT);
            toast.show();
            result = false;
            return;
        } else {
            passValid = true;
        }

        //Если все данные прошли проверку, то пишем в true в поле result,
        //чтобы можно было передать данные и запустить вторую активити
        if (mailValid == phoneValid == passValid == true) {
            result = true;
        }

    }

    public void changePasswordVisibility(View view) {

        btnChangePassVisibility = (ToggleButton) findViewById(R.id.btn_change_pass_visibility);
        if (btnChangePassVisibility.isChecked()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

}
