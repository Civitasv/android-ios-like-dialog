package com.civitasv.ioslike;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogTextStyle;

public class MainActivity extends AppCompatActivity {

    private Button dialogNormal, dialogBottom;
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
                    .setCanceledOnTouchOutside(false)
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
                            .typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build());
            dialogBottoms.show();
        });
    }
}