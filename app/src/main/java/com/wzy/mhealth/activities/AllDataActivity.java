package com.wzy.mhealth.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wzy.mhealth.R;
import com.wzy.mhealth.db.MySqliteOpenHelper;
import com.wzy.mhealth.inter.FinalValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllDataActivity extends AppCompatActivity {
    private ImageView leftBtn_back;
    private ListView lv_show;
    List<Map<String, String>> list;//数据源
    private MySqliteOpenHelper helper;// 数据库连接对象
    private SQLiteDatabase database;// 数据库
    private Cursor cursor;// 游标
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        leftBtn_back=(ImageView) findViewById(R.id.leftBtn_back);
        leftBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initdata();//初始化数据库
        aboutList();//关于listview的一些操作
        if (!cursor.moveToFirst()) {
        } else {
            dataDB();
        }

    }

    private void dataDB() {
        cursor = database.query(FinalValue.TB_STEP, null, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            String content = cursor.getString(cursor.getColumnIndex("title"));
            String day = cursor.getString(cursor.getColumnIndex("day"));//2016-07-13
            String time = cursor.getString(cursor.getColumnIndex("time"));
            HashMap<String, String> map = new HashMap<>();
            map.put("title", content);
            map.put("day", day);
            map.put("time", time);
            list.add(0, map);
        }
        adapter.notifyDataSetChanged();
    }

    private void initdata() {
        helper = new MySqliteOpenHelper(AllDataActivity.this, FinalValue.DB_NAME, null,
                FinalValue.DB_VERSION);
        database = helper.getReadableDatabase();
        cursor = database.query(FinalValue.TB_STEP, null, null, null, null,
                null, null);
    }

    private void aboutList() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        adapter = new SimpleAdapter(this, getData(),
                R.layout.step_item,
                new String[]{"day", "title"},
                new int[]{R.id.tv_time, R.id.tv_step});
        lv_show.setAdapter(adapter);
    }

    private List<Map<String, String>> getData() {
        list = new ArrayList<>();
        return list;
    }

}
