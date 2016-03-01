package com.cwx.max.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.max.LookImgActivity;
import com.cwx.max.R;
import com.cwx.max.UserDetailActivity;
import com.cwx.max.bean.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SearchListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private DisplayImageOptions options;

	private ArrayList<User> data;

	public SearchListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		data = new ArrayList<User>();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.d_noneimg).showImageForEmptyUri(R.drawable.d_noneimg)
				.showImageOnFail(R.drawable.d_noneimg).cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setData(List<User> list) {
		data.clear();
		data.addAll(list);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_useritem, null);
			holder = new ViewHolder();
			holder.userImg = (ImageView) convertView.findViewById(R.id.useritem_imageview_userimg);
			holder.userName = (TextView) convertView.findViewById(R.id.useritem_textview_username);
			holder.userId = (TextView) convertView.findViewById(R.id.useritem_textview_userid);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final User user = data.get(position);

		ImageLoader.getInstance().displayImage(user.getUserImg(), holder.userImg, options);
		holder.userName.setText(user.getUserName());
		holder.userId.setText(user.getUserID());

		holder.userImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), LookImgActivity.class);
				intent.putExtra("url", user.getUserImg());
				v.getContext().startActivity(intent);
			}
		});

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
				intent.putExtra("name", user.getUserName());
				intent.putExtra("url", user.getUserUrl());
				v.getContext().startActivity(intent);
			}
		});

		return convertView;
	}

	private static class ViewHolder {
		ImageView userImg;
		TextView userName;
		TextView userId;
	}

}
