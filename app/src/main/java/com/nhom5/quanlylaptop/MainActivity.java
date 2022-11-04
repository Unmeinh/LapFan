package com.nhom5.quanlylaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nhom5.quanlylaptop.Activity.PickRoleActivity;
import com.nhom5.quanlylaptop.Activity.SignInActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, PickRoleActivity.class));
    }
}