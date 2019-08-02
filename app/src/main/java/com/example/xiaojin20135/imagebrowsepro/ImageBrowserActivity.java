package com.example.xiaojin20135.imagebrowsepro;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xiaojin20135.imagebrowsepro.adapter.ImageBrowseAdapter;
import com.example.xiaojin20135.imagebrowsepro.inter.ImageLongClick;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageBrowserActivity extends AppCompatActivity implements ImageLongClick {

    private ProgressBar loading_progress;
    private TextView mNumberTextView;
    private ArrayList<String> imageList = new ArrayList<>();
    private ViewPager imageBrowserViewPager;
    private ImageBrowseAdapter imageBrowseAdapter;
    private int currentIndex = 0;
    //是否是网络图片
    private boolean fromNet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        //加载动画
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadData();
        initView();
        initEvents();
        //展示数据
        showData();
    }

    private void loadData(){
        Intent intent = getIntent ();
        if(intent != null){
            currentIndex = intent.getIntExtra("index", 0);
            fromNet = intent.getBooleanExtra("fromNet",false);
            imageList = (ArrayList<String>) intent.getStringArrayListExtra("imageList");
        }
    }

    private void initView(){
        loading_progress = (ProgressBar)findViewById(R.id.loading_progress);
        mNumberTextView = (TextView)findViewById(R.id.number_textview);
        imageBrowserViewPager = (ViewPager)findViewById (R.id.imageBrowseViewPager);
        imageBrowseAdapter = new ImageBrowseAdapter (this,imageList,this);
        imageBrowserViewPager.setAdapter (imageBrowseAdapter);
        imageBrowserViewPager.setCurrentItem (currentIndex);
    }

    private void initEvents(){
        imageBrowserViewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                currentIndex = position;
                updateBottomIndex(position + 1);
            }
            @Override
            public void onPageSelected (int position) {
                updateBottomIndex(position + 1);
            }
            @Override
            public void onPageScrollStateChanged (int state) {

            }
        });
    }

    /*
     * @author lixiaojin
     * create on 2019/8/1 16:17
     * description: 展示数据
     */
    private void showData(){
        if(imageList.size() > 0) {
            updateBottomIndex(currentIndex + 1);
        }
    }

    /*
     * @author lixiaojin
     * create on 2019/8/1 16:13
     * description: 底部数字索引
     */
    private void updateBottomIndex(int count){
        mNumberTextView.setText(count + "/" + imageList.size());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back(){
        Intent intent = new Intent ();
        intent.putStringArrayListExtra ("imageList",imageList);
        setResult (RESULT_OK,intent);
        this.finish ();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in_disappear,R.anim.fade_out_disappear);
    }

    @Override
    public void longClickImage(int position) {
        //如果是网络图片
        if(fromNet){

        }else{//如果是本地图片预览

        }
    }
}
