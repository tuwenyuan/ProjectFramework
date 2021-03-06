package com.twy.projectframework;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.twy.network.interfaces.OnRecvDataListener;
import com.twy.projectframework.base.BaseFragment;
import com.twy.projectframework.databinding.FragmentMyBinding;
import com.twy.projectframework.listener.OnTitleClickListener;
import com.twy.projectframework.view.TitleView;

import java.util.Random;

public class MyFragment extends BaseFragment {

    private FragmentMyBinding binding;

    @Override
    public View getContentView() {
        binding = initView(R.layout.fragment_my);
        return binding.getRoot();
    }

    @Override
    protected void initHeader(TitleView title) {
        title.showTitleView(R.mipmap.nav_icon_back, "左边", "标题", R.mipmap.ic_launcher, "右边", new OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(getContext(),"左边",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(getContext(),"右边",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyFragment.this.getContext(),TestActivity.class);
                startActivity(intent);
            }
        },true);
    }

    @Override
    protected void initData() {
        switch (index){
            case 0:
                rtBinding.title.setTitle("首页");
                binding.tvMsg.setText("首页");
                break;
            case 1:
                rtBinding.title.setTitle("商城");
                binding.tvMsg.setText("商城");
                break;
            case 2:
                rtBinding.title.setTitle("订单");
                binding.tvMsg.setText("订单");
                break;
            case 3:
                rtBinding.title.setTitle("我的");
                binding.tvMsg.setText("我的");
                break;
        }
    }

    private Random rd = new Random();

    @Override
    protected void initListener() {
        binding.tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading(false);
                startRequestNetData(service.getUserGet("涂文远", "123456"), new OnRecvDataListener<User>() {
                    @Override
                    public void onRecvData(User o) {
                        Toast.makeText(getContext(),o.toString(),Toast.LENGTH_SHORT).show();
                        switch (rd.nextInt(3)){
                            case 0:
                                showNoNetView();
                                break;
                            case 1:
                                showNoDataView();
                                break;
                            case 2:
                                showErrorView();
                                break;
                        }
                        hideLoding();
                    }
                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),"发生错误："+e.getMessage(),Toast.LENGTH_SHORT).show();
                        hideLoding();
                    }
                });

            }
        });

        /*binding.tpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>= Region.Op.values().length-1){
                    i = 0;
                }else {
                    i++;
                }
                binding.tpv.setPorterDuffXfermode(Region.Op.values()[i]);
            }
        });*/





    }

    public int i = 0;

    private int index;
    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public void initImmersionBar() {
        if(index%2==0){
            super.initImmersionBar();
        }else{
            //支持4.4以下版本
            /*
            * ImmersionBar.with(this).statusBarView(rtBinding.title.statusBar)
                    .statusBarDarkFont(true, 0.2f)
                    .init();*/
            ImmersionBar.with(this).statusBarView(rtBinding.title.statusBar)
                    .autoDarkModeEnable(true)
                    .statusBarColor(R.color.white)
                    .navigationBarColor(R.color.white)
                    .init();
        }
    }
}
