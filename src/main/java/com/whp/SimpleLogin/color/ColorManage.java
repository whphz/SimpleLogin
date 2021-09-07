package com.whp.SimpleLogin.color;

public class ColorManage {

    public String setColorText(String oldText) {
        return Color.MBLUE+oldText;
    }

    public String haveTempleText(String oldText) {
        return oldText.replaceAll("&", "§");
    }
}
