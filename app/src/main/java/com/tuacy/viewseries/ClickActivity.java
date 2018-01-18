package com.tuacy.viewseries;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class ClickActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, ClickActivity.class));
	}

	private Context      mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
	}

	private void initEvent() {
	}

	private void initData() {
	}
}
