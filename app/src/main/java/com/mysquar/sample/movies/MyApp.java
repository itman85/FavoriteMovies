package com.mysquar.sample.movies;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mysquar.sample.movies.di.AppModule;
import com.mysquar.sample.movies.di.component.AppComponent;
import com.mysquar.sample.movies.di.component.DaggerAppComponent;

import android.app.Application;

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
        //AppDB.init(this);
        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
