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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.adapter.DialogBottomAdapter;
import com.civitasv.ioslike.divider.DialogBottomItemDecoration;
import com.civitasv.ioslike.model.DialogText;
import com.civitasv.ioslike.model.DialogTextStyle;
import com.civitasv.ioslike.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Civitasv
 * 仿ios底部弹出框
 * 2020-11-21
 */
public class DialogBottom {
    private final Context mContext;
    private final Dialog mDialog; // 弹窗
    private final Button mCancel; // 取消按钮
    private final TextView mTitle; // 标题
    private final View mSplit;
    private final RecyclerView mRecyclerView;
    private DialogBottomAdapter mAdapter;
    private boolean mShowTitle = false; // 是否展示title
    private boolean mShowCancel = true; // 是否展示底部取消按钮

    public DialogBottom(Context context) {
        this(context, null);
    }

    public DialogBottom(Context context, List<DialogText> items) {
        mContext = context;
        // 获取Dialog布局
        View view = View.inflate(context, R.layout.dialog_bottom, null);

        mTitle = view.findViewById(R.id.title);
        mCancel = view.findViewById(R.id.cancel);
        mSplit = view.findViewById(R.id.split);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mDialog = new Dialog(context, R.style.BottomDialogStyle);
        mDialog.setContentView(view);
        // 默认可以自动关闭
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (DisplayUtil.getInstance(context).getScreenWidth());
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        initBottomList();
        if (items != null) {
            setBottomList(items);
        } else setBottomList(new ArrayList<>());
    }

    /**
     * 初始化底部recycleView
     */
    private void initBottomList() {
        mAdapter = new DialogBottomAdapter(mContext);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        DialogBottomItemDecoration dividerItemDecoration = new DialogBottomItemDecoration(mContext);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置底部显示条目list
     *
     * @param items 底部条目list
     * @return 弹窗对象
     */
    public DialogBottom setBottomList(List<DialogText> items) {
        if (items == null)
            throw new NullPointerException("items can't be null!");
        mAdapter.setItems(items);
        return this;
    }

    /**
     * 添加条目
     *
     * @param item 条目
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(DialogText item) {
        if (item == null)
            throw new NullPointerException("item can't be null!");
        mAdapter.addItem(item);
        return this;
    }

    /**
     * 添加条目
     *
     * @param item 条目
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item) {
        if (item == null)
            throw new NullPointerException();
        return addBottomItem(new DialogText.Builder(item).build());
    }

    /**
     * 添加条目
     *
     * @param resId 条目
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(@StringRes int resId) {
        return addBottomItem(mContext.getResources().getString(resId));
    }

    /**
     * 添加条目
     *
     * @param item            条目
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, View.OnClickListener onClickListener) {
        if (item == null || onClickListener == null)
            throw new NullPointerException();
        return addBottomItem(new DialogText.Builder(item).setOnclickListener(onClickListener).build());
    }

    /**
     * 添加条目
     *
     * @param resId           条目
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(@StringRes int resId, View.OnClickListener onClickListener) {
        return addBottomItem(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 添加条目
     *
     * @param item      条目
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, DialogTextStyle itemStyle) {
        if (item == null || itemStyle == null)
            throw new NullPointerException();
        return addBottomItem(new DialogText.Builder(item).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 添加条目
     *
     * @param resId     条目
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(@StringRes int resId, DialogTextStyle itemStyle) {
        return addBottomItem(mContext.getResources().getString(resId), null, itemStyle);
    }

    /**
     * 添加条目
     *
     * @param item            条目
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (item == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return addBottomItem(new DialogText.Builder(item).setOnclickListener(onClickListener).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 添加条目
     *
     * @param resId           条目内容
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return addBottomItem(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogBottom setTitle(String title) {
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
    public DialogBottom setTitle(@StringRes int resId) {
        return setTitle(mContext.getResources().getString(resId));
    }

    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom setTitle(String title, View.OnClickListener onClickListener) {
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
    public DialogBottom setTitle(@StringRes int resId, View.OnClickListener onClickListener) {
        return setTitle(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置标题
     *
     * @param title     标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogBottom setTitle(String title, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null)
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).setDialogTextStyle(itemStyle).build());
    }

    /**
     * 设置标题
     *
     * @param resId     标题
     * @param itemStyle 样式
     * @return 弹窗对象
     */
    public DialogBottom setTitle(@StringRes int resId, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(resId), itemStyle);
    }


