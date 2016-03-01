package com.cwx.max.action;

import java.util.List;

import android.os.Handler;

import com.cwx.max.bean.RankUser;
import com.cwx.max.utils.HtmlReader;

public class RankAction extends BaseHtmlAction {

	private OnGetListFinishListener listener;

	public RankAction(Handler handler) {
		super("http://dotamax.com/ladder/", handler);
	}

	public void start(OnGetListFinishListener lis) {
		listener = lis;
		startAction(new OnActionFinishListener() {

			@Override
			public void onFinish(String result) {
				if (listener != null) {
					listener.onGetFinish(HtmlReader.getRankUserListFromHtml(result));
				}
			}
		});
	}

	public interface OnGetListFinishListener {
		public void onGetFinish(List<RankUser> data);
	}
}
