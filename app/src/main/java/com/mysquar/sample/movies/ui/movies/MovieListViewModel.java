package com.mysquar.sample.movies.ui.movies;

import android.util.Log;

import com.mysquar.sample.movies.di.Injector;
import com.mysquar.sample.movies.ui.base.BaseViewModel;

import javax.inject.Inject;

import me.henrytao.mvvmlifecycle.rx.Transformer;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieListViewModel extends BaseViewModel<MovieListViewModel.State> {

    @Inject
    RetrieveMoviesListUC moviesListUC;

    public MovieListViewModel() {
        Injector.component.inject(this);
    }

    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
//        compositeSubscription.add(moviesListUC.buildUseCase()
//                .compose(Transformer.applyIoScheduler())
//                .subscribe(movies -> {
//                    mMovies.clear();
//                    mMovies.addAll(movies);
//                    setState(State.RELOADED_MOVIES);
//                }, throwable -> {
//                    Log.e("TEST", throwable.getMessage());
//                }));
    }

    @Override
    public void onDestroy() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
        super.onDestroy();
    }

    protected void loadData() {
        manageSubscription(moviesListUC.buildUseCase()
                .compose(Transformer.applyIoScheduler())
                .subscribe(movies -> {
                    setState(State.RELOADED_MOVIES,
                            "movies", movies);
                }, throwable -> {
                    Log.e("TEST", throwable.getMessage());
                }), UnsubscribeLifeCycle.DESTROY);
    }

    public enum State {
        RELOADED_MOVIES
    }
}
