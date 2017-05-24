package mysquar.com.sample.movies.data.apiws;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
       return movieApiWS.geFavoriteTopMovies(1).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).map(resultModel -> resultModel.getResults());
    }


    @Override
    public Observable<IMovie> getMovie(String id) {
        return null;
    }
}
