package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;

public class SignIn_Activity extends AppCompatActivity {

    TextView gotoSignUpAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);
        gotoSignUpAct = findViewById(R.id.go_to_SignUpAct);

        gotoSignUpAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn_Activity.this, SignUp_Acitivity.class));
            }
        });
    }

    public void logInDemo(View view) {
        startActivity(new Intent(SignIn_Activity.this, Account_KH_Manager_Activity.class));
    }
}