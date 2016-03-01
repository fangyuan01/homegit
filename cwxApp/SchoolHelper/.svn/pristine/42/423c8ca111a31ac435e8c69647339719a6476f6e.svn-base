package com.hbueschoolhelper.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hbueschoolhelper.MainApplication;
import com.hbueschoolhelper.R;
import com.hbueschoolhelper.dao.Course;
import com.hbueschoolhelper.utils.MathUtils;
import com.hbueschoolhelper.utils.WeekUtils;
import com.hbueschoolhelper.view.CourseItem;
import com.hbueschoolhelper.view.CourseItem.OnItemClickListener;

public class CourseListAdapter extends BaseAdapter {

	private TextView lastText;
	private Context mContext;

	private List<Course> courses;

	private List<Map<Integer, String>> maps;

	public CourseListAdapter(Context context) {
		mContext = context;
		courses = new ArrayList<Course>();
		maps = new ArrayList<Map<Integer, String>>();
		for (int i = 0; i <= 8; i++) {
			if (i == 0) {
				maps.add(getLineOne());
			} else {
				maps.add(new HashMap<Integer, String>());
			}
		}
	}

	private int clickedWeek = 0;
	private int clickedLength = 0;

	public void removeClicked() {
		if (clickedWeek == 0 || clickedLength == 0) {
			return;
		}
		for (Course item : courses) {
			if (item.getWeek() == clickedWeek) {
				for (int it : MathUtils
						.getListForNumline(item.getClassLength())) {
					if (it == clickedLength) {
						courses.remove(item);
						MainApplication.getDaoSession(mContext).getCourseDao()
								.delete(item);
						return;
					}
				}
			}
		}
	}

	public void addData(Course course) {
		MainApplication.getDaoSession(mContext).getCourseDao()
				.insertOrReplace(course);
		courses.add(course);
	}

	public void setData(List<Course> data) {
		courses.clear();
		courses.addAll(data);
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void sortData() {
		maps.clear();
		for (int i = 0; i <= 8; i++) {
			if (i == 0) {
				maps.add(getLineOne());
			} else {
				maps.add(new HashMap<Integer, String>());
			}
		}
		for (Course item : courses) {
			Map<Integer, String> dataNow = maps.get(item.getWeek());
			List<Integer> integers = MathUtils.getListForNumline(item
					.getClassLength());
			for (Integer it : integers) {
				if (item.getOddWeeks() == 2) {
					dataNow.put(it,
							item.getCourseName() + "\n" + item.getClassRoom()
									+ "\n" + "第" + item.getStartweek() + "到"
									+ item.getLastweek() + "周" + "(双周)");
				} else if (item.getOddWeeks() == 1) {
					dataNow.put(it,
							item.getCourseName() + "\n" + item.getClassRoom()
									+ "\n" + "第" + item.getStartweek() + "到"
									+ item.getLastweek() + "周" + "(单周)");
				} else {
					dataNow.put(it,
							item.getCourseName() + "\n" + item.getClassRoom()
									+ "\n" + "第" + item.getStartweek() + "到"
									+ item.getLastweek() + "周");
				}
			}
		}
	}

	public Map<Integer, String> getLineOne() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "第一节");
		map.put(2, "第二节");
		map.put(3, "第三节");
		map.put(4, "第四节");
		map.put(5, "第五节");
		map.put(6, "第六节");
		map.put(7, "第七节");
		map.put(8, "第八节");
		map.put(9, "晚自习");
		return map;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		CourseItem item = null;
		item = new CourseItem(mContext, maps.get(position),
				WeekUtils.getWeekChineseByInt(position),
				new OnItemClickListener() {

					@Override
					public void onItemClick(View v, int pos) {
						if (position == 0) {
							return;
						}
						if (lastText != null) {
							lastText.setTextColor(Color.BLACK);
							lastText.setBackgroundColor(mContext.getResources()
									.getColor(R.color.invisable));
						}
						((TextView) v).setTextColor(Color.WHITE);
						v.setBackgroundColor(mContext.getResources().getColor(
								R.color.title_color));
						lastText = (TextView) v;
						String str = "该课程在["
								+ WeekUtils.getWeekChineseByInt(position)
								+ "] 第[" + pos + "]节";
						Toast.makeText(mContext, str, Toast.LENGTH_SHORT)
								.show();
						clickedWeek = position;
						clickedLength = pos;
					}
				});
		return item;
	}
}
