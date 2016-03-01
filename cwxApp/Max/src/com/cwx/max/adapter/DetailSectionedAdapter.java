package com.cwx.max.adapter;

import java.util.ArrayList;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwx.max.R;
import com.cwx.max.bean.UserHero;
import com.cwx.max.bean.UserMatch;
import com.cwx.max.bean.UserRecord;

public class DetailSectionedAdapter extends SectionedBaseAdapter {

	private List<UserHero> heros;
	private List<UserMatch> matchs;
	private List<UserRecord> records;

	public DetailSectionedAdapter() {
		heros = new ArrayList<UserHero>();
		matchs = new ArrayList<UserMatch>();
		records = new ArrayList<UserRecord>();
	}

	@Override
	public Object getItem(int section, int position) {
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		return 0;
	}

	@Override
	public int getSectionCount() {
		return 3;
	}

	@Override
	public int getCountForSection(int section) {
		switch (section) {
		case 0:
			return heros.size();
		case 1:
			return matchs.size();
		case 2:
			return records.size();
		}
		return 0;
	}

	@Override
	public int getItemViewTypeCount() {
		return 3;
	}

	@Override
	public int getItemViewType(int section, int position) {
		return section;
	}

	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {
		RelativeLayout layout = null;
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (RelativeLayout) inflator.inflate(R.layout.item_usetdetail, null);
		} else {
			layout = (RelativeLayout) convertView;
		}
		return layout;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		LinearLayout layout = null;
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = (LinearLayout) inflator.inflate(R.layout.item_detailsection, null);
		} else {
			layout = (LinearLayout) convertView;
		}
		switch (section) {
		case 0:
			((TextView) layout.findViewById(R.id.detailsection_textview_sectionname)).setText("常用英雄");
			break;
		case 1:
			((TextView) layout.findViewById(R.id.detailsection_textview_sectionname)).setText("最近比赛");
			break;
		case 2:
			((TextView) layout.findViewById(R.id.detailsection_textview_sectionname)).setText("最高记录");
			break;
		}
		return layout;
	}

	public List<UserHero> getHeros() {
		return heros;
	}

	public List<UserMatch> getMatchs() {
		return matchs;
	}

	public List<UserRecord> getRecords() {
		return records;
	}

	public void setHeros(List<UserHero> heros) {
		this.heros.clear();
		this.heros.addAll(heros);
	}

	public void setMatchs(List<UserMatch> matchs) {
		this.matchs.clear();
		this.matchs.addAll(matchs);
	}

	public void setRecords(List<UserRecord> records) {
		this.records.clear();
		this.records.addAll(records);
	}

}
