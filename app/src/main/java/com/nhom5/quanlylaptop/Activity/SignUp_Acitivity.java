package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;

public class SignUp_Acitivity extends AppCompatActivity {

    TextView gotoSignInAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        gotoSignInAct = findViewById(R.id.go_to_SignInAct);

        gotoSignInAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_Acitivity.this, SignIn_Activity.class));
                finish();
            }
        });
    }
}