package com.civitasv.ioslike.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Objects;

/**
 * @author 胡森
 * recyclerView分割线
 * 2020-08-26.
 */
public class DialogBottomItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "DividerItem";

    private static final int[] ATTRS = new int[]{16843284};

    private Drawable mDivider;
    private final Rect mBounds = new Rect();

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public DialogBottomItemDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(0);
        if (this.mDivider == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
    }


    public void setDrawable(@NonNull Drawable drawable) {
        this.mDivider = drawable;
    }

    @Override
    public void onDraw(@NonNull Canvas c, RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            this.drawVertical(c, parent);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        //当最后一个子项的时候去除下划线
        for (int i = 0; i < childCount - 1; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (this.mDivider == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            int lastCount = Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1;
            //纵向RecyclerView
            //如果不是最后一条 正常赋值 如果是最后一条 赋值为0
            if (childAdapterPosition != lastCount && view.getVisibility() == View.VISIBLE) {
                outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}
