package com.example.activity_02_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity02 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity02);
		Button myButton = (Button) findViewById(R.id.myButton);
		myButton.setText("按下会跳转到另一个activity");
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("hello", "Hello Android");
				intent.setClass(Activity02.this, HelloAndroid.class);
				Activity02.this.startActivity(intent);
			}
		});
	}

}
