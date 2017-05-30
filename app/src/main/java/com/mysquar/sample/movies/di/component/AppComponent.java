package com.mysquar.sample.movies.di.component;

import com.mysquar.sample.movies.di.AppModule;
import com.mysquar.sample.movies.ui.detail.di.DetailMovieComponent;
import com.mysquar.sample.movies.ui.detail.di.DetailMovieModule;
import com.mysquar.sample.movies.ui.movies.di.MoviesListComponent;
import com.mysquar.sample.movies.ui.movies.di.MoviesListModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by phannguyen on 5/23/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

  //RetrieveMoviesListUC getRetrieveMoviesListUC();

//    void inject(MovieListViewModel viewModel);
//
//    void inject(DetaiMovieViewModel viewModel);

//    void inject(MoviesListActivity activity);

  DetailMovieComponent plus(DetailMovieModule detailMovieModule);

  MoviesListComponent plus(MoviesListModule moviesListModule);
}
