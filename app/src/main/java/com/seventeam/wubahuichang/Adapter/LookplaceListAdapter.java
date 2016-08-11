package com.seventeam.wubahuichang.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.seventeam.wubahuichang.Bean.LookplaceItemBean;
import com.seventeam.wubahuichang.R;

import java.util.List;

/**
 * Author: gengjiarong
 * Date: 2016/8/12
 */
public class LookplaceListAdapter extends RecyclerView.Adapter<LookplaceListAdapter.VH> {

    private List<LookplaceItemBean> imageArray;

    public LookplaceListAdapter(List<LookplaceItemBean> array) {
        imageArray = array;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lookplace_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return imageArray== null ? 0 : imageArray.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.imageView.setImageResource(imageArray.get(position).imageId);
    }

    static class VH extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_Item);
        }
    }

}
