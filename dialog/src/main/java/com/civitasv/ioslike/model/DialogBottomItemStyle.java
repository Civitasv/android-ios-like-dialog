package com.civitasv.ioslike.model;

import android.graphics.Color;
import android.graphics.Typeface;


/**
 * @author Civitasv
 * 底部弹窗条目样式 构造者模式
 * 2020-11-21
 */
public final class DialogBottomItemStyle {
    private final int color; // 颜色

    private final int textSize; // 字大

    private final Typeface typeface; // 样式

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

    public int getTextSize() {
        return textSize;
    }

    public Typeface getTypeface() {
        return typeface;
    }
}
