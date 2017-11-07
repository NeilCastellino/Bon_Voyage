package com.neilcastellino.bonvoyage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date_andDay extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;
    TextView Cdate;
    EditText Vdate, Vday;
    Button conf;
    static String abc, p_email, p_price, p_hname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Cdate = (TextView) findViewById(R.id.date_click);
        Vdate = (EditText) findViewById(R.id.date_value);
        Vday = (EditText) findViewById(R.id.day_value);
        conf = (Button) findViewById(R.id.submit_date);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        p_price = intent.getStringExtra("price");
        p_email = intent.getStringExtra("email");
        p_hname = intent.getStringExtra("name");

        Cdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
                String curDate = sdf.format(calendar.getTime());

                if (Vday.getText().toString() == null || Vday.getText().toString().isEmpty() || Integer.parseInt(Vday.getText().toString()) > 14 || Integer.parseInt(Vday.getText().toString()) < 1)
                    Toast.makeText(getBaseContext(), "Number of days have to be between 1 - 14", Toast.LENGTH_LONG).show();

                Intent i = new Intent(Date_andDay.this, Book.class);
                i.putExtra("date", Vdate.getText().toString());
                i.putExtra("day", Vday.getText().toString());
                i.putExtra("email", p_email);
                i.putExtra("price", p_price);
                i.putExtra("name", p_hname);
                startActivity(i);
            }
        });
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            abc = selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear;
            Vdate.setText(abc);
        }
    };
}
