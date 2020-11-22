package com.civitasv.ioslike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.dialog.DialogHud;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogTextStyle;

public class MainActivity extends AppCompatActivity {

    private Button dialogNormal, dialogBottom, dialogHud;
    private DialogBottom dialogBottoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindOperation();
    }

    private void initView() {
        dialogNormal = findViewById(R.id.normal);
        dialogBottom = findViewById(R.id.bottom);
        dialogHud = findViewById(R.id.hud);
    }

    private void bindOperation() {
        dialogNormal.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题", new DialogTextStyle.Builder()
                            .color(getResources().getColor(R.color.ios_like_red))
                            .textSize(18)
                            .typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setContent("内容", content -> {
                        Toast.makeText(this, "点击内容", Toast.LENGTH_LONG).show();
                    })
                    .setCancel("取消")
                    .setCancelClickListener(cancel -> Toast.makeText(this, "取消按钮点击", Toast.LENGTH_LONG).show())
                    .show();
        });

        dialogBottom.setOnClickListener(v -> {
            dialogBottoms = new DialogBottom(this)
                    .setTitle("这张照片将从所有设备上的\"iCloud照片\"中删除。")
                    .addBottomItem("删除照片")
                    .addBottomItem("添加照片")
                    .addBottomItem("点击事件", v1 -> dialogBottoms.dismiss())
                    .addBottomItem("颜色样式", new DialogTextStyle.Builder()
                            .color(getResources().getColor(R.color.ios_like_red))
                            .textSize(18)
                            .typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setShowCancel(false);
            dialogBottoms.show();
        });

        dialogHud.setOnClickListener(v -> {
            DialogHud dialogHud = new DialogHud(this)
                    .setCanceledOnTouchOutside(true)
                    .setMaxProgress(100)
                    .setProgress(20)
                    .setProgressWidth(10);
            dialogHud.setLabel("测试", v2 -> {
                dialogHud.setProgress(30);
            });
            dialogHud.setLabelDetail("下载完了", v2 -> {
                dialogHud.showSuccess();
                dialogHud.setShowLabel(false);
                dialogHud.setShowLabelDetail(false);
            });
            dialogHud.show();
        });

    }
}