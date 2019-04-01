package com.tcrj.micro.adpater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.entity.EnterpriseEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import java.util.ArrayList;


public class EnterpriseListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<EnterpriseEntity> lists;

    public EnterpriseListAdapter(Context context, ArrayList<EnterpriseEntity> lists) {
        super();
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int index, View view, ViewGroup arg2) {

        EnterpriseEntity entity = lists.get(index);
        if (view == null) {
            view = inflater.inflate(R.layout.enterprise_list_item, null);
        }
        view.setBackgroundColor(Color.WHITE);

        TextView textView1 = (TextView) view.findViewById(R.id.title);
        TextView textView2 = (TextView) view.findViewById(R.id.regno);
        TextView textView3 = (TextView) view.findViewById(R.id.regcap);

        textView1.setText(entity.getEntName());
        textView2.setText("注册号:"+entity.getRegNo());
        if(!entity.getRegCap().equals("")){
            textView3.setText("资本:"+entity.getRegCap()+"万");
        }else{
            textView3.setText("资本:");
        }
        return view;
    }


}
