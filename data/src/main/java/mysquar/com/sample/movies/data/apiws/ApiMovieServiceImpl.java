package mysquar.com.sample.movies.data.apiws;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import rx.Observable;


/**
 * Created by phannguyen on 5/23/17.
 */

public class ApiMovieServiceImpl implements IApiMovieService {
    IMovieApiWS movieApiWS;

    public ApiMovieServiceImpl(IMovieApiWS movieApiWS) {
        this.movieApiWS = movieApiWS;
    }

    @Override
    public Observable<List<? extends IMovie>> retrieveMoviesList() {
        return movieApiWS.geFavoriteTopMovies(1)
                .map(resultModel -> resultModel.getResults());
    }

    @Override
    public Observable<? extends IMovie> getDetailMovie(int id) {
        return movieApiWS.geMovieById(id);
    }

    @Override
    public Observable<List<? extends IMovie>> loadMoreMoviesList(int page) {
        return movieApiWS.geFavoriteTopMovies(page)
            .map(resultModel -> resultModel.getResults());
    }

}
