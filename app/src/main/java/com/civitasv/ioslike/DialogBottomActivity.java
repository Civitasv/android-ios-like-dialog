package com.civitasv.ioslike;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.civitasv.ioslike.databinding.ActivityDialogBottomBinding;
import com.civitasv.ioslike.databinding.ActivityDialogNormalBinding;
import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogText;
import com.civitasv.ioslike.model.DialogTextStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogBottomActivity extends AppCompatActivity {

    private ActivityDialogBottomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_bottom);
        bindOperation();
    }

    private void bindOperation() {
        binding.first.setOnClickListener(v -> {
            new DialogBottom(this)
                    .setTitle("标题")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.second.setOnClickListener(v -> {
            new DialogBottom(this)
                    .setTitle("标题")
                    .setCancel("取消")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.third.setOnClickListener(v -> {
            new DialogBottom(this)
                    .setTitle("标题")
                    .setCancel("取消")
                    .addBottomItem("按钮1")
                    .addBottomItem("按钮2")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.fourth.setOnClickListener(v -> {
            DialogText dialogText = new DialogText.Builder("按钮1").build();
            DialogText dialogText2 = new DialogText.Builder("按钮2").build();
            List<DialogText> list = new ArrayList<>();
            list.add(dialogText);
            list.add(dialogText2);
            new DialogBottom(this)
                    .setTitle("标题")
                    .setCancel("取消")
                    .setBottomList(list)
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.fifth.setOnClickListener(v -> {
            new DialogBottom(this)
                    .setTitle("标题")
                    .setCancel("取消")
                    .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_green).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .addBottomItem("按钮1", new DialogTextStyle.Builder(this).color(R.color.ios_like_pink).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .addBottomItem("按钮2", new DialogTextStyle.Builder(this).color(R.color.ios_like_purple).textSize(20).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.sixth.setOnClickListener(v -> {
            new DialogBottom(this)
                    .setTitle("标题", v2 -> {
                        Toast.makeText(this, "点击标题", Toast.LENGTH_SHORT).show();
                    })
                    .setCancel("取消", v2 -> {
                        Toast.makeText(this, "点击取消", Toast.LENGTH_SHORT).show();
                    }, false)
                    .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_green).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .addBottomItem("按钮1", v2 -> Toast.makeText(this, "点击按钮1", Toast.LENGTH_SHORT).show(), new DialogTextStyle.Builder(this).color(R.color.ios_like_pink).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .addBottomItem("按钮2", new DialogTextStyle.Builder(this).color(R.color.ios_like_purple).textSize(20).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setCanceledOnTouchOutside(true)
                    .show();
        });
    }
}