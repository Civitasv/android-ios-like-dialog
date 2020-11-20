package com.civitasv.ioslike.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.civitasv.ioslike.R;
import com.civitasv.ioslike.util.DisplayUtil;

/**
 * @author civitasv
 * @description 仿ios普通弹出框
 * @date 2020-11-20
 */
public class DialogNormal {
    // 必须参数
    private final View view;
    private final Context mContext;
    private final Dialog mDialog;

    private final TextView mTitle;
    private final TextView mContent;
    private final Button mCancel;
    private final Button mConfirm;
    private final View mSplit;

    private boolean mShowTitle = false;
    private boolean mShowContent = false;
    private boolean mShowCancel = false;
    private boolean mShowConfirm = false;

    public DialogNormal(Context context) {
        mContext = context;
        // 获取Dialog布局
        view = View.inflate(context, R.layout.dialog_normal, null);

        mTitle = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);
        mCancel = view.findViewById(R.id.cancel);
        mConfirm = view.findViewById(R.id.confirm);
        mSplit = view.findViewById(R.id.split);
        mDialog = new Dialog(context, R.style.NormalDialogStyle);
        mDialog.setContentView(view);
        Window dialogWindow = mDialog.getWindow();
        if (dialogWindow == null) return;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (DisplayUtil.getInstance(context).getScreenWidth() * 0.75);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    public DialogNormal setTitle(String title) {
        mTitle.setText(title);
        mShowTitle = true;
        return this;
    }

    public DialogNormal setContent(String content) {
        mContent.setText(content);
        mShowContent = true;
        return this;
    }

    public DialogNormal setCancel(String cancel) {
        mCancel.setText(cancel);
        mCancel.setOnClickListener(v -> mDialog.dismiss());
        mShowCancel = true;
        return this;
    }

    public DialogNormal setCancel(String cancel, View.OnClickListener listener) {
        mCancel.setText(cancel);
        mCancel.setOnClickListener(v -> {
            listener.onClick(v);
            mDialog.dismiss();
        });
        mShowCancel = true;
        return this;
    }

    public DialogNormal setCancelListener(View.OnClickListener listener) {
        mCancel.setText("取消");
        mCancel.setOnClickListener(v -> {
            listener.onClick(v);
            mDialog.dismiss();
        });
        mShowCancel = true;
        return this;
    }

    public DialogNormal setCancelTextColor(int cancelTextColor) {
        mCancel.setTextColor(cancelTextColor);
        return this;
    }

    public DialogNormal setCancelTextColor(String cancelTextColor) {
        mCancel.setTextColor(Color.parseColor(cancelTextColor));
        return this;
    }

    public DialogNormal setConfirm(String confirm) {
        mConfirm.setText(confirm);
        mConfirm.setOnClickListener(v -> mDialog.dismiss());
        mShowConfirm = true;
        return this;
    }

    public DialogNormal setConfirm(String confirm, View.OnClickListener listener) {
        mConfirm.setText(confirm);
        mConfirm.setOnClickListener(v -> {
            listener.onClick(v);
            mDialog.dismiss();
        });
        mShowConfirm = true;
        return this;
    }

    public DialogNormal setConfirmListener(View.OnClickListener listener) {
        mConfirm.setText("确定");
        mConfirm.setOnClickListener(v -> {
            listener.onClick(v);
            mDialog.dismiss();
        });
        mShowConfirm = true;
        return this;
    }

    public DialogNormal setConfirmTextColor(int cancelTextColor) {
        mConfirm.setTextColor(cancelTextColor);
        return this;
    }

    public DialogNormal setConfirmTextColor(String cancelTextColor) {
        mConfirm.setTextColor(Color.parseColor(cancelTextColor));
        return this;
    }

    public DialogNormal setWidthRatio(float widthRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (DisplayUtil.getInstance(mContext).getScreenWidth() * widthRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    public DialogNormal setHeightRatio(float heightRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) (DisplayUtil.getInstance(mContext).getScreenHeight() * heightRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    public DialogNormal setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
        return this;
    }

    public DialogNormal setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    public void show() {
        mTitle.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        mContent.setVisibility(mShowContent ? View.VISIBLE : View.GONE);
        mCancel.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        mConfirm.setVisibility(mShowConfirm ? View.VISIBLE : View.GONE);
        mSplit.setVisibility(mShowCancel && mShowConfirm ? View.VISIBLE : View.GONE);
        if (mShowCancel && !mShowConfirm) {
            mCancel.setBackground(ContextCompat.getDrawable(mContext, R.drawable.dialog_normal_btn_single));
        }
        if (!mShowCancel && mShowConfirm) {
            mConfirm.setBackground(ContextCompat.getDrawable(mContext, R.drawable.dialog_normal_btn_single));
        }
        mDialog.setContentView(view);
        mDialog.show();
    }
}
