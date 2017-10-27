package com.testtask.oleg.mailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btnChangePassVisibility;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changePasswordVisibility(View view){
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnChangePassVisibility = (ToggleButton) findViewById(R.id.btn_change_pass_visibility);
        if (btnChangePassVisibility.isChecked()){
            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }
}
