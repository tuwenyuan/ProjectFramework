package com.twy.projectframework;

import android.view.View;

import com.twy.projectframework.base.BaseActivity;
import com.twy.projectframework.databinding.ActivityTestBinding;
import com.twy.projectframework.listener.OnTitleClickListener;
import com.twy.projectframework.view.TitleView;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/6/12.
 * PS: Not easy to write code, please indicate.
 */
public class TestActivity extends BaseActivity {

    private ActivityTestBinding binding;

    @Override
    public View getContentView() {
        binding = initView(R.layout.activity_test);
        return binding.getRoot();
    }

    @Override
    protected void initHeader(TitleView title) {
        title.showTitleView(R.mipmap.nav_icon_back, null, "测试", null, null, new OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        },true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
