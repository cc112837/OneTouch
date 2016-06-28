package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    private EditText et_content, ageView, marryView, sexView, nameView;
    private TextView tv_timeselect, tv_submit;
    private DatePicker datePicker;
    private Button btn_confirm;
    private int year, month, day;


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
        ageView = (EditText) v.findViewById(R.id.ageView);
        marryView = (EditText) v.findViewById(R.id.marryView);
        sexView = (EditText) v.findViewById(R.id.sexView);
        nameView = (EditText) v.findViewById(R.id.nameView);
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
                if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                    tv_timeselect.setText(year + "-" + (int) ((int) month + 1) + "-" + day);
                    flag = 1;
                } else if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                    if (month > Calendar.getInstance().get(Calendar.MONTH)) {
                        tv_timeselect.setText(year + "-" + (int) ((int) month + 1) + "-" + day);
                        flag = 1;
                    } else if (month == Calendar.getInstance().get(Calendar.MONTH)) {
                        if (day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH) || day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                            tv_timeselect.setText(year + "-" + (int) ((int) month + 1) + "-" + day);
                            flag = 1;

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
                String age = ageView.getText().toString();
                String marry = marryView.getText().toString();
                String sex = sexView.getText().toString();
                String name = nameView.getText().toString();
                // TODO: 2016/6/27
                Toast.makeText(getActivity(),content+"****"+sex , Toast.LENGTH_LONG).show();

            }
        });

    }


}
