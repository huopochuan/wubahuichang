package com.seventeam.wubahuichang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seventeam.wubahuichang.Bean.ListBean;
import com.seventeam.wubahuichang.R;

import java.util.ArrayList;

/**
 * Created by 58 on 2016/8/12.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<ListBean> mData;
    private LayoutInflater mInflater;
    public ListAdapter(Context context,ArrayList<ListBean> data){
        mInflater= LayoutInflater.from(context);
       mData=data;
    }

    @Override
    public int getCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.title = (TextView)convertView.findViewById(R.id.tv_title);
            holder.info = (TextView)convertView.findViewById(R.id.tv_content);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.img.setBackgroundResource((Integer)mData.get(position).getResid());
        holder.title.setText((String)mData.get(position).getTitle());
        holder.info.setText((String)mData.get(position).getContent());

        return convertView;
    }

    static class ViewHolder{
        ImageView img;
        TextView title;
        TextView info;
    }
}
