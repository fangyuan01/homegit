package com.hbue.homeworkapp;

import java.util.UUID;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.hbue.homeworkapp.dao.DaoMaster;
import com.hbue.homeworkapp.dao.DaoMaster.OpenHelper;
import com.hbue.homeworkapp.dao.DaoSession;
import com.hbue.homeworkapp.service.MainURLs;
import com.hbue.homeworkapp.service.XmppUserUtils;
import com.hbue.homeworkapp.utils.XmppManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainApplication extends Application {

	private static DaoMaster daoMaster;
	private static DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();

		Thread thread = new Thread() {
			@Override
			public void run() {
				super.run();
				System.out.println("create");
				XmppUserUtils.Account = "hbue_"
						+ getDeviceId(getApplicationContext());
				XmppManager.createAccount(XmppUserUtils.Account,
						XmppUserUtils.getXmppPassword(), MainURLs.Jabber_host,
						MainURLs.Jabber_port);
			}
		};
		thread.start();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 获取机器的唯一ID
	 * 
	 * @return
	 */
	public static String getDeviceId(Context context) {
		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(((long) tmDevice.hashCode() << 32)
				| tmSerial.hashCode(), androidId.hashCode());
		String uniqueId = deviceUuid.toString().replace("-", "");
		return uniqueId;
	}

	public static DaoMaster getDaoMaster(Context context) {

		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, "data",
					null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}
}
