package com.cwx.QRCodeWriterReader;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeMaker {
	private String string;

	public QRCodeMaker(String string) {
		this.string = string;
	}
	public QRCodeMaker() {
	}

	public String getString() {
		return string;
	}

	public boolean setString(String string) {
		if (string.equals("")) {
			return false;
		} else {
			this.string = string;
			return true;
		}
	}

	public Bitmap getQRCodeBitmap(int bitmapWidth,int bitmapHeight ) {
		Bitmap bitmap = null;
		try {
			if (!string.equals("")) {
				// 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小
				BitMatrix matrix = new MultiFormatWriter().encode(string,
						BarcodeFormat.QR_CODE, bitmapWidth, bitmapHeight);
				int width = matrix.getWidth();
				int height = matrix.getHeight();
				// 二维矩阵转为一维像素数组,也就是一直横着排了
				int[] pixels = new int[width * height];
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						if (matrix.get(x, y)) {
							pixels[y * width + x] = 0xff000000;
						}
					}
				}
				bitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ARGB_8888);
				// 通过像素数组生成bitmap,具体参考api
				bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
			} else {
			}
		} catch (Exception er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		return bitmap;
	}
}
