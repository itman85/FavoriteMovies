package com.mysquar.sample.movies.di.component;

import com.mysquar.sample.movies.di.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;

/**
 * Created by phannguyen on 5/23/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    RetrieveMoviesListUC getRetrieveMoviesListUC();

//    void inject(MoviesListActivity activity);

    //@ApplicationContext
    //Context getAppContext();
}
