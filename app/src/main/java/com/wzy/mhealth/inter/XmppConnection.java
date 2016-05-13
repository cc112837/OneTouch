package com.wzy.mhealth.inter;

import android.util.Log;

import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Bingli2;
import com.wzy.mhealth.model.DoctorEntity;
import com.wzy.mhealth.model.Friend;
import com.wzy.mhealth.model.GuaHao;
import com.wzy.mhealth.model.GuahaoDoctorEntity;
import com.wzy.mhealth.model.HospitalEntity;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.model.YuyueDoctor;
import com.wzy.mhealth.utils.DateUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class XmppConnection {
	public Handler handler;
	private static XmppConnection xmppConnection;
	private static List<Friend> friendList = new ArrayList<>();
	private static List<DoctorEntity> doctorList = new ArrayList<>();
	private static List<GuahaoDoctorEntity> guahaoDoctorList = new ArrayList<>();
	private static List<GuaHao> guahaoList = new ArrayList<>();
	private static List<HospitalEntity> hospitalList = new ArrayList<>();

	public static XmppConnection getInstance() {
		if (xmppConnection == null) {
			xmppConnection = new XmppConnection();
		}
		return xmppConnection;
	}

	public List<Friend> getFriendList() {
		return friendList;
	}
	public List<Friend> getFriends() {
		friendList.clear();
		return friendList;
	}

	public List<Friend> getFriendBothList() {
		return null;
	}


	public List<DoctorEntity> getDoctorListBykeshi(final String keshi) {
		Thread th = new Thread(){
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/DoctorServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("firstdepId", keshi));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					// request.setEntity(new UrlEncodedFormEntity());
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						doctorList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);

							DoctorEntity doctor = new DoctorEntity();
							doctor.setDactroUsername(objJson.getString("doctorId"));
							doctor.setDoctorObjectId(objJson.getString("objectId"));
							doctor.setName(objJson.getString("doctorName"));
							doctor.setSpecialization(objJson.getString("specialization"));
							doctor.setFirstdepName(objJson.getString("firstdep"));
							doctor.setHospitalName(objJson.getString("hospital"));
							doctor.setDoctorTitle(objJson.getString("doctorTitle"));
//							doctor.setImage(objJson.getImage("image"));
							doctor.setIntroduction(objJson.getString("introduction"));
							doctor.setPrice_add(objJson.getString("price_add"));
							doctor.setPrice_phone(objJson.getString("price_phone"));
							doctor.setPrice_picture(objJson.getString("price_picture"));
							doctor.setPrice_private(objJson.getString("price_private"));
							doctor.setPrice_vedio(objJson.getString("price_vedio"));
							doctor.setRecommend(objJson.getString("recommend"));
							doctor.setAttitude(objJson.getString("attitude"));
							doctor.setLevel(objJson.getString("level"));
							
							doctorList.add(doctor);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		th.start();
		while(th.isAlive()){
			
		}
		return doctorList;

	}
	
	public List<GuahaoDoctorEntity> getGuahaoDoctorList(final String hospital,final String keshi) {
		Thread th = new Thread(){
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/DoctorServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					if(keshi.equals("")||keshi == null){
					}
					else{
						info.add(new BasicNameValuePair("firstdepId", keshi));
					}
					info.add(new BasicNameValuePair("hospitalId", hospital));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					// request.setEntity(new UrlEncodedFormEntity());
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						guahaoDoctorList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);

							GuahaoDoctorEntity doctor = new GuahaoDoctorEntity();
							doctor.setName(objJson.getString("doctorName"));
							doctor.setBrief(objJson.getString("specialization"));
							doctor.setDactroId(objJson.getString("doctorId"));
							doctor.setZhicheng(objJson.getString("doctorTitle"));
//							doctor.setImage(objJson.getImage("image"));
							doctor.setJiezhenliang("12");
							doctor.setRegisterFee(objJson.getString("registerFee"));
							doctor.setPingfen(objJson.getString("attitude"));
						    doctor.setYouhao(objJson.getInt("doctorArrangeFuture7Num"));
							guahaoDoctorList.add(doctor);
						}
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
			
		}
		return guahaoDoctorList;

	}
	public List<GuaHao> getGuahaoList(final String doctorId) {
		Thread th = new Thread(){
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
//					HttpPost request = new HttpPost(
//							"http://123.57.191.21:8080/mhealth/servlet/RegisterServlet");
					//// TODO: 2016/2/17  
							HttpPost request = new HttpPost(
					"http://123.57.191.21:8080/mhealth/servlet/DoctorArrangeServlet");

					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("doctorId", doctorId));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						guahaoList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);

							GuaHao guahao = new GuaHao();
							guahao.setId(doctorId);
							guahao.setDate(DateUtil.str1ToStr1(objJson.getString("time")));
