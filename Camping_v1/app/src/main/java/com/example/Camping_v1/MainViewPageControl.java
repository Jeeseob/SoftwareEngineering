package com.example.Camping_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.Camping_v1.R;

import java.util.ArrayList;

public class MainViewPageControl extends PagerAdapter {

    private Context Context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {

            R.drawable.camping1,
            R.drawable.camping2,
            R.drawable.camping3,
            R.drawable.camping4 };


    public MainViewPageControl(Context context){
        this.Context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager, null);

        ImageView imageView = view.findViewById(R.id.imageView_pager);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Toast.makeText(Context, "slide 1 clicked", Toast.LENGTH_SHORT).show();
                }else if(position == 1){
                    Toast.makeText(Context, "Slide 2 clicked", Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(Context, "Slide 3 clicked", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Context, "Slide 4 clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewPager vp = (ViewPager)container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}
