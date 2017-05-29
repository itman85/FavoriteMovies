package com.mysquar.sample.movies.ui.movies.item;

import android.databinding.ObservableField;

import com.mysquar.sample.movies.ui.base.BaseViewModel;

import mysquar.com.sample.movies.domain.model.IMovie;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieItemViewModel extends BaseViewModel {

    public ObservableField<String> description = new ObservableField<>();

    public ObservableField<String> title = new ObservableField<>();

    private IMovie movie;

    public MovieItemViewModel() {
        register(this, Event.ON_TASK_ITEM_CLICK);
    }

    public void setMovie(IMovie movieModel){
        this.movie = movieModel;
        description.set(this.movie.getDescription());
        title.set(this.movie.getTitle());

    }

    public void onItemClick() {
        dispatch(Event.ON_TASK_ITEM_CLICK, this.movie);
    }

    public enum Event {
        ON_TASK_ITEM_CLICK,
    }
}
