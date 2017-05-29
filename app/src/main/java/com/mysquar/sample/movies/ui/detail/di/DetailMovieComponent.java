package com.mysquar.sample.movies.ui.detail.di;

import com.mysquar.sample.movies.ui.detail.DetaiMovieViewModel;

import dagger.Subcomponent;

/**
 * Created by phannguyen on 5/29/17.
 */
@Subcomponent(modules = DetailMovieModule.class)
public interface DetailMovieComponent {

    DetaiMovieViewModel getDetailViewModel();
}
