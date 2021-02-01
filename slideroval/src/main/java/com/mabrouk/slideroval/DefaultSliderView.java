package com.mabrouk.slideroval;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DefaultSliderView extends SliderView {

    private String description;
    private String descriptionTextColor ="#ffffff";
    private float descriptionTextSize = 1;
    private Typeface descriptionTextFont;

    @Override
    public View getView() {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(context).inflate(R.layout.image_slider_layout_item, null, true);
        ImageView autoSliderImage = (ImageView) v.findViewById(R.id.iv_auto_image_slider);
        try {
            TextView tv_description = v.findViewById(R.id.tv_auto_image_slider);
            tv_description.getBackground();
            if (descriptionTextSize != 1) {
                tv_description.setTextSize(descriptionTextSize);
            }
            tv_description.setTextColor(Color.parseColor(descriptionTextColor));
            tv_description.setText(getDescription());
            if (descriptionTextFont!=null)
                tv_description.setTypeface(descriptionTextFont);
        }catch (Exception e){}
        bindViewData(v, autoSliderImage);
        return v;
    }


    @Override
    protected void bindViewData(View v, View autoSliderImage) {
        final DefaultSliderView con = this;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSliderClickListener != null) {
                    onSliderClickListener.onSliderClick(con);
                }
            }
        });
        setImage((ImageView)autoSliderImage);
    }

    public DefaultSliderView(Context context) {
        super(context);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescriptionTextColor(String descriptionTextColor) {
        this.descriptionTextColor = descriptionTextColor;
    }

    public void setDescriptionTextFont(Typeface textFont){
        this.descriptionTextFont=textFont;
    }

    public void setDescriptionTextSize(float descriptionTextSize) {
        this.descriptionTextSize = descriptionTextSize;
    }

}
