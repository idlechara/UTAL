package com.develop;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ListViewTest extends LinearLayout{
	
	InfoView parent;

	public ListViewTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public void setParent(InfoView i){
		this.parent=i;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		this.parent.onTouch(ev);
		return super.onInterceptTouchEvent(ev);
	}

}
