package com.spring.chanba.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.spring.chanba.R;
import com.spring.chanba.adapter.MeasureListAdapter;
import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.contract.MeasureListContract;
import com.spring.chanba.presenter.MeasureListPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.NListView;

import java.util.HashMap;

public class MeasurePopupwindow extends PopupWindow implements MeasureListContract.View {
    private String TAG = MeasurePopupwindow.class.getSimpleName();
    private View view;
    private Activity mActivity;
    private NListView listView;
    private MeasureListAdapter adapter;
    private MeasureListContract.Presenter presenter;
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;

    public MeasurePopupwindow(final Activity activity) {
        this.mActivity = activity;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_talentloan, null);
        initView(view);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(false);
        loadData();
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {
        listView = (NListView) view.findViewById(R.id.popu_listview);
        imgTitleBack = (ImageButton) view.findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) view.findViewById(R.id.tv_title_head);
        tvTitleHead.setText("更多分类");
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "100");
        map.put("parentId", "101");
        presenter = new MeasureListPresenter(this);
        presenter.getData(map);
    }

    @Override
    public void failedMessage(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData(DictionaryTypeEntity entity) {
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getData())) {
                adapter = new MeasureListAdapter(mActivity);
                adapter.setData(entity.getData());
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void setPresenter(MeasureListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
