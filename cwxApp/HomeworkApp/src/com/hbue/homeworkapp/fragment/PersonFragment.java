package com.hbue.homeworkapp.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hbue.homeworkapp.MainActivity;
import com.hbue.homeworkapp.MainApplication;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.dao.User;
import com.hbue.homeworkapp.service.XmppUserUtils;

public class PersonFragment extends Fragment implements OnClickListener {

	private Button change;

	private TextView name;
	private TextView account;
	private TextView right;
	private TextView sex;
	private TextView age;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_person, container, false);

		name = (TextView) v.findViewById(R.id.person_textview_name);
		account = (TextView) v.findViewById(R.id.person_textview_number);
		right = (TextView) v.findViewById(R.id.person_textview_power);
		sex = (TextView) v.findViewById(R.id.person_sex);
		age = (TextView) v.findViewById(R.id.person_age);

		try {
			User user = MainApplication.getDaoSession(getActivity())
					.getUserDao().loadAll().get(0);
			name.setText("姓名:" + user.getUserName());
		} catch (Exception e) {
			name.setText("姓名:" + "缺省");
			e.printStackTrace();
		}
		account.setText("用户名:" + XmppUserUtils.Account);
		if (MainActivity.isStudent) {
			right.setText("身份:学生");
		} else {
			right.setText("身份:教师");
		}
		SharedPreferences preferences = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		sex.setText("性别:" + preferences.getString("sex", "缺省"));
		age.setText("年龄:" + preferences.getString("age", "缺省"));

		change = (Button) v.findViewById(R.id.person_change);
		change.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		Builder builder = new Builder(getActivity());
		builder.setTitle("修改信息");
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText name = new EditText(getActivity());
		name.setHint("姓名");
		layout.addView(name);

		final RadioGroup group = new RadioGroup(getActivity());
		group.setOrientation(LinearLayout.HORIZONTAL);
		final RadioButton radioButton1 = new RadioButton(getActivity());
		radioButton1.setText("男");
		group.addView(radioButton1);
		final RadioButton radioButton2 = new RadioButton(getActivity());
		radioButton2.setText("女");
		group.addView(radioButton2);
		layout.addView(group);

		final EditText age = new EditText(getActivity());
		age.setHint("年龄");
		age.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout.addView(age);

		builder.setView(layout);
		builder.setNegativeButton("确定", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				PersonFragment.this.name.setText("姓名:" + name.getText());
				try {
					User user = new User(XmppUserUtils.Account, name.getText()
							.toString(), null);
					MainApplication.getDaoSession(getActivity()).getUserDao()
							.insertOrReplace(user);
				} catch (Exception e) {
					name.setText("姓名:" + "缺省");
					e.printStackTrace();
				}
				PersonFragment.this.account.setText("用户名:"
						+ XmppUserUtils.Account);

				if (radioButton1.isChecked()) {
					PersonFragment.this.sex.setText("性别:男");
				} else if (radioButton2.isChecked()) {
					PersonFragment.this.sex.setText("性别:女");
				} else {
					PersonFragment.this.sex.setText("性别:缺省");
				}
				PersonFragment.this.age.setText("年龄:"
						+ age.getText().toString());

				SharedPreferences preferences = getActivity().getPreferences(
						Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				editor.putString("age", age.getText().toString());
				if (radioButton1.isChecked()) {
					editor.putString("sex", "男");
				} else if (radioButton2.isChecked()) {
					editor.putString("sex", "女");
				} else {
					editor.putString("sex", "缺省");
				}
				editor.commit();
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
