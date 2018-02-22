package com.example.android.shirysartstudio;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ceciliaHumlelu on 2018-02-21.
 */

public class PhotoPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflator;
    int[] photoResources = {R.drawable.shirys_art_studio
            ,R.drawable.shiry3,R.drawable.shiry4
            ,R.drawable.shiry5,R.drawable.shiry6};

    public PhotoPagerAdapter(Context cxt){
        mContext = cxt;
        mLayoutInflator = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return photoResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflator.inflate(R.layout.photo,container,false);
        ImageView photo = itemView.findViewById(R.id.photo);
        photo.setImageResource(photoResources[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
