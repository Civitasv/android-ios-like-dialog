package com.civitasv.ioslike.model;

import android.graphics.Color;
import android.graphics.Typeface;


/**
 * @author Civitasv
 * 底部弹窗条目样式
 * 2020-11-21
 */
public class DialogBottomItemStyle {
    private int color; // 颜色

    private int textSize; // 字大

    private Typeface typeface; // 样式

    public static class Builder {
        // 可选参数
        private int color = Color.parseColor("#0040dd");
        private int textSize = 16; // 单位：sp
        private Typeface typeface = Typeface.defaultFromStyle(Typeface.NORMAL);

        public Builder() {

        }

        public Builder color(String color) {
            this.color = Color.parseColor(color);
            return this;
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public DialogBottomItemStyle build() {
            return new DialogBottomItemStyle(this);
        }
    }

    private DialogBottomItemStyle(Builder builder) {
        color = builder.color;
        textSize = builder.textSize;
        typeface = builder.typeface;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
