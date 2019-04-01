package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.contract.SKUTailorMadeContract;
import com.spring.chanba.popup.MeasurePopupwindow;
import com.spring.chanba.presenter.SKUTailorMadePresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.LabelsView;

import java.util.HashMap;

/**
 * 量身定制SKU
 */
public class SKUTailorMadeActivity extends BaseActivity implements View.OnClickListener, SKUTailorMadeContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private LabelsView tradeListview;
    private LabelsView scaleListview;
    private LabelsView typeListview;
    private MeasurePopupwindow popup = null;
    private SKUTailorMadeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailormade);
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
        tradeListview = (LabelsView) findViewById(R.id.trade_listview);
        scaleListview = (LabelsView) findViewById(R.id.scale_listview);
        typeListview = (LabelsView) findViewById(R.id.type_listview);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        tvTitleHead.setText("量身定制");
    }

    /**
     * 加载数据
     */
    private void loadData() {
        showProgressDialog();
        presenter = new SKUTailorMadePresenter(this);
        HashMap<String, String> trade = new HashMap<>();
        trade.put("pageIndex", "1");
        trade.put("pageSize", "100");
        trade.put("parentId", "101");
        presenter.getTradeData(trade);
        HashMap<String, String> scale = new HashMap<>();
        scale.put("pageIndex", "1");
        scale.put("pageSize", "100");
        scale.put("parentId", "102");
        presenter.getScaleData(scale);
        HashMap<String, String> type = new HashMap<>();
        type.put("pageIndex", "1");
        type.put("pageSize", "100");
        type.put("parentId", "103");
        presenter.getTypeData(type);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        }
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 行业
     *
     * @param entity
     */
    @Override
    public void initTradeData(DictionaryTypeEntity entity) {
        if (entity != null) {
            dismisProgressDialog();
            if (!Utils.isStringEmpty(entity.getData())) {
                tradeListview.setLabels(entity.getData(), new LabelsView.LabelTextProvider<DictionaryTypeEntity.DataBean>() {
                    @Override
                    public CharSequence getLabelText(TextView label, int position, DictionaryTypeEntity.DataBean data) {
                        return data.getName();
                    }
                });
                tradeListview.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
                    @Override
                    public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                        popup = new MeasurePopupwindow(SKUTailorMadeActivity.this);
                        popup.showAsDropDown(tvTitleHead, Gravity.RIGHT | Gravity.RIGHT, 0, 0);
                    }
                });
            }
        } else {
            dismisProgressDialog();
        }
    }

    /**
     * 规模
     *
     * @param entity
     */
    @Override
    public void initScaleData(DictionaryTypeEntity entity) {
        if (entity != null) {
            dismisProgressDialog();
            if (!Utils.isStringEmpty(entity.getData())) {
                scaleListview.setLabels(entity.getData(), new LabelsView.LabelTextProvider<DictionaryTypeEntity.DataBean>() {
                    @Override
                    public CharSequence getLabelText(TextView label, int position, DictionaryTypeEntity.DataBean data) {
                        return data.getName();
                    }
                });
                scaleListview.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
                    @Override
                    public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                        popup = new MeasurePopupwindow(SKUTailorMadeActivity.this);
                        popup.showAsDropDown(tvTitleHead, Gravity.RIGHT | Gravity.RIGHT, 0, 0);
                    }
                });
            }
        } else {
            dismisProgressDialog();
        }
    }

    /**
     * 企业类型
     *
     * @param entity
     */
    @Override
    public void initTypeData(DictionaryTypeEntity entity) {
        if (entity != null) {
            dismisProgressDialog();
            if (!Utils.isStringEmpty(entity.getData())) {
                typeListview.setLabels(entity.getData(), new LabelsView.LabelTextProvider<DictionaryTypeEntity.DataBean>() {
                    @Override
                    public CharSequence getLabelText(TextView label, int position, DictionaryTypeEntity.DataBean data) {
                        return data.getName();
                    }
                });
                typeListview.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
                    @Override
                    public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                        popup = new MeasurePopupwindow(SKUTailorMadeActivity.this);
                        popup.showAsDropDown(tvTitleHead, Gravity.RIGHT | Gravity.RIGHT, 0, 0);
                    }
                });
            }
        } else {
            dismisProgressDialog();
        }
    }

    @Override
    public void setPresenter(SKUTailorMadeContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
