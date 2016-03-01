package com.cwx.max.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cwx.max.R;
import com.cwx.max.UserDetailActivity;
import com.cwx.max.action.SearchUserAction;
import com.cwx.max.action.SearchUserAction.OnSearhFinishListener;
import com.cwx.max.adapter.SearchListAdapter;
import com.cwx.max.bean.User;
import com.cwx.max.utils.StringUtil;
import com.cwx.max.utils.ViewTools;

public class SearchFragment extends Fragment {

	protected static View containerView;

	private ListView userList;
	private SearchListAdapter userListAdapter;

	private RelativeLayout search;
	private EditText searchText;

	private void initView(View v) {
		userList = (ListView) v.findViewById(R.id.search_listview_searchlist);
		search = (RelativeLayout) v.findViewById(R.id.search_layout_btnconfirmsearch);
		searchText = (EditText) v.findViewById(R.id.search_edittext_seatchtext);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		if (containerView == null) {
			v = inflater.inflate(R.layout.fragment_search, container, false);
			containerView = v;
		} else {
			containerView.requestFocus();
			((ViewGroup) containerView.getParent()).removeView(containerView);
			v = containerView;
			initView(v);
			searchText.clearFocus();
			return v;
		}

		initView(v);

		userListAdapter = new SearchListAdapter(getActivity());
		userList.setAdapter(userListAdapter);
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ViewTools.hideSoftInputFromWindow(searchText);
				if (StringUtil.isNullOrEmpty(searchText.getText().toString())) {
					Toast.makeText(v.getContext(), "请输入搜索的内容", Toast.LENGTH_SHORT).show();
					return;
				}
				try {
					SearchUserAction action = new SearchUserAction(searchText.getText().toString(), new Handler());
					action.start(new OnSearhFinishListener() {

						@Override
						public void onSearchFinish(List<User> data, String detail) {
							if (detail == null) {
								userListAdapter.setData(data);
								userListAdapter.notifyDataSetChanged();
							} else {
								Intent intent = new Intent(getActivity(), UserDetailActivity.class);
								intent.putExtra("detail", detail);
								getActivity().startActivity(intent);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		return v;
	}
}
