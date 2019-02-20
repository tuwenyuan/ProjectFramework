package com.twy.projectframework;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.twy.network.business.Net;
import com.twy.network.interfaces.OnRecvDataListener;
import com.twy.projectframework.base.BaseActivity;
import com.twy.projectframework.databinding.ActivityMainBinding;
import com.twy.projectframework.listener.OnTitleClickListener;
import com.twy.projectframework.view.TitleView;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    public View getContentView() {
        //DataBindingUtil.inflate(getLayoutInflater(),R.layout.activity_main,null,false);
        binding = initView(R.layout.activity_main);
        return binding.getRoot();
    }

    @Override
    protected void initHeader(TitleView title) {

    }

    @Override
    protected void initData() {
        binding.vp.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        binding.vp.setOffscreenPageLimit(4);
    }


    @Override
    protected void initListener() {
        binding.contentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        binding.vp.setCurrentItem(0);
                        break;
                    case R.id.rb_shopping:
                        binding.vp.setCurrentItem(1);
                        break;
                    case R.id.rb_order:
                        binding.vp.setCurrentItem(2);
                        break;
                    case R.id.rb_my:
                        binding.vp.setCurrentItem(3);
                        break;
                }
            }
        });

        binding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        binding.rbHome.setChecked(true);
                        break;
                    case 1:
                        binding.rbShopping.setChecked(true);
                        break;
                    case 2:
                        binding.rbOrder.setChecked(true);
                        break;
                    case 3:
                        binding.rbMy.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
