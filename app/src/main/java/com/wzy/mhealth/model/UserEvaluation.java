package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

public class UserEvaluation implements Serializable {


	/**
	 * createTime : 2016-11-01 11:55:00.0
	 * evaluate : 哈哈哈
	 * satisfy : 满意
	 * userName : 18369956786
	 */

	private List<DataEntity> data;

	public void setData(List<DataEntity> data) {
		this.data = data;
	}

	public List<DataEntity> getData() {
		return data;
	}

	public static class DataEntity {
		private String createTime;
		private String evaluate;
		private String satisfy;
		private String userName;

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public void setEvaluate(String evaluate) {
			this.evaluate = evaluate;
		}

		public void setSatisfy(String satisfy) {
			this.satisfy = satisfy;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getCreateTime() {
			return createTime;
		}

		public String getEvaluate() {
			return evaluate;
		}

		public String getSatisfy() {
			return satisfy;
		}

		public String getUserName() {
			return userName;
		}
	}
}
