package com.ebtang.ebtangebook.view.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.api.ApiService;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.aquery.ApiClient;
import com.ebtang.ebtangebook.aquery.Task;
import com.ebtang.ebtangebook.aquery.TaskType;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.mvpView.UserInfoView;
import com.ebtang.ebtangebook.persenter.UserInfoPersenter;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.utils.camer.CropPhotoUtil;
import com.ebtang.ebtangebook.view.setting.bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by fengzongwei on 16/9/19.
 */
public class UserInfoSettingActivity extends BaseActivity implements UserInfoView{
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.user_info_setting_id)
    TextView textView_id;
    @Bind(R.id.user_info_setting_nicheng)
    TextView textView_nicheng;
    @Bind(R.id.user_info_setting_qianming)
    TextView textView_qianming;
    @Bind(R.id.user_info_setting_phonenum)
    TextView textView_phoneNum;
    @Bind(R.id.user_info_setting_name)
    TextView textView_name;
    @Bind(R.id.user_info_setting_img)
    ImageView imageView;
    @Bind(R.id.user_info_setting_exit)
    Button button_exit;
    @Bind(R.id.user_info_setting_touxiang_body)
    LinearLayout linearLayout_touxiang;
    @Bind(R.id.user_info_setting_nicheng_body)
    LinearLayout linearLayout_nicheng;
    @Bind(R.id.user_info_setting_vip_body)
    LinearLayout linearLayout_vip;
    @Bind(R.id.user_info_setting_qianming_body)
    LinearLayout linearLayout_qianming;
    @Bind(R.id.user_info_setting_mobile_body)
    LinearLayout linearLayout_mobile;
    @Bind(R.id.user_info_setting_realname_body)
    LinearLayout linearLayout_realname;
    @Bind(R.id.user_info_setting_psw_body)
    LinearLayout linearLayout_psw;

    UserInfoPersenter userInfoPersenter;
    private Dialog dialog_choose_img_way;
    private UserInfoBean userInfoBean;

    private static final int CODE_REQUEST_CAMERA_PHOTO = 200;
    private static final int CODE_REQUEST_CROP_PHOTO = 201;
    private static final int CODE_REQUEST_PICTURE = 202;
    private String mCurrentPhotoName;
    private String mNewCurrentPhotoName;

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer(70))
            .build();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == TaskType.USER_IMG_MODIFY){
                String result = (String)msg.obj;
                try{
                    JSONObject object = new JSONObject(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_setting);
        checkPermission();
        ButterKnife.bind(this);
        userInfoPersenter = new UserInfoPersenter();
        userInfoPersenter.attachView(this);
        initView();
    }

    private void checkPermission(){
        String permission1 = "android.permission.CAMERA";
        String permission2 = "android.permission.WRITE_EXTERNAL_STORAGE";
        String permission3 = "android.permission.READ_EXTERNAL_STORAGE";
        String[] permissionArray  = {permission1, permission2, permission3};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArray, 123);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initView() {
        imageView_back = (ImageView) findViewById(R.id.top_title_left);
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        imageView_back.setOnClickListener(this);
        textView_title.setText("个人信息");

        linearLayout_touxiang.setOnClickListener(this);
        linearLayout_nicheng.setOnClickListener(this);
        linearLayout_vip.setOnClickListener(this);
        linearLayout_qianming.setOnClickListener(this);
        linearLayout_mobile.setOnClickListener(this);
        linearLayout_realname.setOnClickListener(this);
        button_exit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        userInfoPersenter.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.user_info_setting_touxiang_body:
                showChooseWayDialog();
                break;
            case R.id.user_info_setting_nicheng_body:
                Intent intent_nicheng = new Intent(this,SettingInfoEditActivity.class);
                intent_nicheng.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_NICHENG);
                startActivity(intent_nicheng);
                break;
            case R.id.user_info_setting_vip_body:
                break;
            case R.id.user_info_setting_qianming_body:
                Intent intent_qianming = new Intent(this,SettingInfoEditActivity.class);
                intent_qianming.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_QIANMING);
                startActivity(intent_qianming);
                break;
            case R.id.user_info_setting_mobile_body:
                break;
            case R.id.user_info_setting_realname_body:
                Intent intent_realname = new Intent(this,SettingInfoEditActivity.class);
                intent_realname.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_XINGMING);
                startActivity(intent_realname);
                break;
            case R.id.user_info_setting_exit:
                final MaterialDialog materialDialog = new MaterialDialog(this);
                materialDialog.setTitle("提示");
                materialDialog.setMessage("您确定要退出当前账号吗?");
                materialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
                materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPrefHelper.getInstance(UserInfoSettingActivity.this).setIsLogin(false);
                        materialDialog.dismiss();
                        finish();
                    }
                });
                materialDialog.show();
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showUserInfo(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
        ImageLoader.getInstance().displayImage(userInfoBean.getUserpic(),imageView,options);
        textView_id.setText(userInfoBean.getId()+"");
        textView_nicheng.setText(userInfoBean.getNick());
        if(!TextUtils.isEmpty(userInfoBean.getSignature()))
            textView_qianming.setText(userInfoBean.getSignature());
        else
            textView_qianming.setText("暂未设置");
        if(!TextUtils.isEmpty(userInfoBean.getMobile()))
            textView_phoneNum.setText(userInfoBean.getMobile());
        else
            textView_phoneNum.setText("暂未绑定手机");
        if(!TextUtils.isEmpty(userInfoBean.getRealName()))
            textView_name.setText(userInfoBean.getRealName());
        else
            textView_name.setText("暂未实名认证");
    }

    /**
     * 选择图片上传的方式
     */
    private void showChooseWayDialog() {
        dialog_choose_img_way = new Dialog(this, R.style.MyDialogStyle);
        dialog_choose_img_way.setContentView(R.layout.user_info_set_dialog_choose_img);
        dialog_choose_img_way.setCanceledOnTouchOutside(true);
        dialog_choose_img_way.findViewById(R.id.other_view).setOnClickListener(
                listener);
        dialog_choose_img_way.findViewById(R.id.bt_dialog_cancel)
                .setOnClickListener(listener);
        // 拍照上传
        dialog_choose_img_way.findViewById(R.id.bt_choose_by_camera)
                .setOnClickListener(listener);
        // 本地上传
        dialog_choose_img_way.findViewById(R.id.bt_choose_by_local)
                .setOnClickListener(listener);
        dialog_choose_img_way.show();
    }

    /**
     * 普通的View.onclick点击事件
     */
    public View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            switch (arg0.getId()) {
                case R.id.bt_choose_by_local://本地选取照片
                    Intent intentFromGallery = new Intent();
                    intentFromGallery.setType("image/*");//选择图片
                    intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intentFromGallery, CODE_REQUEST_PICTURE);
                    dialog_choose_img_way.cancel();
                    break;
                case R.id.bt_choose_by_camera://拍照上传
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    mCurrentPhotoName = System.currentTimeMillis() + ".png";
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(),mCurrentPhotoName)));
                    startActivityForResult(intent, CODE_REQUEST_CAMERA_PHOTO);
                    dialog_choose_img_way.cancel();
                    break;
                case R.id.bt_dialog_cancel://取消
                    dialog_choose_img_way.cancel();
                    break;
                case R.id.other_view://空白
                    dialog_choose_img_way.cancel();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_REQUEST_CAMERA_PHOTO){
            if(resultCode == Activity.RESULT_OK){
//                if(data == null) {
//                    return;
//                }
                File tempFile = new File(
                        Environment.getExternalStorageDirectory(),
                        mCurrentPhotoName);
                mNewCurrentPhotoName = "pz_" + mCurrentPhotoName;
                String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
                CropPhotoUtil.cropRawPhoto(this, Uri.fromFile(tempFile), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
            }
        }else if(requestCode == CODE_REQUEST_CROP_PHOTO){
            if(resultCode == Activity.RESULT_OK){
                String path =  Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
//                userInfoPersenter.commitImg(path);
                commitBitmapInfo(path);
            }
        }else if(requestCode == CODE_REQUEST_PICTURE && resultCode == Activity.RESULT_OK){
            if(data == null) return;
            mNewCurrentPhotoName = System.currentTimeMillis() + ".jpg";
            String outPath = Environment.getExternalStorageDirectory() + File.separator + mNewCurrentPhotoName;
            CropPhotoUtil.cropRawPhoto(this, data.getData(), CODE_REQUEST_CROP_PHOTO, outPath, 200, 200);
        }
    }

    /**
     * 提取上传的信息
     */
    private void commitBitmapInfo(String path) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        File file = new File(path);
        params.put("headImage", file);
        params.put("userId",SharedPrefHelper.getInstance(this).getUserId());
        Task ts_commit = new Task(TaskType.USER_IMG_MODIFY, params, ApiService.POST_USER_IMG);
        ApiClient.saveFile(ts_commit, handler);
    }

}
