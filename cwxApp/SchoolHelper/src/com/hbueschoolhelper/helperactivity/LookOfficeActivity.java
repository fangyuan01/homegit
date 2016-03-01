package com.hbueschoolhelper.helperactivity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hbueschoolhelper.R;

public class LookOfficeActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helper_lookoffice);
	}

	public void onBack(View v) {
		finish();
	}

	@Override
	public void onClick(View v) {
		Builder builder = new Builder(this);
		builder.setTitle("提醒");
		String phone = "";
		switch (v.getId()) {
		case R.id.lookoffice_jiaowuchu:
			builder.setMessage("此操作将会拨通教务处电话");
			phone = "81973775";
			break;
		case R.id.lookoffice_caiwuchu:
			builder.setMessage("此操作将会拨通财务处电话");
			phone = "81973933";
			break;
		case R.id.lookoffice_tushuguan:
			builder.setMessage("此操作将会拨通图书馆电话");
			phone = "81973801";
			break;
		case R.id.lookoffice_xiaoyiyuan:
			builder.setMessage("此操作将会拨通校医院电话");
			phone = "81973575";
			break;
		default:
			break;
		}
		final String str = phone;
		builder.setPositiveButton("确认", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent phoneIntent = new Intent("android.intent.action.CALL",
						Uri.parse("tel:" + str));
				startActivity(phoneIntent);
			}
		});
		builder.setNegativeButton("取消", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
}
