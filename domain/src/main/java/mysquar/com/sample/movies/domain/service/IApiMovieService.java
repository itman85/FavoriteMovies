package mysquar.com.sample.movies.domain.service;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import rx.Observable;

/**
 * Created by phannguyen on 5/23/17.
 */

public interface IApiMovieService {
    Observable<List<? extends IMovie>> retrieveMoviesList();

    Observable<IMovie> getMovie(String id);
}
