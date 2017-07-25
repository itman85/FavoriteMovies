package com.mysquar.sample.movies.ui.movies;

import com.mysquar.sample.movies.R;
import com.mysquar.sample.movies.databinding.ActivityMoviesListBinding;
import com.mysquar.sample.movies.ui.base.BaseActivity;
import com.mysquar.sample.movies.ui.detail.MovieDetailActivity;
import com.mysquar.sample.movies.ui.movies.di.MoviesListModule;
import com.mysquar.sample.movies.ui.movies.item.MovieItemViewHolder;
import com.mysquar.sample.movies.utils.RecyclerViewObservableUtil;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.henrytao.mvvmlifecycle.MVVMObserver;
import me.henrytao.mvvmlifecycle.recyclerview.RecyclerViewBindingAdapter;
import me.henrytao.mvvmlifecycle.rx.UnsubscribeLifeCycle;
import mysquar.com.sample.movies.domain.model.IMovie;
import rx.subjects.PublishSubject;

public class MoviesListActivity extends BaseActivity<ActivityMoviesListBinding> {

  private static final String TAG = MoviesListActivity.class.getSimpleName();

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
    mLayoutManager = new LinearLayoutManager(this);
    mBinding.list.setLayoutManager(mLayoutManager);
    //setup scroll event for list movie
    setupListScroll();

    manageSubscription(mViewModel.getState().subscribe(state -> {
      switch (state.getName()) {
        case RELOADED_MOVIES:
          List<IMovie> movieList = state.getData().getList("movies", IMovie.class);
          mAdapter.setMovies(movieList);
          break;
        case CLICK_MOVIE:
          Intent intent = new Intent(this, MovieDetailActivity.class);
          Bundle bundle = new Bundle();
          bundle.putInt("id", state.getData().getInt("id"));
          intent.putExtras(bundle);
          startActivity(intent);
          break;
        case LOADMORE_MOVIES:
          List<IMovie> movieList1 = state.getData().getList("movies", IMovie.class);
          mAdapter.addMoreMovies(movieList1);
          loading = false;
          break;
      }
    }), UnsubscribeLifeCycle.DESTROY_VIEW);
  }

  private boolean loading = false;

  LinearLayoutManager mLayoutManager;

  int pastVisiblesItems, visibleItemCount, totalItemCount;

  PublishSubject<Integer> recycleSubject = PublishSubject.create();

  PublishSubject<Boolean> bottomSubject = PublishSubject.create();

  private void setupListScroll() {
    RecyclerViewObservableUtil.observeLoadMore(mBinding.list, 500)
        .filter(abool -> abool && !loading)
        .subscribe(aObj -> {
          Log.d(TAG, "onNext");
          loading = true;
          mViewModel.loadMore();
        });

    //mBinding.list.addOnScrollListener(new OnScrollListener() {
    //  @Override
    //  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    //    if (dy > 0) //check for scroll down
    //    {
    //      visibleItemCount = mLayoutManager.getChildCount();
    //      totalItemCount = mLayoutManager.getItemCount();
    //      pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
    //
    //      if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && !loading) {
    //        bottomSubject.onNext(true);
    //      }
    //    }
    //
    //  }
    //
    //  @Override
    //  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    //    super.onScrollStateChanged(recyclerView, newState);
    //    recycleSubject.onNext(newState);
    //  }
    //});
    //
    //
    // Observable<Boolean> sub1 =  recycleSubject.debounce(400,TimeUnit.MILLISECONDS)
    //    .filter(state -> state == SCROLL_STATE_IDLE).flatMap(state->
    // Observable.just(true));
    //
    //Observable<Boolean> sub2 = bottomSubject.debounce(400,TimeUnit.MILLISECONDS)
    //    .filter(abool->abool).flatMap(state->
    //    Observable.just(true));
    //Observable.zip(sub1,sub2,(state1,state2)->state1&state2).subscribe(state->mViewModel.loadMore());

  }

  @Override
  public void onInitializeViewModels() {
    mViewModel = getAppComponent().plus(new MoviesListModule()).getMoviesListViewModel();
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

    public void addMoreMovies(List<IMovie> movies) {
      int currentSize = mData.size();
      mData.addAll(movies);
      //notifyDataSetChanged();
      notifyItemRangeChanged(currentSize, movies.size());
    }
  }
}
