package com.mysquar.sample.movies.ui.movies;

import android.util.Log;

import com.mysquar.sample.movies.ui.base.BaseViewModel;
import com.mysquar.sample.movies.ui.movies.item.MovieItemViewModel;

import me.henrytao.mvvmlifecycle.event.Event1;
import me.henrytao.mvvmlifecycle.rx.Transformer;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieListViewModel extends BaseViewModel<MovieListViewModel.State> {

    RetrieveMoviesListUC moviesListUC;

    public MovieListViewModel(RetrieveMoviesListUC retrieveMoviesListUC) {
//        Injector.component.inject(this);
        this.moviesListUC = retrieveMoviesListUC;
    }

    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        manageSubscription(subscribe(MovieItemViewModel.Event.ON_TASK_ITEM_CLICK, new Event1<>(this::onMovieItemClick)),
                UnsubscribeLifeCycle.DESTROY);
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

    protected void onMovieItemClick(IMovie movie) {
        setState(State.CLICK_MOVIE, "id", movie.getId());
    }

    public enum State {
        RELOADED_MOVIES,
        CLICK_MOVIE
    }
}
