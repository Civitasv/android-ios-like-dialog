package com.civitasv.ioslike;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.civitasv.ioslike.databinding.ActivityDialogHudBinding;
import com.civitasv.ioslike.databinding.ActivityDialogNormalBinding;
import com.civitasv.ioslike.dialog.DialogHud;
import com.civitasv.ioslike.dialog.DialogNormal;
import com.civitasv.ioslike.model.DialogTextStyle;

public class DialogHudActivity extends AppCompatActivity {

    private ActivityDialogHudBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_hud);
        bindOperation();
    }

    private void bindOperation() {
        binding.first.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.LOADING)
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.second.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.LOADING)
                    .setLabel("Please wait...")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.third.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.LOADING)
                    .setLabel("Please wait...")
                    .setLabelDetail("downloading...")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.fourth.setOnClickListener(v -> {
            DialogHud dialogHud = new DialogHud(this)
                    .setMode(DialogHud.Mode.ANNULAR)
                    .setLabel("Please wait...")
                    .setLabelDetail("downloading...")
                    .setAutomaticDisappear(true)
                    .setDisappearTime(500)
                    .setMaxProgress(100)
                    .setCanceledOnTouchOutside(true);
            dialogHud.show();
            dialogHud.setLabelClickListener(v2 -> {
                dialogHud.setProgress(100);
            });
        });

        binding.fifth.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.SUCCESS)
                    .setLabel("download success!")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.sixth.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.FAIL)
                    .setLabel("download fail!")
                    .setCanceledOnTouchOutside(true)
                    .show();
        });

        binding.seventh.setOnClickListener(v -> {
            new DialogHud(this)
                    .setMode(DialogHud.Mode.LOADING)
                    .setLabel("Please wait...")
                    .setLabelDetail("downloading...")
                    .setCanceledOnTouchOutside(true)
                    .setAutomaticDisappear(true)
                    .show();
        });
    }
}