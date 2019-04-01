package com.spring.chanba.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.FragmentAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.InformationEntity;
import com.spring.chanba.contract.InformationContract;
import com.spring.chanba.presenter.InformationPresenter;
import com.spring.chanba.ui.info.BriefedListFragment;
import com.spring.chanba.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 资讯
 */
public class InformationFragment extends Fragment implements InformationContract.View {
    private InformationContract.Presenter presenter;
    private TextView tvTitleHead;
    private TabLayout tabLayout;
    private List<Fragment> fragments = null;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infomation, container, false);
        initView(view);
        loadData();
        return view;
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {
        tvTitleHead = (TextView) view.findViewById(R.id.tv_title_head);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_information);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tvTitleHead.setText("资讯");
    }

    /**
     * 请求数据
     */
    private void loadData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("code", "CompanyNews");
        presenter = new InformationPresenter(this);
        presenter.getData(map);
    }

    /**
     * 返回服务器失败信息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        ((BaseActivity) getContext()).displayToast(message);
    }

    /**
     * 请求服务器数据
     *
     * @param entity
     */
    @Override
    public void initData(InformationEntity entity) {
        if (entity != null) {
            List<InformationEntity.SubDataBean> tabList = entity.getSubData();
            if (!Utils.isStringEmpty(tabList)) {
                List<String> strList = new ArrayList<>();
                fragments = new ArrayList<>();
                for (int i = 0; i < tabList.size(); i++) {
                    if (!Utils.isStringEmpty(tabList.get(i).getCode())) {
                        strList.add(tabList.get(i).getName());
                        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i).getName()));
                        fragments.add(BriefedListFragment.newInstance(tabList.get(i).getCode()));
                    }
                }
                FragmentAdapter adapter = new FragmentAdapter(getFragmentManager(), fragments, strList);
                //给ViewPager设置适配器
                viewPager.setAdapter(adapter);
                //将TabLayout和ViewPager关联起来。
                tabLayout.setupWithViewPager(viewPager);
                //给TabLayout设置适配器
                tabLayout.setTabsFromPagerAdapter(adapter);
            }
        }
    }

    @Override
    public void setPresenter(InformationContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
