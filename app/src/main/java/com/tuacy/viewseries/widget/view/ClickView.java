package com.tuacy.viewseries.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tuacy.viewseries.R;
import com.tuacy.viewseries.utils.DensityUtils;
import com.tuacy.viewseries.utils.TextDrawUtils;

import java.util.Calendar;


public class ClickView extends View {

	private final static int DEFAULT_SIZE_DP = 200;

	private Context   mContext;
	private int       mRadius;
	private int       mCenterX;
	private int       mContentY;
	private int       mRingWidth;
	private int       mRingColor;
	private int       mHourScaleWidth;
	private int       mHourScaleHeight;
	private int       mHourScaleColor;
	private int       mMinuterScaleWidth;
	private int       mMinuterScaleHeight;
	private int       mMinuterScaleColor;
	private int       mScaleTextColor;
	private int       mScaleTextSize;
	private int       mScaleTextSpace;
	private int       mHourPointerWidth;
	private int       mHourPointerColor;
	private int       mMinuterPointerWidth;
	private int       mMinuterPointerColor;
	private int       mSecondPointerWidth;
	private int       mSecondPointerColor;
	private Paint     mPaint;
	private TextPaint mTextPaint;
	private RectF     mRectTemp;

	public ClickView(Context context) {
		this(context, null);
	}

