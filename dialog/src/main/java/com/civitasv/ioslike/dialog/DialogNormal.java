package com.civitasv.ioslike.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.util.DisplayUtil;

/**
 * @author Civitasv
 * 仿ios普通弹出框
 * 2020-11-20
 */
public class DialogNormal {
    // 必须参数
    private final Context mContext;
    private final Dialog mDialog;

    private final TextView mTitle;
    private final TextView mContent;
    private final Button mCancel;
    private final Button mConfirm;
    private final View mSplit;

    private boolean mShowTitle = false;
    private boolean mShowContent = false;
    private boolean mShowCancel = true;
    private boolean mShowConfirm = true;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public DialogNormal(Context context) {
        mContext = context;
        // 获取Dialog布局
        View view = View.inflate(context, R.layout.dialog_normal, null);

        // 初始化
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
        // 设置宽度、高度、居中
        lp.width = (int) (DisplayUtil.getInstance(context).getScreenWidth() * 0.75);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogNormal setTitle(String title) {
        if (title == null)
            throw new NullPointerException("title can't be null!");
        mTitle.setText(title);
        mShowTitle = true;
        return this;
    }

    /**
     * 设置内容
     *
     * @param content 弹窗内容
     * @return 弹窗对象
     */
    public DialogNormal setContent(String content) {
        if (content == null)
            throw new NullPointerException("content can't be null!");
        mContent.setText(content);
        mShowContent = true;
        return this;
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮字符串内容
     * @return 弹窗对象
     */
    public DialogNormal setCancelText(String cancelText) {
        if (cancelText == null)
            throw new NullPointerException("cancelText can't be null!");
        mCancel.setText(cancelText);
        return this;
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮res id
     * @return 弹窗对象
     */
    public DialogNormal setCancelText(int cancelText) {
        mCancel.setText(cancelText);
        return this;
    }

    /**
     * 取消按钮点击事件
     *
     * @param listener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setOnCancelClickListener(View.OnClickListener listener) {
        if (listener == null)
            throw new NullPointerException("listener can't be null!");
        mCancel.setOnClickListener(listener);
        return this;
    }

    /**
     * 取消按钮颜色
     *
     * @param cancelTextColor 取消按钮res id颜色
     * @return 弹窗对象
     */
    public DialogNormal setCancelTextColor(int cancelTextColor) {
        mCancel.setTextColor(cancelTextColor);
        return this;
    }

    /**
     * 取消按钮颜色
     *
     * @param cancelTextColor 取消按钮字符串颜色
     * @return 弹窗对象
     */
    public DialogNormal setCancelTextColor(String cancelTextColor) {
        if (cancelTextColor == null)
            throw new NullPointerException("cancelTextColor can't be null!");
        mCancel.setTextColor(Color.parseColor(cancelTextColor));
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param confirmText 确定字符串文字
     * @return 弹窗对象
     */
    public DialogNormal setConfirmText(String confirmText) {
        if (confirmText == null)
            throw new NullPointerException("confirmText can't be null!");
        mConfirm.setText(confirmText);
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param confirmText 确定内容的res id
     * @return 弹窗对象
     */
    public DialogNormal setConfirmText(int confirmText) {
        mConfirm.setText(confirmText);
        return this;
    }

    /**
     * 确定按钮点击事件
     *
     * @param listener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setOnConfirmClickListener(View.OnClickListener listener) {
        if (listener == null)
            throw new NullPointerException("listener can't be null!");
        mConfirm.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置确定按钮颜色
     *
     * @param cancelTextColor 确定按钮颜色res id
     * @return 弹窗对象
     */
    public DialogNormal setConfirmTextColor(int cancelTextColor) {
        mConfirm.setTextColor(cancelTextColor);
        return this;
    }

    /**
     * 设置确定按钮颜色
     *
     * @param cancelTextColor 确定按钮字符串颜色
     * @return 弹窗对象
     */
    public DialogNormal setConfirmTextColor(String cancelTextColor) {
        if (cancelTextColor == null)
            throw new NullPointerException("cancelTextColor can't be null!");
        mConfirm.setTextColor(Color.parseColor(cancelTextColor));
        return this;
    }

    /**
     * 设置弹窗宽度所占比例
     *
     * @param widthRatio 宽度比例
     * @return 弹窗对象
     */
    public DialogNormal setWidthRatio(float widthRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (DisplayUtil.getInstance(mContext).getScreenWidth() * widthRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置弹窗高度所占比例
     *
     * @param heightRatio 高度比例
     * @return 弹窗对象
     */
    public DialogNormal setHeightRatio(float heightRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) (DisplayUtil.getInstance(mContext).getScreenHeight() * heightRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置弹窗是否可以使用返回键消失
     *
     * @param cancelable true: 可以使用返回键使其消失 反正不可以
     * @return 弹窗对象
     */
    public DialogNormal setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
        return this;
    }

    /**
     * 设置点击外部区域，弹窗是否消失
     *
     * @param canceledOnTouchOutside true： 点击外部区域，弹窗消失 反正不消失
     * @return 弹窗对象
     */
    public DialogNormal setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    /**
     * 设置取消按钮可见性
     *
     * @param mShowCancel 取消按钮可见性
     * @return 弹窗对象
     */
    public DialogNormal setShowCancel(boolean mShowCancel) {
        this.mShowCancel = mShowCancel;
        return this;
    }

    /**
     * 设置确定按钮可见性
     *
     * @param mShowConfirm 确定按钮可见性
     * @return 弹窗对象
     */
    public DialogNormal setShowConfirm(boolean mShowConfirm) {
        this.mShowConfirm = mShowConfirm;
        return this;
    }

    /**
     * 设置标题可见性
     *
     * @param mShowTitle 标题可见性
     * @return 弹窗对象
     */
    public DialogNormal setShowTitle(boolean mShowTitle) {
        this.mShowTitle = mShowTitle;
        return this;
    }

    /**
     * 设置内容可见性
     *
     * @param mShowContent 内容可见性
     * @return 弹窗对象
     */
    public DialogNormal setShowContent(boolean mShowContent) {
        this.mShowContent = mShowContent;
        return this;
    }

    /**
     * 设置可见性，并展示该弹窗
     */
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
        mDialog.show();
    }
}
