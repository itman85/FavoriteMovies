package com.mysquar.sample.movies.ui.detail;

import android.databinding.ObservableField;
import android.util.Log;

import com.mysquar.sample.movies.ui.base.BaseViewModel;

import me.henrytao.mvvmlifecycle.rx.Transformer;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.usecase.GetDetailMovieUC;

/**
 * Created by phannguyen on 5/29/17.
 */

public class DetaiMovieViewModel extends BaseViewModel<DetaiMovieViewModel.State> {

    GetDetailMovieUC getDetailMovieUC;
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    private int movieId;
    public DetaiMovieViewModel(int movieId, GetDetailMovieUC getDetailMovieUC){
        this.movieId = movieId;
        this.getDetailMovieUC = getDetailMovieUC;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
    }

    protected void loadData(){
        manageSubscription(getDetailMovieUC.with(movieId).buildUseCase().compose(Transformer.applyIoScheduler())
                .subscribe(movie->{
                    title.set(movie.getTitle());
                    description.set(movie.getDescription());
                    //setState(State.RELOADED_MOVIE,"movie",movie);
                },throwable -> {
                    Log.e("TEST", throwable.getMessage());
                }), UnsubscribeLifeCycle.DESTROY);

    }

    public void setData(IMovie movie){
        title.set(movie.getTitle());
        description.set(movie.getDescription());
    }
    public enum State {
        RELOADED_MOVIE
    }

}
