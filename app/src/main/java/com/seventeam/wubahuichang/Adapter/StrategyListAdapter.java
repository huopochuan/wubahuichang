package com.seventeam.wubahuichang.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.seventeam.wubahuichang.Bean.LookplaceItemBean;
import com.seventeam.wubahuichang.R;

import java.util.List;

/**
 * Author: gengjiarong
 * Date: 2016/8/12
 */
public class StrategyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LookplaceItemBean> imageArray;
    public OnItemClickListener onItemClickListener;
    public SparseArray<String> stringArray;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_ITEM = 0;

    public StrategyListAdapter(SparseArray<String> array) {
        stringArray = array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bottom, parent, false);
            return new FooterHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strategy_list_item, parent, false);
            return new VH(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VH) {
            VH vh = (VH) holder;
            vh.imageView.setImageResource(R.mipmap.strategy_item);
        } else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return stringArray == null ? 0 : stringArray.size() + 1;
    }

    // 重写，来判断item的类型
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }


    class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_PhotoItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {

        private Button button;

        public FooterHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.btn_LookMore);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
