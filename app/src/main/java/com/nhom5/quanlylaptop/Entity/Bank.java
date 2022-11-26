package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

public class Bank extends Binder {
    private static final Bank bank = new Bank();
    private int imgRes;
    private String nameBank;
    private String selected;

    public Bank() {
    }

    public Bank(int imgRes, String nameBank) {
        this.imgRes = imgRes;
        this.nameBank = nameBank;
    }

    public int getImgRes() {
        return imgRes;
    }

    public static Bank getInstance() {
        return bank;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "imgRes = " + imgRes +
                ", nameBank = '" + nameBank + '\'' +
                '}';
    }
}
