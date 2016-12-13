package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Address;
import com.wzy.mhealth.model.ProvinceBean;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ReadUtil;
import com.wzy.mhealth.utils.ToastUtil;
import com.wzy.mhealth.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdaAddressActivity extends Activity implements View.OnClickListener {
    private TextView title, et_city;
    private ImageView leftBtn;
    private String flag;
    private CheckBox sw_default;
    private EditText et_adddetail, et_tel, tv_who;
    private Button btn_confirm;
    ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();
    private Address.DataEntity addre;
    private OptionsPickerView pvOptions;
    int flagdefault = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 279:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (("1").equals(stepInfo.getStatus())) {
                        ToastUtil.show(UpdaAddressActivity.this, "保存成功");
                        finish();
                    } else {
                        ToastUtil.show(UpdaAddressActivity.this, stepInfo.getData());
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upda_address);
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        addre = (Address.DataEntity) intent.getSerializableExtra("id");
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        et_adddetail = (EditText) findViewById(R.id.et_adddetail);
        et_city = (TextView) findViewById(R.id.et_city);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_who = (EditText) findViewById(R.id.tv_who);
        sw_default = (CheckBox) findViewById(R.id.sw_default);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        leftBtn.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        et_city.setOnClickListener(this);
        //选项选择器
        pvOptions = new OptionsPickerView(this);
        String s = ReadUtil.readFromRaw(getApplicationContext(), R.raw.provice);
        parseJson(s);
        pvOptions.setPicker(provinceBeanList, cityList, districtList, true);
        pvOptions.setCyclic(false, true, true);
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String city = provinceBeanList.get(options1).getPickerViewText();
                String address;
                //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京".equals(city) || "上海".equals(city) || "天津".equals(city) || "重庆".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + districtList.get(options1).get(option2).get(options3);
                } else {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + cityList.get(options1).get(option2)
                            + " " + districtList.get(options1).get(option2).get(options3);
                }
                et_city.setText(address);
            }
        });
        if ("new".equals(flag)){
            title.setText("新增收货地址");
        } else {
            title.setText("修改收货地址");
            tv_who.setText(addre.getName() + "");
            et_city.setText(addre.getArea() + "");
            et_tel.setText(addre.getTelephone() + "");
            et_adddetail.setText(addre.getAddress() + "");
            if (addre.getSid() == 1) {
                sw_default.setChecked(true);
            } else {
                sw_default.setChecked(false);
            }

        }

        sw_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

                                              {
                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {//开
                                                          flagdefault = 1;
                                                      } else {//关
                                                          flagdefault = 0;
                                                      }
                                                  }
                                              }

        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvOptions.isShowing()) {
                pvOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //  解析json填充集合
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.et_city:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(UpdaAddressActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptions.show();
                break;
            case R.id.btn_confirm:
                String adddetail = et_adddetail.getText().toString();
                String city = et_city.getText().toString();
                String tel = et_tel.getText().toString();
                String who = tv_who.getText().toString();
                if (Util.getInstance().isMobileNumber(tel)) {
                    String url = Constants.SERVER_URL + "MhealthShopAddressSaveServlet";
                    Recommend recommend = new Recommend();
                    recommend.setName(who);
                    recommend.setData(city);
                    recommend.setContext(adddetail);
                    recommend.setTaoId(flagdefault);
                    if (flag.equals("update")) {
                        recommend.setStatus(addre.getAddressId() + "");
                    } else {
                        recommend.setStatus("");
                    }
                    recommend.setTaocanNum(tel);
                    MyHttpUtils.handData(handler, 279, url, recommend);
                } else {
                    ToastUtil.show(UpdaAddressActivity.this, "请输入正确的手机号码");
                }

                break;
        }
    }
}
