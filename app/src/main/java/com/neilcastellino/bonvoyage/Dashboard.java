package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class Dashboard extends AppCompatActivity {

    String test, url_name, url_email, url_gender, url_age, url_contact, url_random, url_hotel_name;
    private String mUserId;
    private Firebase mRef, nRef, eRef, gRef, aRef, cRef, testRef, rRef, hRef;
    Button EDIT, BROWSE, DONE;
    static String provider, email_pass, name;
    EditText Ename, Egen, Eage, Econ;
    TextView NAME, EMAIL, GENDER, AGE, CONTACT, EMAIL_V, HOTEL, HOTEL_V;
    String val_name, val_gen, val_age, val_con;
    static String e1, e2, e4, e5, e3, e6;
    int randomInt;

    public Dashboard() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NAME = (TextView) findViewById(R.id.dash_name_value);
        EMAIL = (TextView) findViewById(R.id.dash_email);
        EMAIL_V = (TextView) findViewById(R.id.dash_email_value);
        GENDER = (TextView) findViewById(R.id.dash_gender_value);
        AGE = (TextView) findViewById(R.id.dash_age_value);
        CONTACT = (TextView) findViewById(R.id.dash_contact_value);
        HOTEL = (TextView) findViewById(R.id.dash_hotel);
        HOTEL_V = (TextView) findViewById(R.id.dash_hotel_value);

        Ename = (EditText) findViewById(R.id.ed_name_value);
        Egen = (EditText) findViewById(R.id.ed_gender_value);
        Eage = (EditText) findViewById(R.id.ed_age_value);
        Econ = (EditText) findViewById(R.id.ed_contact_value);

        EDIT = (Button) findViewById(R.id.edit_details);
        BROWSE = (Button) findViewById(R.id.browse);
        DONE = (Button) findViewById(R.id.ed_btn_done);

        mRef = new Firebase(Constants.FIREBASE_URL);
        if (mRef.getAuth() == null) {
            loadLoginView();
        }

        try {
            mUserId = mRef.getAuth().getUid();
        } catch (Exception e) {
            loadLoginView();
        }

        test = Constants.FIREBASE_URL + "/users/";
        url_name = Constants.FIREBASE_URL + "/users/" + mUserId + "/name";
        url_email = Constants.FIREBASE_URL + "/users/" + mUserId + "/email";
        url_gender = Constants.FIREBASE_URL + "/users/" + mUserId + "/gender";
        url_age = Constants.FIREBASE_URL + "/users/" + mUserId + "/age";
        url_contact = Constants.FIREBASE_URL + "/users/" + mUserId + "/contact";
        url_random = Constants.FIREBASE_URL + "/users/" + mUserId + "/random";
        url_hotel_name = Constants.FIREBASE_URL + "/users/" + mUserId + "/hotel";

        testRef = new Firebase(test);
        nRef = new Firebase(url_name);
        eRef = new Firebase(url_email);
        gRef = new Firebase(url_gender);
        aRef = new Firebase(url_age);
        cRef = new Firebase(url_contact);
        rRef = new Firebase(url_random);
        hRef = new Firebase(url_hotel_name);

        Intent intent = getIntent();
//        provider = intent.getStringExtra("provider");
        email_pass = intent.getStringExtra("email_main");
//        name = intent.getStringExtra("name");

//        if (provider.equals("yes")) {
//            eRef.setValue(email_pass.trim());
//            nRef.setValue(name);
//        }

//        testRef.setValue("");

        eRef.setValue(email_pass);

        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(100);
        rRef.setValue(randomInt);

        testRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                e1 = dataSnapshot.child(mUserId).child("name").getValue(String.class);
                e2 = dataSnapshot.child(mUserId).child("email").getValue(String.class);
                e3 = dataSnapshot.child(mUserId).child("contact").getValue(String.class);
                e4 = dataSnapshot.child(mUserId).child("age").getValue(String.class);
                e5 = dataSnapshot.child(mUserId).child("gender").getValue(String.class);
                e6 = dataSnapshot.child(mUserId).child("hotel").getValue(String.class);

                if (e1 != null)
                    NAME.setText(e1);
                if (e2 != null) {
                    EMAIL_V.setText(e2);
                    email_pass = e2;
                }
                if (e3 != null)
                    CONTACT.setText(e3);
                if (e4 != null)
                    AGE.setText(e4);
                if (e5 != null)
                    GENDER.setText(e5);
                if (e6 != null)
                    HOTEL_V.setText(e6);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (provider.equals("yes")){
//                    NAME.setVisibility(View.GONE);
//                    CONTACT.setVisibility(View.GONE);
//                    GENDER.setVisibility(View.GONE);
//                    AGE.setVisibility(View.GONE);
//                    EMAIL_V.setVisibility(View.GONE);
//                    EMAIL.setVisibility(View.GONE);
//                    HOTEL_V.setVisibility(View.GONE);
//                    HOTEL.setVisibility(View.GONE);
//
//                    Econ.setVisibility(View.VISIBLE);
//                    Ename.setVisibility(View.GONE);
//                    Egen.setVisibility(View.VISIBLE);
//                    Eage.setVisibility(View.VISIBLE);
//
//                    EDIT.setVisibility(View.GONE);
//                    BROWSE.setVisibility(View.GONE);
//                    DONE.setVisibility(View.VISIBLE);
//                } else{
                NAME.setVisibility(View.GONE);
                CONTACT.setVisibility(View.GONE);
                GENDER.setVisibility(View.GONE);
                AGE.setVisibility(View.GONE);
                EMAIL_V.setVisibility(View.GONE);
                EMAIL.setVisibility(View.GONE);
                HOTEL_V.setVisibility(View.GONE);
                HOTEL.setVisibility(View.GONE);

                Econ.setVisibility(View.VISIBLE);
                Ename.setVisibility(View.VISIBLE);
                Egen.setVisibility(View.VISIBLE);
                Eage.setVisibility(View.VISIBLE);

                EDIT.setVisibility(View.GONE);
                BROWSE.setVisibility(View.GONE);
                DONE.setVisibility(View.VISIBLE);
//                }
            }
        });

        DONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (provider.equals("yes")) {
//                    val_gen = Egen.getText().toString();
//                    val_age = Eage.getText().toString();
//                    val_con = Econ.getText().toString();
//
//                    gRef.setValue(val_gen);
//                    aRef.setValue(val_age);
//                    cRef.setValue(val_con);
//                }else{
                val_name = Ename.getText().toString();
                val_gen = Egen.getText().toString();
                val_age = Eage.getText().toString();
                val_con = Econ.getText().toString();

                nRef.setValue(val_name);
                gRef.setValue(val_gen);
                aRef.setValue(val_age);
                cRef.setValue(val_con);

//                }

                testRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        e1 = dataSnapshot.child(mUserId).child("name").getValue(String.class);
                        e2 = dataSnapshot.child(mUserId).child("email").getValue(String.class);
                        e3 = dataSnapshot.child(mUserId).child("contact").getValue(String.class);
                        e4 = dataSnapshot.child(mUserId).child("age").getValue(String.class);
                        e5 = dataSnapshot.child(mUserId).child("gender").getValue(String.class);
                        e6 = dataSnapshot.child(mUserId).child("hotel").getValue(String.class);

                        email_pass = e2;

                        NAME.setText(e1);
                        EMAIL_V.setText(e2);
                        CONTACT.setText(e3);
                        AGE.setText(e4);
                        GENDER.setText(e5);
                        HOTEL_V.setText(e6);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                NAME.setVisibility(View.VISIBLE);
                CONTACT.setVisibility(View.VISIBLE);
                GENDER.setVisibility(View.VISIBLE);
                AGE.setVisibility(View.VISIBLE);
                EMAIL_V.setVisibility(View.VISIBLE);
                EMAIL.setVisibility(View.VISIBLE);
                HOTEL_V.setVisibility(View.VISIBLE);
                HOTEL.setVisibility(View.VISIBLE);

                Econ.setVisibility(View.GONE);
                Ename.setVisibility(View.GONE);
                Egen.setVisibility(View.GONE);
                Eage.setVisibility(View.GONE);

                EDIT.setVisibility(View.VISIBLE);
                BROWSE.setVisibility(View.VISIBLE);
                DONE.setVisibility(View.GONE);
            }
        });

        BROWSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Destinations.class);
                startActivity(i);
            }
        });
    }

    private void loadLoginView() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public String getEmail() {
        if (email_pass != null)
            return email_pass;
        else
            return "ERROR";
    }
}
