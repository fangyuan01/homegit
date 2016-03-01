package com.example.activity_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity {
	private TextView myTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����������layout
		setContentView(R.layout.other);
		Intent intent = getIntent();
		// �õ���ֵ�Ե�ֵ
		String value = intent.getStringExtra("textIntent");
		myTextView = (TextView) findViewById(R.id.myTextView);
		// ��ʾ��ֵ�Ե�ֵ
		myTextView.setText(value);
	}

}
