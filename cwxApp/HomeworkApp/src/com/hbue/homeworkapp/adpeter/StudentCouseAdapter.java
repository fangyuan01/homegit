package com.hbue.homeworkapp.adpeter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.dao.Course;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class StudentCouseAdapter extends BaseAdapter {

	private ArrayList<Course> courses;

	private DisplayImageOptions options;

	private OnItemButtonClickListener listener;

	private LayoutInflater inflater;

	public StudentCouseAdapter(Context context, OnItemButtonClickListener lis) {
		inflater = LayoutInflater.from(context);
		courses = new ArrayList<Course>();

		listener = lis;

		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(1080))
				.imageScaleType(ImageScaleType.EXACTLY)
				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.drawable.ic_launcher).build();
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

	public Course getItemData(int position) {
		return courses.get(position);
	}

	@Override
	public Object getItem(int position) {
		return courses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			v = inflater.inflate(R.layout.item_classlist, null);
		} else {
			v = convertView;
		}
		ImageView iv = (ImageView) v
				.findViewById(R.id.item_classlist_imageview_classimg);
		ImageLoader.getInstance().displayImage(
				courses.get(position).getImgPath(), iv, options);

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
