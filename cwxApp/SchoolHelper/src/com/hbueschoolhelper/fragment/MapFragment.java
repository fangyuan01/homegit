package com.hbueschoolhelper.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

import com.hbueschoolhelper.R;
import com.polites.android.GestureImageView;

public class MapFragment extends Fragment {

	private EditText x;
	private EditText y;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_map, container, false);

		x = (EditText) v.findViewById(R.id.x);
		y = (EditText) v.findViewById(R.id.y);

		final GestureImageView image = new GestureImageView(getActivity());
		image.setBackgroundColor(Color.BLACK);
		image.setImageResource(R.drawable.map);
		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		((ViewGroup) v).addView(image);

		// final HBMapView hbMapView = new HBMapView(getActivity());
		// hbMapView.setLocationColor(Color.RED);
		// hbMapView.loadMapResourse(R.drawable.map);
		// hbMapView.setBackgroundColor(Color.BLACK);
		// hbMapView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT));
		// ((ViewGroup) v).addView(hbMapView);

		// final Location location = new Location(getActivity());
		// location.setLocatonObtainedListener(new LocationObtainedListener() {
		//
		// @Override
		// public void locationObtained(LocationEvent event) {
		// image.setLocation(event.x, event.y);
		// }
		// });

		Button locationButton = (Button) v
				.findViewById(R.id.map_button_location);
		locationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					image.setLocation(Double.valueOf(x.getText().toString()),
							Double.valueOf(y.getText().toString()));
				} catch (Exception e) {
					image.setLocation(30.417221, 114.43807);
				}
				// location.start();
			}
		});

		return v;
	}
}
