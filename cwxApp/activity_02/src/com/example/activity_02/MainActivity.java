package com.example.activity_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button myButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		myButton = (Button) findViewById(R.id.mybutton);
		myButton.setText("it is a button");
		MyButtonListener mybuttonlistener = new MyButtonListener();
		myButton.setOnClickListener(mybuttonlistener);
	}

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 键值对
			intent.putExtra("textIntent", "123");
			// 从这个对象跳转到OtherActivity
			intent.setClass(MainActivity.this, OtherActivity.class);
			// 把Intent加入这个类的对象中
			MainActivity.this.startActivity(intent);
			// MainActivity.this.startService(intent);错误写法
		}
	}

}
