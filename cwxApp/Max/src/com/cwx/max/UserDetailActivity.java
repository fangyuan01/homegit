package com.cwx.max;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwx.max.action.UserDetailAction;
import com.cwx.max.action.UserDetailAction.OnGetDetailListener;
import com.cwx.max.adapter.DetailSectionedAdapter;
import com.cwx.max.bean.UserDetail;
import com.cwx.max.utils.HtmlReader;
import com.cwx.max.utils.ViewTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UserDetailActivity extends Activity {

	private TextView userName;
	private RelativeLayout backButton;

	private ProgressBar progressBar;

	private PinnedHeaderListView listView;
	private DetailSectionedAdapter adapter;

	private ImageView userImg;
	private TextView userNameInfo;
	private TextView userId;
	private TextView userTime;
	private ImageView userCountry;

	private DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdetail);

		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.d_noneimg).showImageForEmptyUri(R.drawable.d_noneimg)
				.showImageOnFail(R.drawable.d_noneimg).cacheInMemory(true).cacheOnDisc(true).build();

		String name = getIntent().getStringExtra("name");
		String url = "http://dotamax.com" + getIntent().getStringExtra("url");

		String detailHtml = getIntent().getStringExtra("detail");

		userImg = (ImageView) findViewById(R.id.userdetail_imageview_userimg);
		userNameInfo = (TextView) findViewById(R.id.userdetail_textview_usernameinfo);
		userId = (TextView) findViewById(R.id.userdetail_textview_userid);
		userTime = (TextView) findViewById(R.id.userdetail_textview_register_time);
		userCountry = (ImageView) findViewById(R.id.userdetail_imageview_country);

		progressBar = (ProgressBar) findViewById(R.id.userdetail_progressbar_titleprogressbar);

		View head = new View(this);
		head.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, ViewTools.getPxFromDp(80, this)));

		listView = (PinnedHeaderListView) findViewById(R.id.userdetail_pinnedListView);
		listView.addHeaderView(head);
		adapter = new DetailSectionedAdapter();
		listView.setAdapter(adapter);

		userName = (TextView) findViewById(R.id.userdetail_textview_username);
		userName.setText(name);
		backButton = (RelativeLayout) findViewById(R.id.userdetail_layout_back);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		if (detailHtml == null) {
			UserDetailAction action = new UserDetailAction(url, new Handler());
			progressBar.setVisibility(View.VISIBLE);
			action.start(new OnGetDetailListener() {

				@Override
				public void onGetDetail(UserDetail detail) {
					setDetail(detail);
					progressBar.setVisibility(View.INVISIBLE);
				}
			});
		} else {
			setDetail(HtmlReader.getUserDetailFromHtml(detailHtml));
			progressBar.setVisibility(View.INVISIBLE);
		}
	}

	public void setDetail(UserDetail detail) {
		ImageLoader.getInstance().displayImage(detail.getUserImg(), userImg, options);
		ImageLoader.getInstance().displayImage(detail.getUserCountry(), userCountry, options);
		userNameInfo.setText(detail.getName());
		userId.setText(detail.getId());
		userTime.setText("注册时间:" + detail.getCreateTime());

		adapter.setHeros(detail.getUsuallyHeros());
		adapter.setMatchs(detail.getLateMatchs());
		adapter.setRecords(detail.getUserRecords());
		adapter.notifyDataSetChanged();
	}
}
