package mysquar.com.sample.movies.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import mysquar.com.sample.movies.domain.usecase.base.BaseUsecase;
import rx.Observable;


/**
 * Created by phannguyen on 5/23/17.
 */

public class RetrieveMoviesListUC implements BaseUsecase<List<? extends IMovie>> {

    private final IApiMovieService apiMovieService;
    private final ILocalService localService;

    @Inject
    public RetrieveMoviesListUC(IApiMovieService apiMovieService, ILocalService localService) {
        this.apiMovieService = apiMovieService;
        this.localService = localService;
    }

    @Override
    public Observable<List<? extends IMovie>> buildUseCase() {
        return localService.getMovies()
                .flatMap(iMovies -> {
                    if (iMovies != null && !iMovies.isEmpty()) {
                        return Observable.just(iMovies);
                    } else {
                        return apiMovieService.retrieveMoviesList()
                                .flatMap(iMovies1 -> localService.saveMovies(iMovies1)
                                        .map(aVoid -> iMovies1));
                    }
                });

//        return localService.getMovies().switchIfEmpty(apiMovieService.retrieveMoviesList());
    }
}
