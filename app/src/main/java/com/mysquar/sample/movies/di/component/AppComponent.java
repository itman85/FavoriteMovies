package com.mysquar.sample.movies.di.component;

import android.content.Context;

import com.mysquar.sample.movies.di.AppModule;
import com.mysquar.sample.movies.di.ApplicationContext;
import com.mysquar.sample.movies.ui.movies.MovieListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by phannguyen on 5/23/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //RetrieveMoviesListUC getRetrieveMoviesListUC();

    void inject(MovieListViewModel viewModel);

//    void inject(MoviesListActivity activity);

    @ApplicationContext
    Context getAppContext();
}
