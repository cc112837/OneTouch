package com.wzy.mhealth.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.LeanChat.util.PathUtils;
import com.wzy.mhealth.LeanChat.util.PhotoUtils;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.AboutActivity;
import com.wzy.mhealth.activities.BarCodeActivity;
import com.wzy.mhealth.activities.DecreseActivity;
import com.wzy.mhealth.activities.ExaminationOrderActivity;
import com.wzy.mhealth.activities.ExaminationRecordActivity;
import com.wzy.mhealth.activities.ManageActivity;
import com.wzy.mhealth.activities.MyGradeActivity;
import com.wzy.mhealth.activities.ProudActivity;
import com.wzy.mhealth.activities.SettingActivity;
import com.wzy.mhealth.activities.StepCountActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Proud;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.CacheUtils;
import com.wzy.mhealth.utils.MyAndroidUtil;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends D3Fragment implements View.OnClickListener {

    private RelativeLayout myrecord, setting, manager, erweima, myorder, about, familyHealth;
    private LinearLayout about1, ll_decrease, ll_grade, myproud;
    private ImageView headImage, iv_spped, iv_blood, iv_shop;
    private String dateTime;
    private Proud proud;
    private TextView cameraPic, albumPic, username, tv_gradenum,tv_decrease;
    private AlertDialog avatarDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 31:
                    StepInfo stepInfo1 = (StepInfo) msg.obj;
                    if (stepInfo1.getStatus().equals("1") || stepInfo1.getStatus().equals("2")) {
                        Intent intent = new Intent(getActivity(), StepCountActivity.class);
                        startActivity(intent);
                    }
                    break;

                case 170:
                    proud = (Proud) msg.obj;
                    tv_gradenum.setText(proud.getIntegration()+"");
                    tv_decrease.setText(proud.getCouponNum()+"");
                    if (proud.isStepNum()) {
                        iv_spped.setImageResource(R.mipmap.speed_proud);
                    }
                    if (proud.isShop()) {
                        iv_shop.setImageResource(R.mipmap.gift_red);
                    }
                    if (proud.isBlood()) {
                        iv_blood.setImageResource(R.mipmap.love_red);
                    }
                    break;
                case 266:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    break;
            }
        }
    };



    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd("my-list-fragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = setContentView(inflater, R.layout.fragment_my);
        ShareSDK.initSDK(getContext());
        iv_spped = (ImageView) view.findViewById(R.id.iv_spped);
        iv_blood = (ImageView) view.findViewById(R.id.iv_blood);
        iv_shop = (ImageView) view.findViewById(R.id.iv_shop);
        tv_decrease=(TextView) view.findViewById(R.id.tv_decrease);
        String url = Constants.SERVER_URL + "StepIntegrationServlet";
        MyHttpUtils.handData(handler, 170, url, null);
        ll_decrease = (LinearLayout) view.findViewById(R.id.ll_decrease);
        ll_grade = (LinearLayout) view.findViewById(R.id.ll_grade);
        tv_gradenum = (TextView) view.findViewById(R.id.tv_gradenum);
        myproud = (LinearLayout) view.findViewById(R.id.myproud);
        myrecord = (RelativeLayout) view.findViewById(R.id.myrecord);
        about1 = (LinearLayout) view.findViewById(R.id.about1);
        erweima = (RelativeLayout) view.findViewById(R.id.erweima);
        manager = (RelativeLayout) view.findViewById(R.id.manager);
        myorder = (RelativeLayout) view.findViewById(R.id.myorder);
        setting = (RelativeLayout) view.findViewById(R.id.setting);
        about = (RelativeLayout) view.findViewById(R.id.about);
        familyHealth = (RelativeLayout) view.findViewById(R.id.familyHealth);
        username = (TextView) view.findViewById(R.id.username);
        username.setText(Constants.USER_NAME);
        headImage = (ImageView) view.findViewById(R.id.headView);
        LeanchatUser.getCurrentUser().fetchInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                LeanchatUser user = (LeanchatUser) avObject;
                String avatarUrl = user.getAvatarUrl();
                MyAndroidUtil.editXmlByString(
                        Constants.icon, avatarUrl);
                ImageLoader.getInstance().displayImage(avatarUrl, headImage,
                        com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOption);
            }
        });
        manager.setOnClickListener(this);
        headImage.setOnClickListener(this);
        setting.setOnClickListener(this);
        myproud.setOnClickListener(this);
        about1.setOnClickListener(this);
        erweima.setOnClickListener(this);
        about.setOnClickListener(this);
        myrecord.setOnClickListener(this);
        ll_decrease.setOnClickListener(this);
        ll_grade.setOnClickListener(this);
        myorder.setOnClickListener(this);
        familyHealth.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart("my-list-fragment");
    }

    public void AvatarDialog() {

        avatarDialog = new AlertDialog.Builder(getContext()).create();
        avatarDialog.setCanceledOnTouchOutside(true);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.my_headicon, null);
        avatarDialog.show();
        avatarDialog.setContentView(v);
        avatarDialog.getWindow().setGravity(Gravity.CENTER);
        albumPic = (TextView) v.findViewById(R.id.album_pic);
        cameraPic = (TextView) v.findViewById(R.id.camera_pic);
        albumPic.setOnClickListener(this);
        cameraPic.setOnClickListener(this);
    }

    private void showAvatarFromCamera() {
        File f = new File(CacheUtils.getCacheDirectory(getContext(), true, "icon") + dateTime);
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(f);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(camera, 1);
    }

    private void showAvatarFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                String files = CacheUtils.getCacheDirectory(getContext(), true, "icon") + dateTime;
                File file = new File(files);
                if (file.exists() && file.length() > 0) {
                    Uri uri = Uri.fromFile(file);
                    startPhotoZoom(uri);
                } else {
                }
                break;
            case 2:
                if (data == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case 3:
                if (data != null) {
                    final String path = saveCropAvatar(data);
                    LeanchatUser user = LeanchatUser.getCurrentUser();
                    user.saveAvatar(path, new SaveCallback() {
                        @Override
                        public void done(AVException arg0) {
                            if (arg0 == null) {
                                LeanchatUser curUser = LeanchatUser.getCurrentUser(LeanchatUser.class);
                                String avatarUrl = curUser.getAvatarUrl();
                                String urll = Constants.SERVER_URL + "MhealthUserImageServlet";
                                TiUser useri = new TiUser();
                                useri.setName(LeanchatUser.getCurrentUser().getUsername());
                                useri.setTel(avatarUrl + "");
                                MyHttpUtils.handData(handler, 266, urll, useri);
                                ImageLoader
                                        .getInstance()
                                        .displayImage(
                                                avatarUrl,
                                                headImage,
                                                com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOption);
                            }

                        }
                    });
                }
                break;
            default:
                break;
        }

    }

    private String saveCropAvatar(Intent data) {
        Bundle extras = data.getExtras();
        String path = null;
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            if (bitmap != null) {
                bitmap = PhotoUtils.toRoundCorner(bitmap, 10);
                path = PathUtils.getAvatarCropPath();
                PhotoUtils.saveBitmap(path, bitmap);
                if (bitmap != null && bitmap.isRecycled() == false) {
                    bitmap.recycle();
                }
            }
        }
        return path;
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(getContext());
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("一点就医");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.wzy.mhealth");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一点就医");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://pp.myapp.com/ma_icon/0/icon_42275805_1467870148/96");//
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.wzy.mhealth");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("一点就医");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.wzy.mhealth");
        // 启动分享GUI
        oks.show(getContext());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_decrease:
                intent = new Intent(getActivity(), DecreseActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_grade:
                intent = new Intent(getActivity(), MyGradeActivity.class);
                intent.putExtra("count", tv_gradenum.getText().toString());
                startActivity(intent);
                break;
            case R.id.myrecord:
                intent = new Intent(getActivity(), ExaminationRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.erweima:
                intent = new Intent();
                intent.setClass(getActivity(), BarCodeActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.manager:
                intent = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.myproud:
                intent = new Intent(getActivity(), ProudActivity.class);
                intent.putExtra("proud",proud);
                startActivity(intent);
                break;
            case R.id.album_pic:
                avatarDialog.dismiss();
                Date date1 = new Date(System.currentTimeMillis());
                dateTime = date1.getTime() + "";
                showAvatarFromAlbum();
                break;
            case R.id.myorder:
                intent = new Intent(getActivity(), ExaminationOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.familyHealth:
                TiUser step = new TiUser();
                step.setName(LeanchatUser.getCurrentUser().getUsername());
                String ul = Constants.SERVER_URL + "StepNumInitServlet";
                MyHttpUtils.handData(handler, 31, ul, step);
                break;
            case R.id.about1:
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.headView:
                AvatarDialog();
                break;
            case R.id.about:
                showShare();
                break;
            case R.id.camera_pic:
                avatarDialog.dismiss();
                Date date = new Date(System.currentTimeMillis());
                dateTime = date.getTime() + "";
                showAvatarFromCamera();
                break;
        }
    }
}
