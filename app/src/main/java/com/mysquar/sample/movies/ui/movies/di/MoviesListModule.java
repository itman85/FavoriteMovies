package com.mysquar.sample.movies.ui.movies.di;

import com.mysquar.sample.movies.ui.movies.MovieListViewModel;

import dagger.Module;
import dagger.Provides;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;

/**
 * Created by phannguyen on 5/29/17.
 */
@Module
public class MoviesListModule {
    @Provides
    MovieListViewModel provideMovieListViewModel(RetrieveMoviesListUC retrieveMoviesListUC){
        return new MovieListViewModel(retrieveMoviesListUC);

    }
}
