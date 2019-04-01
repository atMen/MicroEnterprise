package com.spring.chanba.ui.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.spring.chanba.R;
import com.spring.chanba.adapter.BannerMenuAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.banner.XBanner;
import com.spring.chanba.bean.MenuListEntity;
import com.spring.chanba.bean.NoticeBannerEntity;
import com.spring.chanba.contract.BannerListContract;
import com.spring.chanba.loader.GlideShapImgLoader;
import com.spring.chanba.presenter.BannerListPresenter;
import com.spring.chanba.ui.home.BusinessHatchActivity;
import com.spring.chanba.ui.home.BusinessServiceActivity;
import com.spring.chanba.ui.home.FinanceServiceActivity;
import com.spring.chanba.ui.home.LawServiceActivity;
import com.spring.chanba.ui.home.PropertyTaxActivity;
import com.spring.chanba.ui.home.TalentLoanActivity;
import com.spring.chanba.ui.home.TrainServiceActivity;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends Fragment implements BannerListContract.View {
    private BannerMenuAdapter adapter;
    private XBanner banner;
    private MyGridView gridView;
    private BannerListContract.Presenter presenter;
    private TextView tvTitleHead;
    private ViewFlipper flipper;
    private ImageView imgTalentLoan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        loadGrid();
        loadData();
        return view;
    }

    /**
     * 获取页面控件
     *
     * @param v
     */
    private void initView(View v) {
        tvTitleHead = (TextView) v.findViewById(R.id.tv_title_head);
        banner = (XBanner) v.findViewById(R.id.banner_advert);
        gridView = (MyGridView) v.findViewById(R.id.banner_gridview);
        flipper = (ViewFlipper) v.findViewById(R.id.view_flipper);
        imgTalentLoan = (ImageView) v.findViewById(R.id.img_talent_loan);
        tvTitleHead.setText("首页");
    }

    /**
     * 请求数据
     */
    private void loadGrid() {
        CharSequence[] titles = getActivity().getResources().getStringArray(R.array.banner_arrays);
        TypedArray array = getActivity().getResources().obtainTypedArray(R.array.banner_mipmap);
        List<MenuListEntity> dataList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            MenuListEntity entity = new MenuListEntity();
            entity.setId(i + 1);
            entity.setName(titles[i].toString());
            entity.setIcon(array.getResourceId(i, 0));
            dataList.add(entity);
        }
        adapter = new BannerMenuAdapter(getActivity());
        adapter.setData(dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuListEntity entity = (MenuListEntity) adapter.getItem(position);
                if (entity.getName().equals("工商服务")) {
                    Intent intent = new Intent(getContext(), BusinessServiceActivity.class);
                    startActivity(intent);
                } else if (entity.getName().equals("法律服务")) {
                    Intent law = new Intent(getContext(), LawServiceActivity.class);
                    startActivity(law);
                } else if (entity.getName().equals("创业孵化")) {
                    Intent service = new Intent(getContext(), BusinessHatchActivity.class);
                    startActivity(service);
                } else if (entity.getName().equals("培训服务")) {
                    Intent hatch = new Intent(getContext(), TrainServiceActivity.class);
                    startActivity(hatch);
                } else if (entity.getName().equals("金融服务")) {
                    Intent finan = new Intent(getContext(), FinanceServiceActivity.class);
                    startActivity(finan);
                } else if (entity.getName().equals("财税服务")) {
                    Intent tax = new Intent(getContext(), PropertyTaxActivity.class);
                    startActivity(tax);
                }
            }
        });
    }

    /**
     * 请求数据
     */
    private void loadData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        presenter = new BannerListPresenter(this);
        presenter.getData(map);
    }

    /**
     * 返回服务器消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        ((BaseActivity) getContext()).displayToast(message);
    }

    /**
     * 请求数据
     *
     * @param entity
     */
    @Override
    public void initData(NoticeBannerEntity entity) {
        if (entity != null) {
            List<NoticeBannerEntity.DataBean.SliderBean> itemList = entity.getData().getSlider();
            List<NoticeBannerEntity.DataBean.PlatformNoticeBean> noticeList = entity.getData().getPlatformNotice();
            final List<NoticeBannerEntity.DataBean.IndexTjBean> indexTjBeans = entity.getData().getIndex_tj();
            if (!Utils.isStringEmpty(itemList)) {
                String[] strArray = new String[itemList.size()];
                for (int i = 0; i < itemList.size(); i++) {
                    if (!Utils.isStringEmpty(itemList.get(i).getFilePath())) {
                        strArray[i] = itemList.get(i).getFilePath();
                    }
                }
                List<?> list = Arrays.asList(strArray);
                banner.setImages(list).setImageLoader(new GlideShapImgLoader()).start();
            }
            if (!Utils.isStringEmpty(noticeList)) {
                for (int i = 0; i < noticeList.size(); i++) {
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_view_flipper, null);
                    TextView tvFlipperName = (TextView) view.findViewById(R.id.tv_flipper_name);
                    tvFlipperName.setText(noticeList.get(i).getTitle());
                    flipper.addView(view);
                }
            }
            if (!Utils.isStringEmpty(indexTjBeans)) {
                Glide.with(this).load(indexTjBeans.get(0).getPicPath()).into(imgTalentLoan);
                imgTalentLoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), TalentLoanActivity.class);
                        intent.putExtra("id", indexTjBeans.get(0).getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void setPresenter(BannerListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
