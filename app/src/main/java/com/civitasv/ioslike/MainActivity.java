package com.civitasv.ioslike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.civitasv.ioslike.dialog.DialogNormal;

public class MainActivity extends AppCompatActivity {

    private Button dialogNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindOperation();
    }

    private void initView() {
        dialogNormal = findViewById(R.id.normal);
    }

    private void bindOperation() {
        dialogNormal.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setCancel("取消")
                    .setConfirm("确定")
                    .setTitle("标题")
                    .setContent("内容")
                    .setCanceledOnTouchOutside(false)
                    .show();
        });
    }
}