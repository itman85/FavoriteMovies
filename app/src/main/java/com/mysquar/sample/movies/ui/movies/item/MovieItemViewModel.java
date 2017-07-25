package com.mysquar.sample.movies.ui.movies.item;

import com.mysquar.sample.movies.ui.base.BaseViewModel;

import android.databinding.ObservableField;

import mysquar.com.sample.movies.domain.model.IMovie;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieItemViewModel extends BaseViewModel {

    public ObservableField<String> description = new ObservableField<>();

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> number = new ObservableField<>();
    public ObservableField<String> posterUrl = new ObservableField<>();

    private IMovie movie;

    public MovieItemViewModel() {
        register(this, Event.ON_TASK_ITEM_CLICK);
    }

    public void setMovie(IMovie movieModel){
        this.movie = movieModel;
        description.set(this.movie.getDescription());
        title.set(this.movie.getTitle());
        number.set(this.movie.getId()+"");
        posterUrl.set("http://image.tmdb.org/t/p/w185/"+movieModel.getPosterUrl());

    }

    public void onItemClick() {
        dispatch(Event.ON_TASK_ITEM_CLICK, this.movie);
    }

    public enum Event {
        ON_TASK_ITEM_CLICK,
    }
}
