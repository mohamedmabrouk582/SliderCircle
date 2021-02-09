package com.mabrouk.slideroval;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.databinding.ObservableField;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mabrouk.slideroval.IndicatorView.PageIndicatorView;
import com.mabrouk.slideroval.IndicatorView.animation.type.AnimationType;
import com.mabrouk.slideroval.Transformations.AntiClockSpinTransformation;
import com.mabrouk.slideroval.Transformations.Clock_SpinTransformation;
import com.mabrouk.slideroval.Transformations.CubeInDepthTransformation;
import com.mabrouk.slideroval.Transformations.CubeInRotationTransformation;
import com.mabrouk.slideroval.Transformations.CubeInScalingTransformation;
import com.mabrouk.slideroval.Transformations.CubeOutDepthTransformation;
import com.mabrouk.slideroval.Transformations.CubeOutRotationTransformation;
import com.mabrouk.slideroval.Transformations.CubeOutScalingTransformation;
import com.mabrouk.slideroval.Transformations.DepthTransformation;
import com.mabrouk.slideroval.Transformations.FadeTransformation;
import com.mabrouk.slideroval.Transformations.FanTransformation;
import com.mabrouk.slideroval.Transformations.FidgetSpinTransformation;
import com.mabrouk.slideroval.Transformations.GateTransformation;
import com.mabrouk.slideroval.Transformations.HingeTransformation;
import com.mabrouk.slideroval.Transformations.HorizontalFlipTransformation;
import com.mabrouk.slideroval.Transformations.PopTransformation;
import com.mabrouk.slideroval.Transformations.SimpleTransformation;
import com.mabrouk.slideroval.Transformations.SpinnerTransformation;
import com.mabrouk.slideroval.Transformations.TossTransformation;
import com.mabrouk.slideroval.Transformations.VerticalFlipTransformation;
import com.mabrouk.slideroval.Transformations.VerticalShutTransformation;
import com.mabrouk.slideroval.Transformations.ZoomOutTransformation;

import java.util.Timer;
import java.util.TimerTask;

public class SliderLayout extends FrameLayout implements CircularSliderHandle.CurrentPageListener {
    private static final long DELAY_MS = 500;
    private static PagerAdapter mFlippingPagerAdapter;
    private int currentPage = 0;
    private CircularSliderHandle circularSliderHandle;
    private ViewPager mSliderPager;
    private PageIndicatorView pagerIndicator;
    private int scrollTimeInSec = 1;
    private ScrollTimeType timeType=ScrollTimeType.SECOND;
    private Handler handler = new Handler();
    private Timer flippingTimer;
    private boolean autoScrolling = true;
    private int indicatorRadius=5;
    private int indicator_margin;
    boolean indicatorVaisable;
    int loopCount,currentLoopCount;
    private SliderListener mListener;
    private boolean calledBefore;
    private boolean isInside;
    private int selectedColor;
    private int unSelectedColor;

    public SliderLayout(Context context) {
        super(context);
        setLayout(context,null);
    }

    public void setSliderListener(SliderListener mListener) {
        this.mListener = mListener;
    }

