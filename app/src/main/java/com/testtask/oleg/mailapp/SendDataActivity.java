package com.testtask.oleg.mailapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class SendDataActivity extends AppCompatActivity {

    private TextView et_email;
    private TextView et_phone;
    private TextView et_password;
    private Button btn_send;

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
        btn_send = (Button) findViewById(R.id.btn_sendToEmail);

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
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"olegkurnakov@mail.ru"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, mail+"\n" + phone + "\n" + password);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Выберите email клиент :"));
    }

}
