package com.civitasv.ioslike.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author 胡森
 * 界面大小
 * 2020-11-20
 */
public class DisplayUtil {
    private static DisplayUtil instance;
    private final int screenWidth;
    private final int screenHeight;

    public static DisplayUtil getInstance(Context mContext) {
        if (instance == null) {
            synchronized (DisplayUtil.class) {
                if (instance == null)
                    instance = new DisplayUtil(mContext);
            }
        }
        return instance;
    }

    private DisplayUtil(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        screenHeight = dm.heightPixels;// 获取屏幕分辨率高度
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        return screenWidth;
    }

    //获取屏幕高度
    public int getScreenHeight() {
        return screenHeight;
    }
}
