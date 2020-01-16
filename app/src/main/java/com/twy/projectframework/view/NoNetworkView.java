package com.twy.projectframework.view;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.twy.projectframework.R;
import com.twy.projectframework.databinding.ViewNoNetworkBinding;
import com.twy.projectframework.listener.OnRetryClickListner;



public class NoNetworkView extends LinearLayout {

    private ViewNoNetworkBinding mNoDataViewBinding;

    public NoNetworkView(Context context) {
        super(context);
        init(context);
    }

    public NoNetworkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoNetworkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mNoDataViewBinding = DataBindingUtil.inflate((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE), R.layout.view_no_network, this, false);
        addView(mNoDataViewBinding.getRoot());
        mNoDataViewBinding.tvRetry.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        initListner();
    }


    private void initListner() {
        mNoDataViewBinding.tvRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner != null ){
                    listner.OnRetry(v);
                }
            }
        });
    }

    public OnRetryClickListner listner;

    public void setOnRetryClickListner(OnRetryClickListner listner){
        this.listner = listner;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
