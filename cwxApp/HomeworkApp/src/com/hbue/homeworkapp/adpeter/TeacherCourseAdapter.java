package com.hbue.homeworkapp.adpeter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.dao.Course;
import com.hbue.homeworkapp.utils.QRCodeMaker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class TeacherCourseAdapter extends BaseAdapter {

	private ArrayList<Course> courses;

	private DisplayImageOptions options;

	private OnItemButtonClickListener listener;

	private LayoutInflater inflater;

	public TeacherCourseAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		courses = new ArrayList<Course>();
	}

	public void setOnItemButtonClickListener(OnItemButtonClickListener lis) {
		listener = lis;
	}

	public Course getItemData(int pos) {
		return courses.get(pos);
	}

	public void setData(List<Course> data) {
		courses.clear();
		courses.addAll(data);
	}

	public void addData(Course course) {
		courses.add(course);
	}

	@Override
	public int getCount() {
		return courses.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			v = inflater.inflate(R.layout.item_lookcourse, null);
		} else {
			v = convertView;
		}

		Gson gson = new Gson();
		QRCodeMaker codeMaker = new QRCodeMaker();
		codeMaker.setString(gson.toJson(this.getItemData(position)));

		ImageView iv = (ImageView) v
				.findViewById(R.id.item_classlist_imageview_classimg);
		iv.setBackgroundColor(Color.WHITE);
		iv.setImageBitmap(codeMaker.getQRCodeBitmap(300, 300));
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Gson gson = new Gson();
				QRCodeMaker codeMaker = new QRCodeMaker();
				codeMaker.setString(gson.toJson(getItemData(position)));
				ImageView imageView = new ImageView(v.getContext());
				imageView.setBackgroundColor(Color.WHITE);
				imageView.setImageBitmap(codeMaker.getQRCodeBitmap(300, 300));

				Builder builder = new Builder(v.getContext());
				builder.setTitle("查看二维码");
				builder.setView(imageView);
				builder.setNegativeButton("确定", new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				builder.create().show();
			}
		});

		TextView title = (TextView) v
				.findViewById(R.id.item_classlist_textview_classname);
		title.setText(courses.get(position).getCourseName());

		TextView time = (TextView) v
				.findViewById(R.id.item_classlist_textview_classdata);
		time.setText(courses.get(position).getCourseTime());

		TextView room = (TextView) v
				.findViewById(R.id.item_classlist_textview_classroom);
		room.setText(courses.get(position).getClassRoom());

		Button button = (Button) v
				.findViewById(R.id.item_classlist_button_pubish);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onItemButtonClick(v, position);
				}
			}
		});
		return v;
	}

	public interface OnItemButtonClickListener {
		public void onItemButtonClick(View v, int pos);
	}

}
