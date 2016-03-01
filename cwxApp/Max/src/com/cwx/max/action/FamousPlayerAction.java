package com.cwx.max.action;

import java.util.List;

import android.os.Handler;

import com.cwx.max.bean.User;
import com.cwx.max.utils.HtmlReader;

public class FamousPlayerAction extends BaseHtmlAction {

	private OnGetListFinishListener listener;

	public FamousPlayerAction(Handler handler) {
		super("http://dotamax.com/player/", handler);
	}

	public void start(OnGetListFinishListener lis) {
		listener = lis;
		startAction(new OnActionFinishListener() {

			@Override
			public void onFinish(String result) {
				if (listener != null) {
					listener.onGetFinish(HtmlReader
							.getUserListFromFamousHtml(result));
				}
			}
		});
	}

	public interface OnGetListFinishListener {
		public void onGetFinish(List<User> data);
	}

}
