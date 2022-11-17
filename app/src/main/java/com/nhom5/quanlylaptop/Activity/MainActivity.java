package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.showText);
        ChangeType changeType = new ChangeType();

        textView.setText(String.valueOf(changeType.stringMoneyToInt("13.990.000₫")));


        startActivity(new Intent(MainActivity.this, PickRole_Activity.class));
    }
}