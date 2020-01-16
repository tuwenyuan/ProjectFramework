package com.twy.projectframework.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class RlNoTouchView extends RelativeLayout {
    public RlNoTouchView(Context context) {
        this(context,null);
    }

    public RlNoTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RlNoTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
