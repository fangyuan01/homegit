package com.hbueschoolhelper.helperactivity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hbueschoolhelper.MainApplication;
import com.hbueschoolhelper.R;
import com.hbueschoolhelper.adapter.CourseListAdapter;
import com.hbueschoolhelper.dao.Course;

public class LookClassActivity extends Activity implements OnClickListener {

	private ListView courseListView;
	private CourseListAdapter courseListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helper_lookclass);
		courseListView = (ListView) findViewById(R.id.lookclass_listview_courselist);
		courseListAdapter = new CourseListAdapter(this);

		// ArrayList<Course> data = new ArrayList<Course>();
		// data.add(new Course(System.currentTimeMillis(), "语文", "啊啊", "J1-421",
		// 5, 12, 2, 1, 12));
		// data.add(new Course(System.currentTimeMillis() + 1, "语文", "啊啊",
		// "J1-421", 7, 34, -1, 3, 12));
		// data.add(new Course(System.currentTimeMillis() + 2, "语文", "啊啊",
		// "J1-421", 2, 78, 1, 6, 12));
		// courseListAdapter.setData(data);
		// courseListAdapter.sortData();
		courseListAdapter.setData(MainApplication.getDaoSession(this)
				.getCourseDao().loadAll());
		courseListAdapter.sortData();
		courseListView.setAdapter(courseListAdapter);
	}

	public void onBack(View v) {
		this.finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lookclass_button_deletecourse:
			removeCourse();
			break;
		case R.id.lookclass_button_addcourse:
			addCourse();
			break;
		default:
			break;
		}
	}

	private void removeCourse() {
		Builder builder = new Builder(this);
		builder.setTitle("确认删除");
		builder.setMessage("是否删除该课程");
		builder.setPositiveButton("确认", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				courseListAdapter.removeClicked();
				courseListAdapter.sortData();
				courseListAdapter.notifyDataSetChanged();
			}

		});
		builder.setNegativeButton("取消", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}

	private void addCourse() {
		Builder builder = new Builder(this);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText editTextCourseName = new EditText(this);
		editTextCourseName.setHint("课程名");
		layout.addView(editTextCourseName);

		final EditText editTextTeacherName = new EditText(this);
		editTextTeacherName.setHint("教师名");
		layout.addView(editTextTeacherName);

		final EditText editTextClassRoom = new EditText(this);
		editTextClassRoom.setHint("教室");
		layout.addView(editTextClassRoom);

		LinearLayout layout2 = new LinearLayout(this);
		layout2.setOrientation(LinearLayout.HORIZONTAL);

		final EditText editWeek = new EditText(this);
		editWeek.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		editWeek.setHint("星期");
		editWeek.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout2.addView(editWeek);

		final EditText editlength = new EditText(this);
		editlength.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		editlength.setHint("节数");
		editlength.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout2.addView(editlength);

		layout.addView(layout2);

		LinearLayout layout3 = new LinearLayout(this);
		layout3.setOrientation(LinearLayout.HORIZONTAL);

		final CheckBox checkBox = new CheckBox(this);
		checkBox.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		checkBox.setText("单周");
		layout3.addView(checkBox);

		final CheckBox checkBox2 = new CheckBox(this);
		checkBox2.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		checkBox2.setText("双周");
		layout3.addView(checkBox2);

		layout.addView(layout3);

		LinearLayout layout4 = new LinearLayout(this);
		layout4.setOrientation(LinearLayout.HORIZONTAL);

		final EditText editStart = new EditText(this);
		editStart.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		editStart.setHint("起始周");
		editStart.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout4.addView(editStart);

		final EditText editEnd = new EditText(this);
		editEnd.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1));
		editEnd.setHint("结束周");
		editEnd.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout4.addView(editEnd);

		layout.addView(layout4);

		builder.setView(layout);

		builder.setTitle("添加课程");
		builder.setNegativeButton("确认", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				int oddweek = -1;
				if (checkBox.isChecked()) {
					oddweek = 1;
				}
				if (checkBox2.isChecked()) {
					oddweek = 2;
				}
				if (checkBox.isChecked() && checkBox2.isChecked()) {
					oddweek = -1;
				}
				Course course = new Course(System.currentTimeMillis(),
						editTextCourseName.getText().toString(),
						editTextTeacherName.getText().toString(),
						editTextClassRoom.getText().toString(), Integer
								.parseInt(editWeek.getText().toString()),
						Integer.parseInt(editlength.getText().toString()),
						oddweek, Integer.parseInt(editStart.getText()
								.toString()), Integer.parseInt(editEnd
								.getText().toString()));
				courseListAdapter.addData(course);
				courseListAdapter.sortData();
				courseListAdapter.notifyDataSetChanged();
				dialog.cancel();
			}
		});
		builder.setPositiveButton("取消", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
}
