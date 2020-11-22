package com.civitasv.ioslike.util;

import android.content.Context;

/**
 * @author 胡森
 * @description
 * @date 2020-10-11
 */
public class UIUtil {
    /**
     * dp to px
     *
     * @param context 上下文
     * @param dip     dp值
     * @return px值
     */
    public static int dipToPx(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px to dp
     *
     * @param context 上下文
     * @param px      px值
     * @return dp值
     */
    public static int px2dip(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
