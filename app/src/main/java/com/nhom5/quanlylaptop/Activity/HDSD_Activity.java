package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.nhom5.quanlylaptop.R;

public class HDSD_Activity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdsd);
//        videoView = findViewById(R.id.videoView_HDSD);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vio_mega);
//        videoView.setVideoURI(uri);
//        videoView.start();
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
        useToolbar();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Hướng dẫn sử dụng");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}