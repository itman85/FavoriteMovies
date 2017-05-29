package com.mysquar.sample.movies.ui.base;

import android.databinding.ViewDataBinding;

import com.mysquar.sample.movies.MyApp;
import com.mysquar.sample.movies.di.component.AppComponent;

import me.henrytao.mvvmlifecycle.MVVMActivity;

/**
 * Created by phannguyen on 5/24/17.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends MVVMActivity {
    protected T mBinding;

    public AppComponent getAppComponent() {
        return ((MyApp) getApplication()).getAppComponent();
    }
}
