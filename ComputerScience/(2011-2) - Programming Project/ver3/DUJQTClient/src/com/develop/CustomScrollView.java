package com.develop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	private InfoView parent;
	
	public void setInfoView(InfoView i) {
		this.parent = i;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		Log.i("touchito!", "neko!");
		parent.onTouch(ev);
		return super.onInterceptTouchEvent(ev);
	}
}
