package com.hbue.homeworkapp.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hbue.homeworkapp.MainApplication;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.adpeter.TeacherCourseAdapter;
import com.hbue.homeworkapp.adpeter.TeacherCourseAdapter.OnItemButtonClickListener;
import com.hbue.homeworkapp.dao.Course;

public class LookCourseFragment extends Fragment implements
		OnItemButtonClickListener {

	private ListView courseList;
	private TeacherCourseAdapter classAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_teacher_lookcourse, container,
				false);
		courseList = (ListView) v
				.findViewById(R.id.lookcourse_listview_classlist);
		classAdapter = new TeacherCourseAdapter(getActivity());
		List<Course> courses = MainApplication.getDaoSession(getActivity())
				.getCourseDao().loadAll();
		classAdapter.setData(courses);
		classAdapter.setOnItemButtonClickListener(this);
		courseList.setAdapter(classAdapter);
		return v;
	}

	File[] files;

	@Override
	public void onItemButtonClick(View v, int pos) {
		files = new File[0];
		File file = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/HomeWork/");
		if (!file.exists()) {
			file.mkdir();
		}
		files = file.listFiles();
		Builder builder = new AlertDialog.Builder(getActivity());
		ScrollView scrollView = new ScrollView(getActivity());
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		final ArrayList<TextView> tvs = new ArrayList<TextView>();
		for (File item : files) {
			final File it = item;
			if (!it.getName().startsWith(
					classAdapter.getItemData(pos).getCourseName())) {
				continue;
			}
			TextView cb = new TextView(getActivity());
			cb.setTextColor(Color.BLACK);
			cb.setGravity(Gravity.CENTER);
			cb.setPadding(25, 25, 25, 25);
			cb.setBackgroundResource(R.drawable.xml_edit_text);
			cb.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					try {
						InputStream is = new FileInputStream(it);
						InputStreamReader fr = new InputStreamReader(is,
								"UTF-8");
						BufferedReader br = new BufferedReader(fr);
						String str = "";
						String data = br.readLine();
						while (data != null) {
							str = str + "\n" + data;
							data = br.readLine();
						}
						Builder builder = new Builder(getActivity());
						builder.setTitle("查看作业");
						builder.setMessage(str);
						builder.setNegativeButton("关闭",
								new Dialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								});
						builder.create().show();
						br.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			cb.setText(item.getName());
			tvs.add(cb);
			layout.addView(cb);
		}
		scrollView.addView(layout);
		builder.setView(scrollView);
		builder.setTitle("查看已交作业");
		builder.setNegativeButton("确定", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}

}
