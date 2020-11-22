package com.civitasv.ioslike.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.civitasv.dialog.R;


/**
 * @author Civitasv
 * 旋转进度
 * 2020-08-21.
 */
public class LoadingView extends androidx.appcompat.widget.AppCompatImageView {
    private int frameCount, duration;

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAnimation(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAnimation(attrs);
    }

    public LoadingView(Context context) {
        super(context);
    }

    private void setAnimation(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingView);
        frameCount = a.getInt(R.styleable.LoadingView_frameCount, 8);
        duration = a.getInt(R.styleable.LoadingView_duration, 1000);
        a.recycle();

        setAnimation(frameCount, duration);
    }

    public void setAnimation(final int frameCount, final int duration) {
        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.progress_anim);
        a.setDuration(duration);
        a.setInterpolator(input -> (float) Math.floor(input * frameCount) / frameCount);
        startAnimation(a);
    }

    public void start() {
        setAnimation(frameCount, duration);
    }
}
