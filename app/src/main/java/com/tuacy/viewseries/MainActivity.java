package com.tuacy.viewseries;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
	}

	private void initEvent() {
		findViewById(R.id.button_click).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClickActivity.startUp(mContext);
			}
		});

		findViewById(R.id.button_pie_chart).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PieChartActivity.startUp(mContext);
			}
		});
	}

	private void initData() {
	}


}
