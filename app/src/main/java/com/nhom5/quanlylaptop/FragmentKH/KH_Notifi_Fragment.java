package com.nhom5.quanlylaptop.FragmentKH;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_Notifi_Fragment extends Fragment {

    List<HashMap<String, String>> list = new ArrayList<>();
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_notifi, container, false);
        listView = view.findViewById(R.id.listView_Notifi);

        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.cardview_kh_notifi, new String[]{}, new int[]{});
        listView.setAdapter(simpleAdapter);
        return view;
    }
}