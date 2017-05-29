package com.mysquar.sample.movies.ui.movies.item;

import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.mysquar.sample.movies.R;
import com.mysquar.sample.movies.databinding.MovieItemBinding;

import me.henrytao.mvvmlifecycle.MVVMObserver;
import me.henrytao.mvvmlifecycle.recyclerview.RecyclerViewBindingViewHolder;
import mysquar.com.sample.movies.domain.model.IMovie;

/**
 * Created by phannguyen on 5/24/17.
 */

public class MovieItemViewHolder extends RecyclerViewBindingViewHolder<IMovie> {

    private final MovieItemBinding mBinding;
    private MovieItemViewModel mViewModel;


    public MovieItemViewHolder(MVVMObserver observer, ViewGroup parent) {
        super(observer, parent, R.layout.movie_item);
        mBinding = DataBindingUtil.bind(itemView);
        mBinding.setViewModel(mViewModel);
    }


    @Override
    public void bind(IMovie data) {
        mViewModel.setMovie(data);
        mBinding.executePendingBindings();
    }

    @Override
    public void onInitializeViewModels() {
        mViewModel = new MovieItemViewModel();
        addViewModel(mViewModel);
    }
}
