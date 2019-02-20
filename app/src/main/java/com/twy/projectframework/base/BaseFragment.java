package com.twy.projectframework.base;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.twy.network.business.Net;
import com.twy.network.business.Observable;
import com.twy.network.interfaces.OnRecvDataListener;
import com.twy.projectframework.R;
import com.twy.projectframework.databinding.ViewBaseBinding;
import com.twy.projectframework.listener.IRequestNetData;
import com.twy.projectframework.listener.OnRetryClickListner;
import com.twy.projectframework.net.ITestServices;
import com.twy.projectframework.net.NetConfigMsg;
import com.twy.projectframework.view.TitleView;


public abstract class BaseFragment extends Fragment {

    public ITestServices service = NetConfigMsg.getService();
    private IRequestNetData requestNetData;
    private boolean isBgTransparent = false;

    public ViewBaseBinding rtBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rtBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.view_base,null,false);
        rtBinding.flContent.addView(getContentView());
        initHeader(rtBinding.title);
        initData();
        initListener();

        rtBinding.title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                rtBinding.title.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                rtBinding.loading.v.getLayoutParams().height = rtBinding.title.getTitleHeight();
            }
        });

        return rtBinding.getRoot();
    }

    public abstract View getContentView();

    protected abstract void initHeader(TitleView title);

    protected abstract void initData();

    protected abstract void initListener();

    public void startRequestNetData(final Observable observable, final OnRecvDataListener onRecvDataListener){
        this.requestNetData = new IRequestNetData() {
            @Override
            public void loadNetData() {
                rtBinding.vError.setVisibility(View.GONE);
                rtBinding.vNoData.setVisibility(View.GONE);
                rtBinding.vNoNet.setVisibility(View.GONE);
                Net.startRequestData((AppCompatActivity) getActivity(),observable,onRecvDataListener);
            }
        };
        this.requestNetData.loadNetData();
    }

    public <T> T initView(int id){
        return (T) DataBindingUtil.inflate(getLayoutInflater(),id,null,false);
    }

    protected void showLoading(boolean isBgTransparent){
        this.isBgTransparent = isBgTransparent;
        if(isBgTransparent){
            rtBinding.loading.rlLoading.setBackgroundColor(Color.parseColor("#00000000"));
        }else {
            rtBinding.loading.rlLoading.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        rtBinding.loading.rlNTLoading.setVisibility(View.VISIBLE);
    }

    protected void hideLoding(){
        rtBinding.loading.rlNTLoading.setVisibility(View.GONE);
    }

    private OnRetryClickListner listner = new OnRetryClickListner() {
        @Override
        public void OnRetry(View view) {
            showLoading(isBgTransparent);
            requestNetData.loadNetData();
        }
    };
    public void showErrorView(){
        rtBinding.vError.setVisibility(View.VISIBLE);
        rtBinding.vNoData.setVisibility(View.GONE);
        rtBinding.vNoNet.setVisibility(View.GONE);
        rtBinding.vError.setOnRetryClickListner(listner);
    }

    public void showNoDataView(){
        rtBinding.vNoData.setVisibility(View.VISIBLE);
        rtBinding.vError.setVisibility(View.GONE);
        rtBinding.vNoNet.setVisibility(View.GONE);
        rtBinding.vNoData.setOnRetryClickListner(listner);
    }

    public void showNoNetView(){
        rtBinding.vNoNet.setVisibility(View.VISIBLE);
        rtBinding.vError.setVisibility(View.GONE);
        rtBinding.vNoData.setVisibility(View.GONE);
        rtBinding.vNoNet.setOnRetryClickListner(listner);
    }
}
