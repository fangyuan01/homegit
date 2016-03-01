package com.hbueschoolhelper.utils;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class Location {
	private LocationClient mLocationClient;
	private LocationClientOption option;

	MyLocationListener listener;

	LocationObtainedListener obtainedListener;

	public Location(Context context) {

		mLocationClient = new LocationClient(context.getApplicationContext());
		listener = new MyLocationListener();
		option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");
		option.setCoorType("bd09ll");
		option.setScanSpan(5000);
		option.disableCache(true);
		option.setPoiNumber(5);
		option.setPoiDistance(1000);
		option.setPoiExtraInfo(true);
		option.setPriority(LocationClientOption.GpsFirst);
		mLocationClient.setLocOption(option);

		mLocationClient.registerLocationListener(listener);
	}

	public void start() {
		mLocationClient.start();
		mLocationClient.requestLocation();
	}

	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				if (!mLocationClient.isStarted()) {
					mLocationClient.start();
				}
				mLocationClient.requestLocation();
				return;
			}
			LocationEvent event = new LocationEvent();
			event.x = location.getLongitude();
			event.y = location.getLatitude();
			event.address = location.getAddrStr();
			event.city = location.getCity();
			event.district = location.getDistrict();
			event.street = location.getStreet();

			System.out.println("x:" + location.getLongitude());
			System.out.println("y:" + location.getLatitude());
			System.out.println("city:" + location.getCity());
			System.out.println("district:" + location.getDistrict());
			System.out.println("street:" + location.getStreet());

			obtainedListener.locationObtained(event);

			mLocationClient.stop();
		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	public void setLocatonObtainedListener(LocationObtainedListener listener) {
		this.obtainedListener = listener;
	}

	public class LocationEvent {
		public double x = -1;
		public double y = -1;
		public String address;
		public String city;
		public String district;
		public String street;
	}

	public interface LocationObtainedListener {
		public void locationObtained(LocationEvent event);
	}
}
