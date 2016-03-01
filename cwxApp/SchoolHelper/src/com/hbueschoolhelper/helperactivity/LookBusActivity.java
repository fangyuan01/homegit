package com.hbueschoolhelper.helperactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hbueschoolhelper.R;

public class LookBusActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helper_lookbus);
	}

	public void onBack(View v) {
		this.finish();
	}
}
