package com.cwx.max.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwx.max.R;

public class CollectionFragment extends Fragment {
	protected static View containerView;

	private void initView(View v) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		if (containerView == null) {
			v = inflater.inflate(R.layout.fragment_collection, container, false);
			containerView = v;
		} else {
			((ViewGroup) containerView.getParent()).removeView(containerView);
			v = containerView;
			initView(v);
			return v;
		}

		initView(v);

		return v;
	}
}
