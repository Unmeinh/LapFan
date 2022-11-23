package com.nhom5.quanlylaptop.HelloWorld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nhom5.quanlylaptop.Activity.MainActivity;
import com.nhom5.quanlylaptop.Activity.PickRole_Activity;
import com.nhom5.quanlylaptop.R;

public class Hello_Fragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_4, container, false);
        Button btn_getstart = view.findViewById(R.id.btn_start);
        btn_getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PickRole_Activity.class) ;
                startActivity(intent);
            }
        });
        return view;
    }
}