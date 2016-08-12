package com.seventeam.wubahuichang.Adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Author: gengjiarong
 * Date: 2016/8/12
 */
public class LookplacePagerAdapter extends PagerAdapter {

    private List<ImageView> mlist;

    public LookplacePagerAdapter(List<ImageView> list) {
        mlist = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mlist.get(position));
        return mlist.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mlist.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        System.out.println(view == object);
        return view == object;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
