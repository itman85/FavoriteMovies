package com.mysquar.sample.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;
import rx.Subscriber;

public class MoviesListActivity extends AppCompatActivity {

  //    @Inject
  RetrieveMoviesListUC moviesListUC;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movies_list);
    init();
  }


  private void init() {
    moviesListUC = ((MyApp) getApplication()).getAppComponent().getRetrieveMoviesListUC();
    moviesListUC.buildUseCase().subscribe(new Subscriber<List<? extends IMovie>>() {
      @Override
      public void onCompleted() {
        Log.i("Test", "Complete");
      }

      @Override
      public void onError(Throwable e) {
        Log.e("Test", "Error " + e.getMessage());
      }

      @Override
      public void onNext(List<? extends IMovie> iMovies) {
        //
        Log.i("Test", "got data" + iMovies.size());
      }
    });
  }
}
