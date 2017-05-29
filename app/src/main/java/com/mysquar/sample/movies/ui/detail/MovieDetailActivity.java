package com.mysquar.sample.movies.ui.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.mysquar.sample.movies.R;
import com.mysquar.sample.movies.databinding.ActivityDetailMovieBinding;
import com.mysquar.sample.movies.ui.base.BaseActivity;
import com.mysquar.sample.movies.ui.detail.di.DetailMovieModule;

/**
 * Created by phannguyen on 5/29/17.
 */

public class MovieDetailActivity extends BaseActivity<ActivityDetailMovieBinding>{
    DetaiMovieViewModel movieViewModel;
    @Override
    public void onSetContentView(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        mBinding.setViewModel(movieViewModel);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onInitializeViewModels() {
        Bundle bundle = getIntent().getExtras();
        int movieId = bundle.getInt("id");
        movieViewModel = getAppComponent().plus(new DetailMovieModule(movieId)).getDetailViewModel();
        addViewModel(movieViewModel);
    }

    @Override
    public void onCreate() {
        super.onCreate();

       // ResourceUtils.supportDrawableTint(this, mBinding.toolbar, ResourceUtils.Palette.PRIMARY);
        //init();
    }
//
//    private void init(){
//        manageSubscription(movieViewModel.getState().subscribe(state -> {
//            switch (state.getName()) {
//                case RELOADED_MOVIE:
//                    IMovie movie =  state.getData().get("movie", IMovie.class);
//                    movieViewModel.setData(movie);
//                    break;
//            }
//        }), UnsubscribeLifeCycle.DESTROY_VIEW);
//    }
}
