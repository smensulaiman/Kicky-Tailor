package com.ekattorit.kickytailor.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.ekattorit.kickytailor.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private Context context;
    private ImageSliderAdapterListener imageSliderAdapterListener;

    public ImageSliderAdapter(Context context,ArrayList<Integer> IMAGES) {
        this.context = context;
        this.images=IMAGES;
    }

    public void setImageSliderAdapterListener(ImageSliderAdapterListener imageSliderAdapterListener) {
        this.imageSliderAdapterListener = imageSliderAdapterListener;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = LayoutInflater.from(context).inflate(R.layout.layout_sliding_image, view, false);

        assert imageLayout != null;
        final KenBurnsView imageView = imageLayout.findViewById(R.id.imgSlider);
        imageView.setImageResource(images.get(position));

        imageView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                      if(imageSliderAdapterListener != null){
                          imageSliderAdapterListener.changeImage();
                      }
            }
        });

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
