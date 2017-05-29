package mysquar.com.sample.movies.domain.usecase;

import javax.inject.Inject;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import mysquar.com.sample.movies.domain.usecase.base.BaseUsecase;
import rx.Observable;

/**
 * Created by phannguyen on 5/29/17.
 */

public class GetDetailMovieUC implements BaseUsecase<IMovie> {
    ILocalService localService;
    IApiMovieService apiMovieService;

    @Inject
    public GetDetailMovieUC(ILocalService localService, IApiMovieService apiMovieService) {
        this.localService = localService;
        this.apiMovieService = apiMovieService;
    }

    private int movieId;

    public GetDetailMovieUC with(int id){
        movieId = id;
        return this;
    }

    @Override
    public Observable<IMovie> buildUseCase() {
        return localService.getDetailMovie(movieId)
                .flatMap(movie->{
                    if(movie!=null)
                        return Observable.just(movie);
                    else{
                        return apiMovieService.getDetailMovie(movieId)
                                .flatMap(movie1->localService.saveMovie(movie1)
                                .map(aVoid -> movie1));
                    }

                });
    }
}
