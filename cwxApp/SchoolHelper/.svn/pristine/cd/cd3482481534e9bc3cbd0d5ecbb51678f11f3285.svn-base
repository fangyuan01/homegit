package com.hbueschoolhelper.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeatherUtils {
	public static void getWuHanWeather(final onGetWeatherListener lis) {
		Thread thread = new Thread() {
			public void run() {
				String url = "http://m.weather.com.cn/data/101200101.html";
				try {
					URL remoteUrl = new URL(url);
					URLConnection connection = remoteUrl.openConnection();
					connection.setConnectTimeout(1500);
					System.out.println(url);
					BufferedReader instream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String buffT = null;
					String bufferStr = "";
					while ((buffT = instream.readLine()) != null) {
						if (buffT.contains("<html")) {
							return;
						}
						bufferStr += buffT;
					}
					instream.close();
					System.out.println(bufferStr);
					JSONObject jobj = JSON.parseObject(bufferStr);
					Weather weather = JSONObject.toJavaObject(jobj, Weather.class);
					Thread.sleep(2000);
					lis.onGet(weather.getWeatherinfo());
					return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		};
		thread.start();
	}

	public interface onGetWeatherListener {
		public void onGet(WeatherInfo info);
	}
}
