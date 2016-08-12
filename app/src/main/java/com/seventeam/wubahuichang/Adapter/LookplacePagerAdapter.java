package com.seventeam.wubahuichang.Adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: gengjiarong
 * Date: 2016/8/12
 */
public class LookplacePagerAdapter extends PagerAdapter {

    private SparseArray<View> viewArray;

    public LookplacePagerAdapter(SparseArray<View> array) {
        viewArray = array;
    }

    @Override
    public int getCount() {
        return viewArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewArray.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewArray.get(position));
        return viewArray.get(position);
    }
}
