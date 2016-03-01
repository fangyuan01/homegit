package com.example.recordgps;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recordgps.Location.LocationEvent;
import com.example.recordgps.Location.LocationObtainedListener;

public class MainActivity extends Activity {

	private double gpsX;
	private double gpsY;

	Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((TextView) findViewById(R.id.text_gpsvalue))
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						SharedPreferences preferences = getSharedPreferences(
								"record", MODE_PRIVATE);
						Editor editor = preferences.edit();
						editor.clear();
						editor.commit();
						((TextView) findViewById(R.id.text_log)).setText("");
						return true;
					}
				});

		location = new Location(this);
		location.setLocatonObtainedListener(new LocationObtainedListener() {

			@Override
			public void locationObtained(LocationEvent event) {
				gpsX = event.x;
				gpsY = event.y;
				System.out.println("finish");
				((TextView) findViewById(R.id.text_gpsvalue)).setText("gpsX->"
						+ gpsX + " gpsY->" + gpsY);
			}
		});

		SharedPreferences preferences = getSharedPreferences("record",
				MODE_PRIVATE);
		((TextView) findViewById(R.id.text_log)).setText(preferences.getString(
				"data", ""));
	}

	public void onCalculate(View v) {
		location.start();
		v.setClickable(false);
		((TextView) findViewById(R.id.text_gpsvalue)).setText("正在定位...");
	}

	public void onRecord(View v) {
		((TextView) findViewById(R.id.text_log))
				.setText(((TextView) findViewById(R.id.text_log)).getText()
						+ "\n"
						+ "note->"
						+ ((EditText) findViewById(R.id.edittext_note))
								.getText().toString() + "|gpsX->" + gpsX
						+ " gpsY->" + gpsY);
		SharedPreferences preferences = getSharedPreferences("record",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("data", ((TextView) findViewById(R.id.text_log))
				.getText().toString());
		editor.commit();
	}

}
