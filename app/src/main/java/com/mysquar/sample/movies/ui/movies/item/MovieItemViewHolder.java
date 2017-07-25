package com.mysquar.sample.movies.ui.movies.item;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mysquar.sample.movies.R;
import com.mysquar.sample.movies.databinding.MovieItemBinding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

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
        //Uri uri = Uri.parse("http://image.tmdb.org/t/p/w185/"+data.getPosterUrl());
        //SimpleDraweeView draweeView = (SimpleDraweeView) itemView.findViewById(R.id.movie_poster_image);
        //draweeView.setImageURI(uri);
    }

    @BindingAdapter({"draweeImageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        //Uri uri = Uri.parse("http://image.tmdb.org/t/p/w185/"+data.getPosterUrl());
        view.setImageURI(imageUrl);
    }

    @Override
    public void onInitializeViewModels() {
        mViewModel = new MovieItemViewModel();
        addViewModel(mViewModel);
    }
}
