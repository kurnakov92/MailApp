package com.testtask.oleg.mailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btnChangePassVisibility;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private Button btnView;

    //Ключи для обмена данными между Activities
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


        //Валидация телефона
//        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public void changePasswordVisibility(View view){

        btnChangePassVisibility = (ToggleButton) findViewById(R.id.btn_change_pass_visibility);
        if (btnChangePassVisibility.isChecked()){
            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    public void openSendDataActivity(View view){

        Intent intent = new Intent(this, SendDataActivity.class);
        intent.putExtra(MainActivity.EMAIL, inputEmail.getText().toString());
        intent.putExtra(MainActivity.PHONE_NUMBER, inputPhone.getText().toString());
        intent.putExtra(MainActivity.PASSWORD, inputPassword.getText().toString());
        startActivity(intent);
    }

    private void findViews(){
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnView = (Button) findViewById(R.id.btn_view);
    }




}
