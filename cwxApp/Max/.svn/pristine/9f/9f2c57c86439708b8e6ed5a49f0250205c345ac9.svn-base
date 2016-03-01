package com.cwx.max.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cwx.max.bean.RankUser;
import com.cwx.max.bean.User;
import com.cwx.max.bean.UserDetail;
import com.cwx.max.bean.UserHero;
import com.cwx.max.bean.UserMatch;
import com.cwx.max.bean.UserRecord;

public class HtmlReader {

	public static UserDetail getUserDetailFromHtml(String htmls) {
		UserDetail detail = new UserDetail();
		Document doc = Jsoup.parse(htmls);
		Elements links = doc.select("div");
		for (Element link : links) {
			try {
				if (link.attr("class").equals("iconTooltip")) {
					for (Element item : link.select("img")) {
						if (item.attr("class").equals("iconTooltip_img")) {
							detail.setUserImg(item.attr("src"));
						}
					}
					for (Element item : link.select("span")) {
						if (item.attr("class").equals("gold-color")) {
							detail.setName(item.text());
						} else {
							detail.setCreateTime(item.text());
						}
					}
					String id = link.text();
					id = id.substring(id.indexOf("ID"), id.indexOf("注册时间:")).trim();
					detail.setId(id);
					for (Element item : link.select("div")) {
						if (!item.attr("class").equals("iconTooltip")) {
							for (Element itemit : item.select("img")) {
								detail.setUserCountry(itemit.attr("src"));
							}
						}
					}
				}
			} catch (Exception e) {
			}
		}

		Elements list = links.select("tbody");

		Element ususally = list.get(0);
		Element match = list.get(1);
		Element record = list.get(2);

		ArrayList<UserHero> heros = new ArrayList<UserHero>();

		for (Element item : ususally.select("tr")) {
			try {
				Elements tds = item.select("td");
				UserHero hero = new UserHero();
				String url = item.attr("onclick");
				hero.setUrl(item.attr("onclick").substring(7, url.length() - 3));
				hero.setName(tds.get(0).text());
				hero.setImg(tds.get(0).select("img").attr("src"));
				hero.setMatchTimes(tds.get(1).text());
				hero.setWinRate(tds.get(2).text());
				hero.setKDA(tds.get(3).select("div").get(0).ownText());
				hero.setKDAInfo(tds.get(3).select("div").get(1).ownText());
				hero.setNomalKDA(tds.get(4).text());
				heros.add(hero);
			} catch (Exception e) {
			}
		}

		ArrayList<UserMatch> matchs = new ArrayList<UserMatch>();

		for (Element item : match.select("tr")) {
			try {
				Elements tds = item.select("td");
				UserMatch userMatch = new UserMatch();
				String url = item.attr("onclick");
				userMatch.setMatchUrl(item.attr("onclick").substring(7, url.length() - 3));
				userMatch.setHeroName(tds.get(0).text());
				userMatch.setHeroImg(tds.get(0).select("img").attr("src"));
				userMatch.setMatchId(tds.get(1).select("a").get(0).ownText());
				userMatch.setMatchType(tds.get(1).select("font").get(0).text());
				userMatch.setMatchTime(tds.get(2).text());
				userMatch.setMatchResult(tds.get(3).text());
				userMatch.setMatchKDA(tds.get(4).text());
				userMatch.setMatchLevel(tds.get(5).text());
				matchs.add(userMatch);
			} catch (Exception e) {
			}
		}

		ArrayList<UserRecord> records = new ArrayList<UserRecord>();
		for (Element item : record.select("tr")) {
			try {
				Elements tds = item.select("td");
				UserRecord userRecord = new UserRecord();
				String url = item.attr("onclick");
				userRecord.setMatchUrl(item.attr("onclick").substring(7, url.length() - 3));
				userRecord.setRecordType(tds.get(0).text());
				userRecord.setMatchId(tds.get(1).text());
				userRecord.setMatchResult(tds.get(2).text());
				userRecord.setMatchHeroImg(tds.get(3).select("img").get(0).attr("src"));
				userRecord.setMatchHeroName(tds.get(3).text());
				userRecord.setRecordNumber(tds.get(4).text());
				records.add(userRecord);
			} catch (Exception e) {
			}
		}

		detail.setUsuallyHeros(heros);
		detail.setLateMatchs(matchs);
		detail.setUserRecords(records);

		return detail;
	}

	public static boolean isUserDetail(String htmls) {
		Document doc = Jsoup.parse(htmls);
		Elements links = doc.select("ul").select("a");
		for (Element link : links) {
			if (link.attr("href").equals("/player/home/")) {
				if (link.text().equals("我的主页")) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<RankUser> getRankUserListFromHtml(String htmls) {
		ArrayList<RankUser> list = new ArrayList<RankUser>();

		if (isUserDetail(htmls)) {

		}

		if (StringUtil.isNullOrEmpty(htmls)) {
			System.out.println("html is null");
			return list;
		}

		Document doc = Jsoup.parse(htmls);
		Elements links = doc.select("table").select("tbody").select("tr");

		for (Element link : links) {
			try {
				Elements its = link.select("td");
				RankUser rankUser = new RankUser();
				rankUser.setRank(its.get(0).text());
				rankUser.setPoint(its.get(2).text());

				for (Element item : its.get(3).select("img")) {
					rankUser.setCountryImg(item.attr("src"));
				}

				for (Element item : its.get(3).select("font")) {
					rankUser.setName(item.text());
				}

				rankUser.setTeam(its.get(4).text());
				list.add(rankUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<User> getUserListFromFamousHtml(String htmls) {
		ArrayList<User> list = new ArrayList<User>();

		if (StringUtil.isNullOrEmpty(htmls)) {
			System.out.println("html is null");
			return list;
		}

		Document doc = Jsoup.parse(htmls);
		Elements links = doc.select("table").select("tbody").select("tr");

		for (Element link : links) {
			try {
				User user = new User();
				user.setUserUrl(link.select("a").get(0).attr("href"));
				user.setUserName(link.select("a").get(0).text());
				for (Element item : link.select("img")) {
					user.setUserImg(item.attr("src"));
				}
				user.setUserID("ID: " + link.select("td").get(2).text());
				list.add(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static List<User> getUserListFromSearchHtml(String htmls) {
		ArrayList<User> list = new ArrayList<User>();

		if (StringUtil.isNullOrEmpty(htmls)) {
			System.out.println("html is null");
			return list;
		}

		Document doc = Jsoup.parse(htmls);
		Elements links = doc.select("table").select("tr").select("a");

		for (Element link : links) {
			User user = new User();
			user.setUserUrl(link.attr("href"));
			user.setUserName(link.ownText());
			for (Element item : link.select("img")) {
				user.setUserImg(item.attr("src"));
			}
			for (Element item : link.select("div")) {
				user.setUserID(item.text());
			}
			if (user.getUserUrl().startsWith("/player/detail/")) {
				list.add(user);
			}
		}
		return list;
	}
}