    /**
     * 设置标题
     *
     * @param title           标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogBottom setTitle(String title, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        if (title == null || itemStyle == null || onClickListener == null)
            throw new NullPointerException();
        return setTitle(new DialogText.Builder(title).setDialogTextStyle(itemStyle).setOnclickListener(onClickListener).build());
    }

    /**
     * 设置标题
     *
     * @param resId           标题
     * @param onClickListener 点击事件
     * @param itemStyle       样式
     * @return 弹窗对象
     */
    public DialogBottom setTitle(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setTitle(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogBottom setTitle(DialogText title) {
        if (title == null)
            throw new NullPointerException("argument can't be null!");
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
    public DialogBottom setTitleStyle(DialogTextStyle style) {
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
    public DialogBottom setTitleClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mTitle.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 取消按钮字符串内容
     * @return 弹窗对象
     */
    public DialogBottom setCancel(String cancelText) {
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
    public DialogBottom setCancel(@StringRes int resId) {
        return setCancel(mContext.getResources().getString(resId));
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText      标题
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom setCancel(String cancelText, View.OnClickListener onClickListener) {
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
    public DialogBottom setCancel(@StringRes int resId, View.OnClickListener onClickListener) {
        return setCancel(mContext.getResources().getString(resId), onClickListener);
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelText 标题
     * @param itemStyle  样式
     * @return 弹窗对象
     */
    public DialogBottom setCancel(String cancelText, DialogTextStyle itemStyle) {
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
    public DialogBottom setCancel(@StringRes int resId, DialogTextStyle itemStyle) {
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
    public DialogBottom setCancel(String cancelText, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
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
    public DialogBottom setCancel(@StringRes int resId, View.OnClickListener onClickListener, DialogTextStyle itemStyle) {
        return setCancel(mContext.getResources().getString(resId), onClickListener, itemStyle);
    }

    /**
     * 设置取消按钮
     *
     * @param cancel 取消按钮
     * @return 弹窗对象
     */
    public DialogBottom setCancel(DialogText cancel) {
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
    public DialogBottom setCancelStyle(DialogTextStyle style) {
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
    public DialogBottom setCancelClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null)
            throw new NullPointerException();
        mCancel.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置弹窗是否可以使用返回键消失
     *
     * @param cancelable true: 可以使用返回键使其消失 反正不可以
     * @return 弹窗对象
     */
    public DialogBottom setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
        return this;
    }

    /**
     * 设置点击外部区域，弹窗是否消失
     *
     * @param canceledOnTouchOutside true： 点击外部区域，弹窗消失 反正不消失
     * @return 弹窗对象
     */
    public DialogBottom setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    /**
     * 设置是否显示取消按钮
     *
     * @param showCancel 是否显示取消按钮
     * @return 弹窗对象
     */
    public DialogBottom setShowCancel(boolean showCancel) {
        mShowCancel = showCancel;
        return this;
    }

    /**
     * 设置背景色
     *
     * @param amount 背景暗色程度
     * @return 弹窗对象
     */
    public DialogBottom setDimAmount(float amount) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setDimAmount(amount);
        return this;
    }

    /**
     * 展示
     */
    public void show() {
        mTitle.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        mCancel.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        mSplit.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        mDialog.show();
    }

    /**
     * 消失
     */
    public void dismiss() {
        mDialog.dismiss();
    }
}