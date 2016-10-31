package com.wzy.mhealth.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.fragments.ChangeFragmentHelper;
import com.wzy.mhealth.fragments.QuestionFragment;
import com.wzy.mhealth.fragments.QuestionFragment1;
import com.wzy.mhealth.fragments.QuestionFragment2;
import com.wzy.mhealth.fragments.QuestionFragment3;
import com.wzy.mhealth.fragments.QuestionFragment4;
import com.wzy.mhealth.fragments.QuestionFragment5;
import com.wzy.mhealth.fragments.QuestionFragment6;
import com.wzy.mhealth.fragments.QuestionFragment7;
import com.wzy.mhealth.fragments.QuestionFragment8;
import com.wzy.mhealth.fragments.QuestionFragment9;
import com.wzy.mhealth.fragments.QuestionFragmentt;
import com.wzy.mhealth.fragments.QuestionFragmenttt;
import com.wzy.mhealth.model.Question;
import com.wzy.mhealth.model.ReDefine;
import com.wzy.mhealth.utils.MyHttpUtils;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView leftBtn;
    private FrameLayout question_contain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Fragment fragment = new QuestionFragment();
        ChangeFragmentHelper helper = new ChangeFragmentHelper();
        helper.setTargetFragment(fragment);
        helper.setIsClearStackBack(true);
        changeFragment(helper);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        question_contain = (FrameLayout) findViewById(R.id.question_contain);
        String url = Constants.SERVER_URL + "MhealthUserSurveyServlet";
        MyHttpUtils.handData(handler, 220, url, null);
        leftBtn.setOnClickListener(this);

    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 261:
                    ReDefine reDefine = (ReDefine) msg.obj;
                    if (reDefine.getStatus().equals("1")) {
                    }
                    break;
                case 220:
                    final Question question = (Question) msg.obj;
                    if (question.getPageCount() == 11) {
                        Intent intent = new Intent(QuestionActivity.this, RecommandActivity.class);
                        startActivity(intent);
                    } else if (question.getPageCount() == -1) {

                    } else {
                        new AlertDialog.Builder(QuestionActivity.this).setTitle("提示")
                                .setMessage("您有未完成的体检定制，是否继续定制？").setPositiveButton("继续定制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Fragment fragment = null;
                                switch (question.getPageCount()) {
                                    case 0:
                                        fragment = new QuestionFragment1();
                                        break;
                                    case 1:
                                        fragment = new QuestionFragment2();
                                        break;
                                    case 2:
                                        fragment = new QuestionFragment3();
                                        break;
                                    case 3:
                                        fragment = new QuestionFragment4();
                                        break;
                                    case 4:
                                        fragment = new QuestionFragment5();
                                        break;
                                    case 5:
                                        fragment = new QuestionFragment6();
                                        break;
                                    case 6:
                                        fragment = new QuestionFragment7();
                                        break;
                                    case 7:
                                        fragment = new QuestionFragment8();
                                        break;
                                    case 8:
                                        fragment = new QuestionFragment9();
                                        break;
                                    case 9:
                                        fragment = new QuestionFragmentt();
                                        break;
                                    case 10:
                                        fragment = new QuestionFragmenttt();
                                        break;
                                }
                                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                                helper.setTargetFragment(fragment);
                                helper.setIsClearStackBack(true);
                                changeFragment(helper);
                            }
                        }).setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String ut = Constants.SERVER_URL + "MhealthUserReSurveyServlet";
                                MyHttpUtils.handData(handler, 261, ut, null);
                                dialog.dismiss();
                            }
                        }).show();
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                new AlertDialog.Builder(QuestionActivity.this).setTitle("提示")
                        .setMessage("您真的要放弃此次专家免费定制的机会吗").setPositiveButton("忍痛放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("继续定制", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }

    public void changeFragment(ChangeFragmentHelper helper) {

        //获取需要跳转的Fragment
        Fragment targetFragment = helper.getTargetFragment();
        //获取是否清空回退栈
        boolean clearStackBack = helper.isClearStackBack();
        //获取是否加入回退栈
        String targetFragmentTag = helper.getTargetFragmentTag();
        //获取Bundle
        Bundle bundle = helper.getBundle();
        //是否给Fragment传值
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (targetFragment != null) {
            fragmentTransaction.replace(R.id.question_contain, targetFragment);
        }

        //是否添加到回退栈
        if (targetFragmentTag != null) {
            fragmentTransaction.addToBackStack(targetFragmentTag);
        }

        //是否清空回退栈
        if (clearStackBack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentTransaction.commit();
    }
}
