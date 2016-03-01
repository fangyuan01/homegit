package com.hbueschoolhelper.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbueschoolhelper.MainApplication;
import com.hbueschoolhelper.R;
import com.hbueschoolhelper.dao.Shop;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ShopListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Shop> shops;
	private DisplayImageOptions options;

	public ShopListAdapter(Context context) {
		mContext = context;
		shops = new ArrayList<Shop>();
		options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(1080)).imageScaleType(ImageScaleType.EXACTLY)
				.resetViewBeforeLoading(true).showImageOnLoading(R.drawable.ic_launcher).build();

	}

	public void remove(int pos) {
		Shop shop = shops.get(pos);
		MainApplication.getDaoSession(mContext).getShopDao().delete(shop);
		shops.remove(shop);
	}

	public void addData(Shop shop) {
		MainApplication.getDaoSession(mContext).getShopDao().insertOrReplace(shop);
		shops.add(shop);
	}

	public List<Shop> getData() {
		return shops;
	}

	public void setData(List<Shop> list) {
		shops.clear();
		shops.addAll(list);
	}

	@Override
	public int getCount() {
		return shops.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.item_shoplist, null);
		TextView tv = (TextView) v.findViewById(R.id.shoplist_imageview_shopname);
		tv.setText(shops.get(position).getShopName());
		ImageView iv = (ImageView) v.findViewById(R.id.shoplist_imageview_shopimg);
		if (shops.get(position).getShopImgPath().startsWith("/mnt")) {
			String uri = "file:///" + shops.get(position).getShopImgPath();
			DisplayImageOptions moptions = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(1080))
					.imageScaleType(ImageScaleType.EXACTLY).resetViewBeforeLoading(true).showImageOnLoading(R.drawable.ic_launcher).build();
			ImageLoader.getInstance().displayImage(uri, iv, moptions);
		} else {
			ImageLoader.getInstance().displayImage(shops.get(position).getShopImgPath(), iv, options);
		}
		return v;
	}

}
