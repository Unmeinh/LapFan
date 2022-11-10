package com.nhom5.quanlylaptop.FragmentNV_Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nhom5.quanlylaptop.R;

public class NVA_FPTShop_Fragment extends Fragment {

    WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nva_fptshop, container, false);
        webView = view.findViewById(R.id.webView_FPT);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl("https://fptshop.com.vn/may-tinh-xach-tay");
        return view;
    }
}