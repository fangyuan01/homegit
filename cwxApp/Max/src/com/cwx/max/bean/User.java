package com.cwx.max.bean;

public class User {
	private String userID;
	private String userName;
	private String userImg;
	private String userUrl;

	public User(String userID, String userName, String userImg, String userUrl) {
		this.userID = userID;
		this.userName = userName;
		this.userImg = userImg;
		this.userUrl = userUrl;
	}

	public User() {
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

}
