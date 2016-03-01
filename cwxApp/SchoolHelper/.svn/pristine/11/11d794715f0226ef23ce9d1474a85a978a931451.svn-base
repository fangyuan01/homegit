package com.hbueschoolhelper.helperactivity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hbueschoolhelper.MainApplication;
import com.hbueschoolhelper.R;
import com.hbueschoolhelper.adapter.ShopListAdapter;
import com.hbueschoolhelper.dao.Shop;
import com.hbueschoolhelper.view.ViewTools;
import com.hbueschoolhelper.view.ZkImageCamera;

public class LookShopActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {

	private ListView shopListView;
	private ShopListAdapter shopListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helper_lookshop);

		shopListView = (ListView) findViewById(R.id.lookshop_listview_shoplist);
		shopListAdapter = new ShopListAdapter(this);

		List<Shop> shops = MainApplication.getDaoSession(this).getShopDao().loadAll();
		shopListAdapter.setData(shops);

		shopListView.setAdapter(shopListAdapter);
		shopListView.setOnItemClickListener(this);
		shopListView.setOnItemLongClickListener(this);
	}

	public void onBack(View v) {
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		imageCamera.onResult(requestCode, resultCode, data);
	}

	private ZkImageCamera imageCamera;

	public void onAdd(View v) {
		Builder builder = new Builder(this);
		builder.setTitle("添加商户");
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		final EditText editText1 = new EditText(this);
		editText1.setHint("商户名");
		final EditText editText2 = new EditText(this);
		editText2.setHint("商户电话");
		editText2.setInputType(InputType.TYPE_CLASS_PHONE);
		imageCamera = new ZkImageCamera(this);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewTools.getPxFromDp(80, getApplicationContext()),
				ViewTools.getPxFromDp(80, getApplicationContext()));
		imageCamera.setLayoutParams(layoutParams);
		layout.addView(imageCamera);
		layout.addView(editText1);
		layout.addView(editText2);
		builder.setView(layout);
		builder.setNegativeButton("确定", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				shopListAdapter.addData(new Shop(System.currentTimeMillis(), editText1.getText().toString(), imageCamera.getPath(),
						editText2.getText().toString()));
				shopListAdapter.notifyDataSetChanged();
			}
		});
		builder.setPositiveButton("取消", new Dialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		Builder builder = new Builder(this);
		builder.setTitle("提醒");
		builder.setMessage("点确定将会拨通商户电话");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String phoneStr = shopListAdapter.getData().get(arg2).getShopPhone();
				Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneStr));
				startActivity(phoneIntent);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		Builder builder = new Builder(this);
		builder.setTitle("警告");
		builder.setMessage("是否删除该商家");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				shopListAdapter.remove(arg2);
				shopListAdapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
		return true;
	}
}
