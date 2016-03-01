package com.hbueschoolhelper.utils;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

	/**
	 * 反模糊化坐标 如:n=17203,head=30.4 -> 30.417203
	 * 
	 * @param n
	 * @param head
	 * @return
	 */
	public static double disFuzzedXY(double n, double head) {
		String str1 = String.valueOf(n);
		String str2 = String.valueOf(head);
		return Double.parseDouble(str2 + str1);
	}

	/**
	 * 模糊化坐标 如:30.417203->17203,30.425992->25992,114.42797->27970
	 * 
	 * @param n
	 * @return
	 */
	public static double fuzzedXY(double n) {
		String str = String.valueOf(n);
		str = str.substring(str.indexOf(".4") + 2, str.length());
		while (str.length() < 5) {
			str += "0";
		}
		return Double.parseDouble(str);
	}

	/**
	 * 求两点之间距离
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getPointToPointLength(double x1, double y1, double x2,
			double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	/**
	 * 求点线距离
	 * 
	 * @param pointX
	 * @param pointY
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getPointToLineLength(double pointX, double pointY,
			double x1, double y1, double x2, double y2) {
		double space = 0;
		double a, b, c;
		a = getPointToPointLength(x1, y1, x2, y2);// 线段的长度
		b = getPointToPointLength(x1, y1, pointX, pointY);// (x1,y1)到点的距离
		c = getPointToPointLength(x2, y2, pointX, pointY);// (x2,y2)到点的距离
		if (c <= 0.000001 || b <= 0.000001) {
			space = 0;
			return space;
		}
		if (a <= 0.000001) {
			space = b;
			return space;
		}
		if (c * c >= a * a + b * b) {
			space = b;
			return space;
		}
		if (b * b >= a * a + c * c) {
			space = c;
			return space;
		}
		double p = (a + b + c) / 2;// 半周长
		double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
		space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
		return space;
	}

	public static List<Integer> getListForNumline(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int num = n;
		while (num > 10) {
			list.add(num % 10);
			num = num / 10;
		}
		list.add(num);
		return list;
	}
}
