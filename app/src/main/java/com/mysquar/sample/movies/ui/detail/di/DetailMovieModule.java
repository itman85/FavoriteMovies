package com.mysquar.sample.movies.ui.detail.di;

import com.mysquar.sample.movies.ui.detail.DetaiMovieViewModel;

import dagger.Module;
import dagger.Provides;
import mysquar.com.sample.movies.domain.usecase.GetDetailMovieUC;

/**
 * Created by phannguyen on 5/29/17.
 */
@Module
public class DetailMovieModule {

    private final int movieId;

    public DetailMovieModule(int movieId) {
        this.movieId = movieId;
    }

    @Provides
    DetaiMovieViewModel provideDetaiMovieViewModel(GetDetailMovieUC getDetailMovieUC) {
        return new DetaiMovieViewModel(movieId, getDetailMovieUC);
    }
}
