package com.mysquar.sample.movies;

import android.app.Application;

import com.mysquar.sample.movies.di.AppModule;
import com.mysquar.sample.movies.di.component.AppComponent;
import com.mysquar.sample.movies.di.component.DaggerAppComponent;

import mysquar.com.sample.movies.data.apiws.db.AppDB;

/**
 * Created by phannguyen on 5/23/17.
 */

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        AppDB.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
