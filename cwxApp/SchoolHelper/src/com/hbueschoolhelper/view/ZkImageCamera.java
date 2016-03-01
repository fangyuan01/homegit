package com.hbueschoolhelper.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hbueschoolhelper.R;
import com.hbueschoolhelper.utils.CameraTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 可照相的imageView 需要调用setActivity绑定acivity
 * 
 * @author Chen
 * 
 */
public class ZkImageCamera extends ImageView {

	private String path;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	/**
	 * 刷新视图
	 */
	private void refreshSrc() {
		if (path != null) {
			String uri = "file:///" + path;
			options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(1080)).imageScaleType(ImageScaleType.EXACTLY)
					.resetViewBeforeLoading(true).showImageOnLoading(R.drawable.ic_launcher).build();
			imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(uri, this, options);
		} else {
		}
	}

	/**
	 * 获取图片路径
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	public ZkImageCamera(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ZkImageCamera(Context context) {
		super(context);
		init();
	}

	public void onResult(int requestCode, int resultCode, Intent data) {
		System.out.println("onResult");
		refreshSrc();
		this.setClickable(false);
	}

	private void init() {
		this.setBackgroundColor(Color.rgb(225, 225, 225));
		this.setImageResource(R.drawable.ic_launcher);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				path = CameraTools.getPhotoFromCamera((Activity) getContext(), ZkImageCamera.this.hashCode());
			}
		});
	}

}
