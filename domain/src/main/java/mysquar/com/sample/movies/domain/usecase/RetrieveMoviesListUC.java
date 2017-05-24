package mysquar.com.sample.movies.domain.usecase;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.usecase.base.BaseUsecase;
import rx.Observable;

/**
 * Created by phannguyen on 5/23/17.
 */

public class RetrieveMoviesListUC implements BaseUsecase<List<? extends IMovie>>{

    IApiMovieService apiMovieService;

    public RetrieveMoviesListUC(IApiMovieService apiMovieService) {
        this.apiMovieService = apiMovieService;
    }

    @Override
    public Observable<List<? extends IMovie>> buildUseCase() {
        return apiMovieService.retrieveMoviesList();
    }
}
