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
		// 加入这个类的layout
		setContentView(R.layout.other);
		Intent intent = getIntent();
		// 得到键值对的值
		String value = intent.getStringExtra("textIntent");
		myTextView = (TextView) findViewById(R.id.myTextView);
		// 显示键值对的值
		myTextView.setText(value);
	}

}
