package com.nhom5.quanlylaptop.Support;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeType {
    String TAG = "ChangeType_____";

    public Bitmap byteToBitmap(byte[] avatar) {
        if (avatar != null) {
            return BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
        }
        return null;
    }

    public byte[] bitmapToByte(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            Log.d(TAG, "bitmapToByte: bitmap: " + bitmap + " byte: " + outputStream.toByteArray());
            return outputStream.toByteArray();
        }
        return null;
    }

    public byte[] checkByteInput(byte[] checkByte) {
        while (checkByte.length > 500000) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(checkByte, 0, checkByte.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            checkByte = stream.toByteArray();
        }
        return checkByte;
    }

    public long stringToLongDate(String string){
        long date = 0;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(string).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String longDateToString(long date){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
    }
}
