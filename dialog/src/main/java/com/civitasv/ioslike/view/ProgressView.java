package com.civitasv.ioslike.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.util.UIUtil;


/**
 * @author Civitasv
 * 圆形加载进度条
 * 2020-11-22
 */
public class ProgressView extends View {
    private int progressColor;    // 进度的颜色
    private float progressRadius;    // 进度半径大小
    private int progressBgColor;    // 背景颜色
    private int progressTextColor;   // 圆环内文字颜色
    private float progressTextSize;    // 圆环内文字大小
    private Typeface progressTextTypeface; // 圆环内文字样式
    private float progressWidth;    // 圆环的宽度
    private float maxProgress;    // 最大进度
    private float progress;    // 当前进度
    private int direction;
    private final RectF oval;
    private boolean showProgressText = true;

    public enum Direction {
        LEFT(0, 180.0f),
        TOP(1, 270.0f),
        RIGHT(2, 0.0f),
        BOTTOM(3, 90.0f);

        private final int direction;
        private final float degree;

        Direction(int direction, float degree) {
            this.direction = direction;
            this.degree = degree;
        }

        public int getDirection() {
            return direction;
        }

        public float getDegree() {
            return degree;
        }

        public boolean equalsDirection(int direction) {
            return this.direction == direction;
        }

        public static Direction getDirection(int direction) {
            for (Direction enumObject : values()) {
                if (enumObject.equalsDirection(direction)) {
                    return enumObject;
                }
            }
            return RIGHT;
        }

        public static float getDegree(int direction) {
            Direction enumObject = getDirection(direction);
            if (enumObject == null) {
                return 0;
            }
            return enumObject.getDegree();
        }
    }

    private final Paint paint;
    private final Rect rect;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressView, defStyleAttr, 0);
        progressColor = a.getColor(R.styleable.ProgressView_progress_color, ContextCompat.getColor(getContext(), R.color.white));
        progressRadius = a.getDimension(R.styleable.ProgressView_progress_radius, UIUtil.dipToPx(getContext(), 30.0f));
        progressBgColor = a.getColor(R.styleable.ProgressView_background_color, ContextCompat.getColor(getContext(), R.color.black));
        progressTextColor = a.getColor(R.styleable.ProgressView_progress_text_color, ContextCompat.getColor(getContext(), R.color.white));
        progressTextSize = a.getDimension(R.styleable.ProgressView_progress_text_size, UIUtil.dipToPx(getContext(), 12.0f));
        progressTextTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        progressWidth = a.getDimension(R.styleable.ProgressView_progress_width, UIUtil.dipToPx(getContext(), 2.0f));
        progress = a.getFloat(R.styleable.ProgressView_progress, 0);
        maxProgress = a.getInt(R.styleable.ProgressView_max_progress, 100);
        direction = a.getInt(R.styleable.ProgressView_direction, Direction.BOTTOM.direction);
        a.recycle();
        paint = new Paint();
        oval = new RectF();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circlePoint = getWidth() / 2;
        //第一步:画背景(即内层圆)
        paint.setColor(progressBgColor); //设置圆的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(progressWidth); //设置圆的宽度
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(circlePoint, circlePoint, progressRadius, paint); //画出圆

        //第二步:画进度(圆弧)
        paint.setStrokeWidth(2 * progressWidth); //设置圆的宽度
        paint.setColor(progressColor);  //设置进度的颜色
        oval.set(circlePoint - progressRadius + progressWidth / 2, circlePoint - progressRadius + progressWidth / 2, circlePoint + progressRadius - progressWidth / 2, circlePoint + progressRadius - progressWidth / 2);  //用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval, Direction.getDegree(direction), 360 * (progress / maxProgress), false, paint);  //根据进度画圆弧

        if (showProgressText) {
            //第三步:画圆环内百分比文字
            paint.setColor(progressTextColor);
            paint.setTextSize(progressTextSize);
            paint.setTypeface(progressTextTypeface);
            paint.setStrokeWidth(0);
            //圆环内文字
            String progressText = getProgressText();
            paint.getTextBounds(progressText, 0, progressText.length(), rect);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;  //获得文字的基准线
            canvas.drawText(progressText, getMeasuredWidth() / 2f - rect.width() / 2f, baseline, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = (int) ((2 * progressRadius) + progressWidth);
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = (int) ((2 * progressRadius) + progressWidth);
        }
        setMeasuredDimension(width, height);
    }

    //中间的进度百分比
    private String getProgressText() {
        return (int) ((progress / maxProgress) * 100) + "%";
    }

    public synchronized float getMaxProgress() {
        return maxProgress;
    }

    public synchronized void setMaxProgress(float maxProgress) {
        if (maxProgress < 0) {
            //此为传递非法参数异常
            throw new IllegalArgumentException("maxProgress should not be less than 0");
        }
        this.maxProgress = maxProgress;
    }

    public synchronized float getProgress() {
        return progress;
    }

    /**
     * 设置进度条颜色
     *
     * @param progressColor 进度条颜色
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        invalidate();
    }

    /**
     * 设置是否显示进度条文字
     *
     * @param showProgressText 是否显示进度条文字
     */
    public void setShowProgressText(boolean showProgressText) {
        this.showProgressText = showProgressText;
        invalidate();
    }

    /**
     * 设置进度条背景颜色
     *
     * @param backgroundColor 背景颜色
     */
    public void setProgressBgColor(int backgroundColor) {
        this.progressBgColor = backgroundColor;
        invalidate();
    }

    /**
     * 设置进度条半径
     *
     * @param progressRadius 半径
     */
    public void setProgressRadius(float progressRadius) {
        this.progressRadius = progressRadius;
        invalidate();
    }

    /**
     * 设置进度条内部字体颜色
     *
     * @param progressTextColor 进度条内部字体颜色
     */
    public void setProgressTextColor(int progressTextColor) {
        this.progressTextColor = progressTextColor;
        invalidate();
    }

    /**
     * 设置进度条内部字体大小
     *
     * @param progressTextSize 进度条内部字体大小 单位：dp
     */
    public void setProgressTextSize(float progressTextSize) {
        this.progressTextSize = UIUtil.dipToPx(getContext(), progressTextSize);
        invalidate();
    }

    /**
     * 设置进度条内部字体样式
     *
     * @param typeface 进度条内部字体样式
     */
    public void setProgressTextTypeface(Typeface typeface) {
        this.progressTextTypeface = typeface;
        invalidate();
    }

    /**
     * 设置进度条宽度
     *
     * @param progressWidth 进度条宽度 单位: dp
     */
    public void setProgressWidth(float progressWidth) {
        this.progressWidth = UIUtil.dipToPx(getContext(), progressWidth);
        invalidate();
    }

    /**
     * 设置进度条方向
     *
     * @param direction 方向
     */
    public void setDirection(int direction) {
        this.direction = direction;
        invalidate();
    }

    //加锁保证线程安全,能在线程中使用
    public synchronized void setProgress(float progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress should not be less than 0");
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }
        if (isShown() && this.progress != progress)
            startAnim(this.progress, progress);
        this.progress = progress;
    }

    private void startAnim(float startProgress, float endProgress) {
        ValueAnimator animator = ObjectAnimator.ofFloat(startProgress, endProgress);
        animator.addUpdateListener(animation -> {
            this.progress = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.setStartDelay(500);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}
