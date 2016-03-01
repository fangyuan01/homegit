package com.cwx.max;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class LookImgActivity extends Activity {

	private DisplayImageOptions options;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookimg);
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.d_noneimg).showImageForEmptyUri(R.drawable.d_noneimg)
				.showImageOnFail(R.drawable.d_noneimg).imageScaleType(ImageScaleType.EXACTLY_STRETCHED).cacheInMemory(true)
				.cacheOnDisc(true).build();
		imageView = (ImageView) findViewById(R.id.lookimg_img);
		String imgUrl = getIntent().getStringExtra("url");
		System.out.println(imgUrl);
		ImageLoader.getInstance().displayImage(imgUrl, imageView, options);
	}
}
