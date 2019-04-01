package com.spring.chanba.ui.center;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.CommunalEntity;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.CompanyProveContract;
import com.spring.chanba.popup.TakePhotoPopupwindow;
import com.spring.chanba.presenter.CompanyProvePresenter;
import com.spring.chanba.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 企业认证
 */
public class CompanyProveActivity extends BaseActivity implements View.OnClickListener, CompanyProveContract.View {
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private EditText edtCompanyName;
    private EditText edtLegalPerson;
    private EditText edtPersonCard;
    private EditText edtContactNumber;
    private EditText edtOrganizeCode;
    private ImageView imgBusinessLicence;
    private ImageView imgLicenceSample;
    private Button btnTakephoto;
    private CommunalEntity communalEntity;
    private Uri photoUri;
    private CompanyProveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyprove);
        setCommunal();
        initView();
    }

    private void setCommunal() {
        communalEntity = new CommunalEntity();
        communalEntity.setImageURL("");
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        edtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        edtLegalPerson = (EditText) findViewById(R.id.edt_legal_person);
        edtPersonCard = (EditText) findViewById(R.id.edt_person_card);
        edtContactNumber = (EditText) findViewById(R.id.edt_contact_number);
        edtOrganizeCode = (EditText) findViewById(R.id.edt_organize_code);
        imgBusinessLicence = (ImageView) findViewById(R.id.img_business_licence);
        imgLicenceSample = (ImageView) findViewById(R.id.img_licence_sample);
        btnTakephoto = (Button) findViewById(R.id.btn_takephoto_submit);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        btnTakephoto.setOnClickListener(this);
        imgBusinessLicence.setOnClickListener(this);
        tvTitleHead.setText("企业认证");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.img_business_licence) {
            new TakePhotoPopupwindow(this).setOnItemListener(new TakePhotoPopupwindow.TakePhotoCallBack() {
                @Override
                public void setOnTakePhotoListener() {
                    if (ContextCompat.checkSelfPermission(CompanyProveActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CompanyProveActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                    } else {
                        takePhoto();
                    }
                }

                @Override
                public void setOnChosePhotoListener() {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CompanyProveActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");//相片类型
                        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                    }
                }
            });

        } else if (i == R.id.btn_takephoto_submit) {
            String name = edtCompanyName.getText().toString();
            String person = edtLegalPerson.getText().toString();
            String card = edtPersonCard.getText().toString();
            String number = edtContactNumber.getText().toString();
            String organize = edtOrganizeCode.getText().toString();
            if (Utils.isStringEmpty(name)) {
                displayToast("请输入公司名称");
                return;
            }
            if (Utils.isStringEmpty(person)) {
                displayToast("请输入法人姓名");
                return;
            }
            if (Utils.isStringEmpty(card)) {
                displayToast("请输入法人身份证");
                return;
            }
            if (Utils.isStringEmpty(number)) {
                displayToast("请输入联系电话");
                return;
            }
            if (Utils.isStringEmpty(organize)) {
                displayToast("请输入组织机构代码");
                return;
            }
            if (Utils.isStringEmpty(communalEntity.getImageURL())) {
                displayToast("请上传营业执照");
                return;
            }
            submit();

        }
    }

    /**
     * 提交
     */
    private void submit() {
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        String name = edtCompanyName.getText().toString();
        String person = edtLegalPerson.getText().toString();
        String card = edtPersonCard.getText().toString();
        String number = edtContactNumber.getText().toString();
        String organize = edtOrganizeCode.getText().toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        map.put("companyName", name);
        map.put("legalperson", person);
        map.put("legalpersonIdcard", card);
        map.put("contactPhone", number);
        map.put("organizationCode", organize);
        map.put("imageStr", "data:image/jpeg;base64," + communalEntity.getImageURL() + "#");
        presenter = new CompanyProvePresenter(this);
        presenter.getData(map);
    }

    /**
     * 打开系统相机
     */
    private void takePhoto() {
        if (Build.VERSION.SDK_INT >= 24) {
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoUri = get24MediaFileUri(MY_PERMISSIONS_REQUEST_CALL_PHONE);
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takeIntent, CROP_PHOTO);
        } else {
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoUri = getMediaFileUri(MY_PERMISSIONS_REQUEST_CALL_PHONE);
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takeIntent, CROP_PHOTO);
        }
    }

    /**
     * 版本24以上
     *
     * @param type
     * @return
     */
    public Uri get24MediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", mediaFile);
    }

    /**
     * 版本24以下
     *
     * @param type
     * @return
     */
    public Uri getMediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        /**创建Media File**/
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return Uri.fromFile(mediaFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Bitmap bit = null;
                    try {
                        bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    communalEntity.setImageURL(Utils.bitmapToBase64(bit));
                    imgBusinessLicence.setImageBitmap(bit);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Bitmap bitmap = data.getParcelableExtra("data");
                            communalEntity.setImageURL(Utils.bitmapToBase64(bitmap));
                            imgBusinessLicence.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= 24) {
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoUri));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            communalEntity.setImageURL(Utils.bitmapToBase64(bitmap));
                            imgBusinessLicence.setImageBitmap(bitmap);
                        } else {
                            Bitmap bitmap = BitmapFactory.decodeFile(photoUri.getPath());
                            communalEntity.setImageURL(Utils.bitmapToBase64(bitmap));
                            imgBusinessLicence.setImageBitmap(bitmap);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(HandleEntity entity) {
        if (entity.getState() == 1) {
            displayToast(entity.getMessage());
            finish();
        } else {
            displayToast(entity.getMessage());
        }
    }

    @Override
    public void setPresenter(CompanyProveContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
