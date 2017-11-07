package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Book extends AppCompatActivity {

    String email, price, name, day, date;
    Button book;
    TextView txt, cno;
    Firebase mRef, hRef;
    static String emailto = "", subject = "", message12 = "", url_hotel, mUserId;
    RadioButton credit, debit, visa, mc, ae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        book = (Button) findViewById(R.id.pay_btn);
        txt = (TextView) findViewById(R.id.dash_hotel_value);
        cno = (TextView) findViewById(R.id.card_no);
        credit = (RadioButton) findViewById(R.id.credit_card);
        debit = (RadioButton) findViewById(R.id.debit_card);
        visa = (RadioButton) findViewById(R.id.visa);
        mc = (RadioButton) findViewById(R.id.master_card);
        ae = (RadioButton) findViewById(R.id.american_express);

        Intent intent = getIntent();
        price = intent.getStringExtra("price");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        day = intent.getStringExtra("day");
        date = intent.getStringExtra("date");

        mRef = new Firebase(Constants.FIREBASE_URL);
        if (mRef.getAuth() == null) {
            loadLoginView();
        }

        try {
            mUserId = mRef.getAuth().getUid();
        } catch (Exception e) {
            loadLoginView();
        }

        url_hotel = Constants.FIREBASE_URL + "/users/" + mUserId + "/hotel";

        hRef = new Firebase(url_hotel);

        hRef.setValue(name);

        credit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setVisibility(View.GONE);
                debit.setVisibility(View.GONE);
                visa.setVisibility(View.VISIBLE);
                mc.setVisibility(View.VISIBLE);
                ae.setVisibility(View.VISIBLE);
            }
        });

        debit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setVisibility(View.GONE);
                debit.setVisibility(View.GONE);
                visa.setVisibility(View.VISIBLE);
                mc.setVisibility(View.VISIBLE);
                ae.setVisibility(View.VISIBLE);
            }
        });

        visa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cno.setFocusable(true);
                cno.requestFocus();
            }
        });

        ae.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cno.setFocusable(true);
                cno.requestFocus();
            }
        });

        mc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cno.setFocusable(true);
                cno.requestFocus();
            }
        });

        emailto = email;
        subject = "Payment Confirmation by Bon Voyage";
        message12 = "Hi,\nYour payment is confirmed for the hotel " + name + "\non "
                + date + "\nfor " + day + " days.\nThe amount received by us is "
                + price + ".\nThanking you,\nBon Voyage Team.";

        book.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cno.getText().toString().length() != 10)
                    Toast.makeText(getBaseContext(), "Card number must be 10 digits", Toast.LENGTH_LONG).show();
                else{
                    sendEmail();
                }
            }
        });
    }

    private void loadLoginView() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void sendEmail() {
        //Creating SendMail object
        SendMail sm = new SendMail(this, emailto, subject, message12);

        //Executing sendmail to send email
        sm.execute();
    }
}
