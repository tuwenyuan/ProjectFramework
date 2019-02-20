package com.twy.projectframework;

import android.support.v4.util.SparseArrayCompat;

import com.twy.projectframework.base.BaseFragment;

public class FragmentFactory {

    private static SparseArrayCompat<BaseFragment> mainFragmentCaches = new SparseArrayCompat<>();

    public static BaseFragment getMainFragment(int index) {
        BaseFragment fragment = mainFragmentCaches.get(index);
        if (fragment == null) {
            fragment = new MyFragment();
            ((MyFragment) fragment).setIndex(index);
            /*switch (index) {
                case 0:
                    //fragment = new CJYFragment();
                    fragment = new MyFragment();
                    break;
                case 1:
                    //fragment = new ShoppingFragment();
                    fragment = new ShoppingFragment1();
                    break;
                case 2:
                    fragment = new OrderFragment1();
                    break;
                case 3:
                    fragment = new MyFragment();
                    break;
            }*/
            mainFragmentCaches.put(index,fragment);
        }
        return fragment;
    }

}
