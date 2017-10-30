package com.testtask.oleg.mailapp;

import android.content.Intent;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.hardware.Camera;

import com.testtask.oleg.mailapp.validators.Validator;

public class MainActivity extends AppCompatActivity {


    private ToggleButton btnChangePassVisibility;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private Intent intent;

    //Ключи для обмена данными между Activities
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String PHOTO = "PHOTO";

    private boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        intent = new Intent(this, SendDataActivity.class);

        //Валидация телефона
//        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }


    public void changePasswordVisibility(View view) {

        btnChangePassVisibility = (ToggleButton) findViewById(R.id.btn_change_pass_visibility);
        if (btnChangePassVisibility.isChecked()) {
            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    private void validateFields(String email, String phone, String pass){
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

        if (!validator.isPhoneValid(phone)) {
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
    
    public void openSendDataActivity(View view) {

        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String pass = inputPassword.getText().toString();
        validateFields(email, phone, pass);
        if (result){
            intent.putExtra(MainActivity.EMAIL, email);
            intent.putExtra(MainActivity.PHONE_NUMBER, phone);
            intent.putExtra(MainActivity.PASSWORD, pass);
            startActivity(intent);
        }

    }

    private void findViews() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputPassword = (EditText) findViewById(R.id.input_password);
    }


}
