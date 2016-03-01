package com.hbue.homeworkapp.adpeter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.dao.Msg;

public class MessageAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	private ArrayList<Msg> msgs;

	public MessageAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		msgs = new ArrayList<Msg>();
	}

	public void addData(Msg msg) {
		msgs.add(msg);
	}

	public void setData(List<Msg> data) {
		msgs.clear();
		msgs.addAll(data);
	}

	@Override
	public int getCount() {
		return msgs.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			v = inflater.inflate(R.layout.item_messagelist, null);
		} else {
			v = convertView;
		}
		TextView title = (TextView) v
				.findViewById(R.id.item_classlist_textview_msgname);
		title.setText(msgs.get(position).getMsgTitle() + "("
				+ msgs.get(position).getMsgSender() + ")");

		TextView info = (TextView) v.findViewById(R.id.item_msg_info);
		info.setText(msgs.get(position).getMsgInfo());

		TextView date = (TextView) v.findViewById(R.id.item_msg_data);
		date.setText(msgs.get(position).getMsgData());

		return v;
	}

}
