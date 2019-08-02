package com.example.xiaojin20135.imagebrowsepro.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiaojin20135.imagebrowsepro.R;
import com.example.xiaojin20135.imagebrowsepro.inter.ImageLongClick;

import java.util.ArrayList;

/*
* @author lixiaojin
* create on 2019/8/2 09:55
* description:
*/
public class ImageBrowseAdapter extends PagerAdapter {
    private static final String TAG = "ImageBrowseAdapter";

    private Activity activity;
    private ArrayList<String> imageList;
    //图片长按事件
    private ImageLongClick imageLongClick;

    public ImageBrowseAdapter(Activity activity,ArrayList<String> imageList){
        this.activity = activity;
        this.imageList = imageList;
    }

    public ImageBrowseAdapter(Activity activity,ArrayList<String> imageList,ImageLongClick imageLongClick){
        this(activity,imageList);
        this.imageLongClick = imageLongClick;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final PhotoView photoView = new PhotoView(activity);
        //开启图片缩放功能
        photoView.enable();
        //设置缩放级别
        photoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //设置最大缩放倍数
        photoView.setMaxScale(2.5f);
        //参数设置
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.image_error);
        //加载图片
        Glide.with(activity)
                .load(imageList.get(position))
                .apply(requestOptions)
                .into(photoView);
        //点击事件，返回
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoView.disenable();
                activity.finish();
            }
        });
        //双击事件
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(imageLongClick != null){
                    imageLongClick.longClickImage(position);
                }
                return true;
            }
        });
        container.addView(photoView);
        return photoView;
    }


    @Override
    public void destroyItem (@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ((View) object);
    }

}
