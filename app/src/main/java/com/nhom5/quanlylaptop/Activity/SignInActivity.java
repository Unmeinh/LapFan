package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;

public class SignInActivity extends AppCompatActivity {

    TextView gotoSignUpAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);
        gotoSignUpAct = findViewById(R.id.gotoSignUpAct);

        gotoSignUpAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpAcitivity.class));
                finish();
            }
        });
    }
}