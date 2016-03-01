package com.hbueschoolhelper;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.XMPPException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hbueschoolhelper.fragment.HelperFragment;
import com.hbueschoolhelper.fragment.MapFragment;
import com.hbueschoolhelper.fragment.MoreFragment;
import com.hbueschoolhelper.fragment.TalkFragment;
import com.hbueschoolhelper.helperactivity.LookBusActivity;
import com.hbueschoolhelper.helperactivity.LookClassActivity;
import com.hbueschoolhelper.helperactivity.LookOfficeActivity;
import com.hbueschoolhelper.helperactivity.LookShopActivity;
import com.hbueschoolhelper.utils.WeatherInfo;
import com.hbueschoolhelper.utils.WeatherUtils;
import com.hbueschoolhelper.utils.WeatherUtils.onGetWeatherListener;
import com.hbueschoolhelper.utils.XmppManager;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = new Intent();
		intent.setAction("com.hbueschoolhelper.utils.MainService");
		startService(intent);

		fragmentManager = getSupportFragmentManager();
	}

	public void onFragmentButtonClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.helper_button_lookclass:
			intent = new Intent(this, LookClassActivity.class);
			startActivity(intent);
			break;
		case R.id.helper_button_lookbus:
			intent = new Intent(this, LookBusActivity.class);
			startActivity(intent);
			break;
		case R.id.helper_button_lookshop:
			intent = new Intent(this, LookShopActivity.class);
			startActivity(intent);
			break;
		case R.id.helper_button_lookoffice:
			intent = new Intent(this, LookOfficeActivity.class);
			startActivity(intent);
			break;
		case R.id.helper_button_outhelper:
			outhelper();
			break;
		case R.id.talk_button_aroundstudent:
			aroundstucend();
			break;
		case R.id.talk_button_aroundteacher:
			aroundstucend();
			break;
		case R.id.talk_button_mytalker:
			break;
		case R.id.more_button_about:
			break;
		case R.id.more_button_telus:
			break;
		case R.id.more_button_updata:
			break;
		default:
			break;
		}
	}

	private void outhelper() {
		Builder builder = new Builder(this);
		builder.setTitle("请等待");
		builder.setMessage("正在读取天气数据...");
		final AlertDialog dialog = builder.create();
		dialog.show();
		final Handler handler = new Handler();
		WeatherUtils.getWuHanWeather(new onGetWeatherListener() {

			@Override
			public void onGet(final WeatherInfo info) {

				handler.post(new Runnable() {

					@Override
					public void run() {
						dialog.cancel();
						Builder mBuilder = new Builder(MainActivity.this);
						mBuilder.setTitle("天气信息");
						mBuilder.setMessage("日期:" + info.getDate_y()
								+ info.getWeek() + "\n" + "当天气为:"
								+ info.getWeather1() + "  " + info.getTemp1()
								+ "\n" + "明日天气为:" + info.getWeather2() + "  "
								+ info.getTemp2() + "\n" + "后天天气为:"
								+ info.getWeather3() + "  " + info.getTemp3()
								+ "\n消息来源:中国天气网");
						mBuilder.setNegativeButton("确定", null);
						mBuilder.create().show();
					}
				});

			}
		});
	}

	private void aroundstucend() {
		Builder builder = new Builder(this);
		List<String> users = XmppManager.searchUser("hbue");
		ScrollView scrollView = new ScrollView(this);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		final EditText et = new EditText(this);
		layout.addView(et);
		final ArrayList<CheckBox> boxs = new ArrayList<CheckBox>();
		for (String item : users) {
			CheckBox cb = new CheckBox(this);
			cb.setText(item);
			boxs.add(cb);
			layout.addView(cb);
		}
		scrollView.addView(layout);
		builder.setView(scrollView);
		builder.setTitle("选择账户发送消息");
		builder.setNegativeButton("发送", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				for (CheckBox item : boxs) {
					if (item.isChecked()) {
						try {
							XmppManager.SendMessage(et.getText().toString(),
									item.getText().toString());
							Toast.makeText(
									getApplicationContext(),
									"发送数据\"" + et.getText().toString()
											+ "\"给\""
											+ item.getText().toString()
											+ "\"成功", Toast.LENGTH_SHORT)
									.show();
						} catch (XMPPException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		builder.create().show();
	}

	@Override
	public void onClick(View v) {
		String TAG = "FragmentTag";
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment fragment = null;
		switch (v.getId()) {
		case R.id.main_layout_mapbutton:
			fragment = new MapFragment();
			break;
		case R.id.main_layout_helperbutton:
			fragment = new HelperFragment();
			break;
		case R.id.main_layout_talkbutton:
			fragment = new TalkFragment();
			break;
		case R.id.main_layout_morebutton:
			fragment = new MoreFragment();
			break;
		}
		if (fragment != null) {
			if (fragmentManager.findFragmentByTag(TAG) != null) {
				transaction.replace(R.id.main_layout_pagearea, fragment);
			} else {
				transaction.add(R.id.main_layout_pagearea, fragment, TAG);
			}
		}
		transaction.commit();
	}
}
