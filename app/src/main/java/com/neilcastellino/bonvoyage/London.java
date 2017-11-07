package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class London extends AppCompatActivity {

    private TextView tx1, tx2,tx3;
    ScrollView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_london);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx1=(TextView)findViewById(R.id.hotels_title_ln);
        tx2=(TextView)findViewById(R.id.ln_h1);
        tx3=(TextView)findViewById(R.id.ln_h2);
        sc=(ScrollView)findViewById(R.id.sc_ln);

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.fullScroll(ScrollView.FOCUS_DOWN);
                tx2.setVisibility(View.VISIBLE);
                tx3.setVisibility(View.VISIBLE);
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(London.this,CorinthiaHotel.class);
                startActivity(i);
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(London.this,LondonBridgeHotel.class);
                startActivity(i);
            }
        });
    }

}
