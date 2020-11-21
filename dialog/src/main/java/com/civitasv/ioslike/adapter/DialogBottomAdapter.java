package com.civitasv.ioslike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.civitasv.dialog.R;
import com.civitasv.ioslike.model.DialogText;

import java.util.List;

/**
 * @author Civitasv
 * 底部弹窗适配器
 * 2020-11-21
 */
public class DialogBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DialogBottomAdapter";
    private final Context mContext;
    private List<DialogText> mItems;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param items   列表
     */
    public DialogBottomAdapter(Context context, List<DialogText> items) {
        mContext = context;
        mItems = items;
    }

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public DialogBottomAdapter(Context context) {
        mContext = context;
    }

    public void setItems(List<DialogText> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void addItem(DialogText item) {
        if (item == null)
            throw new NullPointerException();
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_bottom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mItems == null)
            return;
        DialogText item = mItems.get(position);
        if (item.getText() != null)
            ((ViewHolder) holder).button.setText(item.getText());
        if (item.getOnClickListener() != null)
            ((ViewHolder) holder).button.setOnClickListener(item.getOnClickListener());
        if (item.getDialogTextStyle() != null) {
            ((ViewHolder) holder).button.setTextColor(item.getDialogTextStyle().getColor());
            ((ViewHolder) holder).button.setTextSize(item.getDialogTextStyle().getTextSize());
            ((ViewHolder) holder).button.setTypeface(item.getDialogTextStyle().getTypeface());
        }
        if (position == getItemCount() - 1) {
            ((ViewHolder) holder).button.setBackground(ContextCompat.getDrawable(mContext, R.drawable.dialog_bottom_item_last_btn));
        } else
            ((ViewHolder) holder).button.setBackground(ContextCompat.getDrawable(mContext, R.drawable.dialog_bottom_item_middle_btn));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.item);
        }
    }
}
