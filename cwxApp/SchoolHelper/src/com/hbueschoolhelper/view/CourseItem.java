package com.hbueschoolhelper.view;

import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CourseItem extends RelativeLayout {

	public CourseItem(Context context, Map<Integer, String> courses,
			String week, final OnItemClickListener listener) {
		super(context);
		this.setBackgroundColor(Color.WHITE);
		this.setLayoutParams(new ListView.LayoutParams(
				ListView.LayoutParams.WRAP_CONTENT, ViewTools.getPxFromDp(80,
						context)));

		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, ViewTools.getPxFromDp(80, context)));
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
				ViewTools.getPxFromDp(100, context),
				LinearLayout.LayoutParams.MATCH_PARENT);

		LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
				ViewTools.getPxFromDp(1, context),
				LinearLayout.LayoutParams.MATCH_PARENT);

		for (int i = 0; i < 10; i++) {
			TextView textView = new TextView(context);
			textView.setTag(i);
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(textParams);
			if (i == 0) {
				textView.setText(week);
			} else {
				if (courses != null) {
					String str = courses.get(i);
					if (str != null) {
						textView.setText(str);
						textView.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (listener != null) {
									listener.onItemClick(v,
											(Integer) v.getTag());
								}
							}
						});
					}
				}
			}
			textView.setTextColor(Color.BLACK);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
			linearLayout.addView(textView);

			View v = new View(context);
			v.setLayoutParams(viewParams);
			v.setBackgroundColor(Color.BLACK);
			linearLayout.addView(v);
		}

		this.addView(linearLayout);
	}

	public interface OnItemClickListener {
		public void onItemClick(View v, int position);
	}
}
