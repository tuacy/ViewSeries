package com.tuacy.viewseries.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tuacy.viewseries.R;
import com.tuacy.viewseries.utils.DensityUtils;


public class ClickView extends View {

	private final static int DEFAULT_SIZE_DP = 50;

	private Context mContext;
	private int     mRadius;
	private int     mCenterX;
	private int     mContentY;

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
	}

	// DensityUtils.sp2px(getContext(), 14)
	// Color.parseColor("#FFFFFF")
	private void initAttribute(AttributeSet attrs, int defStyleAttr) {
		TypedArray typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.ClickView, defStyleAttr, 0);
		typeArray.recycle();
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
	}
}
