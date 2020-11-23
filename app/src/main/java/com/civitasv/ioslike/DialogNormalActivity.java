package com.civitasv.ioslike;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.civitasv.ioslike.databinding.ActivityDialogNormalBinding;
import com.civitasv.ioslike.dialog.DialogBottom;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogTextStyle;

public class DialogNormalActivity extends AppCompatActivity {

    private ActivityDialogNormalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_normal);
        bindOperation();
    }

    private void bindOperation() {
        binding.first.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setContent("内容")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.second.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setContent("内容")
                    .setConfirm("确定")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.third.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setContent("内容")
                    .setConfirm("确定")
                    .setCancel("取消")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.fourth.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setContent("内容")
                    .setConfirm("确定", new DialogTextStyle.Builder(this).color(R.color.ios_like_red).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)).build())
                    .setCancel("取消")
                    .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_red).build())
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.fifth.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setTitleClickListener(v2 -> {
                        Toast.makeText(this, "点击标题", Toast.LENGTH_SHORT).show();
                    })
                    .setContent("内容", v2 -> {
                        Toast.makeText(this, "点击内容", Toast.LENGTH_SHORT).show();
                    })
                    .setConfirm("确定", v2 -> {
                        Toast.makeText(this, "点击确定", Toast.LENGTH_SHORT).show();
                    })
                    .setCancel("取消", v2 -> {
                        Toast.makeText(this, "点击取消", Toast.LENGTH_SHORT).show();
                    })
                    .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_green).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setConfirmStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_purple).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)).build())
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.sixth.setOnClickListener(v -> {
            new DialogNormal(this)
                    .setTitle("标题")
                    .setTitleClickListener(v2 -> {
                        Toast.makeText(this, "点击标题", Toast.LENGTH_SHORT).show();
                    })
                    .setContent("内容", v2 -> {
                        Toast.makeText(this, "点击内容", Toast.LENGTH_SHORT).show();
                    })
                    .setConfirm("确定", v2 -> {
                        Toast.makeText(this, "点击确定", Toast.LENGTH_SHORT).show();
                    })
                    .setCancel("取消", v2 -> {
                        Toast.makeText(this, "点击取消", Toast.LENGTH_SHORT).show();
                    }, false)
                    .setCancelStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_green).typeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC)).build())
                    .setConfirmStyle(new DialogTextStyle.Builder(this).color(R.color.ios_like_purple).typeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)).build())
                    .setCanceledOnTouchOutside(true)
                    .show();
        });
    }
}