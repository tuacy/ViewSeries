package com.tuacy.viewseries;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tuacy.viewseries.utils.ResourceUtils;
import com.tuacy.viewseries.widget.view.PieChartView;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, PieChartActivity.class));
	}

	private Context mContext;
	private PieChartView mViewPie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pie_chart);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mViewPie = findViewById(R.id.pie_view);
	}

	private void initEvent() {
		mViewPie.setOnPieChartListener(new PieChartView.OnPieChartListener() {
			@Override
			public void onPieSelect(PieChartView.PieDataHolder pieDataHolder) {

			}

			@Override
			public void onNoPieSelect() {

			}
		});
	}

	private void initData() {
		mViewPie.setPieData(obtainPieDataList());
	}

	private List<PieChartView.PieDataHolder> obtainPieDataList() {
		List<PieChartView.PieDataHolder> dataList = new ArrayList<>();
		dataList.add(new PieChartView.PieDataHolder(121.23f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_0), "南 昌"));
		dataList.add(new PieChartView.PieDataHolder(58f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_1), "萍 乡"));
		dataList.add(new PieChartView.PieDataHolder(568f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_2), "高 安"));
		dataList.add(new PieChartView.PieDataHolder(12.65f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_3), "上 饶"));
		dataList.add(new PieChartView.PieDataHolder(356.89f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_4), "吉 安"));
		dataList.add(new PieChartView.PieDataHolder(100f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_5), "抚 州"));
		dataList.add(new PieChartView.PieDataHolder(58.63f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_6), "九 江"));
		dataList.add(new PieChartView.PieDataHolder(336.98f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_7), "鹰 潭"));
		dataList.add(new PieChartView.PieDataHolder(120.98f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_8), "宜　春"));
		dataList.add(new PieChartView.PieDataHolder(56.36f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_9), "樟　树"));
		dataList.add(new PieChartView.PieDataHolder(58f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_10), "上　高"));
		dataList.add(new PieChartView.PieDataHolder(236.69f, ResourceUtils.getColor(mContext, R.color.pie_chart_color_11), "赣　州"));
		return dataList;
	}
}
