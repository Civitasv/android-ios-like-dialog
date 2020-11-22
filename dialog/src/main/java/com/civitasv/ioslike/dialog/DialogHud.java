package com.civitasv.ioslike.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.model.DialogText;
import com.civitasv.ioslike.model.DialogTextStyle;
import com.civitasv.ioslike.util.DisplayUtil;
import com.civitasv.ioslike.util.UIUtil;
import com.civitasv.ioslike.view.ProgressView;
import com.civitasv.ioslike.view.LoadingView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Civitasv
 * iOS Human Interface Guidelines
 * 2020-11-22
 */
public class DialogHud {
    // 必须参数
    private final Context mContext;
    private final Dialog mDialog;

    private final LoadingView mLoadingView;
    private final ProgressView mProgressView;
    private final TextView mLabel;
    private final TextView mDetailLabel;

    private boolean mShowLabel = false;
    private boolean mShowDetailLabel = false;
    private boolean mShowLoading = true;
    private boolean mShowProgress = false;
    private boolean mAutomaticDisappear = false; // 自动消失
    private int mDisappearTime = 2000; // 自动消失时间 2s

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public DialogHud(Context context) {
        mContext = context;
        // 获取Dialog布局
        View view = View.inflate(context, R.layout.dialog_hud, null);

        // 初始化
        mLabel = view.findViewById(R.id.label);
        mDetailLabel = view.findViewById(R.id.label_detail);
        mLoadingView = view.findViewById(R.id.progress_view);
        mProgressView = view.findViewById(R.id.pie);
        mDialog = new Dialog(context, R.style.HudDialogStyle);
        mDialog.setContentView(view);
        // 默认只能通过dismiss()方法关闭
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        if (dialogWindow == null) return;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 设置宽度、高度、居中
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 设置加载信息
     *
     * @param label 加载信息
     * @return 弹窗对象
     */
    public DialogHud setLabel(String label) {
        if (label == null)
            throw new NullPointerException();
        return setLabel(new DialogText.Builder(label).build());
    }

    /**
     * 设置加载信息
     *
     * @param resId 加载信息
     * @return 弹窗对象
     */
    public DialogHud setLabel(@StringRes int resId) {
        return setLabel(mContext.getResources().getString(resId));
    }

    /**
     * 设置加载信息
     *
     * @param title           加载信息
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabel(String title, View.OnClickListener onClickListener) {
        if (title == null || onClickListener == null)
            throw new NullPointerException();
        return setLabel(new DialogText.Builder(title).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置加载信息
     *
     * @param resId           加载信息
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabel(@StringRes int resId, View.OnClickListener onClickListener) {
        return setLabel(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置加载信息
     *
     * @param title     加载信息
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogHud setLabel(String title, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null)
            throw new NullPointerException();
        return setLabel(new DialogText.Builder(title).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置加载信息
     *
     * @param resId     加载信息
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogHud setLabel(@StringRes int resId, DialogTextStyle itemStyle) {
        return setLabel(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置加载信息
     *
     * @param title           加载信息
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogHud setLabel(String title, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setLabel(new DialogText.Builder(title).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置加载信息
     *
     * @param resId           加载信息
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogHud setLabel(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setLabel(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置加载信息
     *
     * @param label 加载信息
     * @return 弹窗对象
     */
    public DialogHud setLabel(DialogText label) {
        if (label == null)
            throw new NullPointerException("title can't be null!");
        if (label.getText() != null)
            mLabel.setText(label.getText());
        if (label.getOnClickListener() != null)
            mLabel.setOnClickListener(label.getOnClickListener());
        if (label.getDialogTextStyle() != null) {
            mLabel.setTextSize(label.getDialogTextStyle().getTextSize());
            mLabel.setTextColor(label.getDialogTextStyle().getColor());
            mLabel.setTypeface(label.getDialogTextStyle().getTypeface());
        }
        mShowLabel = true;
        return this;
    }

    /**
     * 设置加载信息样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogHud setLabelStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mLabel.setTextSize(style.getTextSize());
        mLabel.setTextColor(style.getColor());
        mLabel.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置加载信息点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabelClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mLabel.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置加载信息详情内容
     *
     * @param labelDetail 加载信息详情内容
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(String labelDetail) {
        if (labelDetail == null)
            throw new NullPointerException();
        return setLabelDetail(new DialogText.Builder(labelDetail).build());
    }

    /**
     * 设置详情信息内容
     *
     * @param resId 弹窗内容
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(@StringRes int resId) {
        return setLabelDetail(mContext.getResources().getString(resId));
    }

    /**
     * 设置详情信息内容
     *
     * @param labelDetail     弹窗内容
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(String labelDetail, View.OnClickListener onClickListener) {
        if (labelDetail == null || onClickListener == null)
            throw new NullPointerException();
        return setLabelDetail(new DialogText.Builder(labelDetail).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置详情信息内容
     *
     * @param resId           弹窗内容
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(@StringRes int resId, View.OnClickListener onClickListener) {
        return setLabelDetail(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置详情信息内容
     *
     * @param labelDetail 弹窗内容
     * @param itemStyle   样式
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(String labelDetail, DialogTextStyle itemStyle) {
        if (labelDetail == null || itemStyle == null)
            throw new NullPointerException();
        return setLabelDetail(new DialogText.Builder(labelDetail).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置详情信息内容
     *
     * @param resId     弹窗内容
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(@StringRes int resId, DialogTextStyle itemStyle) {
        return setLabelDetail(mContext.getResources().getString(resId), itemStyle);
    }

    /**
     * 设置详情信息内容
     *
     * @param labelDetail     弹窗内容
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(String labelDetail, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (labelDetail == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setLabelDetail(new DialogText.Builder(labelDetail).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置详情信息内容
     *
     * @param resId           弹窗内容
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setLabelDetail(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置详情信息内容
     *
     * @param labelDetail 详情内容
     * @return 弹窗对象
     */
    public DialogHud setLabelDetail(DialogText labelDetail) {
        if (labelDetail == null)
            throw new NullPointerException("content can't be null!");
        if (labelDetail.getText() != null)
            mDetailLabel.setText(labelDetail.getText());
        if (labelDetail.getOnClickListener() != null)
            mDetailLabel.setOnClickListener(labelDetail.getOnClickListener());
        if (labelDetail.getDialogTextStyle() != null) {
            mDetailLabel.setTextSize(UIUtil.dipToPx(mContext, labelDetail.getDialogTextStyle().getTextSize()));
            mDetailLabel.setTextColor(labelDetail.getDialogTextStyle().getColor());
            mDetailLabel.setTypeface(labelDetail.getDialogTextStyle().getTypeface());
        }
        mShowDetailLabel = true;
        return this;
    }

    /**
     * 设置内容样式
     *
     * @param style 样式
     * @return 弹窗对象
     */
    public DialogHud setLabelDetailStyle(DialogTextStyle style) {
        if (style == null)
            throw new NullPointerException();
        mDetailLabel.setTextSize(style.getTextSize());
        mDetailLabel.setTextColor(style.getColor());
        mDetailLabel.setTypeface(style.getTypeface());
        return this;
    }

    /**
     * 设置内容点击事件
     *
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogHud setLabelDetailClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mDetailLabel.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置弹窗宽度所占比例
     *
     * @param widthRatio 宽度比例
     * @return 弹窗对象
     */
    public DialogHud setWidthRatio(float widthRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (DisplayUtil.getInstance(mContext).getScreenWidth() * widthRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置弹窗宽度
     *
     * @param width 宽度 单位：dp
     * @return 弹窗对象
     */
    public DialogHud setWidth(float width) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = UIUtil.dipToPx(mContext, width);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置弹窗高度所占比例
     *
     * @param heightRatio 高度比例
     * @return 弹窗对象
     */
    public DialogHud setHeightRatio(float heightRatio) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) (DisplayUtil.getInstance(mContext).getScreenHeight() * heightRatio);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置弹窗高度
     *
     * @param height 高度 单位：dp
     * @return 弹窗对象
     */
    public DialogHud setHeight(float height) {
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = UIUtil.dipToPx(mContext, height);
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置循环图像
     *
     * @param resId resource id
     * @return 弹窗对象
     */
    public DialogHud setLoadingImage(@DrawableRes int resId) {
        return setLoadingImage(ResourcesCompat.getDrawable(mContext.getResources(), resId, mContext.getTheme()));
    }

    /**
     * 设置循环图像
     *
     * @param drawable 循环图像
     * @return 弹窗对象
     */
    public DialogHud setLoadingImage(Drawable drawable) {
        if (drawable == null)
            throw new NullPointerException();
        mLoadingView.setImageDrawable(drawable);
        loading();
        return this;
    }

    /**
     * 设置循环图像
     *
     * @param bitmap 循环图像
     * @return 弹窗对象
     */
    public DialogHud setLoadingImage(Bitmap bitmap) {
        if (bitmap == null)
            throw new NullPointerException();
        mLoadingView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置循环图像
     *
     * @param uri 循环图像
     * @return 弹窗对象
     */
    public DialogHud setLoadingImage(Uri uri) {
        if (uri == null)
            throw new NullPointerException();
        mLoadingView.setImageURI(uri);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param resId 颜色id
     * @return 弹窗对象
     */
    public DialogHud setBackgroundColor(@ColorRes int resId) {
        ConstraintLayout constraintLayout = mDialog.findViewById(R.id.dialog_hud);
        GradientDrawable drawable = (GradientDrawable) constraintLayout.getBackground();
        drawable.setColor(mContext.getResources().getColor(resId));
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param color 颜色
     * @return 弹窗对象
     */
    public DialogHud setBackgroundColor(String color) {
        ConstraintLayout constraintLayout = mDialog.findViewById(R.id.dialog_hud);
        GradientDrawable drawable = (GradientDrawable) constraintLayout.getBackground();
        drawable.setColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置背景色
     *
     * @param amount 背景暗色程度
     * @return 弹窗对象
     */
    public DialogHud setDimAmount(float amount) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setDimAmount(amount);
        return this;
    }

    /**
     * 停止动画转动
     *
     * @return 弹窗
     */
    public DialogHud stopAnimation() {
        mLoadingView.clearAnimation();
        return this;
    }

    /**
     * 开始动画转动
     *
     * @return 弹窗
     */
    public DialogHud startAnimation() {
        mLoadingView.start();
        return this;
    }

    /**
     * 设置弹窗是否可以使用返回键消失
     *
     * @param cancelable true: 可以使用返回键使其消失 反正不可以
     * @return 弹窗对象
     */
    public DialogHud setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
        return this;
    }

    /**
     * 设置点击外部区域，弹窗是否消失
     *
     * @param canceledOnTouchOutside true： 点击外部区域，弹窗消失 反正不消失
     * @return 弹窗对象
     */
    public DialogHud setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    /**
     * 设置详情信息可见性
     *
     * @param showLabel 详情信息可见性
     * @return 弹窗对象
     */
    public DialogHud setShowLabel(boolean showLabel) {
        this.mShowLabel = showLabel;
        mLabel.setVisibility(showLabel ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置详情信息按钮可见性
     *
     * @param showLabelDetail 详情信息按钮可见性
     * @return 弹窗对象
     */
    public DialogHud setShowLabelDetail(boolean showLabelDetail) {
        this.mShowDetailLabel = showLabelDetail;
        mDetailLabel.setVisibility(showLabelDetail ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置弹窗自动消失
     *
     * @param automaticDisappear true: 自动消失 false 不自动消失
     * @return 弹窗对象
     */
    public DialogHud setAutomaticDisappear(boolean automaticDisappear) {
        this.mAutomaticDisappear = automaticDisappear;
        return this;
    }

    /**
     * 设置弹窗自动消失时间
     *
     * @param disappearTime 自动消失时间 单位: ms
     * @return 弹窗对象
     */
    public DialogHud setDisappearTime(int disappearTime) {
        this.mDisappearTime = disappearTime;
        return this;
    }

    /**
     * 设置弹窗显示加载成功
     */
    public void showSuccess() {
        setLoadingImage(R.drawable.ic_load_success);
        stopAnimation();
        show();
    }

    /**
     * 设置弹窗显示加载成功
     */
    public void showLoading() {
        setLoadingImage(R.drawable.ic_progress_view);
        startAnimation();
        show();
    }

    /**
     * 设置弹窗显示加载失败
     */
    public void showFail() {
        setLoadingImage(R.drawable.ic_load_fail);
        stopAnimation();
        show();
    }

    /**
     * 展示加载弹窗
     *
     * @param progress 当前进度
     * @return 弹窗对象
     */
    public DialogHud setProgress(float progress) {
        mProgressView.setProgress(progress);
        return this;
    }

    /**
     * 设置进度最大值
     *
     * @param maxProgress 进度最大值
     * @return 弹窗对象
     */
    public DialogHud setMaxProgress(float maxProgress) {
        progress();
        mProgressView.setMaxProgress(maxProgress);
        return this;
    }

    /**
     * 设置是否显示进度条内部文字
     *
     * @param showProgressText 是否显示进度条内部文字
     * @return 弹窗对象
     */
    public DialogHud setShowProgressText(boolean showProgressText) {
        mProgressView.setShowProgressText(showProgressText);
        return this;
    }

    /**
     * 设置进度条内部字体大小
     *
     * @param progressTextSize 进度条内部字体大小
     * @return 弹窗对象
     */
    public DialogHud setProgressTextSize(float progressTextSize) {
        mProgressView.setProgressTextSize(progressTextSize);
        return this;
    }

    /**
     * 设置进度条内部字体颜色
     *
     * @param resId 进度条内部字体颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressTextColor(@ColorRes int resId) {
        mProgressView.setProgressTextColor(resId);
        return this;
    }

    /**
     * 设置进度条内部字体颜色
     *
     * @param color 进度条内部字体颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressTextColor(String color) {
        mProgressView.setProgressTextColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置进度条内部字体样式
     *
     * @param typeface 进度条内部字体样式
     * @return 弹窗对象
     */
    public DialogHud setProgressTextTypeface(Typeface typeface) {
        mProgressView.setProgressTextTypeface(typeface);
        return this;
    }

    /**
     * 设置进度条颜色
     *
     * @param color 进度条颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressColor(String color) {
        mProgressView.setProgressColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置进度条颜色
     *
     * @param resId 进度条颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressColor(@ColorRes int resId) {
        mProgressView.setProgressColor(resId);
        return this;
    }

    /**
     * 设置进度条背景颜色
     *
     * @param color 进度条背景颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressBgColor(String color) {
        mProgressView.setProgressBgColor(Color.parseColor(color));
        return this;
    }

    /**
     * 设置进度条背景颜色
     *
     * @param resId 进度条背景颜色
     * @return 弹窗对象
     */
    public DialogHud setProgressBgColor(@ColorRes int resId) {
        mProgressView.setProgressBgColor(resId);
        return this;
    }

    /**
     * 设置进度条半径
     *
     * @param progressRadius 进度条半径 单位: dp
     * @return 弹窗对象
     */
    public DialogHud setProgressRadius(float progressRadius) {
        mProgressView.setProgressRadius(progressRadius);
        return this;
    }

    /**
     * 设置进度条宽度
     *
     * @param progressWidth 进度条宽度 单位: dp
     * @return 弹窗对象
     */
    public DialogHud setProgressWidth(float progressWidth) {
        mProgressView.setProgressWidth(progressWidth);
        return this;
    }

    /**
     * 设置进度条方向
     *
     * @param direction 方向
     * @return 弹窗对象
     */
    public DialogHud setDirection(ProgressView.Direction direction) {
        mProgressView.setDirection(direction.getDirection());
        return this;
    }


    /**
     * 展示圆形进度条
     */
    private void progress() {
        mShowLoading = false;
        mShowProgress = true;
    }

    /**
     * 展示loading动画
     */
    private void loading() {
        mShowLoading = true;
        mShowProgress = false;
    }

    /**
     * 设置可见性
     */
    private void setVisibility() {
        mLabel.setVisibility(mShowLabel ? View.VISIBLE : View.GONE);
        mDetailLabel.setVisibility(mShowDetailLabel ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(mShowLoading ? View.VISIBLE : View.GONE);
        mProgressView.setVisibility(mShowProgress ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置可见性，并展示该弹窗
     */
    public void show() {
        mLabel.setVisibility(mShowLabel ? View.VISIBLE : View.GONE);
        mDetailLabel.setVisibility(mShowDetailLabel ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(mShowLoading ? View.VISIBLE : View.GONE);
        mProgressView.setVisibility(mShowProgress ? View.VISIBLE : View.GONE);
        if (!mDialog.isShowing())
            mDialog.show();
        if (mAutomaticDisappear) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, mDisappearTime);
        }
    }
}
