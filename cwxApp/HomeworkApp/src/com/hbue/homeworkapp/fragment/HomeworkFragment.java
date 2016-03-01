package com.hbue.homeworkapp.fragment;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.cwx.QRCodeWriterReader.CaptureActivity;
import com.hbue.homeworkapp.MainApplication;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.adpeter.StudentCouseAdapter;
import com.hbue.homeworkapp.adpeter.StudentCouseAdapter.OnItemButtonClickListener;
import com.hbue.homeworkapp.utils.XmppManager;

public class HomeworkFragment extends Fragment implements
		OnItemButtonClickListener {

	private ListView classList;
	private Button addButton;
	public StudentCouseAdapter classAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_homework, container, false);
		classList = (ListView) v.findViewById(R.id.homework_listview_classlist);
		classAdapter = new StudentCouseAdapter(getActivity(), this);
		classAdapter.setData(MainApplication.getDaoSession(getActivity())
				.getCourseDao().loadAll());
		classList.setAdapter(classAdapter);

		addButton = (Button) v.findViewById(R.id.homeword_button_addbutton);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), CaptureActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		return v;
	}

	File[] files;

	@Override
	public void onItemButtonClick(View v, final int pos) {
		Builder builder = new AlertDialog.Builder(getActivity());
		files = new File[0];
		File file = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/HomeWork/");
		if (!file.exists()) {
			file.mkdir();
		}
		files = file.listFiles();
		ScrollView scrollView = new ScrollView(getActivity());
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		final ArrayList<CheckBox> boxs = new ArrayList<CheckBox>();
		for (File item : files) {
			CheckBox cb = new CheckBox(getActivity());
			cb.setText(item.getName());
			boxs.add(cb);
			layout.addView(cb);
		}
		scrollView.addView(layout);
		builder.setView(scrollView);
		builder.setTitle("选择文件");
		builder.setNegativeButton("确定", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				for (CheckBox item : boxs) {
					if (item.isChecked()) {
						File file = files[boxs.indexOf(item)];
						File newf = new File(classAdapter.getItemData(pos)
								.getCourseName() + "_" + file.getAbsolutePath());
						file.renameTo(newf);
						XmppManager.sendFile(classAdapter.getItemData(pos)
								.getTeacherName(), newf, classAdapter
								.getItemData(pos).getCourseName());
					}
				}
			}
		});
		builder.create().show();
	}
}
