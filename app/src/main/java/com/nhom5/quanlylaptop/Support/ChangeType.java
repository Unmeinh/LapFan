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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if (i == length) {
                money = iS + money;
            } else {
                if (check == 0) {
                    money = iS + "." + money;
                } else {
                    money = iS + money;
                }
            }
        }
        Log.d(TAG, "intMoneyToString: Money: " + money);
        return money;
    }

    public float getRatingFloat(float rating) {
        if (rating > 0 && rating < 0.5F) {
            return 0;
        } else if (rating > 0.5F && rating < 1) {
            return 0.5F;
        } else if (rating > 1 && rating < 1.5F) {
            return 1;
        } else if (rating > 1.5F && rating < 2) {
            return 1.5F;
        } else if (rating > 2 && rating < 2.5F) {
            return 2;
        } else if (rating > 2.5F && rating < 3) {
            return 2.5F;
        } else if (rating > 3 && rating < 3.5F) {
            return 3;
        } else if (rating > 3.5F && rating < 4) {
            return 3.5F;
        } else if (rating > 4 && rating < 4.5F) {
            return 4;
        } else if (rating > 4.5F && rating < 5) {
            return 4.5F;
        }

        return rating;
    }

    public String fullNameToFirstName(String name) {
        String firstName = "";
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            String getString = name.substring(i, i + 1);
            if (getString.equals(" ")) {
                index.add(i);
            }
        }

        if (index.size() > 0) {
            if (index.size() == 1) {
                int lastIndex = index.get(0);
                firstName = name.substring(lastIndex);
            } else {
                int lastIndex = index.get(index.size() - 1);
                firstName = name.substring(lastIndex);
                if (firstName.equals("Anh")) {
                    lastIndex = index.get(index.size() - 2);
                    firstName = name.substring(lastIndex);
                }
            }
        } else {
            firstName = name;
        }

        return firstName;
    }
}
