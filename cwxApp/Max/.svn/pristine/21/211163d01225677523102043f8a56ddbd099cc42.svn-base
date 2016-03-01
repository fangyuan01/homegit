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
import com.cwx.max.action.RankAction;
import com.cwx.max.action.RankAction.OnGetListFinishListener;
import com.cwx.max.adapter.RankListAdapter;
import com.cwx.max.bean.RankUser;

public class RankFragment extends Fragment {
	protected static View containerView;

	private static boolean hasData = false;

	private ListView rankList;
	private RankListAdapter adapter;

	private void initView(View v) {
		rankList = (ListView) v.findViewById(R.id.rank_listview_ranklist);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		if (containerView == null) {
			v = inflater.inflate(R.layout.fragment_rank, container, false);
			containerView = v;
			hasData = false;
		} else {
			((ViewGroup) containerView.getParent()).removeView(containerView);
			v = containerView;
			initView(v);
			if (!hasData) {
				RankAction action = new RankAction(new Handler());
				action.start(new OnGetListFinishListener() {

					@Override
					public void onGetFinish(List<RankUser> data) {
						if (data.size() > 0) {
							hasData = true;
						}
						if (adapter == null) {
							adapter = new RankListAdapter(getActivity());
							rankList.setAdapter(adapter);
						}
						adapter.setData(data);
						adapter.notifyDataSetChanged();
					}
				});
			}

			return v;
		}

		initView(v);
		adapter = new RankListAdapter(getActivity());
		rankList.setAdapter(adapter);
		if (!hasData) {
			RankAction action = new RankAction(new Handler());
			action.start(new OnGetListFinishListener() {

				@Override
				public void onGetFinish(List<RankUser> data) {
					if (data.size() > 0) {
						hasData = true;
					}
					if (adapter == null) {
						adapter = new RankListAdapter(getActivity());
						rankList.setAdapter(adapter);
					}
					adapter.setData(data);
					adapter.notifyDataSetChanged();
				}
			});
		}

		return v;
	}
}
