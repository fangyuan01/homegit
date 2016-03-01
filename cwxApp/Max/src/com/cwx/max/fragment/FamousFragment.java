package com.cwx.max.fragment;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cwx.max.R;
import com.cwx.max.action.FamousPlayerAction;
import com.cwx.max.action.FamousPlayerAction.OnGetListFinishListener;
import com.cwx.max.adapter.SearchListAdapter;
import com.cwx.max.bean.User;

public class FamousFragment extends Fragment {
	protected static View containerView;

	private static boolean hasData = false;

	private ListView famousList;
	private SearchListAdapter listAdapter;

	private void initView(View v) {
		famousList = (ListView) v.findViewById(R.id.famous_listview_famouslist);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		if (containerView == null) {
			v = inflater.inflate(R.layout.fragment_famous, container, false);
			containerView = v;
			hasData = false;
		} else {
			((ViewGroup) containerView.getParent()).removeView(containerView);
			v = containerView;
			initView(v);
			if (!hasData) {
				FamousPlayerAction action = new FamousPlayerAction(new Handler());
				action.start(new OnGetListFinishListener() {

					@Override
					public void onGetFinish(List<User> data) {
						if (listAdapter == null) {
							listAdapter = new SearchListAdapter(containerView.getContext());
							famousList.setAdapter(listAdapter);
						}
						if (data.size() > 0) {
							hasData = true;
						}
						listAdapter.setData(data);
						listAdapter.notifyDataSetChanged();
					}
				});
			}
			return v;
		}

		initView(v);

		listAdapter = new SearchListAdapter(getActivity());
		famousList.setAdapter(listAdapter);

		if (!hasData) {
			FamousPlayerAction action = new FamousPlayerAction(new Handler());
			action.start(new OnGetListFinishListener() {

				@Override
				public void onGetFinish(List<User> data) {
					if (listAdapter == null) {
						listAdapter = new SearchListAdapter(containerView.getContext());
						famousList.setAdapter(listAdapter);
					}
					if (data.size() > 0) {
						hasData = true;
					}
					listAdapter.setData(data);
					listAdapter.notifyDataSetChanged();
				}
			});
		}

		return v;
	}
}
