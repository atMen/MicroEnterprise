package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.FragmentTypeAdapter;
import com.spring.chanba.adapter.SuccessCaseAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.TablayoutTitleEntity;
import com.spring.chanba.contract.BusinessHatchContract;
import com.spring.chanba.presenter.BusinessHatchPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.NListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 创业孵化
 */
public class BusinessHatchActivity extends BaseActivity implements View.OnClickListener, BusinessHatchContract.View {
    private SuccessCaseAdapter adapter;
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private NListView listView;
    private TabLayout tabList;
    private ViewPager viewPager;
    private List<Fragment> fragments = null;
    private LinearLayout layoutMore;
    private BusinessHatchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainservice);
        initView();
        loadData();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        listView = (NListView) findViewById(R.id.listview_trainservice);
        tabList = (TabLayout) findViewById(R.id.tabs_project);
        viewPager = (ViewPager) findViewById(R.id.pager_project);
        layoutMore = (LinearLayout) findViewById(R.id.layout_case_more);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        layoutMore.setOnClickListener(this);
        tvTitleHead.setText("创业孵化");
    }

    /**
     * 获取数据
     */
    private void loadData() {
        showProgressDialog();
        presenter = new BusinessHatchPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "2");
        map.put("type", "0");
        presenter.getData(map);
        HashMap<String, String> bMap = new HashMap<>();
        bMap.put("pageIndex", "1");
        bMap.put("pageSize", "20");
        bMap.put("parentId", "106");
        presenter.getType(bMap);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.layout_case_more) {
            openNewActivity(BusinessHatchListActivity.class);

        }
    }

    /**
     * 获取服务器失败消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 请求数据
     *
     * @param entity
     */
    @Override
    public void initData(LawServiceListEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                dismisProgressDialog();
                adapter = new SuccessCaseAdapter(this);
                adapter.setData(entity.getData());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LawServiceListEntity.DataBean bean = (LawServiceListEntity.DataBean) adapter.getItem(position);
                        Log.i("BusinessHatchActivity", bean.getId());
                        Intent intent = new Intent(BusinessHatchActivity.this, BusinessHatchDetailActivity.class);
                        intent.putExtra("id", bean.getId());
                        startActivity(intent);
                    }
                });
            } else {
                dismisProgressDialog();
            }
        } else {
            dismisProgressDialog();
        }
    }

    @Override
    public void initType(DictionaryTypeEntity entity) {
        List<TablayoutTitleEntity> titles = new ArrayList<TablayoutTitleEntity>();
        if (entity != null) {
            if (entity.getState() == 1) {
                dismisProgressDialog();
                if (!Utils.isStringEmpty(entity.getData())) {
                    fragments = new ArrayList<>();
                    for (int i = 0; i < entity.getData().size(); i++) {
                        DictionaryTypeEntity.DataBean bean = entity.getData().get(i);
                        TablayoutTitleEntity item = new TablayoutTitleEntity();
                        item.setCategoryId(bean.getId());
                        item.setCategoryName(bean.getName());
                        titles.add(item);
                    }
                    for (int j = 0; j < titles.size(); j++) {
                        tabList.addTab(tabList.newTab().setText(titles.get(j).getCategoryName()));
                        fragments.add(ProjectDisplayFragment.newInstance(titles.get(j).getCategoryId()));
                    }
                    FragmentTypeAdapter adapter = new FragmentTypeAdapter(getSupportFragmentManager(), fragments, titles);
                    //给ViewPager设置适配器
                    viewPager.setAdapter(adapter);
                    //将TabLayout和ViewPager关联起来。
                    tabList.setupWithViewPager(viewPager);
                    //给TabLayout设置适配器
                    tabList.setTabsFromPagerAdapter(adapter);
                }
            } else {
                dismisProgressDialog();
            }
        } else {
            dismisProgressDialog();
        }
    }

    @Override
    public void setPresenter(BusinessHatchContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