	public ClickView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		initAttribute(attrs, defStyleAttr);
		init();
	}

	private void initAttribute(AttributeSet attrs, int defStyleAttr) {
		TypedArray typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.ClickView, defStyleAttr, 0);
		mRingWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_ring_width,
													   (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
																					   getResources().getDisplayMetrics()));
		mRingColor = typeArray.getColor(R.styleable.ClickView_click_ring_color, Color.parseColor("#F0F0F0"));
		mHourScaleWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_hour_scale_width,
															(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
																							getResources().getDisplayMetrics()));
		mHourScaleHeight = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_hour_scale_height,
															 (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
																							 getResources().getDisplayMetrics()));
		mHourScaleColor = typeArray.getColor(R.styleable.ClickView_click_hour_scale_color, Color.BLACK);
		mMinuterScaleWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_minuter_scale_width,
															   (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
																							   getResources().getDisplayMetrics()));
		mMinuterScaleHeight = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_minuter_scale_height,
																(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
																								getResources().getDisplayMetrics()));
		mMinuterScaleColor = typeArray.getColor(R.styleable.ClickView_click_minuter_scale_color, Color.GRAY);
		mScaleTextColor = typeArray.getColor(R.styleable.ClickView_click_minuter_scale_color, Color.BLACK);
		mScaleTextSize = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_scale_text_size,
														   (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
																						   getResources().getDisplayMetrics()));
		mScaleTextSpace = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_scale_text_space,
															(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
																							getResources().getDisplayMetrics()));
		mHourPointerWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_hour_pointer_width,
															  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
																							  getResources().getDisplayMetrics()));
		mHourPointerColor = typeArray.getColor(R.styleable.ClickView_click_hour_pointer_color, Color.BLACK);
		mMinuterPointerWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_minuter_pointer_width,
																 (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
																								 getResources().getDisplayMetrics()));
		mMinuterPointerColor = typeArray.getColor(R.styleable.ClickView_click_minuter_pointer_color, Color.BLACK);
		mSecondPointerWidth = typeArray.getDimensionPixelOffset(R.styleable.ClickView_click_second_pointer_width,
																(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
																								getResources().getDisplayMetrics()));
		mSecondPointerColor = typeArray.getColor(R.styleable.ClickView_click_second_pointer_color, Color.parseColor("#139DF5"));
		typeArray.recycle();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mTextPaint = new TextPaint();
		mTextPaint.setAntiAlias(true);
		mRectTemp = new RectF();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getMode(widthMeasureSpec)),
							 measuredDimension(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getMode(heightMeasureSpec)));
		mRadius = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
						   getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / 2;
		if (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() < getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) {
			mCenterX = mRadius + getPaddingLeft();
			mContentY = getPaddingTop() + (getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / 2;
		} else {
			mCenterX = getPaddingLeft() + (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
			mContentY = mRadius + getPaddingTop();
		}
	}

	private int measuredDimension(int dimension, int mode) {
		int result = DensityUtils.dp2px(mContext, DEFAULT_SIZE_DP);
		switch (mode) {
			case MeasureSpec.EXACTLY:
				result = dimension;
				break;
			case MeasureSpec.AT_MOST:
				result = DensityUtils.dp2px(mContext, DEFAULT_SIZE_DP);
				break;
			case MeasureSpec.UNSPECIFIED:
				result = DensityUtils.dp2px(mContext, DEFAULT_SIZE_DP);
				break;
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.translate(mCenterX, mContentY);
		drawRing(canvas);
		drawScale(canvas);
		drawPointer(canvas);
		postInvalidateDelayed(1000);
	}

	private void drawRing(Canvas canvas) {
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(mRingColor);
		mPaint.setStrokeWidth(mRingWidth);
		canvas.drawCircle(0, 0, mRadius - mRingWidth / 2, mPaint);
	}

	private void drawScale(Canvas canvas) {
		//时钟刻度
		for (int hour = 0; hour < 12; hour++) {
			canvas.save();
			canvas.rotate(30 * hour);
			int scaleYStart = -mRadius + mRingWidth;
			int scaleYEnd = -mRadius + mRingWidth + mHourScaleHeight;
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(mHourScaleColor);
			mPaint.setStrokeWidth(mHourScaleWidth);
			canvas.drawLine(0, scaleYStart, 0, scaleYEnd, mPaint);
			canvas.restore();
		}
		//分钟刻度
		for (int minuter = 0; minuter < 60; minuter++) {
			if (minuter % 5 == 0) {
				continue;
			}
			canvas.save();
			canvas.rotate(6 * minuter);
			int scaleYStart = -mRadius + mRingWidth;
			int scaleYEnd = -mRadius + mRingWidth + mMinuterScaleHeight;
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(mMinuterScaleColor);
			mPaint.setStrokeWidth(mMinuterScaleWidth);
			canvas.drawLine(0, scaleYStart, 0, scaleYEnd, mPaint);
			canvas.restore();
		}
		//刻度上面的值
		for (int index = 0; index < 12; index++) {
			String hourText = String.valueOf(12 - index);
			float hourTextHeight = TextDrawUtils.getTextHeight(mTextPaint, mScaleTextSize);
			float hourTextWidth = TextDrawUtils.getTextWidth(hourText, mTextPaint, mScaleTextSize);
			updateHourRectF(12 - index, hourTextWidth, hourTextHeight);
			mTextPaint.setTextSize(mScaleTextSize);
			mTextPaint.setColor(mScaleTextColor);
			mTextPaint.setTextAlign(Paint.Align.CENTER);
			canvas.drawText(hourText, mRectTemp.left + mRectTemp.width() / 2,
							TextDrawUtils.getTextBaseLineByBottom(mRectTemp.bottom, mTextPaint, mScaleTextSize), mTextPaint);
		}
	}

	private void updateHourRectF(int hour, float width, float height) {
		float left = 0;
		float top = 0;
		int innerRadius = mRadius - mRingWidth - mHourScaleHeight - mScaleTextSpace;
		switch (hour) {
			case 1:
				left = (float) (innerRadius * Math.cos(Math.toRadians(60))) - width;
				top = (float) (-innerRadius * Math.sin(Math.toRadians(60)));
				break;

			case 2:
				left = (float) (innerRadius * Math.cos(Math.toRadians(30))) - width;
				top = (float) (-innerRadius * Math.sin(Math.toRadians(30))) - height / 2;
				break;
			case 3:
				left = mRadius - mRingWidth - mHourScaleHeight - mScaleTextSpace - width;
				top = -height / 2;
				break;

			case 4:
				left = (float) (innerRadius * Math.cos(Math.toRadians(30))) - width;
				top = (float) (innerRadius * Math.sin(Math.toRadians(30))) - height / 2;
				break;
			case 5:
				left = (float) (innerRadius * Math.sin(Math.toRadians(30))) - width;
				top = (float) (innerRadius * Math.cos(Math.toRadians(30))) - height;
				break;
			case 6:
				left = -width / 2;
				top = mRadius - mRingWidth - mHourScaleHeight - mScaleTextSpace - height;
				break;
			case 7:
				left = -(float) (innerRadius * Math.sin(Math.toRadians(30)));
				top = (float) (innerRadius * Math.cos(Math.toRadians(30))) - height;
				break;
			case 8:
				left = (float) -(innerRadius * Math.cos(Math.toRadians(30)));
				top = (float) (innerRadius * Math.sin(Math.toRadians(30))) - height / 2;
				break;
			case 9:
				left = -mRadius + mRingWidth + mHourScaleHeight + mScaleTextSpace;
				top = -height / 2;
				break;
			case 10:
				left = (float) -(innerRadius * Math.cos(Math.toRadians(30)));
				top = (float) -(innerRadius * Math.sin(Math.toRadians(30))) - height / 2;
				break;
			case 11:
				left = -(float) (innerRadius * Math.cos(Math.toRadians(60)));
				top = (float) (-innerRadius * Math.sin(Math.toRadians(60)));
				break;

			case 12:
				left = -width / 2;
				top = -mRadius + mRingWidth + mHourScaleHeight + mScaleTextSpace;
				break;

		}
		mRectTemp.set(left, top, left + width, top + height);
	}

	public void drawPointer(Canvas canvas) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minuter = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		//时针
		float hourRadius = mRadius - mRingWidth - mHourScaleHeight - TextDrawUtils.getTextHeight(mTextPaint, mScaleTextSize);
		mPaint.setColor(mHourPointerColor);
		mPaint.setStrokeWidth(mHourPointerWidth);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		canvas.save();
		float hourAngle = (hour * 60 + minuter) / 720f * 360;
		canvas.rotate(hourAngle);
		canvas.drawLine(0, 0, 0, -hourRadius, mPaint);
		canvas.restore();
		//分针
		float minuterRadius = mRadius - mRingWidth - mHourScaleHeight - 5;
		mPaint.setColor(mMinuterPointerColor);
		mPaint.setStrokeWidth(mMinuterPointerWidth);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		canvas.save();
		float minuterAngle = (minuter * 60 + second) / 3600f * 360;
		canvas.rotate(minuterAngle);
		canvas.drawLine(0, 0, 0, -minuterRadius, mPaint);
		canvas.restore();
		//秒针
		float secondRadius = mRadius - mRingWidth - mMinuterScaleHeight;
		mPaint.setColor(mSecondPointerColor);
		mPaint.setStrokeWidth(mSecondPointerWidth);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		canvas.save();
		canvas.rotate(second / 60f * 360);
		canvas.drawLine(0, secondRadius / 10, 0, -secondRadius, mPaint);
		canvas.restore();
		//中心园
		mPaint.setColor(mSecondPointerColor);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawCircle(0, 0, mHourPointerWidth * 1.5f / 2, mPaint);
	}
}
