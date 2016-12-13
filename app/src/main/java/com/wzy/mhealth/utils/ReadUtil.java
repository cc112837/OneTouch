package com.wzy.mhealth.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/1/12.
 */
public class ReadUtil {
    /**
     * 从raw中读取txt
     */
    public  static String readFromRaw(Context context,int content) {
        String text = null;
        try {
            InputStream is = context.getResources().openRawResource(content);
            text = readTextFromSDcard(is);
            return text;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
