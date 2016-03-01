package com.cwx.max.action;

import java.net.URLEncoder;
import java.util.List;

import android.os.Handler;

import com.cwx.max.bean.User;
import com.cwx.max.utils.HtmlReader;

public class SearchUserAction extends BaseHtmlAction {

	private OnSearhFinishListener listener;

	public SearchUserAction(String searchStr, Handler handler) throws Exception {
		super("http://dotamax.com/search/?q=" + URLEncoder.encode(searchStr, "UTF-8"), handler);
	}

	public void start(OnSearhFinishListener lis) {
		listener = lis;
		startAction(new OnActionFinishListener() {

			@Override
			public void onFinish(String result) {
				if (listener != null) {
					if (HtmlReader.isUserDetail(result)) {
						listener.onSearchFinish(HtmlReader.getUserListFromSearchHtml(result), result);
					} else {
						listener.onSearchFinish(HtmlReader.getUserListFromSearchHtml(result), null);
					}
				}
			}
		});
	}

	public interface OnSearhFinishListener {
		public void onSearchFinish(List<User> data, String detail);
	}
}
