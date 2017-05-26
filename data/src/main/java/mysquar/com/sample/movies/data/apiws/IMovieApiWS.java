package mysquar.com.sample.movies.data.apiws;

import java.util.List;

import mysquar.com.sample.movies.data.apiws.model.MovieModel;
import mysquar.com.sample.movies.data.apiws.model.ResultModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by phannguyen on 5/23/17.
 */

public interface IMovieApiWS {
    public static final String END_POINT = "http://api.themoviedb.org/3/movie/";
    public final String API_KEY = "8551c026bbf22a4a386ebb5b87a5296b";

    @GET("discover/movie")
    //Observable<BaseResponse<List<MovieModel>>> getPopularMovies(@Query("sort_by") String sortValue);
    Observable<List<MovieModel>> getPopularMovies(@Query("sort_by") String sortValue);

    @GET("top_rated")
    Observable<ResultModel> geFavoriteTopMovies(@Query("page") Integer page);
}
