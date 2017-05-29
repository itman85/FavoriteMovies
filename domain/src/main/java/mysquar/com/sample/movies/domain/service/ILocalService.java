package mysquar.com.sample.movies.domain.service;

import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import rx.Observable;


/**
 * Created by phannguyen on 5/25/17.
 */

public interface ILocalService {
    Observable<List<? extends IMovie>> getMovies();
    Observable<Void> saveMovies(List<? extends IMovie> movies);
    Observable<IMovie> getDetailMovie(int id);
    Observable<Void> saveMovie(IMovie movie);

}
