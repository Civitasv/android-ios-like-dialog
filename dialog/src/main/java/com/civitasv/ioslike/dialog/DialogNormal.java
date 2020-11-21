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
        return setTitle(new DialogText(title));
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogNormal setTitle(int title) {
        return setTitle(mContext.getResources().getString(title));
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
        return setTitle(new DialogText(title, onClickListener));
    }

    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setTitle(int title, View.OnClickListener onClickListener) {
        return setTitle(mContext.getResources().getString(title), onClickListener);
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
        return setTitle(new DialogText(title, null, itemStyle));
    }

    /**
     * 设置标题
     *
     * @param title     标题
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(int title, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(title), itemStyle);
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
        return setTitle(new DialogText(title, onClickListener, itemStyle));
    }

    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogNormal setTitle(int title, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(title), onClickListener, itemStyle);
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
     * 设置内容
     *
     * @param content 弹窗内容
     * @return 弹窗对象
     */
    public DialogNormal setContent(String content) {
        if (content == null)
            throw new NullPointerException();
        return setContent(new DialogText(content));
    }

    /**
     * 设置内容
     *
     * @param content 弹窗内容
     * @return 弹窗对象
     */
    public DialogNormal setContent(int content) {
        return setContent(mContext.getResources().getString(content));
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
        return setContent(new DialogText(content, onClickListener));
    }

    /**
     * 设置标题
     *
     * @param content         标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setContent(int content, View.OnClickListener onClickListener) {
        return setContent(mContext.getResources().getString(content), onClickListener);
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
        return setContent(new DialogText(content, null, itemStyle));
    }

    /**
     * 设置标题
     *
     * @param content   标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(int content, DialogTextStyle itemStyle) {
        return setContent(mContext.getResources().getString(content), itemStyle);
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
        return setContent(new DialogText(content, onClickListener, itemStyle));
    }

    /**
     * 设置标题
     *
     * @param content         标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setContent(int content, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setContent(mContext.getResources().getString(content), onClickListener, itemStyle);
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
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮字符串内容
     * @return 弹窗对象
     */
    public DialogNormal setCancel(String cancelText) {
        if (cancelText == null)
            throw new NullPointerException();
        return setCancel(new DialogText(cancelText));
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮res id
     * @return 弹窗对象
     */
    public DialogNormal setCancel(int cancelText) {
        return setCancel(mContext.getResources().getString(cancelText));
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
        return setCancel(new DialogText(cancelText, onClickListener));
    }


    /**
     * 设置取消按钮内容
     *
     * @param cancelText      标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setCancel(int cancelText, View.OnClickListener onClickListener) {
        return setCancel(mContext.getResources().getString(cancelText), onClickListener);
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
        return setCancel(new DialogText(cancelText, null, itemStyle));
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 标题
     * @param itemStyle  样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(int cancelText, DialogTextStyle itemStyle) {
        return setCancel(mContext.getResources().getString(cancelText), itemStyle);
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
        return setCancel(new DialogText(cancelText, onClickListener, itemStyle));
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText      标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setCancel(int cancelText, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setCancel(mContext.getResources().getString(cancelText), onClickListener, itemStyle);
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
     * 设置确定按钮文字
     *
     * @param confirmText 确定字符串文字
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(String confirmText) {
        if (confirmText == null)
            throw new NullPointerException();
        return setConfirm(new DialogText(confirmText));
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText 确定内容的res id
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(int confirmText) {
        return setConfirm(mContext.getResources().getString(confirmText));
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
        return setConfirm(new DialogText(confirmText, onClickListener));
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText     标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(int confirmText, View.OnClickListener onClickListener) {
        return setConfirm(mContext.getResources().getString(confirmText), onClickListener);
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
        return setConfirm(new DialogText(confirmText, null, itemStyle));
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText 确定按钮内容
     * @param itemStyle   样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(int confirmText, DialogTextStyle itemStyle) {
        return setConfirm(mContext.getResources().getString(confirmText), itemStyle);
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
        return setConfirm(new DialogText(confirmText, onClickListener, itemStyle));
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmText     确定按钮内容
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogNormal setConfirm(int confirmText, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setConfirm(mContext.getResources().getString(confirmText), onClickListener, itemStyle);
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
            mCancel.setText(confirm.getText());
        if (confirm.getOnClickListener() != null)
            mCancel.setOnClickListener(confirm.getOnClickListener());
        if (confirm.getDialogTextStyle() != null) {
            mCancel.setTextSize(confirm.getDialogTextStyle().getTextSize());
            mCancel.setTextColor(confirm.getDialogTextStyle().getColor());
            mCancel.setTypeface(confirm.getDialogTextStyle().getTypeface());
        }
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