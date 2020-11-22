package com.civitasv.ioslike.model;

import android.view.View;


/**
 * @author Civitasv
 * 底部弹窗条目
 * 2020-11-21
 */
public final class DialogText {
    private final String text; // 内容

    private final View.OnClickListener onClickListener; // 点击事件

    private final DialogTextStyle dialogTextStyle; // 样式

    public String getText() {
        return text;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public DialogTextStyle getDialogTextStyle() {
        return dialogTextStyle;
    }

    public static class Builder {
        private final String text; // 内容

        private View.OnClickListener onClickListener = null; // 点击事件

        private DialogTextStyle dialogTextStyle = null; // 样式

        public Builder(String text) {
            this.text = text;
        }

        public Builder setOnclickListener(View.OnClickListener onclickListener) {
            this.onClickListener = onclickListener;
            return this;
        }

        public Builder setDialogTextStyle(DialogTextStyle dialogTextStyle) {
            this.dialogTextStyle = dialogTextStyle;
            return this;
        }

        public DialogText build() {
            return new DialogText(this);
        }
    }

    public DialogText(Builder builder) {
        this.text = builder.text;
        this.onClickListener = builder.onClickListener;
        this.dialogTextStyle = builder.dialogTextStyle;
    }
}
