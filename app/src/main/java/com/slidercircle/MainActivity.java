package com.slidercircle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.mabrouk.slideroval.DefaultSliderView;
import com.mabrouk.slideroval.IndicatorAnimations;
import com.mabrouk.slideroval.IndicatorView.animation.type.SlideAnimation;
import com.mabrouk.slideroval.ScrollTimeType;
import com.mabrouk.slideroval.SliderAnimations;
import com.slidercircle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding layoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        layoutBinding.sliderLayout.setScrollTimeInSec(1, ScrollTimeType.SECOND);
        layoutBinding.sliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP);
        layoutBinding.sliderLayout.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);

        for (int i = 0; i <10 ; i++) {
            DefaultSliderView sliderView=new DefaultSliderView(this);
            sliderView.setImageUrl("https://shamelq8.com/uploads/advertising/uprCocfdqVljWrnLIRCUD2cRu.jpg");
            sliderView.setDescription("huhuhhhhhhhhhhhhhhhhygygygy");
            layoutBinding.sliderLayout.addSliderView(sliderView);
        }

    }
}
