package com.wzy.mhealth.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.bigkoo.pickerview.TimePickerView;
import com.wzy.mhealth.LeanChat.util.PathUtils;
import com.wzy.mhealth.LeanChat.util.PhotoUtils;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ImageItem;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

import org.json.JSONArray;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AidsManagerActivity extends AppCompatActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 150:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    Toast.makeText(AidsManagerActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private GridView gridView1;
    private TimePickerView pvOptions;
    private final int IMAGE_OPEN = 1;        //打开图片标记
    private String pathImage;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.iv_jiantou)
    ImageView ivJiantou;
    @Bind(R.id.et_per)
    EditText etPer;
    @Bind(R.id.tv_examrecord)
    RadioButton tvExamrecord;
    @Bind(R.id.tv_dooraid)
    RadioButton tvDooraid;
    @Bind(R.id.tv_hisaid)
    RadioButton tvHisaid;
    @Bind(R.id.tv_change)
    RadioButton tvChange;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    String type = "体检报告";
    private Bitmap bmp;
    private SimpleAdapter simpleAdapter;
    private List imageItem;
    private List<ImageItem> listitem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aids_manager);
        ButterKnife.bind(this);
        gridView1 = (GridView) findViewById(R.id.gridView1);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_defaluat);
        imageItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[]{"itemImage"}, new int[]{R.id.imageView1});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);
        gridView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    dialog(position);
                }
                return false;
            }
        });
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (imageItem.size() == 7) { //第一张为默认图片
                    Toast.makeText(AidsManagerActivity.this, "图片数6张已满", Toast.LENGTH_SHORT).show();
                } else if (position == 0) { //点击图片位置为+ 0对应0张图片
                    Toast.makeText(AidsManagerActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
                    //选择图片
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, IMAGE_OPEN);
                    //通过onResume()刷新数据
                } else {
                }
            }
        });

        pvOptions = new TimePickerView(AidsManagerActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvOptions.setTime(new Date());
        pvOptions.setCyclic(false);
        pvOptions.setCancelable(true);
        //时间选择后回调
        pvOptions.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvDate.setText(getTime(date));
            }
        });
    }

    //刷新图片
    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(pathImage)) {
            Bitmap addbmp = BitmapFactory.decodeFile(pathImage);
            HashMap<String, Object> map = new HashMap<>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.griditem_addpic,
                    new String[]{"itemImage"}, new int[]{R.id.imageView1});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    if (view instanceof ImageView && data instanceof Bitmap) {
                        ImageView i = (ImageView) view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            pathImage = null;
        }
    }

    private String saveCropAvatar(Intent data) {
        Bundle extras = data.getExtras();
        String path = null;
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            if (bitmap != null) {
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
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);

    }

    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if (resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
            try {
                startPhotoZoom(data.getData());
            } catch (NullPointerException e) {
                e.printStackTrace();// 用户点击取消操作
            }
        } else if (requestCode == 3) {
            pathImage = saveCropAvatar(data);
            ImageItem imageItem = new ImageItem();
            imageItem.setPath(pathImage);
            listitem.add(imageItem);
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AidsManagerActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                listitem.remove(position - 1);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
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

    @OnClick({R.id.leftBtn, R.id.tv_date, R.id.iv_jiantou, R.id.tv_examrecord, R.id.tv_dooraid, R.id.tv_hisaid, R.id.tv_change, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_date:
            case R.id.iv_jiantou:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(AidsManagerActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptions.show();
                break;
            case R.id.tv_examrecord:
                type = "体检报告";
                tvHisaid.setChecked(false);
                tvChange.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(true);
                break;
            case R.id.tv_dooraid:
                type = "门诊病历";
                tvDooraid.setChecked(true);
                tvChange.setChecked(false);
                tvHisaid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_hisaid:
                type = "住院病历";
                tvHisaid.setChecked(true);
                tvChange.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_change:
                type = "诊断处方";
                tvChange.setChecked(true);
                tvHisaid.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_submit:
                String data = tvDate.getText().toString();
                String et_hos = etPer.getText().toString();
                if (("").equals(data) || ("").equals(et_hos)) {
                    Toast.makeText(AidsManagerActivity.this, "请检查输入内容是否有空值", Toast.LENGTH_LONG).show();
                } else {
                    String url = Constants.SERVER_URL + "CaseImageUploadServlet";
                    Recommend recommend = new Recommend();
                    recommend.setImage(et_hos);
                    recommend.setData(data);
                    recommend.setContext(type + "");
                    recommend.setName(LeanchatUser.getCurrentUser().getUsername() + "");
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < listitem.size(); i++) {
                        jsonArray.put(new File(listitem.get(i).getPath()));
                    }
                    recommend.setNewPrice(jsonArray.toString());
                    MyHttpUtils.handData(handler, 150, url, recommend);
                }
                break;
        }
    }


}
