package com.hbue.homeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.hbue.homeworkapp.dao.Course;
import com.hbue.homeworkapp.fragment.HomeworkFragment;
import com.hbue.homeworkapp.fragment.MessageFragment;
import com.hbue.homeworkapp.fragment.MoreFragment;
import com.hbue.homeworkapp.fragment.PersonFragment;
import com.hbue.homeworkapp.fragment.TeacherCourseFragment;
import com.hbue.homeworkapp.utils.StringUtils;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private int nowFragmentId = 0;
	private String fragmentTag = "FRAGMENT";

	public static boolean isStudent = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String role = getIntent().getStringExtra("role");

		Intent intent = new Intent();
		intent.setAction("com.hbue.homeworkapp.service.MainService");
		startService(intent);

		if (role.equals("student")) {
			setContentView(R.layout.activity_main);
			fragmentManager = getSupportFragmentManager();
			changeFragmentById(R.id.main_button_homework,
					R.id.main_layout_pagearea);
			isStudent = true;
		} else if (role.equals("teacher")) {
			setContentView(R.layout.teacher_activity_main);
			fragmentManager = getSupportFragmentManager();
			changeFragmentById(R.id.teacher_main_button_homework,
					R.id.teacher_main_layout_pagearea);
			isStudent = false;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		try {
			String code = arg2.getStringExtra("code");
			if (!StringUtils.isNullOrEmpty(code)) {
				Gson gson = new Gson();
				Course course = gson.fromJson(code, Course.class);
				MainApplication.getDaoSession(getApplicationContext())
						.getCourseDao().insertOrReplace(course);
				HomeworkFragment fragmHomeworkFragment = (HomeworkFragment) fragment;
				fragmHomeworkFragment.classAdapter.addData(course);
				fragmHomeworkFragment.classAdapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		changeFragmentById(v.getId(), R.id.main_layout_pagearea);
	}

	public void onClick2(View v) {
		changeFragmentById(v.getId(), R.id.teacher_main_layout_pagearea);
	}

	Fragment fragment;

	private void changeFragmentById(int id, int res) {
		if (id == nowFragmentId) {
			return;
		}
		switch (id) {
		case R.id.main_button_homework:
			fragment = new HomeworkFragment();
			break;
		case R.id.main_button_message:
			fragment = new MessageFragment();
			break;
		case R.id.main_button_person:
			fragment = new PersonFragment();
			break;
		case R.id.teacher_main_button_homework:
			fragment = new TeacherCourseFragment();
			break;
		case R.id.teacher_main_button_message:
			fragment = new MessageFragment();
			break;
		case R.id.teacher_main_button_person:
			fragment = new PersonFragment();
			break;
		case R.id.main_button_more:
			fragment = new MoreFragment();
			break;
		default:
			return;
		}
		fragmentTransaction = fragmentManager.beginTransaction();
		if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
			fragmentTransaction.replace(res, fragment, fragmentTag);
			// fragmentTransaction.detach(fragmentManager.findFragmentByTag(tag));
			// fragmentTransaction.attach(fragment);
		} else {
			fragmentTransaction.add(res, fragment, fragmentTag);
		}
		fragmentTransaction.commit();
		nowFragmentId = id;
	}

}
