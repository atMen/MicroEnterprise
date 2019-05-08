package com.tcrj.micro.activity.jzfp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.until.ShowImageUtils;
import com.bm.library.PhotoView;
import java.util.List;


public class PhototViewActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    TextView photo_num;
    ImageView btnback;
    List<String> result;
    SamplePagerAdapter samplePagerAdapter;
    private fpjlListInfo.ContentBean fpdtinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photot_view);

        Bundle extras = getIntent().getExtras();
        fpdtinfo = (fpjlListInfo.ContentBean) extras.getSerializable("fpdtinfo");
        int position = extras.getInt("position", -1);

        result = fpdtinfo.getPicPath();


        btnback = findViewById(R.id.btnback);
        viewPager = findViewById(R.id.view_pager);

         photo_num = findViewById(R.id.photo_num);

        btnback.setOnClickListener(this);


         samplePagerAdapter = new SamplePagerAdapter();
        viewPager.setAdapter(samplePagerAdapter);
        if(viewPager != null){
            viewPager.setCurrentItem(position);
        }
        photo_num.setText((position+1)+"/"+result.size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                photo_num.setText((position+1)+"/"+result.size());
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public void onClick(View v) {

        finish();

    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(PhototViewActivity.this, R.layout.item_base,null);

            PhotoView photoView = view.findViewById(R.id.photo_view);
            photoView.enable();
            photoView.setVisibility(View.VISIBLE);

            ShowImageUtils.showImageView(PhototViewActivity.this, ApiConstants.BASEIMAGE+result.get(position),photoView,R.drawable.ic_placeholder);
            container.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish(); //结束当前的activity的生命周期

            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class clazz, Bundle bundle){

        Intent intent = new Intent(context,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
