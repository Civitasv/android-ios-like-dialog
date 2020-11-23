package com.civitasv.ioslike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.civitasv.ioslike.databinding.ActivityMainBinding;
import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.dialog.DialogHud;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogTextStyle;

public class MainActivity extends AppCompatActivity {

    private DialogBottom dialogBottoms;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bindOperation();
    }

    private void bindOperation() {
        binding.normal.setOnClickListener(v -> {
            // 跳转至查看普通弹窗效果
            Intent intent = new Intent(MainActivity.this, DialogNormalActivity.class);
            startActivity(intent);
        });

        binding.bottom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DialogBottomActivity.class);
            startActivity(intent);
        });

        binding.hud.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DialogHudActivity.class);
            startActivity(intent);
        });

    }
}