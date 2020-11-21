package com.civitasv.ioslike.model;

import android.view.View;


/**
 * @author Civitasv
 * 底部弹窗条目
 * 2020-11-21
 */
public class DialogBottomItem {
    private String text; // 内容

    private View.OnClickListener onClickListener; // 点击事件

    private DialogBottomItemStyle dialogBottomItemStyle; // 样式

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DialogBottomItemStyle getDialogBottomItemStyle() {
        return dialogBottomItemStyle;
    }

    public void setDialogBottomItemStyle(DialogBottomItemStyle dialogBottomItemStyle) {
        this.dialogBottomItemStyle = dialogBottomItemStyle;
    }

    public DialogBottomItem(String text) {
        this(text, null);
    }

    public DialogBottomItem(String text, View.OnClickListener onClickListener) {
        this(text, onClickListener, new DialogBottomItemStyle.Builder().build());
    }

    public DialogBottomItem(String text, View.OnClickListener onClickListener, DialogBottomItemStyle dialogBottomItemStyle) {
        this.text = text;
        this.onClickListener = onClickListener;
        this.dialogBottomItemStyle = dialogBottomItemStyle;
    }
}
