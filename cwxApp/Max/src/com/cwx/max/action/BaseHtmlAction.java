package com.cwx.max.action;

import android.app.Activity;
import android.os.Handler;

import com.cwx.max.MainActivity;
import com.cwx.max.utils.HttpUtils;

public class BaseHtmlAction implements Runnable {

	private static Activity activity;

	private Handler mHandler;
	private String url;
	private OnActionFinishListener listener;

	public static void initActivity(Activity context) {
		activity = context;
	}

	public BaseHtmlAction(String urls, Handler handler) {
		mHandler = handler;
		url = urls;
	}

	protected void startAction(OnActionFinishListener lis) {
		listener = lis;
		Thread thread = new Thread(this);
		thread.start();
		if (activity != null) {
			((MainActivity) activity).setProgressbar(true);
		}
	}

	private String result;

	@Override
	public void run() {
		try {
			result = HttpUtils.testGetHtml(url);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
		if (listener != null) {
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					((MainActivity) activity).setProgressbar(false);
					listener.onFinish(result);
				}
			});
		}
	}

	public interface OnActionFinishListener {
		public void onFinish(String result);
	}
}
