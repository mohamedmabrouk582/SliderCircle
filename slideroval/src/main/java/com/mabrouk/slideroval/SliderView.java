package com.mabrouk.slideroval;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class SliderView {

    @DrawableRes
    protected int imageRes = 0;
    protected OnSliderClickListener onSliderClickListener;
    protected String description;
    protected String imageUrl;
    protected ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    protected Context context;
    protected Bitmap imageBitmap;


    abstract public View getView();

    protected abstract void bindViewData(View v, View autoSliderImage);

    protected   SliderView(Context context) {
        this.context = context;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageRes != 0) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageUrl = imageUrl;
    }

    public void setImageBitmap(Bitmap imageBitmap){
        this.imageBitmap=imageBitmap;
    }

    public void setImageByte(byte[] imageByte) {
        ContextWrapper wrapper = new ContextWrapper(context);
        File file = new File(wrapper.getCacheDir().getAbsolutePath(),"Cached"+System.currentTimeMillis()+".jpeg");
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0,imageByte.length);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 95, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.imageUrl = String.valueOf(Uri.fromFile(file));
    }

    public void setImageDrawable(int imageDrawable) {
        if (!TextUtils.isEmpty(imageUrl)) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageRes = imageDrawable;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public void setImage(ImageView imageView){
        try {
           // imageView.setScaleType(getScaleType());
            if (imageUrl != null) {
                Glide.with(context).load(imageUrl).into(imageView);
            }
            if (imageRes != 0) {
                Glide.with(context).asDrawable().load(imageRes).into(imageView);
            }

            if (imageBitmap!=null){
                imageView.setImageBitmap(imageBitmap);
            }
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }

    }

    public void setImageScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public void setOnSliderClickListener(OnSliderClickListener l) {
        onSliderClickListener = l;
    }

    public interface OnSliderClickListener {
        void onSliderClick(SliderView sliderView);
    }

}
