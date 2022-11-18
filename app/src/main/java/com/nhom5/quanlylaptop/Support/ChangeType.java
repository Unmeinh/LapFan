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

    public long stringToLongDate(String string) {
        long date = 0;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(string).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String longDateToString(long date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(date));
    }

    public int stringMoneyToInt(String sMoney) {
        int money = 0;
        String afterCheck = "";
        String check = "[0-9]";
        for (int i = 0; i < sMoney.length(); i++) {
            String checkS = sMoney.substring(i, i + 1);
            if (checkS.matches(check)) {
                afterCheck += checkS;
            }
        }
        if (afterCheck.length() > 0) {
            money = Integer.parseInt(afterCheck);
        }
        Log.d(TAG, "stringMoneyToInt: Money: " + money);
        return money;
    }

    public String intMoneyToString(int iMoney) {
        String money = "₫";
        String sMoney = String.valueOf(iMoney);
        int length = sMoney.length() - 1;
        for (int i = length; i >= 0; i--) {
            String iS = sMoney.substring(i, i + 1);
            int check = (length - i) % 3;
            if (check == 0) {
                money = iS + "." + money;
            } else {
                money = iS + money;
            }
        }
        Log.d(TAG, "intMoneyToString: Money: " + money);
        return money;
    }
}
