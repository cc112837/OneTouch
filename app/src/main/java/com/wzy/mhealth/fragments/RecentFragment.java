package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.MyYuyueActivity;
import com.wzy.mhealth.activities.TestSelfActivity;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    private TextView et_content, marryView, sexView, nameView;
    private TextView tv_timeselect, tv_submit;
    private DatePicker datePicker;
    private Button btn_confirm;
    private int year, month, day;
    String id, session;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        et_content = (EditText) v.findViewById(R.id.et_content);
        marryView = (TextView) v.findViewById(R.id.marryView);
        sexView = (TextView) v.findViewById(R.id.sexView);
        nameView = (TextView) v.findViewById(R.id.nameView);
        nameView.setText(((TestSelfActivity) getActivity()).getName());
        sexView.setText(((TestSelfActivity) getActivity()).getSex());
        marryView.setText(((TestSelfActivity) getActivity()).getTaocan());
        tv_timeselect = (TextView) v.findViewById(R.id.tv_timeselect);
        tv_submit = (TextView) v.findViewById(R.id.tv_submit);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        btn_confirm = (Button) v.findViewById(R.id.btn_confirm);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(y, m, d, new DatePicker.OnDateChangedListener() {
            @Override

            public void onDateChanged(DatePicker datePicker, int yi, int mi, int di) {
                year = yi;
                month = mi;
                day = di;
            }
        });
        tv_timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_submit.setVisibility(View.GONE);
                datePicker.setVisibility(View.VISIBLE);
                btn_confirm.setVisibility(View.VISIBLE);
                tv_timeselect.setVisibility(View.GONE);
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String mm, dd;
                if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                    flag = judgeData();
                } else if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                    if (month > Calendar.getInstance().get(Calendar.MONTH)) {
                        flag = judgeData();
                    } else if (month == Calendar.getInstance().get(Calendar.MONTH)) {
                        if (day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH) || day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                            flag = judgeData();

                        } else {
                            flag = 0;
                            Toast.makeText(getActivity(), "当前日不能小于今日，请重新设置", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        flag = 0;
                        Toast.makeText(getActivity(), "当前月不能小于今月，请重新设置", Toast.LENGTH_LONG).show();
                    }

                } else {
                    flag = 0;
                    Toast.makeText(getActivity(), "当前年不能小于今年，请重新设置", Toast.LENGTH_LONG).show();
                }
                if (flag == 1) {
                    tv_timeselect.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.GONE);
                    btn_confirm.setVisibility(View.GONE);
                    tv_submit.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString();
                String time = tv_timeselect.getText().toString();
                // TODO: 2016/6/27
                session = ((TestSelfActivity) getActivity()).getSession();
                id = ((TestSelfActivity) getActivity()).geteId();
                String url = "http://113.201.59.226:8081/Healwis/base/recordAction!app_appointment.action?sessid=" + session + "&id=" + id + "&appointmentDate=" + time + "&rdesc=" + content;
                MyHttpUtils.handData(handler, 2, url, null);

            }
        });


    }

    public int judgeData() {
        String mm;
        String dd;
        int flag;
        if (month < 9) {
            mm = "0" + (int) (month + 1);
        } else {
            mm = (int) (month + 1) + "";
        }
        if (day < 10) {
            dd = "0" + day;
        } else {
            dd = day + "";
        }
        tv_timeselect.setText(year + "" + mm + "" + dd);
        flag = 1;
        return flag;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Info info = (Info) msg.obj;
                    Log.e("msg", info.getMsg());
                    if (!info.isSuccess())
                        Toast.makeText(getActivity(), "已预约过", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MyYuyueActivity.class);
                    intent.putExtra("session", session);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    getActivity().finish();
            }
        }
    };

}
