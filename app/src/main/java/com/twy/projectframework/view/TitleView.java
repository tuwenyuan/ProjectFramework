package com.twy.projectframework.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.twy.projectframework.R;
import com.twy.projectframework.listener.OnTitleClickListener;
import com.twy.projectframework.utils.CommonUtil;


/**
 * Created by twy on 2017/6/1.
 */

public class TitleView extends FrameLayout implements View.OnClickListener {
    private ImageView ivLeft;
    private TextView tvLeft;
    public TextView tvCenter;
    private ImageView ivRight;
    public TextView tvRight;
    private View line;
    private View right;
    private View left;
    private OnTitleClickListener onTitleClickListener;
    public View statusBar;
    private View ll;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_title,null);
        initView(view);
        addView(view);
    }

    private void initView(View view) {
        right = view.findViewById(R.id.ll_right);
        left = view.findViewById(R.id.ll_left);
        ivLeft = view.findViewById(R.id.iv_title_left_icon);
        tvLeft = view.findViewById(R.id.tv_title_left_msg);
        tvCenter = view.findViewById(R.id.tv_title_center_msg);
        ivRight = view.findViewById(R.id.iv_title_right_icon);
        tvRight = view.findViewById(R.id.tv_title_right_msg);
        ll = view.findViewById(R.id.ll);
        statusBar = view.findViewById(R.id.v_status_bar);
        line = view.findViewById(R.id.v_title_line);
        this.setVisibility(GONE);
        statusBar.getLayoutParams().height = CommonUtil.getStatusBarHeight(getContext());
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        tvCenter.setOnClickListener(this);
    }

    /**
     * 设置标题
     * @param text
     */
    public void setTitle(String text){
        tvCenter.setVisibility(VISIBLE);
        tvCenter.setText(text);
    }


    /**
     * 显示title
     * @param leftIcon 左边图标 null 代表不显示图标
     * @param leftText 左边文案 null 代表不显示
     * @param title 标题
     * @param rightIcon 右边图标
     * @param rightText 右边文案
     * @param listener 右边/左边点击监听事件
     */
    public void showTitleView(Integer leftIcon, String leftText, String title, Integer rightIcon, String rightText,OnTitleClickListener listener, boolean showDriverLine){
        this.setVisibility(VISIBLE);
        this.onTitleClickListener = listener;
        if(leftIcon!=null){
            ivLeft.setImageResource(leftIcon);
        }else{
            ivLeft.setVisibility(GONE);
        }
        if(leftText!=null){
            tvLeft.setText(leftText);
        }else{
            tvLeft.setVisibility(GONE);
        }
        if(title!=null){
            tvCenter.setText(title);
        }else{
            tvCenter.setVisibility(GONE);
        }
        if(rightIcon!=null){
            ivRight.setImageResource(rightIcon);
        }else{
            ivRight.setVisibility(GONE);
        }
        if(rightText!=null){
            tvRight.setText(rightText);
        }else{
            tvRight.setVisibility(INVISIBLE);
        }
        if(showDriverLine){
            line.setVisibility(VISIBLE);
        }else {
            line.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_left:
                if(tvLeft.getVisibility()== VISIBLE || ivLeft.getVisibility()==VISIBLE) {
                    if (onTitleClickListener != null)
                        onTitleClickListener.onLeftClick();
                }
                break;
            case R.id.ll_right:
                if(tvRight.getVisibility()== VISIBLE || ivRight.getVisibility()==VISIBLE) {
                    if (onTitleClickListener != null)
                        onTitleClickListener.onRightClick();
                }
                break;
        }
    }

    public int getTitleHeight(){
        return ll.getHeight();
    }

}
