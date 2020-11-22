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

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.model.DialogText;
import com.civitasv.ioslike.model.DialogTextStyle;
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
        // 默认只能通过dismiss()方法关闭
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
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
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).build());
    }

    /**
     * 设置标题
     *
     * @param resId 标题
     * @return 弹窗对象
     */
    public DialogNormal setTitle(@StringRes int resId) {
        return setTitle(mContext.getResources().getString(resId));
    }

    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setTitle(String title, View.OnClickListener onClickListener) {
        if (title == null || onClickListener == null)
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置标题
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setTitle(@StringRes int resId, View.OnClickListener onClickListener) {
        return setTitle(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置标题
     *
     * @param title     标题
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(String title, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null)
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置标题
     *
     * @param resId     标题
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(@StringRes int resId, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(String title, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置标题
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogNormal setTitle(DialogText title) {
        if (title == null)
            throw new NullPointerException("title can't be null!");
        if (title.getText() != null)
            mTitle.setText(title.getText());
        if (title.getOnClickListener() != null)
            mTitle.setOnClickListener(title.getOnClickListener());
        if (title.getDialogTextStyle() != null) {
            mTitle.setTextSize(title.getDialogTextStyle().getTextSize());
            mTitle.setTextColor(title.getDialogTextStyle().getColor());
            mTitle.setTypeface(title.getDialogTextStyle().getTypeface());
        }
        mShowTitle = true;
        return this;
    }

    /**
     * 设置标题样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogNormal setTitleStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mTitle.setTextSize(style.getTextSize());
        mTitle.setTextColor(style.getColor());
        mTitle.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置标题点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setTitleClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mTitle.setOnClickListener(onClickListener);
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
            throw new NullPointerException();
        return setContent(new DialogText.Builder(content).build());
    }

    /**
     * 设置内容
     *
     * @param resId 弹窗内容
     * @return 弹窗对象
     */
    public DialogNormal setContent(@StringRes int resId) {
        return setContent(mContext.getResources().getString(resId));
    }

    /**
     * 设置标题
     *
     * @param content         标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setContent(String content, View.OnClickListener onClickListener) {
        if (content == null || onClickListener == null)
            throw new NullPointerException();
        return setContent(new DialogText.Builder(content).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置标题
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setContent(@StringRes int resId, View.OnClickListener onClickListener) {
        return setContent(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置标题
     *
     * @param content   标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(String content, DialogTextStyle itemStyle) {
        if (content == null || itemStyle == null)
            throw new NullPointerException();
        return setContent(new DialogText.Builder(content).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置标题
     *
     * @param resId     标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(@StringRes int resId, DialogTextStyle itemStyle) {
        return setContent(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置标题
     *
     * @param content         标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(String content, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (content == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setContent(new DialogText.Builder(content).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置标题
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setContent(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置内容
     *
     * @param content 弹窗内容
     * @return 弹窗对象
     */
    public DialogNormal setContent(DialogText content) {
        if (content == null)
            throw new NullPointerException("content can't be null!");
        if (content.getText() != null)
            mContent.setText(content.getText());
        if (content.getOnClickListener() != null)
            mContent.setOnClickListener(content.getOnClickListener());
        if (content.getDialogTextStyle() != null) {
            mContent.setTextSize(content.getDialogTextStyle().getTextSize());
            mContent.setTextColor(content.getDialogTextStyle().getColor());
            mContent.setTypeface(content.getDialogTextStyle().getTypeface());
        }
        mShowContent = true;
        return this;
    }

    /**
     * 设置内容样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogNormal setContentStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mContent.setTextSize(style.getTextSize());
        mContent.setTextColor(style.getColor());
        mContent.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置内容点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setContentClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mContent.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮字符串内容
     * @return 弹窗对象
     */
    public DialogNormal setCancel(String cancelText) {
        if (cancelText == null)
            throw new NullPointerException();
        return setCancel(new DialogText.Builder(cancelText).build());
    }

    /**
     * 设置取消按钮内容
     *
     * @param resId 取消按钮res id
     * @return 弹窗对象
     */
    public DialogNormal setCancel(@StringRes int resId) {
        return setCancel(mContext.getResources().getString(resId));
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText      标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setCancel(String cancelText, View.OnClickListener onClickListener) {
        if (cancelText == null || onClickListener == null)
            throw new NullPointerException();
        return setCancel(new DialogText.Builder(cancelText).setOnclickListener(onClickListener).build());
    }


    /**
     * 设置取消按钮内容
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setCancel(@StringRes int resId, View.OnClickListener onClickListener) {
        return setCancel(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 标题
     * @param itemStyle  样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(String cancelText, DialogTextStyle itemStyle) {
        if (cancelText == null || itemStyle == null)
            throw new NullPointerException();
        return setCancel(new DialogText.Builder(cancelText).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置取消按钮内容
     *
     * @param resId     标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(@StringRes int resId, DialogTextStyle itemStyle) {
        return setCancel(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText      标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(String cancelText, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (cancelText == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setCancel(new DialogText.Builder(cancelText).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置取消按钮内容
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setCancel(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置取消按钮
     *
     * @param cancel 取消按钮
     * @return 弹窗对象
     */
    public DialogNormal setCancel(DialogText cancel) {
        if (cancel == null)
            throw new NullPointerException("cancel can't be null!");
        if (cancel.getText() != null)
            mCancel.setText(cancel.getText());
        if (cancel.getOnClickListener() != null)
            mCancel.setOnClickListener(cancel.getOnClickListener());
        if (cancel.getDialogTextStyle() != null) {
            mCancel.setTextSize(cancel.getDialogTextStyle().getTextSize());
            mCancel.setTextColor(cancel.getDialogTextStyle().getColor());
            mCancel.setTypeface(cancel.getDialogTextStyle().getTypeface());
        }
        return this;
    }

    /**
     * 设置取消按钮样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogNormal setCancelStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mCancel.setTextSize(style.getTextSize());
        mCancel.setTextColor(style.getColor());
        mCancel.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置取消按钮点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setCancelClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mCancel.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param confirmText 确定字符串文字
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(String confirmText) {
        if (confirmText == null)
            throw new NullPointerException();
        return setConfirm(new DialogText.Builder(confirmText).build());
    }

    /**
     * 设置确定按钮内容
     *
     * @param resId 确定内容的res id
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(@StringRes int resId) {
        return setConfirm(mContext.getResources().getString(resId));
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText     标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(String confirmText, View.OnClickListener onClickListener) {
        if (confirmText == null || onClickListener == null)
            throw new NullPointerException();
        return setConfirm(new DialogText.Builder(confirmText).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置确定按钮内容
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(@StringRes int resId, View.OnClickListener onClickListener) {
        return setConfirm(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText 确定按钮内容
     * @param itemStyle   样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(String confirmText, DialogTextStyle itemStyle) {
        if (confirmText == null || itemStyle == null)
            throw new NullPointerException();
        return setConfirm(new DialogText.Builder(confirmText).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置确定按钮内容
     *
     * @param resId     确定按钮内容
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(@StringRes int resId, DialogTextStyle itemStyle) {
        return setConfirm(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText     确定按钮内容
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(String confirmText, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (confirmText == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setConfirm(new DialogText.Builder(confirmText).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置确定按钮内容
     *
     * @param resId           确定按钮内容
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setConfirm(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置确定按钮
     *
     * @param confirm 确定按钮
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(DialogText confirm) {
        if (confirm == null)
            throw new NullPointerException("confirm can't be null!");
        if (confirm.getText() != null)
            mConfirm.setText(confirm.getText());
        if (confirm.getOnClickListener() != null)
            mConfirm.setOnClickListener(confirm.getOnClickListener());
        if (confirm.getDialogTextStyle() != null) {
            mConfirm.setTextSize(confirm.getDialogTextStyle().getTextSize());
            mConfirm.setTextColor(confirm.getDialogTextStyle().getColor());
            mConfirm.setTypeface(confirm.getDialogTextStyle().getTypeface());
        }
        return this;
    }

    /**
     * 设置确定按钮样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirmStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mConfirm.setTextSize(style.getTextSize());
        mConfirm.setTextColor(style.getColor());
        mConfirm.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置确定按钮点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setConfirmClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mConfirm.setOnClickListener(onClickListener);
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
     * 设置背景色
     *
     * @param amount 背景暗色程度
     * @return 弹窗对象
     */
    public DialogNormal setDimAmount(float amount) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setDimAmount(amount);
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