    public SliderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout(context,attrs);
    }

    public SliderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout(context,attrs);
    }

    public PageIndicatorView getPagerIndicator() {
        return pagerIndicator;
    }

    public boolean isAutoScrolling() {
        return autoScrolling;
    }

    public void setAutoScrolling(boolean autoScrolling) {
        this.autoScrolling = autoScrolling;
        startAutoCycle();
    }

    private static PagerAdapter getFlippingPagerAdapter() {
        return mFlippingPagerAdapter;
    }

    public int getScrollTimeInSec() {
        return scrollTimeInSec;
    }

    public void setScrollTimeInSec(int time,ScrollTimeType type) {
        scrollTimeInSec = time;
        this.timeType=type;
        startAutoCycle();
    }

    public void setSliderTransformAnimation(SliderAnimations animation) {

        switch (animation) {
            case ANTICLOCKSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new AntiClockSpinTransformation());
                break;
            case CLOCK_SPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new Clock_SpinTransformation());
                break;
            case CUBEINDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInDepthTransformation());
                break;
            case CUBEINROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInRotationTransformation());
                break;
            case CUBEINSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInScalingTransformation());
                break;
            case CUBEOUTDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutDepthTransformation());
                break;
            case CUBEOUTROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutRotationTransformation());
                break;
            case CUBEOUTSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutScalingTransformation());
                break;
            case DEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new DepthTransformation());
                break;
            case FADETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FadeTransformation());
                break;
            case FANTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FanTransformation());
                break;
            case FIDGETSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FidgetSpinTransformation());
                break;
            case GATETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new GateTransformation());
                break;
            case HINGETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HingeTransformation());
                break;
            case HORIZONTALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HorizontalFlipTransformation());
                break;
            case POPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new PopTransformation());
                break;
            case SIMPLETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());
                break;
            case SPINNERTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SpinnerTransformation());
                break;
            case TOSSTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new TossTransformation());
                break;
            case VERTICALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalFlipTransformation());
                break;
            case VERTICALSHUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalShutTransformation());
                break;
            case ZOOMOUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new ZoomOutTransformation());
                break;
            default:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());

        }

    }

    private void setCustomSliderTransformAnimation(ViewPager.PageTransformer animation) {
        mSliderPager.setPageTransformer(false, animation);
    }

    public int getCurrentPagePosition() {
        if (getFlippingPagerAdapter() != null) {
            return mSliderPager.getCurrentItem() % mFlippingPagerAdapter.getCount();
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    public void setIndicatorAnimation(IndicatorAnimations animations) {
        switch (animations) {
            case DROP:
                pagerIndicator.setAnimationType(AnimationType.DROP);
                break;
            case FILL:
                pagerIndicator.setAnimationType(AnimationType.FILL);
                break;
            case NONE:
                pagerIndicator.setAnimationType(AnimationType.NONE);
                break;
            case SWAP:
                pagerIndicator.setAnimationType(AnimationType.SWAP);
                break;
            case WORM:
                pagerIndicator.setAnimationType(AnimationType.WORM);
                break;
            case COLOR:
                pagerIndicator.setAnimationType(AnimationType.COLOR);
                break;
            case SCALE:
                pagerIndicator.setAnimationType(AnimationType.SCALE);
                break;
            case SLIDE:
                pagerIndicator.setAnimationType(AnimationType.SLIDE);
                break;
            case SCALE_DOWN:
                pagerIndicator.setAnimationType(AnimationType.SCALE_DOWN);
                break;
            case THIN_WORM:
                pagerIndicator.setAnimationType(AnimationType.THIN_WORM);
                break;
        }
    }

    public void setPagerIndicatorVisibility(boolean visibility) {
//        if (visibility) {
//            pagerIndicator.setVisibility(VISIBLE);
//        } else {
//            pagerIndicator.setVisibility(GONE);
//        }
        if (visibility){
            pagerIndicator.setIndectorReduis(5);
        }else {
            pagerIndicator.setIndectorReduis(0);
        }

    }

    private void setLayout(Context context,AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SliderLayout, 0, 0);

        View view = LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true);
        mSliderPager = view.findViewById(R.id.vp_slider_layout);
        pagerIndicator = view.findViewById(R.id.pager_indicator);
        pagerIndicator.setDynamicCount(true);

        selectedColor=ta.getColor(R.styleable.SliderLayout_indector_selectedColor,0);
        unSelectedColor=ta.getColor(R.styleable.SliderLayout_indector_unselectedColor,0);
        indicatorRadius=(int)ta.getDimension(R.styleable.SliderLayout_indicator_radius,5);
        autoScrolling=ta.getBoolean(R.styleable.SliderLayout_auto_cycle,true);
        indicator_margin=(int)ta.getDimension(R.styleable.SliderLayout_indicator_margin_bottom,0);
         indicatorVaisable=ta.getBoolean(R.styleable.SliderLayout_indicator_visiabilty,true);
         loopCount=ta.getInt(R.styleable.SliderLayout_loop_count,-1);
         isInside=ta.getBoolean(R.styleable.SliderLayout_piv_inside,false);

         if (indicator_margin>0){
             LayoutParams params = new LayoutParams(
                     LayoutParams.WRAP_CONTENT,
                     LayoutParams.WRAP_CONTENT
             );

             params.gravity = Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
             params.setMargins(0, 0, 0, indicator_margin);
             pagerIndicator.setLayoutParams(params);
         }

         if (isInside){
             LayoutParams params = new LayoutParams(
                     LayoutParams.MATCH_PARENT,
                     LayoutParams.MATCH_PARENT
             );
             params.setMargins(0, 0, 0, 100);
             mSliderPager.setLayoutParams(params);
         }
         if (selectedColor!=0){
             pagerIndicator.setSelectedColor(selectedColor);
         }
         if (unSelectedColor!=0){
             pagerIndicator.setUnselectedColor(unSelectedColor);
         }
        setPagerIndicatorVisibility(indicatorVaisable);
        mFlippingPagerAdapter = new SliderAdapter(context);

        mSliderPager.setAdapter(mFlippingPagerAdapter);

        // Handler for onPageChangeListener
        circularSliderHandle = new CircularSliderHandle(mSliderPager);
        circularSliderHandle.setCurrentPageListener(this);
        mSliderPager.addOnPageChangeListener(circularSliderHandle);
        if (indicatorVaisable)
           pagerIndicator.setIndectorReduis(indicatorRadius);
        //Starting auto cycle at the time of setting up of layout
        startAutoCycle();
    }

    public void setPagingListener(ViewPager.OnPageChangeListener listener){
        mSliderPager.addOnPageChangeListener(listener);
    }

    public void setIndicatorRadius(int indicatorRadius) {
        this.indicatorRadius = indicatorRadius;
        if (indicatorVaisable)
        pagerIndicator.setIndectorReduis(indicatorRadius);
    }

    public void clearSliderViews() {
        ((SliderAdapter) mFlippingPagerAdapter).removeAllSliderViews();
    }

    public void addSliderView(MainSliderView sliderView) {
        ((SliderAdapter) mFlippingPagerAdapter).addSliderView(sliderView);
        if (pagerIndicator != null && mSliderPager != null) {
            pagerIndicator.setViewPager(mSliderPager);
        }
    }

    private void startAutoCycle() {
        if (flippingTimer != null) {
            flippingTimer.cancel();
            flippingTimer.purge();
        }

        if (!autoScrolling) return;

        //Cancel If Thread is Running
        final Runnable scrollingThread = new Runnable() {
            public void run() {
                if (currentPage == getFlippingPagerAdapter().getCount()) {
                    ++currentLoopCount;
                    if (mListener != null && !calledBefore){
                        mListener.finishOneRound(currentLoopCount);
                        calledBefore=true;
                    }
                        if (loopCount == currentLoopCount) {
                            if (mListener != null) mListener.finishAllRounds(currentLoopCount);
                            flippingTimer.cancel();
                            return;
                        } else currentPage = 0;

                }
                // true set for smooth transition between pager

                mSliderPager.setCurrentItem(currentPage++, true);
            }

        };

        flippingTimer = new Timer();
        flippingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(scrollingThread);
            }
        }, DELAY_MS, scrollTimeInSec * (timeType==ScrollTimeType.SECOND?1000:500));
    }

    @Override
    public void onCurrentPageChanged(int currentPosition) {
        this.currentPage = currentPosition;
    }
}
