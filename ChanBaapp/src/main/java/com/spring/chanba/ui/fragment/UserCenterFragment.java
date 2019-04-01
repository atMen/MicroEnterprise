package com.spring.chanba.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.WeChatUserInfoEntity;
import com.spring.chanba.ui.center.CompanyProveActivity;
import com.spring.chanba.ui.center.PerfectInfoActivity;
import com.spring.chanba.ui.center.ProjectApplyActivity;
import com.spring.chanba.ui.center.ServiceApplyActivity;
import com.spring.chanba.ui.center.TrainEnrolmentActivity;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.CircleImageView;

public class UserCenterFragment extends Fragment implements View.OnClickListener {
    private String TAG_DEBUG = UserCenterFragment.class.getSimpleName();
    private CircleImageView imgCirclePhoto;
    private TextView tvCenterName;
    private LinearLayout layoutCenterInfo;
    private LinearLayout layoutCenterService;
    private LinearLayout layoutCenterCompany;
    private LinearLayout layoutCenterCultivate;
    private LinearLayout layoutCenterProject;
    private LinearLayout layoutCenterAbout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usercenter, container, false);
        initView(view);
        return view;
    }

    /**
     * 获取页面控件
     *
     * @param v
     */
    private void initView(View v) {
        imgCirclePhoto = (CircleImageView) v.findViewById(R.id.img_circle_photo);
        tvCenterName = (TextView) v.findViewById(R.id.tv_center_name);
        layoutCenterInfo = (LinearLayout) v.findViewById(R.id.layout_center_info);
        layoutCenterService = (LinearLayout) v.findViewById(R.id.layout_center_service);
        layoutCenterCompany = (LinearLayout) v.findViewById(R.id.layout_center_company);
        layoutCenterCultivate = (LinearLayout) v.findViewById(R.id.layout_center_cultivate);
        layoutCenterProject = (LinearLayout) v.findViewById(R.id.layout_center_project);
        layoutCenterAbout = (LinearLayout) v.findViewById(R.id.layout_center_about);
        layoutCenterInfo.setOnClickListener(this);
        layoutCenterService.setOnClickListener(this);
        layoutCenterCompany.setOnClickListener(this);
        layoutCenterCultivate.setOnClickListener(this);
        layoutCenterProject.setOnClickListener(this);
        layoutCenterAbout.setOnClickListener(this);
//        WeChatUserInfoEntity entity = BaseApplication.getUserInfoEntity();
//        if (entity != null) {
//            if (!Utils.isStringEmpty(entity.getHeadimgurl())) {
//                Glide.with(getContext()).load(entity.getHeadimgurl()).into(imgCirclePhoto);
//            }
//        }
//        tvCenterName.setText(entity.getNickname());
//        Log.i(TAG_DEBUG, BaseApplication.getUserInfo().getId());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.layout_center_info) {
            Intent info = new Intent(getContext(), PerfectInfoActivity.class);
            startActivity(info);

        } else if (i == R.id.layout_center_service) {
            Intent apply = new Intent(getContext(), ServiceApplyActivity.class);
            startActivity(apply);

        } else if (i == R.id.layout_center_company) {
            Intent company = new Intent(getContext(), CompanyProveActivity.class);
            startActivity(company);

        } else if (i == R.id.layout_center_cultivate) {
            Intent cultivate = new Intent(getContext(), TrainEnrolmentActivity.class);
            startActivity(cultivate);

        } else if (i == R.id.layout_center_project) {
            Intent project = new Intent(getContext(), ProjectApplyActivity.class);
            startActivity(project);

        } else if (i == R.id.layout_center_about) {
            ((BaseActivity) getContext()).displayToast("敬请期待");

        }
    }
}
