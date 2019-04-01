package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.FragmentTypeAdapter;
import com.spring.chanba.adapter.GalleryPagerAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.bean.TablayoutTitleEntity;
import com.spring.chanba.contract.LawCaseContract;
import com.spring.chanba.presenter.LawCasePresenter;
import com.spring.chanba.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 培训服务
 */
public class TrainServiceActivity extends BaseActivity implements LawCaseContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private ViewPager viewPager;
    private GalleryPagerAdapter gAdapter;
    private TabLayout tabLayout;
    private List<Fragment> fragments = null;
    private ViewPager pagerHatch;
    private LawCaseContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesshatch);
        initView();
        tabHoast();
        loadData();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        viewPager = (ViewPager) findViewById(R.id.hatch_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs_hatch);
        pagerHatch = (ViewPager) findViewById(R.id.pager_hatch);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleHead.setText("培训服务");
    }

    private void loadData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        presenter = new LawCasePresenter(this);
        presenter.getLawCase(map);
    }

    /**
     * 创业培训,2:财务培训,3: 法律培训
     */
    private void tabHoast() {
        List<TablayoutTitleEntity> dataList = new ArrayList<>();
        String[] strArray = {"创业培训", "财务培训", "法律培训"};
        for (int i = 0; i < strArray.length; i++) {
            TablayoutTitleEntity entity = new TablayoutTitleEntity();
            entity.setCategoryId(String.valueOf(i + 1));
            entity.setCategoryName(strArray[i].toString());
            dataList.add(entity);
        }
        fragments = new ArrayList<>();
        for (int j = 0; j < dataList.size(); j++) {
            tabLayout.addTab(tabLayout.newTab().setText(dataList.get(j).getCategoryName()));
            fragments.add(BusinessHatchFragment.newInstance(dataList.get(j).getCategoryId()));
        }
        FragmentTypeAdapter adapter = new FragmentTypeAdapter(getSupportFragmentManager(), fragments, dataList);
        //给ViewPager设置适配器
        pagerHatch.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabLayout.setupWithViewPager(pagerHatch);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initLawCase(LawServiceGridEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                gAdapter = new GalleryPagerAdapter(this);
                gAdapter.setData(entity.getData());
                viewPager.setOffscreenPageLimit(entity.getData().size());
                viewPager.setAdapter(gAdapter);
            }
        }
    }

    @Override
    public void setPresenter(LawCaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
