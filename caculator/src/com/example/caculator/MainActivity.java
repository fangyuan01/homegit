package com.example.caculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	String s = "";
	double num1;
	double result;
	String sign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView tv_sum = (TextView) findViewById(R.id.tv_sum);
		Button btn_seven = (Button) findViewById(R.id.btn_seven);
		Button btn_eight = (Button) findViewById(R.id.btn_eight);
		Button btn_nine = (Button) findViewById(R.id.btn_nine);
		Button btn_add = (Button) findViewById(R.id.btn_add);
		Button btn_four = (Button) findViewById(R.id.btn_four);
		Button btn_five = (Button) findViewById(R.id.btn_five);
		Button btn_six = (Button) findViewById(R.id.btn_six);
		Button btn_sub = (Button) findViewById(R.id.btn_sub);
		Button btn_one = (Button) findViewById(R.id.btn_one);
		Button btn_two = (Button) findViewById(R.id.btn_two);
		Button btn_three = (Button) findViewById(R.id.btn_three);
		Button btn_multi = (Button) findViewById(R.id.btn_multi);
		Button btn_zero = (Button) findViewById(R.id.btn_zero);
		Button btn_point = (Button) findViewById(R.id.btn_point);
		Button btn_equal = (Button) findViewById(R.id.btn_equal);
		Button btn_divide = (Button) findViewById(R.id.btn_divide);

		btn_seven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_eight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				System.out.println(s);
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_nine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				System.out.println(s);
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_five.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_six.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				System.out.println(s);
				num1 = Double.valueOf(s);// 这一行崩溃了
				tv_sum.setText(s.toString());
			}
		});

		btn_three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_zero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_point.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s = s + ((Button) v).getText();
				num1 = Double.valueOf(s);
				tv_sum.setText(s.toString());
			}
		});

		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				result = num1;
				s = "";
				sign = "+";

			}
		});

		btn_sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				result = num1;
				s = "";
				sign = "-";
			}
		});

		btn_multi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				result = num1;
				s = "";
				sign = "*";
			}
		});

		btn_divide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				result = num1;
				s = "";
				sign = "/";
			}
		});

		btn_equal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (sign == "+") {
					result = result + num1;
					tv_sum.setText(String.valueOf(result));
				} else if (sign == "-") {
					result = result - num1;
					tv_sum.setText(String.valueOf(result));
				} else if (sign == "*") {
					result = result * num1;
					tv_sum.setText(String.valueOf(result));
				} else if (sign == "/") {
					if (num1 == 0.0) {
						tv_sum.setText("被除数不能为0");
					} else {
						result = result / num1;
						tv_sum.setText(String.valueOf(result));
					}
				}
				num1 = result;
				sign = "=";
				s = "";
			}
		});

	}
}
