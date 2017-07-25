package mysquar.com.sample.movies.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.usecase.base.BaseUsecase;
import rx.Observable;

/**
 * Created by phannguyen on 5/31/17.
 */

public class LoadMoreMoviesUC implements BaseUsecase<List<? extends IMovie>> {


  IApiMovieService apiMovieService;

  @Inject
  public LoadMoreMoviesUC(IApiMovieService apiMovieService) {
    this.apiMovieService = apiMovieService;
  }
  private int currentPage;
  public LoadMoreMoviesUC with(int page){
    currentPage = page;
    return this;
  }

  @Override
  public Observable<List<? extends IMovie>> buildUseCase() {
    return apiMovieService.loadMoreMoviesList(currentPage);
  }
}
