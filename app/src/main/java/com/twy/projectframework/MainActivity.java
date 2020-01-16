package com.twy.projectframework;

import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.twy.projectframework.base.BaseActivity;
import com.twy.projectframework.databinding.ActivityMainBinding;
import com.twy.projectframework.view.TitleView;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    //private Random rd = new Random();

    @Override
    public View getContentView() {
        binding = initView(R.layout.activity_main);
        return binding.getRoot();
    }

    @Override
    protected void initHeader(TitleView title) {
        /*title.showTitleView(null, null, "主页面", null, "点我", new OnTitleClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                showLoading(false);
                startRequestNetData(service.getUserPost("李四", "123456"), new OnRecvDataListener<User>() {
                    @Override
                    public void onRecvData(User o) {
                        Toast.makeText(MainActivity.this,o.toString(),Toast.LENGTH_SHORT).show();
                        switch (rd.nextInt(4)){
                            case 0:
                                showNoNetView();
                                break;
                            case 1:
                                showNoDataView();
                                break;
                            case 2:
                                showErrorView();
                                break;
                            default:
                                break;
                        }
                        hideLoding();
                    }
                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this,"发生错误："+e.getMessage(),Toast.LENGTH_SHORT).show();
                        hideLoding();
                    }
                });
            }
        },true);*/
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
