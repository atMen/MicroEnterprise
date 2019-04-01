package com.tcrj.micro.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tcrj.micro.R;
import com.tcrj.micro.adpater.dialogAdapter;
import com.tcrj.micro.entity.zcgsInfo;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by leict on 2018/7/4.
 */

public class BottomStyleDialog extends Dialog implements AdapterView.OnItemClickListener {



    private ListView mLv;
    private Context context;

    private List<zcgsInfo> mList = new ArrayList<>();

    private dialogAdapter mAdapter;

    public BottomStyleDialog(Context context, List<zcgsInfo> list) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialog);
        this.context = context;
        this.mList = list;

        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(10, 10, 10, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_content_list);

        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        initView();
        initData();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.list);
        mLv.setOnItemClickListener(this);
    }

    private void initData() {

        mAdapter = new dialogAdapter(getContext(), mList);
        mLv.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.e("TAG","onItemClick:"+position);
        zcgsInfo listinfoBean = mList.get(position);
        if(mItemClickListener != null){
            mItemClickListener.onItemClick(listinfoBean);
        }
    }


    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(zcgsInfo listinfoBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}

