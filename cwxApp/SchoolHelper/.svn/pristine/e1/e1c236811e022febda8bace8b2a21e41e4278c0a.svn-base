package com.hbueschoolhelper.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

/**
 * 控件工具包
 * 
 * @author Chen
 * 
 */
public class ViewTools {

	/**
	 * 给button设置小图标
	 * 
	 * @param id
	 *            图片id
	 * @param scaler
	 *            图片缩放比例scaler%
	 * @param direct
	 *            path方向0123上下左右
	 * @param btn
	 *            button控件
	 */
	public static void setButtonPath(int id, int scaler, int direct, Button btn) {
		Drawable drawable = btn.getContext().getResources().getDrawable(id);
		drawable.setBounds(0, 0, drawable.getMinimumWidth() * scaler / 100, drawable.getMinimumHeight() * scaler / 100);
		switch (direct) {
		case 0:
			btn.setCompoundDrawables(null, drawable, null, null);
			break;
		case 1:
			btn.setCompoundDrawables(null, null, null, drawable);
			break;
		case 2:
			btn.setCompoundDrawables(drawable, null, null, null);
			break;
		case 3:
			btn.setCompoundDrawables(null, null, drawable, null);
			break;
		}
	}

	/**
	 * 将dp转化为px
	 * 
	 * @param dp
	 * @param context
	 * @return
	 */
	public static int getPxFromDp(int dp, Context context) {
		int padding_in_dp = dp;
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (padding_in_dp * scale + 0.5f);
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param v
	 *            editText
	 */
	public static void hideSoftInputFromWindow(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	/**
	 * 屏蔽长按事件
	 * 
	 * @param v
	 */
	public static void removeLongClick(View v) {
		v.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
	}

}
