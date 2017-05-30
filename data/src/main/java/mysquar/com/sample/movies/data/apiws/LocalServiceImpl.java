package mysquar.com.sample.movies.data.apiws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mysquar.com.sample.movies.data.apiws.db.AppDB;
import mysquar.com.sample.movies.data.apiws.db.EMovie;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.ILocalService;
import rx.Observable;


/**
 * Created by phannguyen on 5/25/17.
 */

public class LocalServiceImpl implements ILocalService {
    AppDB mAppDB;

    public LocalServiceImpl(AppDB appDB){
        mAppDB = appDB;
    }
    @Override
    public Observable<List<? extends IMovie>> getMovies() {
        return Observable.defer(() -> {
           try {
               List<EMovie> movies = mAppDB.movieDao().loadAllMovies();
               return Observable.just(movies);
           } catch (Exception ex) {
               return Observable.error(ex);
           }
        }).map(eMovies -> {
            List<IMovie> movies = new ArrayList<>();
            for(EMovie item: eMovies){
                movies.add(item.cast());
            }
            return movies;
        });

//        return getMoviesData2().map(eMovies -> {
//            List<IMovie> movies = new ArrayList<>();
//            for(EMovie item: eMovies){
//                movies.add(item.cast());
//            }
//            return movies;
//        });
    }



    @Override
    public Observable<Void> saveMovies(List<? extends IMovie> movies) {

        return Observable.defer(() -> {
            try {
                List<EMovie> eMovies = new ArrayList<>();
                for(IMovie iMovie:movies){
                    eMovies.add(new EMovie(iMovie));
                }
                mAppDB.movieDao().insertOrReplaceMovies(eMovies);
                return Observable.just(null);
            } catch (Exception ex) {
                return Observable.error(ex);
            }
        });

        /*return Observable.just(movies).map(imovies->{
            List<EMovie> eMovies = new ArrayList<>();
            for(IMovie iMovie:imovies){
                eMovies.add(new EMovie(iMovie));
            }
            AppDB.getDatabase().movieDao().insertOrReplaceMovies(eMovies);
            return null;
        });*/



        /*List<EMovie> eMovies = new ArrayList<>();
        for(IMovie iMovie:movies){
            eMovies.add(new EMovie(iMovie));
        }

        //return AppDB.getDatabase().movieDao().insertOrReplaceMovies((EMovie[]) eMovies.toArray()).subscribeOn(Schedulers.io());
        return setMoviesData2(eMovies);*/
    }

    @Override
    public Observable<IMovie> getDetailMovie(int id) {
        return Observable.defer(() -> {
            try {
                EMovie movie = mAppDB.movieDao().loadMovieById(id);
                return Observable.just(movie);
            } catch (Exception ex) {
                return Observable.error(ex);
            }
        }).map(eMovie -> eMovie.cast());
    }

    @Override
    public Observable<Void> saveMovie(IMovie movie) {
        return Observable.defer(() -> {
            try {
                EMovie eMovie = new EMovie(movie);
                mAppDB.movieDao().insertOrReplaceMovies(Arrays.asList(eMovie));
                return Observable.just(null);
            } catch (Exception ex) {
                return Observable.error(ex);
            }
        });
    }

    /*
    Callable<List<EMovie>> getMoviesData1() {
        return new Callable<List<EMovie>>() {
            @Override
            public List<EMovie> call() {
                return getDatabase().movieDao().loadAllMovies();
            }
        };
    }

    Observable<List<EMovie>> getMoviesData2() {
        return makeObservable(getMoviesData1())
                .subscribeOn(Schedulers.computation());
    }

    Callable<Void> setMoviesData1(List<EMovie> eMovies) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                getDatabase().movieDao().insertOrReplaceMovies(eMovies);
                return null;
            }
        };
    }

    Observable<Void> setMoviesData2(List<EMovie> eMovies) {
        return makeObservable(setMoviesData1(eMovies))
                .subscribeOn(Schedulers.computation());
    }

    private static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            subscriber.onNext(func.call());
                        } catch(Exception ex) {
                            Log.e(TAG, "Error reading from the database", ex);
                        }
                    }
                });

    }*/

}
