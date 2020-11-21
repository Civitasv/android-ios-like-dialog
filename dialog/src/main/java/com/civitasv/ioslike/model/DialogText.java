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

    public DialogText(String text) {
        this(text, null);
    }

    public DialogText(String text, View.OnClickListener onClickListener) {
        this(text, onClickListener, null);
    }

    public DialogText(String text, View.OnClickListener onClickListener, DialogTextStyle dialogTextStyle) {
        this.text = text;
        this.onClickListener = onClickListener;
        this.dialogTextStyle = dialogTextStyle;
    }
}
