package com.mysquar.sample.movies.ui.movies;

import com.mysquar.sample.movies.ui.base.BaseViewModel;
import com.mysquar.sample.movies.ui.movies.item.MovieItemViewModel;

import android.util.Log;

import me.henrytao.mvvmlifecycle.event.Event1;
import me.henrytao.mvvmlifecycle.rx.Transformer;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.usecase.LoadMoreMoviesUC;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieListViewModel extends BaseViewModel<MovieListViewModel.State> {

    RetrieveMoviesListUC moviesListUC;
    LoadMoreMoviesUC loadMoreMoviesUC;

    int currentPage;

    public MovieListViewModel(RetrieveMoviesListUC retrieveMoviesListUC,LoadMoreMoviesUC loadMoreMoviesUC) {
//        Injector.component.inject(this);
        this.moviesListUC = retrieveMoviesListUC;
        this.loadMoreMoviesUC = loadMoreMoviesUC;
    }

   // private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        manageSubscription(subscribe(MovieItemViewModel.Event.ON_TASK_ITEM_CLICK, new Event1<>(this::onMovieItemClick)),
                UnsubscribeLifeCycle.DESTROY);
        loadData();
        currentPage = 1;
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
        //if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
        //    compositeSubscription.clear();
        //}
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

    public void loadMore(){
        manageSubscription(loadMoreMoviesUC.with(currentPage++).buildUseCase()
            .compose(Transformer.applyIoScheduler())
            .filter(movies->movies!=null && !movies.isEmpty())
            .subscribe(movies -> {
                setState(State.LOADMORE_MOVIES,
                    "movies", movies);
            }, throwable -> {
                Log.e("TEST", throwable.getMessage());
            }), UnsubscribeLifeCycle.DESTROY);
    }

    public enum State {
        RELOADED_MOVIES,
        LOADMORE_MOVIES,
        CLICK_MOVIE
    }
}
