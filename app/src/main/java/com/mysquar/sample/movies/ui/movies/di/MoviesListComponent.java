package com.mysquar.sample.movies.ui.movies.di;

import com.mysquar.sample.movies.ui.movies.MovieListViewModel;

import dagger.Subcomponent;

/**
 * Created by phannguyen on 5/29/17.
 */
@Subcomponent(modules = MoviesListModule.class)
public interface MoviesListComponent {
    MovieListViewModel getMoviesListViewModel();
}
