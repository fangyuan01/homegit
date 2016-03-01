package com.cwx.max;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.cwx.max.action.BaseHtmlAction;
import com.cwx.max.fragment.CollectionFragment;
import com.cwx.max.fragment.FamousFragment;
import com.cwx.max.fragment.FragmentAgent;
import com.cwx.max.fragment.RankFragment;
import com.cwx.max.fragment.SearchFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private FragmentManager fragmentManager;

	private ProgressBar progressBar;

	private View v0;
	private View v1;
	private View v2;
	private View v3;
	private View v4;
	private View v5;

	private View lastVa;
	private View lastVb;
	private RelativeLayout lastBtn;

	private RelativeLayout btnCollection;
	private RelativeLayout btnSeach;
	private RelativeLayout btnFamous;
	private RelativeLayout btnRank;
	private RelativeLayout btnMore;

	public void setProgressbar(boolean isShow) {
		if (isShow) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BaseHtmlAction.initActivity(this);

		v0 = findViewById(R.id.main_view0);
		v1 = findViewById(R.id.main_view1);
		v2 = findViewById(R.id.main_view2);
		v3 = findViewById(R.id.main_view3);
		v4 = findViewById(R.id.main_view4);
		v5 = findViewById(R.id.main_view5);

		progressBar = (ProgressBar) findViewById(R.id.main_progressbar_titleprogressbar);

		btnCollection = (RelativeLayout) findViewById(R.id.main_layout_collectionbutton);
		btnSeach = (RelativeLayout) findViewById(R.id.main_layout_searchbutton);
		btnFamous = (RelativeLayout) findViewById(R.id.main_layout_famousbutton);
		btnRank = (RelativeLayout) findViewById(R.id.main_layout_rankbutton);
		btnMore = (RelativeLayout) findViewById(R.id.main_layout_morebutton);

		btnCollection.setOnClickListener(this);
		btnSeach.setOnClickListener(this);
		btnFamous.setOnClickListener(this);
		btnRank.setOnClickListener(this);
		btnMore.setOnClickListener(this);

		fragmentManager = getSupportFragmentManager();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		FragmentAgent.clearFragments();
	}

	@Override
	public void onClick(View v) {

		String TAG = "FragmentTag";
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment fragment = null;

		if (lastVa != null) {
			lastVa.setBackgroundColor(getResources().getColor(R.color.invisable));
		}
		if (lastVb != null) {
			lastVb.setBackgroundColor(getResources().getColor(R.color.invisable));
		}
		if (lastBtn != null) {
			lastBtn.setBackgroundColor(getResources().getColor(R.color.invisable));
		}
		switch (v.getId()) {
		case R.id.main_layout_collectionbutton:
			v0.setBackgroundColor(getResources().getColor(R.color.c282828));
			v1.setBackgroundResource(R.drawable.d_path01);
			lastVa = v0;
			lastVb = v1;
			fragment = new CollectionFragment();
			break;
		case R.id.main_layout_searchbutton:
			v1.setBackgroundResource(R.drawable.d_path02);
			v2.setBackgroundResource(R.drawable.d_path01);
			lastVa = v1;
			lastVb = v2;
			fragment = new SearchFragment();
			break;
		case R.id.main_layout_famousbutton:
			v2.setBackgroundResource(R.drawable.d_path02);
			v3.setBackgroundResource(R.drawable.d_path01);
			lastVa = v2;
			lastVb = v3;
			fragment = new FamousFragment();
			break;
		case R.id.main_layout_rankbutton:
			v3.setBackgroundResource(R.drawable.d_path02);
			v4.setBackgroundResource(R.drawable.d_path01);
			lastVa = v3;
			lastVb = v4;
			fragment = new RankFragment();
			break;
		case R.id.main_layout_morebutton:
			v4.setBackgroundResource(R.drawable.d_path02);
			v5.setBackgroundColor(getResources().getColor(R.color.c282828));
			lastVa = v4;
			lastVb = v5;
			break;
		}
		v.setBackgroundColor(getResources().getColor(R.color.c282828));
		lastBtn = (RelativeLayout) v;
		if (fragment != null) {
			if (fragmentManager.findFragmentByTag(TAG) != null) {
				transaction.replace(R.id.main_layout_pagearea, fragment);
			} else {
				transaction.add(R.id.main_layout_pagearea, fragment, TAG);
			}
		}
		transaction.commit();
	}
}
