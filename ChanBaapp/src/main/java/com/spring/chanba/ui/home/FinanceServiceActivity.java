package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.BusinessListAdapter;
import com.spring.chanba.adapter.BusinessServiceAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.banner.XBanner;
import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.FinanceServiceContract;
import com.spring.chanba.loader.GlideRectangleLoader;
import com.spring.chanba.presenter.FinanceServicePresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.MyGridView;
import com.spring.chanba.views.NListView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 金融服务
 */
public class FinanceServiceActivity extends BaseActivity implements View.OnClickListener, FinanceServiceContract.View {
    private FinanceServiceContract.Presenter presenter;
    private BusinessServiceAdapter adapter;
    private MyGridView gridView;
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private XBanner banner;
    private BusinessListAdapter lAdapter;
    private BusinessListAdapter cAdapter;
    private LinearLayout layoutTailormade;
    private NListView loreListview;
    private NListView caseListview;
    private LinearLayout layoutMore;
    private TextView tvLoreMore;
    private TextView tvCaseMore;
    private TextView tvLoreName;
    private TextView tvCaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
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
        banner = (XBanner) findViewById(R.id.banner_service);
        gridView = (MyGridView) findViewById(R.id.buiness_gridview);
        layoutTailormade = (LinearLayout) findViewById(R.id.layout_tailormade);
        loreListview = (NListView) findViewById(R.id.lore_listview);
        caseListview = (NListView) findViewById(R.id.case_listview);
        layoutMore = (LinearLayout) findViewById(R.id.layout_tax_more);
        tvLoreMore = (TextView) findViewById(R.id.tv_lore_more);
        tvCaseMore = (TextView) findViewById(R.id.tv_case_more);
        tvCaseName = (TextView) findViewById(R.id.tv_case_name);
        tvLoreName = (TextView) findViewById(R.id.tv_lore_name);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        tvTitleHead.setText("金融服务");
        tvCaseName.setText("经典案例");
        tvLoreName.setText("金融知识");
        layoutTailormade.setOnClickListener(this);
        layoutMore.setOnClickListener(this);
        tvLoreMore.setOnClickListener(this);
        tvCaseMore.setOnClickListener(this);
    }

    /**
     * 请求数据
     */
    private void loadData() {
        showProgressDialog();
        presenter = new FinanceServicePresenter(this);
        HashMap<String, String> mBp = new HashMap<>();
        mBp.put("pageIndex", "1");
        mBp.put("pageSize", "3");
        mBp.put("code", "jrfwlb");
        presenter.getBanner(mBp);
        HashMap<String, String> mMp = new HashMap<>();
        mMp.put("pageIndex", "1");
        mMp.put("pageSize", "4");
        mMp.put("type", "1");
        presenter.getMenuList(mMp);
        HashMap<String, String> mLp = new HashMap<>();
        mLp.put("pageIndex", "1");
        mLp.put("pageSize", "2");
        mLp.put("type", "1");
        presenter.getLore(mLp);
        HashMap<String, String> mCp = new HashMap<>();
        mCp.put("pageIndex", "1");
        mCp.put("pageSize", "2");
        mCp.put("type", "0");
        presenter.getCase(mCp);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.layout_tailormade) {
            Intent intent = new Intent(this, SKUTailorMadeActivity.class);
            startActivity(intent);

        } else if (i == R.id.layout_tax_more) {
            Intent tax = new Intent(this, ServiceMenuActivity.class);
            tax.putExtra("type", "1");
            startActivity(tax);

        } else if (i == R.id.tv_lore_more) {
            openNewActivity(FinanceLoreListActivity.class);

        } else if (i == R.id.tv_case_more) {
            openNewActivity(FinanceCaseListActivity.class);

        }
    }

    /**
     * 返回服务器失败消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 轮番图
     *
     * @param entity
     */
    @Override
    public void initBanner(BannerListEntity entity) {
        if (entity != null) {
            List<BannerListEntity.DataBean> itemList = entity.getData();
            if (!Utils.isStringEmpty(itemList)) {
                dismisProgressDialog();
                String[] strArray = new String[itemList.size()];
                for (int i = 0; i < itemList.size(); i++) {
                    if (!Utils.isStringEmpty(itemList.get(i).getFilePath())) {
                        strArray[i] = itemList.get(i).getFilePath();
                    }
                }
                List<?> list = Arrays.asList(strArray);
                banner.setImages(list).setImageLoader(new GlideRectangleLoader()).start();
            } else {
                dismisProgressDialog();
            }
        } else {
            dismisProgressDialog();
        }
    }

    /**
     * 菜单
     *
     * @param entity
     */
    @Override
    public void initMenuList(ServiceMenuEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                dismisProgressDialog();
                adapter = new BusinessServiceAdapter(this);
                adapter.setData(entity.getData());
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ServiceMenuEntity.DataBean bean = (ServiceMenuEntity.DataBean) adapter.getItem(position);
                        Log.i("FinanceServiceActivity", bean.getId());
                        Intent intent = new Intent(FinanceServiceActivity.this, AptitudeDetailActivity.class);
                        intent.putExtra("Flag", 1);
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

    /**
     * 工商知识
     *
     * @param entity
     */
    @Override
    public void initLore(LawServiceListEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                dismisProgressDialog();
                lAdapter = new BusinessListAdapter(this);
                lAdapter.setData(entity.getData());
                loreListview.setAdapter(lAdapter);
                loreListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LawServiceListEntity.DataBean bean = (LawServiceListEntity.DataBean) lAdapter.getItem(position);
                        Intent intent = new Intent(FinanceServiceActivity.this, FinanceLoreDetailActivity.class);
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

    /**
     * 经典案例
     *
     * @param entity
     */
    @Override
    public void initCase(LawServiceListEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                dismisProgressDialog();
                cAdapter = new BusinessListAdapter(this);
                cAdapter.setData(entity.getData());
                caseListview.setAdapter(cAdapter);
                caseListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LawServiceListEntity.DataBean bean = (LawServiceListEntity.DataBean) cAdapter.getItem(position);
                        Intent intent = new Intent(FinanceServiceActivity.this, FinanceCaseDetailActivity.class);
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
    public void setPresenter(FinanceServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }
}