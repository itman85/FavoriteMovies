package com.mysquar.sample.movies.ui.base;

import android.databinding.ViewDataBinding;

import me.henrytao.mvvmlifecycle.MVVMActivity;

/**
 * Created by phannguyen on 5/24/17.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends MVVMActivity {
    protected T mBinding;


}
