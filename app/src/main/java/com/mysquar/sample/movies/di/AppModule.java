package com.mysquar.sample.movies.di;

import com.mysquar.sample.movies.MyApp;

import dagger.Module;
import mysquar.com.sample.movies.data.apiws.di.DataModule;
import mysquar.com.sample.movies.domain.di.DomainModule;

/**
 * Created by phannguyen on 5/23/17.
 */
@Module(includes = {DataModule.class, DomainModule.class})
public class AppModule {
    private MyApp mApp;

    public AppModule(MyApp app) {
        mApp = app;
    }

   /*@Provides
    @Singleton
    @ApplicationContext
    public Context provideAppContext() {
        return mApp;
    }*/
}