//							guahao.setMenzhen(objJson.getString("menzhen"));
//							guahao.setMon(objJson.getString("time_quantum"));
//							doctor.setImage(objJson.getImage("image"));
							guahao.setNumber(objJson.getInt("remainder"));
					    	guahao.setWeek(objJson.getString("timeQuantum"));
							guahaoList.add(guahao);
						}
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
			
		}
		return guahaoList;

	}
	public List<HospitalEntity> getHospitalList() {
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/HospitalServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("hospital", "hospital"));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						hospitalList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);

							HospitalEntity hospital = new HospitalEntity();
							hospital.setName(objJson.getString("hospitalName"));
							hospital.setId(objJson.getString("hospitalId"));
							hospital.setgrade(objJson
									.getString("HospitalType"));
							hospital.setpingjia("患者评价");
							hospital.setyuyue("预约量");
							hospital.setImage(objJson.getString("imageUrl"));
							hospitalList.add(hospital);
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
			
		}
		return hospitalList;

	}	
	public List<YuyueDoctor> getOwnGuahaoInfo() {
		final List<YuyueDoctor> yuyueDoctorList = new ArrayList<YuyueDoctor>();
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/UserServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("userId", Constants.USER_NAME+"_u"));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						yuyueDoctorList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);
							YuyueDoctor yuyueDoctor = new YuyueDoctor();
							yuyueDoctor.setDoctorname(objJson.getString("doctorName"));
							yuyueDoctor.setId(objJson.getString("doctorId"));
							yuyueDoctor.setDate(DateUtil.str1ToStr1(objJson.getString("time")));
							yuyueDoctor.setDoctorTitle(objJson.getString("doctorTitle"));
							yuyueDoctor.setFirstdepName(objJson.getString("firstDepName"));
							yuyueDoctor.setHospitalName(objJson.getString("hospitalName"));
							yuyueDoctorList.add(yuyueDoctor);
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
			
		}
		return yuyueDoctorList;

	}
	public List<String> getOwnBingliInfo() {
		final List<String> string = new ArrayList<String>();
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/MedicalRecordServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("userId", Constants.USER_NAME+"_u"));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						string.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);
							string.add(objJson.getString("userName"));
							string.add(objJson.getString("sex"));
							string.add(objJson.getString("age"));
							string.add(objJson.getString("marrigement"));
							string.add(objJson.getString("birth"));
							string.add(objJson.getString("operation"));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
		}
		return string;
	}
	public List<UserEvaluation> getUserEvaluation(final String doctorId, final String number) {
		final List<UserEvaluation> userEvaluationList = new ArrayList<UserEvaluation>();
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/UserEvaluateServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("doctorId", doctorId));
					info.add(new BasicNameValuePair("number", number));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						Log.e("runuser", contact);
						userEvaluationList.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);
							UserEvaluation userEvaluation = new UserEvaluation();
							userEvaluation.setUserid(objJson.getString("userId"));
							userEvaluation.setDegree(objJson.getInt("degree"));
							userEvaluation.setComment(objJson.getString("comment"));
							userEvaluation.setTotalRecord(objJson.getInt("totalRecord"));
							userEvaluationList.add(userEvaluation);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
		}
		return userEvaluationList;
	}
	public List<Bingli2> getElectronicMedical() {
		final List<Bingli2> list = new ArrayList<Bingli2>();
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(
							"http://123.57.191.21:8080/mhealth/servlet/ElectronicMedicalRecordServlet");
					List<NameValuePair> info = new ArrayList<NameValuePair>();
					info.add(new BasicNameValuePair("userId", Constants.USER_NAME+"_u"));
					HttpParams params = client.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
					request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					if (response.getStatusLine().getStatusCode() == 200) {
						String contact = EntityUtils.toString(
								response.getEntity(), HTTP.UTF_8);
						list.clear();
						JSONArray reJson = new JSONArray(contact);
						for (int index = 0; index < reJson.length(); index++) {
							JSONObject objJson = reJson.getJSONObject(index);
							Bingli2 bingli = new Bingli2();
							bingli.setIsCertain(objJson.getString("diagnose"));
							bingli.setDetailOfDisease(objJson.getString("condition"));
							bingli.setTime(objJson.getString("diagnoseDate"));
							bingli.setDoctorName(objJson.getString("doctorName"));
							bingli.setTitle(objJson.getString("doctorTitle"));
							list.add(bingli);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		th.start();
		while(th.isAlive()){
		}
		return list;
	}
	
	
}
