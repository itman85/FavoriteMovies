package mysquar.com.sample.movies.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import mysquar.com.sample.movies.data.apiws.db.EMovie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by phannguyen on 5/30/17.
 */

public class MovieDaoTest extends DbTest {

  @Test
  public void insertAndQuery_success() throws Exception{
    List<EMovie> movies = new ArrayList<>();
    for(int i=1;i<5;i++){
      movies.add(new EMovie("description"+i,i,"title"+i,""));
    }
    mAppDB.movieDao().insertOrReplaceMovies(movies);
    //
    EMovie movie = mAppDB.movieDao().loadMovieById(1);
    assertThat(movie,notNullValue());
    assertThat(movie.getId(),equalTo(1));
  }

}
