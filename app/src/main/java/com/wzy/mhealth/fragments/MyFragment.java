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
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.LeanChat.util.PathUtils;
import com.wzy.mhealth.LeanChat.util.PhotoUtils;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.AboutActivity;
import com.wzy.mhealth.activities.BarCodeActivity;
import com.wzy.mhealth.activities.ExaminationOrderActivity;
import com.wzy.mhealth.activities.ExaminationRecordActivity;
import com.wzy.mhealth.activities.ManageActivity;
import com.wzy.mhealth.activities.MyGradeActivity;
import com.wzy.mhealth.activities.ProudActivity;
import com.wzy.mhealth.activities.SettingActivity;
import com.wzy.mhealth.activities.StepCountActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Grade;
import com.wzy.mhealth.model.Proud;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.CacheUtils;
import com.wzy.mhealth.utils.MyAndroidUtil;
import com.wzy.mhealth.utils.MyHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends D3Fragment {

    RelativeLayout myrecord, setting, manager, erweima, myorder, about, familyHealth;
    TextView username, tv_gradenum;
    LinearLayout about1, ll_decrease, ll_grade, myproud;
    ImageView headImage,iv_spped,iv_blood,iv_shop;
    String dateTime;
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
                case 156:
                    Grade grade = (Grade) msg.obj;
                    if (grade != null) {
                        tv_gradenum.setText(grade.getIntegration() + "");
                    }

                    break;
                case  170:
                    Proud proud=(Proud) msg.obj;
                    if (proud.isStepNum()){
                        iv_spped.setImageResource(R.mipmap.speed_proud);
                    }
                    if(proud.isShop()){
                        iv_shop.setImageResource(R.mipmap.gift_red);
                    }
                    if (proud.isBlood()){
                        iv_shop.setImageResource(R.mipmap.love_red);
                    }
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
        iv_spped=(ImageView)view.findViewById(R.id.iv_spped);
        iv_blood=(ImageView)view.findViewById(R.id.iv_blood);
        iv_shop=(ImageView)view.findViewById(R.id.iv_shop);
        String url= Constants.SERVER_URL+"MedalServlet";
        MyHttpUtils.handData(handler,170,url,null);
        ll_decrease = (LinearLayout) view.findViewById(R.id.ll_decrease);
        ll_grade = (LinearLayout) view.findViewById(R.id.ll_grade);
        tv_gradenum = (TextView) view.findViewById(R.id.tv_gradenum);
        myproud = (LinearLayout) view.findViewById(R.id.myproud);
        String ul = Constants.SERVER_URL + "StepIntegrationServlet";
        MyHttpUtils.handData(handler, 156, ul, null);
        ll_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/9/21 优惠劵 
            }
        });
        ll_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyGradeActivity.class);
                intent.putExtra("count", tv_gradenum.getText().toString());
                startActivity(intent);
            }
        });
        myrecord = (RelativeLayout) view.findViewById(R.id.myrecord);
        myrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExaminationRecordActivity.class);
                startActivity(intent);
            }
        });
        about1 = (LinearLayout) view.findViewById(R.id.about1);
        erweima = (RelativeLayout) view.findViewById(R.id.erweima);
        manager = (RelativeLayout) view.findViewById(R.id.manager);
        myorder = (RelativeLayout) view.findViewById(R.id.myorder);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExaminationOrderActivity.class);
                startActivity(intent);
            }
        });
        setting = (RelativeLayout) view.findViewById(R.id.setting);
        about = (RelativeLayout) view.findViewById(R.id.about);
        familyHealth = (RelativeLayout) view.findViewById(R.id.familyHealth);
        username = (TextView) view.findViewById(R.id.username);
        username.setText(Constants.USER_NAME);
        headImage = (ImageView) view.findViewById(R.id.headView);
        LeanchatUser curUser = AVUser.getCurrentUser(LeanchatUser.class);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        myproud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProudActivity.class);
                startActivity(intent);
            }
        });
        String avatarUrl = curUser.getAvatarUrl();
        if (avatarUrl == null) {
            try {
                JSONObject object = new JSONObject(curUser.toString());
                JSONObject serverData = object.getJSONObject("serverData");
                JSONObject avatar = serverData.getJSONObject("avatar");
                avatarUrl = avatar.getString("url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MyAndroidUtil.editXmlByString(
                Constants.icon, avatarUrl);
        ImageLoader.getInstance().displayImage(avatarUrl, headImage,
                com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOption);
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent);
            }
        });
        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvatarDialog();
            }
        });
        about1.setOnClickListener(new View.OnClickListener() {
            /**
             * 创建人：吴聪聪
             * 邮箱:cc112837@163.com
             * 创建时间：2016/4/5 14:35
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });

        erweima.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BarCodeActivity.class);
                getActivity().startActivity(intent);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        familyHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/7/8 我的计步 家人健康FamilyHealthActivity
                TiUser step = new TiUser();
                step.setName(LeanchatUser.getCurrentUser().getUsername());
                String ul = Constants.SERVER_URL + "StepNumInitServlet";
                MyHttpUtils.handData(handler, 31, ul, step);

            }
        });
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
        TextView albumPic = (TextView) v.findViewById(R.id.album_pic);
        TextView cameraPic = (TextView) v.findViewById(R.id.camera_pic);
        albumPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                avatarDialog.dismiss();
                Date date1 = new Date(System.currentTimeMillis());
                dateTime = date1.getTime() + "";
                showAvatarFromAlbum();
            }
        });
        cameraPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                avatarDialog.dismiss();
                Date date = new Date(System.currentTimeMillis());
                dateTime = date.getTime() + "";
                showAvatarFromCamera();
            }
        });
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
        oks.setTitleUrl("http://www.wandoujia.com/apps/com.wzy.mhealth/download");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一点就医");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://pp.myapp.com/ma_icon/0/icon_42275805_1467870148/96");//
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.wandoujia.com/apps/com.wzy.mhealth/download");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("一点就医");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.wandoujia.com/apps/com.wzy.mhealth/download");
        // 启动分享GUI
        oks.show(getContext());
    }
}
