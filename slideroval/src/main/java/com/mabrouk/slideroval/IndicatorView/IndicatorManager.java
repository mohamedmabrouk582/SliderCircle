package com.mabrouk.slideroval.IndicatorView;



import androidx.annotation.Nullable;

import com.mabrouk.slideroval.IndicatorView.animation.AnimationManager;
import com.mabrouk.slideroval.IndicatorView.animation.controller.ValueController;
import com.mabrouk.slideroval.IndicatorView.animation.data.Value;
import com.mabrouk.slideroval.IndicatorView.draw.DrawManager;
import com.mabrouk.slideroval.IndicatorView.draw.data.Indicator;

public class IndicatorManager implements ValueController.UpdateListener {

    private DrawManager drawManager;
    private AnimationManager animationManager;
    private Listener listener;

    interface Listener {
        void onIndicatorUpdated();
    }

    IndicatorManager(@Nullable Listener listener) {
        this.listener = listener;
        this.drawManager = new DrawManager();
        this.animationManager = new AnimationManager(drawManager.indicator(), this);
    }

    public AnimationManager animate() {
        return animationManager;
    }

    public Indicator indicator() {
        return drawManager.indicator();
    }

    public DrawManager drawer() {
        return drawManager;
    }

    @Override
    public void onValueUpdated(@Nullable Value value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onIndicatorUpdated();
        }
    }
}
