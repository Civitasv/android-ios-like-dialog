package com.civitasv.ioslike.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.util.UIUtil;


/**
 * @author Civitasv
 * 底部弹窗条目样式 构造者模式
 * 2020-11-21
 */
public final class DialogTextStyle {
    private final Context context;

    private final int color; // 颜色

    private final float textSize; // 字大

    private final Typeface typeface; // 样式

    public static class Builder {
        private final Context context;
        // 可选参数
        private int color;
        private float textSize; // 单位：sp
        private Typeface typeface;

        public Builder(Context context) {
            this.context = context;
            this.color = ContextCompat.getColor(context, R.color.black);  // 黑色
            this.textSize = 16;
            this.typeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        }

        public Builder color(String color) {
            this.color = Color.parseColor(color);
            return this;
        }

        public Builder color(@ColorRes int color) {
            this.color = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder textSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public DialogTextStyle build() {
            return new DialogTextStyle(this);
        }
    }

    private DialogTextStyle(Builder builder) {
        context = builder.context;
        color = builder.color;
        textSize = builder.textSize;
        typeface = builder.typeface;
    }

    public int getColor() {
        return color;
    }

    public float getTextSize() {
        return textSize;
    }

    public Typeface getTypeface() {
        return typeface;
    }
}
