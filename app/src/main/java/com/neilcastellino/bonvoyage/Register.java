package com.neilcastellino.bonvoyage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Register extends AppCompatActivity {

    protected EditText USER_EMAIL, USER_PASS, CON_PASS;
    String user_pass, con_pass, user_email;
    protected Button REG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        USER_PASS=(EditText)findViewById(R.id.reg_password);
        CON_PASS=(EditText)findViewById(R.id.reg_conf_password);
        USER_EMAIL=(EditText)findViewById(R.id.reg_email);
        REG = (Button)findViewById(R.id.Rbtn);

        final Firebase ref = new Firebase(Constants.FIREBASE_URL);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordLayout);

        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = USER_EMAIL.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();

                if(user_pass.length() < 8) {
                    Toast.makeText(getBaseContext(), "Password must be atleast 8 characters long", Toast.LENGTH_LONG).show();
                }
                else if(!(user_pass.equals(con_pass))){
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
                    USER_EMAIL.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                }
                else{
                    user_email = user_email.trim();
                    user_pass = user_pass.trim();

                    if (user_pass.isEmpty() || user_email.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage(R.string.signup_error_message)
                                .setTitle(R.string.signup_error_title)
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {

                        // signup
                        ref.createUser(user_email, user_pass, new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage(R.string.signup_success)
                                        .setPositiveButton(R.string.login_button_label, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Register.this, Login.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                        });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage(firebaseError.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        });
                    }
                }
            }
        });
    }

}
