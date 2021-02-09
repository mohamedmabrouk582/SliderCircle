package com.slidercircle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mabrouk.slideroval.MainSliderView;

class SliderItem extends MainSliderView {
    private ImageView mImageView;
    private TextView mTextView;
    private String imageUrl,des;



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public View getView(ViewGroup container) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.test_item,container,false);
        mImageView = view.findViewById(R.id.mImage);
        mTextView = view.findViewById(R.id.mText);
        setData();
        return view;
    }

    private void setData(){
        if(imageUrl!=null){
            Glide.with(mImageView.getContext())
                    .load(imageUrl).into(mImageView);
        }

        if (des!=null){
            mTextView.setText(des);
        }
    }
}
