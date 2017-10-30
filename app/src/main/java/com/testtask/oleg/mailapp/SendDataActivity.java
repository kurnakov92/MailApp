package com.testtask.oleg.mailapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class SendDataActivity extends AppCompatActivity {

    private TextView et_email;
    private TextView et_phone;
    private TextView et_password;

    private String mail;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        findViews();
        setDataToViews();
    }

    private void findViews(){

        et_email = (TextView) findViewById(R.id.send_email);
        et_phone = (TextView) findViewById(R.id.send_phone);
        et_password = (TextView) findViewById(R.id.send_pass);

    }

    private void setDataToViews(){

        Intent intent = getIntent();
        mail = intent.getStringExtra(MainActivity.EMAIL);
        phone = intent.getStringExtra(MainActivity.PHONE_NUMBER);
        password = intent.getStringExtra(MainActivity.PASSWORD);

        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createNonTerminated(PredefinedSlots.RUS_PHONE_NUMBER));
        formatWatcher.installOn(et_phone);


        et_email.setText(mail);
        et_phone.setText(phone);
        et_password.setText(password);

    }

    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mail App: данные анкеты");
        intent.putExtra(Intent.EXTRA_TEXT, "Email: " + mail+"\n\rPhone:" + phone + "\n\r");
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Выберите email клиент :"));
    }

}
