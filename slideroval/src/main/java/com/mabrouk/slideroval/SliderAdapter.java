package com.mabrouk.slideroval;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;


class SliderAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<MainSliderView> sliderViews = new ArrayList<>();

    SliderAdapter(Context context) {
        this.context = context;
    }

    public void setSliderViews(ArrayList<MainSliderView> sliderViews) {
        this.sliderViews = sliderViews;
    }

    void addSliderView(MainSliderView view) {
        sliderViews.add(view);
        notifyDataSetChanged();
    }

    public void removeAllSliderViews() {
        sliderViews.clear();
        notifyDataSetChanged();
    }

    public MainSliderView getSliderView(int position) {
        if (sliderViews.isEmpty() || position >= sliderViews.size()) {
            return null;
        }
        return sliderViews.get(position);
    }

    @Override
    public int getCount() {
        return sliderViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MainSliderView imageSliderView = sliderViews.get(position);
        View v = imageSliderView.getView(container);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
