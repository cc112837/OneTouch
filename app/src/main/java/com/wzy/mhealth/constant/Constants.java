package com.wzy.mhealth.constant;


import com.wzy.mhealth.utils.Util;

public class Constants {
	public final static String STEP = "step";
	public final static String STEPDATE="data";
	public final static boolean IS_DEBUG = true;
	public final static String SERVER_URL = "http://117.34.105.29:8209/mhealth/servlet/";
	public final static String PATH =  Util.getInstance().getExtPath()+"/xmpp";
	public final static String SAVE_IMG_PATH = PATH + "/images";//设置保存图片文件的路径
	public final static String SAVE_SOUND_PATH = PATH + "/sounds";//设置声音文件的路径
	
	public final static String SHARED_PREFERENCES = "openfile";
	public final static String LOGIN_CHECK = "check";
	public final static String LOGIN_ACCOUNT = "u1";
	public final static String LOGIN_PWD = "123";
	public static String USER_NAME = "";
	public static String icon="";


}
