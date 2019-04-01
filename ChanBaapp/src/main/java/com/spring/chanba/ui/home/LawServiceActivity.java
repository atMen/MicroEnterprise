package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.LawServiceGridAdapter;
import com.spring.chanba.adapter.LawServiceListAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.banner.XBanner;
import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.LawLoreCaseContract;
import com.spring.chanba.loader.GlideRectangleLoader;
import com.spring.chanba.presenter.LawLoreCasePresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.MyGridView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 法律服务
 */
public class LawServiceActivity extends BaseActivity implements View.OnClickListener, LawLoreCaseContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private ListView listView;
    private LawServiceListAdapter adapter;
    private MyGridView gridView;
    private LinearLayout layoutMore;
    private LawServiceGridAdapter gAdapter;
    private LawLoreCaseContract.Presenter presenter;
    private LinearLayout layoutService;
    private LinearLayout layoutSimple;
    private TextView tvServiceCount;
    private TextView tvServiceLook;
    private XBanner banner;
    private LinearLayout layoutConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawservice);
        initView();
        loadData();
    }

    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        listView = (ListView) findViewById(R.id.listview_lawservice);
        gridView = (MyGridView) findViewById(R.id.lawservice_gridview);
        layoutMore = (LinearLayout) findViewById(R.id.layout_lawservice_more);
        layoutService = (LinearLayout) findViewById(R.id.layout_law_service);
        layoutSimple = (LinearLayout) findViewById(R.id.layout_law_simple);
        tvServiceCount = (TextView) findViewById(R.id.tv_lawservice_count);
        tvServiceLook = (TextView) findViewById(R.id.tv_lawservice_look);
        banner = (XBanner) findViewById(R.id.banner_lawservice);
        layoutConsult = (LinearLayout) findViewById(R.id.layout_consult);
        layoutService.setOnClickListener(this);
        layoutSimple.setOnClickListener(this);
        imgTitleBack.setOnClickListener(this);
        imgTitleBack.setVisibility(View.VISIBLE);
        layoutMore.setOnClickListener(this);
        tvServiceLook.setOnClickListener(this);
        layoutConsult.setOnClickListener(this);
        tvTitleHead.setText("法律服务");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.layout_lawservice_more) {
            Intent lore = new Intent(this, LawLoreListActivity.class);
            startActivity(lore);

        } else if (i == R.id.layout_law_service) {
            Intent service = new Intent(this, LawLoreListActivity.class);
            startActivity(service);

        } else if (i == R.id.layout_law_simple) {
            Intent simple = new Intent(this, LawCaseListActivity.class);
            startActivity(simple);

        } else if (i == R.id.tv_lawservice_look) {
            Intent look = new Intent(this, LawCaseListActivity.class);
            startActivity(look);

        } else if (i == R.id.layout_consult) {
            Intent consult = new Intent(this, PromptlyConsultActivity.class);
            startActivity(consult);

        }
    }

    /**
     * 请求数据
     */
    private void loadData() {
        presenter = new LawLoreCasePresenter(this);
        HashMap<String, String> lore = new HashMap<>();
        lore.put("pageIndex", "1");
        lore.put("pageSize", "2");
        presenter.getLawLore(lore);
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "4");
        presenter.getLawCase(map);
        HashMap<String, String> banMap = new HashMap<>();
        banMap.put("pageIndex", "1");
        banMap.put("pageSize", "5");
        banMap.put("code", "flfwlb");
        presenter.getLBanner(banMap);
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 法律知识
     *
     * @param entity
     */
    @Override
    public void initLawLore(LawServiceListEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                adapter = new LawServiceListAdapter(this);
                adapter.setData(entity.getData());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LawServiceListEntity.DataBean bean = (LawServiceListEntity.DataBean) adapter.getItem(position);
                        Log.i("法律知识：", bean.getId());
                        Intent intent = new Intent(LawServiceActivity.this, WebViewLoreActivity.class);
                        intent.putExtra("id", bean.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    /**
     * 法律案例
     *
     * @param entity
     */
    @Override
    public void initLawCase(LawServiceGridEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                gAdapter = new LawServiceGridAdapter(this);
                gAdapter.setData(entity.getData());
                gridView.setAdapter(gAdapter);
                tvServiceCount.setText("新增经典案例" + entity.getSubData() + "条");
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LawServiceGridEntity.DataBean bean = (LawServiceGridEntity.DataBean) gAdapter.getItem(position);
                        Log.i("法律案例", bean.getId());
                        Intent intent = new Intent(LawServiceActivity.this, WebViewCaseActivity.class);
                        intent.putExtra("id", bean.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    /**
     * 轮播图
     *
     * @param entity
     */
    @Override
    public void initLBanner(BannerListEntity entity) {
        if (entity != null) {
            final List<BannerListEntity.DataBean> listInfo = entity.getData();
            if (!Utils.isStringEmpty(listInfo)) {
                String[] strArray = new String[entity.getData().size()];
                for (int i = 0; i < entity.getData().size(); i++) {
                    if (!Utils.isStringEmpty(entity.getData().get(i).getFilePath())) {
                        strArray[i] = entity.getData().get(i).getFilePath();
                    }
                }
                List<?> arrayList = Arrays.asList(strArray);
                banner.setImages(arrayList).setImageLoader(new GlideRectangleLoader()).start();
            }
        }
    }

    @Override
    public void setPresenter(LawLoreCaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
