package com.hbue.homeworkapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class LoginActivity extends Activity implements OnClickListener {

	private Button btn_login;
	private EditText et_account;
	private EditText et_password;

	private RadioButton buttonStudent;
	private RadioButton buttonTeacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.login_button_login);
		et_account = (EditText) findViewById(R.id.login_edit_account);
		et_password = (EditText) findViewById(R.id.login_edit_password);

		buttonStudent = (RadioButton) findViewById(R.id.login_radiobutton_student);
		buttonTeacher = (RadioButton) findViewById(R.id.login_radiobutton_teacher);

		et_account.setText("teacher");

		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(this, MainActivity.class);
		if (buttonStudent.isChecked()) {
			intent.putExtra("role", "student");
		}
		if (buttonTeacher.isChecked()) {
			intent.putExtra("role", "teacher");
		}
		intent.putExtra("account", et_account.getText().toString());
		intent.putExtra("password", et_password.getText().toString());
		startActivity(intent);
	}
}
