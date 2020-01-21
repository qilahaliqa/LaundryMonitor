package com.example.laundrymonitor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.laundrymonitor.Adapter.SliderAdapter;
import com.example.laundrymonitor.R;

public class SliderActivity extends AppCompatActivity {

    private ViewPager sviewPager;
    private LinearLayout slinearLayout;
    private TextView[] sLayout;
    private SliderAdapter sliderAdapter;
    private Button sNextBtn;
    private Button sBackBtn;
    private int sCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        sviewPager = (ViewPager) findViewById(R.id.viewPager);
        slinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        sNextBtn = (Button) findViewById(R.id.nextBtn);
        sBackBtn = (Button) findViewById(R.id.prevBtn);

        sliderAdapter = new SliderAdapter(this);

        sviewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        sviewPager.addOnPageChangeListener(viewListener);

        sNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sviewPager.setCurrentItem(sCurrentPage + 1);

                String btn = sNextBtn.getText().toString();
                if (btn.equals("Finish")){
                    sNextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });


                }

            }
        });

        sBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sviewPager.setCurrentItem(sCurrentPage - 1);

            }
        });

    }

    public void addDotsIndicator(int position){

        sLayout = new TextView[5];

        for (int i=0; i<sLayout.length; i++) {

            sLayout[i] = new TextView(this);
            sLayout[i].setText(Html.fromHtml("&#8226;"));
            sLayout[i].setTextSize(35);
            sLayout[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            slinearLayout.addView(sLayout[i]);

        }

        if (sLayout.length > 0){

            sLayout[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            sCurrentPage = position;

            if (position == 0){

                sNextBtn.setEnabled(true);
                sBackBtn.setEnabled(false);
                sBackBtn.setVisibility(View.INVISIBLE);

                sNextBtn.setText("Next");
                sBackBtn.setText("");

            } else if (position == sLayout.length - 1) {

                sNextBtn.setEnabled(true);
                sBackBtn.setEnabled(true);
                sBackBtn.setVisibility(View.VISIBLE);

                sNextBtn.setText("Finish");
                sBackBtn.setText("Back");



            }else {

                sNextBtn.setEnabled(true);
                sBackBtn.setEnabled(true);
                sBackBtn.setVisibility(View.VISIBLE);

                sNextBtn.setText("Next");
                sBackBtn.setText("Back");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
