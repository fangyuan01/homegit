package com.hbueschoolhelper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.hbueschoolhelper.utils.MathUtils;

/**
 * 地图控件
 * 
 * @author Administrator
 * 
 */
public class HBMapView extends View implements OnGestureListener {

	private int mapLeftX = 0;
	private int mapTopY = 0;

	private double x1 = 30.417203;
	private double y1 = 114.427970;

	private double x2 = 30.425992;
	private double y2 = 114.43753;

	private double x3 = 30.412392;
	private double y3 = 114.434813;

	private float scale = 1; // map/screen

	private GestureDetector detector;

	private Drawable mapDrawable;

	private int locationX = 0;
	private int locationY = 0;

	public HBMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public HBMapView(Context context) {
		super(context);
		init();
	}

	private int h;
	private int w;

	private int dh;
	private int dw;

	private int ch;
	private int cw;

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		detector = new GestureDetector(getContext(), this);
		ViewTreeObserver observer = getViewTreeObserver();
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				dh = mapDrawable.getMinimumHeight();
				dw = mapDrawable.getMinimumWidth();

				h = getHeight();
				w = getWidth();

				if ((float) dh / (float) dw >= (float) h / (float) w) {
					cw = w;
					ch = dh * w / dw;
					mapDrawable.setBounds(0, 0, cw, ch);
				}

				if ((float) dh / (float) dw < (float) h / (float) w) {
					ch = h;
					cw = dw * h / dh;
					mapDrawable.setBounds(0, 0, cw, ch);
				}
			}
		});
	}

	public void setLocation(double x, double y) {
		x = MathUtils.fuzzedXY(x);
		y = MathUtils.fuzzedXY(y);

		double x1 = MathUtils.fuzzedXY(this.x1);
		double x2 = MathUtils.fuzzedXY(this.x2);
		double x3 = MathUtils.fuzzedXY(this.x3);

		double y1 = MathUtils.fuzzedXY(this.y1);
		double y2 = MathUtils.fuzzedXY(this.y2);
		double y3 = MathUtils.fuzzedXY(this.y3);

		System.out.println(MathUtils.getPointToLineLength(x, y, x1, y1, x2, y2)
				/ MathUtils.getPointToPointLength(x1, y1, x3, y3));

		locationX = (int) (cw
				* MathUtils.getPointToLineLength(x, y, x1, y1, x3, y3) / MathUtils
				.getPointToPointLength(x1, y1, x2, y2));
		locationY = (int) (ch
				* MathUtils.getPointToLineLength(x, y, x1, y1, x2, y2) / MathUtils
				.getPointToPointLength(x1, y1, x3, y3));
		invalidate();
	}

	public void addLarge() {
		scale *= 1.2;
		invalidate();
	}

	public void subLarge() {
		scale /= 1.2;
		invalidate();
	}

	public void setScale(float scale) {
		this.scale = scale;
		invalidate();
	}

	private Paint paint;

	public void setLocationColor(int color) {
		paint.setColor(color);
	}

	public void loadMapResourse(int res) {
		mapDrawable = getContext().getResources().getDrawable(res);
		mapDrawable.setBounds(0, 0, mapDrawable.getMinimumWidth(),
				mapDrawable.getMinimumHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.scale(scale, scale);
		canvas.translate(mapLeftX / scale, mapTopY / scale);
		mapDrawable.draw(canvas);

		if (locationX != 0 && locationY != 0) {
			canvas.drawCircle(locationX, locationY, 20 / scale, paint);
		}

		canvas.restore();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		mapLeftX -= distanceX;
		mapTopY -= distanceY;
		invalidate();
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}
