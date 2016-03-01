package com.cwx.max.bean;

public class UserRecord {
	private String recordType;
	private String matchUrl;
	private String matchId;
	private String matchResult;
	private String matchHeroImg;
	private String matchHeroName;
	private String recordNumber;

	public UserRecord() {
	}

	public UserRecord(String recordType, String matchUrl, String matchId, String matchResult, String matchHeroImg, String matchHeroName,
			String recordNumber) {
		this.recordType = recordType;
		this.matchUrl = matchUrl;
		this.matchId = matchId;
		this.matchResult = matchResult;
		this.matchHeroImg = matchHeroImg;
		this.matchHeroName = matchHeroName;
		this.recordNumber = recordNumber;
	}

	public String getRecordType() {
		return recordType;
	}

	public String getMatchUrl() {
		return matchUrl;
	}

	public String getMatchId() {
		return matchId;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public String getMatchHeroImg() {
		return matchHeroImg;
	}

	public String getMatchHeroName() {
		return matchHeroName;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public void setMatchHeroImg(String matchHeroImg) {
		this.matchHeroImg = matchHeroImg;
	}

	public void setMatchHeroName(String matchHeroName) {
		this.matchHeroName = matchHeroName;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

}
