package com.cwx.testactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.QRCodeWriterReader.CaptureActivity;
import com.cwx.QRCodeWriterReader.QRCodeMaker;
import com.cwx.qrcodetest.R;

public class MainActivity extends Activity {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent intent = getIntent();
		textView.setText("解码为:" + intent.getStringExtra("code"));
	}

	private Button scanQRCode;
	private Button createQRCode;
	private EditText editText;
	private ImageView imageView;
	private TextView textView;
	private QRCodeMaker codeMaker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scanQRCode = (Button) findViewById(R.id.scanQRCode);
		createQRCode = (Button) findViewById(R.id.createQRCode);
		editText = (EditText) findViewById(R.id.editText);
		imageView = (ImageView) findViewById(R.id.imageView);
		textView = (TextView) findViewById(R.id.testView);
		textView.setText("解码为:");
		codeMaker = new QRCodeMaker();

		createQRCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				codeMaker.setString(editText.getText().toString());
				imageView.setImageBitmap(codeMaker.getQRCodeBitmap(350, 350));
			}
		});

		scanQRCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						CaptureActivity.class);
				startActivity(intent);
			}
		});
	}
}
