package com.tcrj.micro.activity.jzfp;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.adpater.GridImageAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;

import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.entity.sendPicInfo;
import com.tcrj.micro.entity.yqfpInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.until.Utils;
import com.tcrj.micro.view.FullyGridLayoutManager;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WyfpActivity extends BaseActivity implements View.OnClickListener {

    private static final int EVENTYPE = 002;
    TextView txtTitle;
    ImageView btnback;
    private MyOkHttp mMyOkhttp;
    private Button btn_true;
    private EditText edt_jz;
    private EditText edt_fpjl;
    private EditText edt_zl;
    private EditText edt_jy;
    private EditText edt_cy;
    private EditText edt_sm;
    private EditText edt_qt;
    private LinearLayout time_pop;
    private TextView fp_time;

    private String token;
    private String id;
    private int num = 0;
    private String picpath ="";

    private fpjlListInfo.ContentBean item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyfp);

         id = getIntent().getStringExtra("id");
         token = getIntent().getStringExtra("token");
         item = (fpjlListInfo.ContentBean) getIntent().getExtras().getSerializable("fpxxKey");

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        initView();
        initPic();
    }

    @Override
    public void initView() {

        time_pop = findViewById(R.id.time_pop);
        fp_time = findViewById(R.id.fp_time);

        time_pop.setOnClickListener(this);

        recycler = findViewById(R.id.recycler);
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        btn_true = findViewById(R.id.btn_true);

        edt_jz = findViewById(R.id.edt_jz);
        edt_zl = findViewById(R.id.edt_zl);
        edt_jy = findViewById(R.id.edt_jy);
        edt_cy = findViewById(R.id.edt_cy);
        edt_sm = findViewById(R.id.edt_sm);
        edt_qt = findViewById(R.id.edt_qt);
        edt_fpjl = findViewById(R.id.edt_fpjl);


        if(item != null){
            edt_jz.setText(item.getAidPoorMoney()+"");
            edt_zl.setText(item.getAidPoorIntelligence()+"");
            edt_jy.setText(item.getAidPoorJob()+"");
            edt_cy.setText(item.getAidPoorIndustry()+"");
            edt_sm.setText(item.getAidPoorBussiness()+"");
            edt_qt.setText(item.getAidPoorOtherType()+"");
            txtTitle.setText("信息修改");
        }else {
            txtTitle.setText("我要扶贫");

        }

        btn_true.setOnClickListener(this);


    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_true:

                if(selectList != null && selectList.size() > 0){

                    sendPicToNet(selectList.get(num));

                }else {

                    String jl = edt_fpjl.getText().toString().trim();
                    String jz = edt_jz.getText().toString().trim();
                    String zl = edt_zl.getText().toString().trim();
                    String jy = edt_jy.getText().toString().trim();
                    String cy = edt_cy.getText().toString().trim();
                    String sm = edt_sm.getText().toString().trim();
                    String qt = edt_qt.getText().toString().trim();
                    getDataFromNet(jz,zl,jy,cy,sm,qt,jl,"");

                }

                break;

            case R.id.time_pop:
                showDatePickerDialog(this,fp_time);
                break;

            default:
                break;
        }
    }

    private static String aidtime;
    public static void showDatePickerDialog(Activity activity, final TextView fp_time) {

        Calendar calendar = Calendar.getInstance();

        new DatePickerDialog(activity,
                // 绑定监听器(How the parent is notified that the date is set.)
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        aidtime = year + "-" + monthOfYear
                                + "-" + dayOfMonth;
                        // 此处得到选择的时间，可以进行你想要的操作
                        fp_time.setText(year + " - " + monthOfYear
                                + " - " + dayOfMonth);


                    }
                }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();

    }



    private void sendPicToNet(LocalMedia localMedia) {

        String loadeData = upLoadeData(localMedia.getCompressPath());

        if(loadeData == null){
            Toast.makeText(this, "图片上传失败，请重新上传", Toast.LENGTH_SHORT).show();
        }else {
            SendPic(token,loadeData);
        }
    }

    /**
     * 上传图片
     */
    private void SendPic(final String token, final String basedata) {
        showProgressDialog();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("base64", basedata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sendpic_Api)
                .jsonParams(jsonObject.toString())
                .tag(this)
                .enqueue(new GsonResponseHandler<sendPicInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Log.e("TAG","msg"+statusCode);
                    }

                    @Override
                    public void onSuccess(int statusCode, sendPicInfo response) {
                        dismisProgressDialog();
                        if("9999".equals(response.getErrorcode())){
                            num++;
                            String att_path = response.getData();

                            picpath += att_path+",";

                            if(num >= selectList.size()){

                                Log.e("TAG","上传图文信息: "+picpath.substring(0,picpath.length()-1));
                                String jl = edt_fpjl.getText().toString().trim();
                                String jz = edt_jz.getText().toString().trim();
                                String zl = edt_zl.getText().toString().trim();
                                String jy = edt_jy.getText().toString().trim();
                                String cy = edt_cy.getText().toString().trim();
                                String sm = edt_sm.getText().toString().trim();
                                String qt = edt_qt.getText().toString().trim();
                                getDataFromNet(jz,zl,jy,cy,sm,qt,jl,picpath.substring(0,picpath.length()-1));

                            }else {

                                sendPicToNet(selectList.get(num));
                            }


                        }
                    }
                });
    }


    //获取网络数据
    public void getDataFromNet(String jz, String zl, String jy, String cy, String sm, String qt, String jl,String pic) {

//      showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {
            //TODO:修改token
            jsonObject.put("aidPoorObjectId", id);
            jsonObject.put("token", token);
            jsonObject.put("aidPoorMoney", jz);
            jsonObject.put("aidPoorIntelligence", zl);
            jsonObject.put("aidPoorJob", jy);
            jsonObject.put("aidPoorIndustry", cy);
            jsonObject.put("aidPoorBussiness", sm);
            jsonObject.put("aidPoorOtherType", qt);

            jsonObject.put("aidTime", aidtime);//时间
            jsonObject.put("aidRecord", jl);//记录
            jsonObject.put("picPaths", pic);//图片

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.wyfp_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();

                        String errorcode = response.getErrorcode();
                        if("9999".equals(errorcode)){
                            Toast.makeText(WyfpActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            sendMsg();
                            finish();
                        }else if("204".equals(errorcode)){
                            toLogin();
                        }


                    }
                });

    }

    private void toLogin(){
        ACache.get(this).clear();
        Intent intent = new Intent();
        intent.putExtra("openid",-2);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

    private void sendMsg() {
        EventBus.getDefault().post(new MessageEvent("FP",EVENTYPE));
    }




    RecyclerView recycler;

    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private int maxSelectNum = 6;
    private int themeId;


    private void initPic() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(WyfpActivity.this);
                } else {
                    Toast.makeText(WyfpActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

        themeId = R.style.picture_default_style;

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(WyfpActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(WyfpActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(WyfpActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });



    }



    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(WyfpActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(6)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(3)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(null)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//              .videoMaxSecond(15)
//              .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的

                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }
        }
    }

    private String upLoadeData(String path) {

        String base64File = null;
        try {
            base64File = Utils.encodeBase64File(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64File;
    }

}
