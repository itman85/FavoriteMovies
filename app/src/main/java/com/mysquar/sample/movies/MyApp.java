package com.mysquar.sample.movies;

import android.app.Application;

import com.mysquar.sample.movies.di.Injector;

import mysquar.com.sample.movies.data.apiws.db.AppDB;

/**
 * Created by phannguyen on 5/23/17.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initialize(this);
        AppDB.init(this);
    }

}
