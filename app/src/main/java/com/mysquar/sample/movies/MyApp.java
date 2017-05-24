package com.mysquar.sample.movies;

import android.app.Application;

import com.mysquar.sample.movies.di.AppModule;
import com.mysquar.sample.movies.di.component.AppComponent;
import com.mysquar.sample.movies.di.component.DaggerAppComponent;

/**
 * Created by phannguyen on 5/23/17.
 */

public class MyApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
