package com.hbue.homeworkapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hbue.homeworkapp.R;

public class TeacherCourseFragment extends Fragment implements OnClickListener {

	private RelativeLayout buttonAddCourse;
	private RelativeLayout buttonLookCourse;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private String fragmentTag = "MSG_FRAGMENT2";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_teacher_course, container, false);
		buttonAddCourse = (RelativeLayout) v.findViewById(R.id.teacher_course_layout_registercourse);
		buttonLookCourse = (RelativeLayout) v.findViewById(R.id.teacher_course_layout_lookcourse);
		buttonAddCourse.setOnClickListener(this);
		buttonLookCourse.setOnClickListener(this);
		return v;
	}

	private boolean addIsShow = false;
	private boolean lookIsShow = false;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.teacher_course_layout_registercourse:
			removeFragmentFromView(R.id.teacher_course_layout_arealookcourse);
			lookIsShow = false;
			if (addIsShow) {
				removeFragmentFromView(R.id.teacher_course_layout_arearegistercourse);
				addIsShow = false;
			} else {
				addFragmentIntoView(R.id.teacher_course_layout_arearegistercourse, new AddCourseFragment());
				addIsShow = true;
			}
			break;
		case R.id.teacher_course_layout_lookcourse:
			removeFragmentFromView(R.id.teacher_course_layout_arearegistercourse);
			addIsShow = false;
			if (lookIsShow) {
				removeFragmentFromView(R.id.teacher_course_layout_arealookcourse);
				lookIsShow = false;
			} else {
				addFragmentIntoView(R.id.teacher_course_layout_arealookcourse, new LookCourseFragment());
				lookIsShow = true;
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 向某控件加入fragment
	 * 
	 * @param id
	 *            该控件id
	 * @param fragment
	 */
	public void addFragmentIntoView(int id, Fragment fragment) {
		if (fragmentManager == null) {
			fragmentManager = getChildFragmentManager();
		}
		fragmentTransaction = fragmentManager.beginTransaction();
		if (fragmentManager.findFragmentByTag(fragmentTag + id) != null) {
			fragmentTransaction.replace(id, fragment, fragmentTag + id);
		} else {
			fragmentTransaction.add(id, fragment, fragmentTag + id);
		}
		fragmentTransaction.commit();
	}

	/**
	 * 清除某控件上fragment 该frament必须是使用{@link addFragmentIntoView} 方法添加
	 * 
	 * @param id
	 *            该控件id
	 */
	public void removeFragmentFromView(int id) {
		if (fragmentManager == null) {
			fragmentManager = getChildFragmentManager();
		}
		fragmentTransaction = fragmentManager.beginTransaction();
		if (fragmentManager.findFragmentByTag(fragmentTag + id) != null) {
			fragmentTransaction.remove(fragmentManager.findFragmentByTag(fragmentTag + id));
		} else {
		}
		fragmentTransaction.commit();
	}
}
