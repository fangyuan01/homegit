package com.cwx.max.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.max.R;
import com.cwx.max.bean.RankUser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RankListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private DisplayImageOptions options;

	private ArrayList<RankUser> data;

	public RankListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		data = new ArrayList<RankUser>();
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.d_noneimg).showImageForEmptyUri(R.drawable.d_noneimg)
				.showImageOnFail(R.drawable.d_noneimg).cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setData(List<RankUser> list) {
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
		ViewHoler holer;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_rankitem, null);
			holer = new ViewHoler();
			holer.rank = (TextView) convertView.findViewById(R.id.rankitem_textview_rank);
			holer.point = (TextView) convertView.findViewById(R.id.rankitem_textview_point);
			holer.country = (ImageView) convertView.findViewById(R.id.rankitem_imageview_country);
			holer.name = (TextView) convertView.findViewById(R.id.rankitem_textview_name);
			holer.team = (TextView) convertView.findViewById(R.id.rankitem_textview_team);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}

		RankUser rankUser = data.get(position);

		holer.rank.setText(rankUser.getRank());
		holer.point.setText(rankUser.getPoint());
		ImageLoader.getInstance().displayImage(rankUser.getCountryImg(), holer.country, options);
		holer.name.setText(rankUser.getName());
		holer.team.setText(rankUser.getTeam());

		return convertView;
	}

	private static class ViewHoler {
		TextView rank;
		TextView point;
		ImageView country;
		TextView name;
		TextView team;
	}

}
