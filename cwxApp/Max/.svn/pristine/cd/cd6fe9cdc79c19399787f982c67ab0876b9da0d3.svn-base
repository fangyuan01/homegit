package com.cwx.max.action;

import android.os.Handler;

import com.cwx.max.bean.UserDetail;
import com.cwx.max.utils.HtmlReader;

public class UserDetailAction extends BaseHtmlAction {

	private OnGetDetailListener listener;

	public UserDetailAction(String urls, Handler handler) {
		super(urls, handler);
	}

	public void start(OnGetDetailListener lis) {
		listener = lis;
		startAction(new OnActionFinishListener() {

			@Override
			public void onFinish(String result) {
				if (listener != null) {
					listener.onGetDetail(HtmlReader.getUserDetailFromHtml(result));
				}
			}
		});
	}

	public interface OnGetDetailListener {
		public void onGetDetail(UserDetail detail);
	}
}
