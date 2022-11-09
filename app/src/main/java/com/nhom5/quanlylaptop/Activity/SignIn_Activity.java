package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nhom5.quanlylaptop.ActivityKH.Main_KH_Navi_Activity;
import com.nhom5.quanlylaptop.ActivityNV_Admin.Main_Admin_Navi_Activity;
import com.nhom5.quanlylaptop.ActivityNV_Admin.Main_NV_Navi_Activity;
import com.nhom5.quanlylaptop.R;

public class SignIn_Activity extends AppCompatActivity {

    TextView gotoSignUpAct;
    String TAG = "SignIn_Activity_____";
    String roleUser = "";
    AppCompatButton loginButton;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);
        gotoSignUpAct = findViewById(R.id.go_to_SignUpAct);
        loginButton = findViewById(R.id.login_button);
        getDataIntent();
        loginTime();
        goToSignUp();
    }

    private void loginTime(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleUser.equals("admin")){
                    Log.d(TAG, "loginTime: U r admin");
                    Intent intent = new Intent(context, Main_Admin_Navi_Activity.class);
                    startActivity(intent);
                }
                if (roleUser.equals("nhanVien")){
                    Log.d(TAG, "loginTime: U r nhanVien");
                    Intent intent = new Intent(context, Main_NV_Navi_Activity.class);
                    startActivity(intent);
                }
                if (roleUser.equals("khachHang")){
                    Log.d(TAG, "loginTime: U r khachHang");
                    Intent intent = new Intent(context, Main_KH_Navi_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getDataIntent(){
        Log.d(TAG, "getDataIntent: true");
        Intent intent = getIntent();
        if (intent != null){
            Log.d(TAG, "getDataIntent: intent != null");
            roleUser = intent.getStringExtra("role");
        }
    }

    private void goToSignUp(){
        gotoSignUpAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignUp_Acitivity.class);
                Log.d(TAG, "goToSignUp: roleUser: " + roleUser);
                intent.putExtra("role", roleUser);
                startActivity(intent);
                finish();
            }
        });
    }
}