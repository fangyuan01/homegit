package com.hbueschoolhelper.utils;

public class Weather {
	public WeatherInfo weatherinfo;

	public WeatherInfo getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(WeatherInfo weatherinfo) {
		this.weatherinfo = weatherinfo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return weatherinfo.toString();
	}

}
