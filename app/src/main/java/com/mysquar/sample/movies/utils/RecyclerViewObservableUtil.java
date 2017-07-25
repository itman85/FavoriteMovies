package com.mysquar.sample.movies.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by phannguyen on 6/1/17.
 */

public class RecyclerViewObservableUtil {

  public static Observable<RecycleScrollInfo> observeScroll(RecyclerView view){

    return Observable.create(subscriber -> {
      view.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);

          RecycleScrollInfo info = new RecycleScrollInfo(
              ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition(),
              recyclerView.getLayoutManager().getChildCount(),
              recyclerView.getLayoutManager().getItemCount(),
              recyclerView.getScrollState());
         // Log.d("RecycleView",info.toString());
          subscriber.onNext(info);
        }

      });
    });

  }

  public static Observable<Integer> observeState(RecyclerView view){

    return Observable.create(subscriber -> {
      view.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
          super.onScrollStateChanged(recyclerView, newState);
          subscriber.onNext(newState);
        }
      });
    });
  }

  public static Observable<Boolean> observeLoadMore(RecyclerView view,long time){
   return
       observeState(view).debounce(time,TimeUnit.MILLISECONDS)
       .filter(state->state==RecyclerView.SCROLL_STATE_IDLE)
       .flatMap(state->observeScroll(view))
       .filter(info-> info.getVisibleItemCount() + info.getPastVisiblesItems() >= info.getTotalItemCount())
       .flatMap(info->Observable.just(true));

  }
}
