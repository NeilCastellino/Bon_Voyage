package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Destinations extends AppCompatActivity {

    private TextView tx1, tx2,tx3,tx4, tx5, tx6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx1=(TextView)findViewById(R.id.dest_ny);
        tx2=(TextView)findViewById(R.id.dest_ln);
        tx3=(TextView)findViewById(R.id.dest_du);
        tx4=(TextView)findViewById(R.id.dest_ma);
        tx5=(TextView)findViewById(R.id.dest_pa);
        tx6=(TextView)findViewById(R.id.dest_ve);

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, NewYork.class);
                startActivity(i);
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, London.class);
                startActivity(i);
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, Dubai.class);
                startActivity(i);
            }
        });

        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, Malaysia.class);
                startActivity(i);
            }
        });

        tx5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, Paris.class);
                startActivity(i);
            }
        });

        tx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destinations.this, Venice.class);
                startActivity(i);
            }
        });
    }

}
