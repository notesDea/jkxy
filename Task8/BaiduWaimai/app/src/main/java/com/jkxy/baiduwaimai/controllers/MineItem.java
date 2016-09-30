package com.jkxy.baiduwaimai.controllers;

/**
 * Created by notes on 2016/8/22.
 */

public class MineItem {
    //ListView 图片资源ID
    private int iconResId;
    //ListView 文字
    private String text;
    //下方的分隔条是否可见
    private int separatorBarIsVisible;

    public MineItem(int iconResId, String text, int separatorBarIsVisible) {
        this.iconResId = iconResId;
        this.text = text;
        this.separatorBarIsVisible = separatorBarIsVisible;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSeparatorBarIsVisible() {
        return separatorBarIsVisible;
    }

    public void setSeparatorBarIsVisible(int separatorBarIsVisible) {
        this.separatorBarIsVisible = separatorBarIsVisible;
    }
}
