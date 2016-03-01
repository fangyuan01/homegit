package com.example.activity_02_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelloAndroid extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helloandroid);
		Intent intent = getIntent();
		TextView myTextView = (TextView) findViewById(R.id.myTextView);
		myTextView.setText(intent.getStringExtra("hello"));
	}

}
