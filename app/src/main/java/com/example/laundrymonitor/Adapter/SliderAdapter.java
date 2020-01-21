package com.example.laundrymonitor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.laundrymonitor.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    public int[] slideImage = {

            R.drawable.step1 ,
            R.drawable.step2 ,
            R.drawable.step3 ,
            R.drawable.step5 ,
            R.drawable.step4

    };

    public String[] slideHeader = {

            "STEP 1" ,
            "STEP 2" ,
            "STEP 3",
            "STEP 4",
            "STEP 5"
    };

    public String[] slideDes = {

            "Choose your college residence" ,
            "Check availability" ,
            "Select your machine and press yes" ,
            "You will receive a notification within 30 minutes" ,
            "Press yes to stop after finished your laundry"


    };

    @Override
    public int getCount() {

        return slideHeader.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeaderView = (TextView) view.findViewById(R.id.slideHeader);
        TextView slideDescView = (TextView) view.findViewById(R.id.sliderDesc);

        slideImageView.setImageResource(slideImage[position]);
        slideHeaderView.setText(slideHeader[position]);
        slideDescView.setText(slideDes[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);

    }
}
