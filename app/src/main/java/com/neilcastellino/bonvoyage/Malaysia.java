package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class Malaysia extends AppCompatActivity {

    private TextView tx1, tx2,tx3,tx4;
    ScrollView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malaysia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx1=(TextView)findViewById(R.id.hotels_title_ml);
        tx2=(TextView)findViewById(R.id.ml_h1);
        tx3=(TextView)findViewById(R.id.ml_h2);
        tx4=(TextView)findViewById(R.id.ml_h3);
        sc=(ScrollView)findViewById(R.id.sc_ma);

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.fullScroll(ScrollView.FOCUS_DOWN);
                tx2.setVisibility(View.VISIBLE);
                tx3.setVisibility(View.VISIBLE);
                tx4.setVisibility(View.VISIBLE);
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Malaysia.this, BayviewHotel.class);
                startActivity(i);
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Malaysia.this, LeMeridienHotel.class);
                startActivity(i);
            }
        });

        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Malaysia.this, GrandHyatt.class);
                startActivity(i);
            }
        });
    }

}
