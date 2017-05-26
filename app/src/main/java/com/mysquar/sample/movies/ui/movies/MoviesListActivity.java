package com.mysquar.sample.movies.ui.movies;

import android.databinding.DataBindingUtil;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mysquar.sample.movies.R;
import com.mysquar.sample.movies.databinding.ActivityMoviesListBinding;
import com.mysquar.sample.movies.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import me.henrytao.mvvmlifecycle.MVVMObserver;
import me.henrytao.mvvmlifecycle.recyclerview.RecyclerViewBindingAdapter;
import me.henrytao.mvvmlifecycle.recyclerview.RecyclerViewBindingViewHolder;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.model.IMovie;

public class MoviesListActivity extends BaseActivity<ActivityMoviesListBinding> {

  private MovieListViewModel mViewModel;
  private Adapter mAdapter;

  @Override
  public void onSetContentView(Bundle savedInstanceState) {
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);
    mBinding.setViewModel(mViewModel);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
  }


  private void init() {
    mAdapter = new Adapter(this);
    mBinding.list.setAdapter(mAdapter);
    mBinding.list.setLayoutManager(new LinearLayoutManager(this));


    manageSubscription(mViewModel.getState().subscribe(state -> {
      switch (state.getName()) {
        case RELOADED_MOVIES:
          List<IMovie> movieList = state.getData().getList("movies", IMovie.class);
          mAdapter.setMovies(movieList);
          break;
      }
    }), UnsubscribeLifeCycle.DESTROY_VIEW);
  }

  @Override
  public void onInitializeViewModels() {
    mViewModel = new MovieListViewModel();
    addViewModel(mViewModel);
  }

  private static class Adapter extends RecyclerViewBindingAdapter<IMovie, MovieItemViewHolder> {

    public Adapter(MVVMObserver observer) {
      super(MovieItemViewHolder.class, observer, new ArrayList<>());
    }

    public void setMovies(List<IMovie> movies) {
      mData.clear();
      mData.addAll(movies);
      notifyDataSetChanged();
    }
  }
}
