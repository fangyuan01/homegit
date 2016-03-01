package com.cwx.max.bean;

public class UserMatch {
	private String matchUrl;
	private String heroName;
	private String heroImg;
	private String matchId;
	private String matchType;
	private String matchTime;
	private String matchResult;
	private String matchKDA;
	private String matchLevel;

	public UserMatch() {
	}

	public UserMatch(String matchUrl, String heroName, String heroImg, String matchId, String matchType, String matchTime,
			String matchResult, String matchKDA, String matchLevel) {
		this.matchUrl = matchUrl;
		this.heroName = heroName;
		this.heroImg = heroImg;
		this.matchId = matchId;
		this.matchType = matchType;
		this.matchTime = matchTime;
		this.matchResult = matchResult;
		this.matchKDA = matchKDA;
		this.matchLevel = matchLevel;
	}

	public String getMatchUrl() {
		return matchUrl;
	}

	public String getHeroName() {
		return heroName;
	}

	public String getHeroImg() {
		return heroImg;
	}

	public String getMatchId() {
		return matchId;
	}

	public String getMatchType() {
		return matchType;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public String getMatchKDA() {
		return matchKDA;
	}

	public String getMatchLevel() {
		return matchLevel;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public void setHeroImg(String heroImg) {
		this.heroImg = heroImg;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public void setMatchKDA(String matchKDA) {
		this.matchKDA = matchKDA;
	}

	public void setMatchLevel(String matchLevel) {
		this.matchLevel = matchLevel;
	}

}
