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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.adapter.DialogBottomAdapter;
import com.civitasv.ioslike.divider.DialogBottomItemDecoration;
import com.civitasv.ioslike.model.DialogBottomItem;
import com.civitasv.ioslike.model.DialogBottomItemStyle;
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

    public DialogBottom(Context context, List<DialogBottomItem> items) {
        mContext = context;
        // 获取Dialog布局
        View view = View.inflate(context, R.layout.dialog_bottom, null);

        mTitle = view.findViewById(R.id.title);
        mCancel = view.findViewById(R.id.cancel);
        mSplit = view.findViewById(R.id.split);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mDialog = new Dialog(context, R.style.BottomDialogStyle);
        mDialog.setContentView(view);
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
    public DialogBottom setBottomList(List<DialogBottomItem> items) {
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
    public DialogBottom addBottomItem(DialogBottomItem item) {
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
        return addBottomItem(new DialogBottomItem(item));
    }

    /**
     * 添加条目
     *
     * @param item            条目
     * @param onClickListener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, View.OnClickListener onClickListener) {
        return addBottomItem(new DialogBottomItem(item, onClickListener));
    }

    /**
     * 添加条目
     *
     * @param item      条目
     * @param itemStyle 条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, DialogBottomItemStyle itemStyle) {
        return addBottomItem(new DialogBottomItem(item, null, itemStyle));
    }

    /**
     * 添加条目
     *
     * @param item            条目
     * @param onClickListener 点击事件
     * @param itemStyle       条目样式
     * @return 弹窗对象
     */
    public DialogBottom addBottomItem(String item, View.OnClickListener onClickListener, DialogBottomItemStyle itemStyle) {
        return addBottomItem(new DialogBottomItem(item, onClickListener, itemStyle));
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 弹窗对象
     */
    public DialogBottom setTitle(String title) {
        if (title == null)
            throw new NullPointerException("title can't be null!");
        mTitle.setText(title);
        mShowTitle = true;
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param titleTextColor 标题颜色res id
     * @return 弹窗对象
     */
    public DialogBottom setTitleTextColor(int titleTextColor) {
        mTitle.setTextColor(titleTextColor);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param titleTextColor 标题颜色字符串
     * @return 弹窗对象
     */
    public DialogBottom setTitleTextColor(String titleTextColor) {
        if (titleTextColor == null)
            throw new NullPointerException("titleTextColor can't be null!");
        mTitle.setTextColor(Color.parseColor(titleTextColor));
        return this;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancelText 取消按钮文字字符串
     * @return 弹窗对象
     */
    public DialogBottom setCancelText(String cancelText) {
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
    public DialogBottom setCancelText(int cancelText) {
        mCancel.setText(cancelText);
        return this;
    }

    /**
     * 确定按钮点击事件
     *
     * @param listener 点击事件
     * @return 弹窗对象
     */
    public DialogBottom setOnCancelClickListener(View.OnClickListener listener) {
        if (listener == null)
            throw new NullPointerException("listener can't be null!");
        mCancel.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置取消按钮颜色
     *
     * @param cancelTextColor 取消按钮颜色 res id
     * @return 弹窗对象
     */
    public DialogBottom setCancelTextColor(int cancelTextColor) {
        mCancel.setTextColor(cancelTextColor);
        return this;
    }

    /**
     * 取消按钮颜色
     *
     * @param cancelTextColor 取消按钮字符串颜色
     * @return 弹窗对象
     */
    public DialogBottom setCancelTextColor(String cancelTextColor) {
        if (cancelTextColor == null)
            throw new NullPointerException("cancelTextColor can't be null!");
        mCancel.setTextColor(Color.parseColor(cancelTextColor));
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
