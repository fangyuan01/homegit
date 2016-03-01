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
			// ��ֵ��
			intent.putExtra("textIntent", "123");
			// �����������ת��OtherActivity
			intent.setClass(MainActivity.this, OtherActivity.class);
			// ��Intent���������Ķ�����
			MainActivity.this.startActivity(intent);
			// MainActivity.this.startService(intent);����д��
		}
	}

}
