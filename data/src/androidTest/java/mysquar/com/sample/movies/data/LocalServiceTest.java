package mysquar.com.sample.movies.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import mysquar.com.sample.movies.data.apiws.LocalServiceImpl;
import mysquar.com.sample.movies.data.apiws.model.MovieModel;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.ILocalService;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by phannguyen on 5/30/17.
 */

public class LocalServiceTest extends DbTest {


  private ILocalService mLocalService;

  @Override
  public void setUp() {
    super.setUp();
    mLocalService = new LocalServiceImpl(mAppDB);
  }

  @Test
  public void testSaveAndQueryData_success() throws Exception {
    List<IMovie> movies = new ArrayList<>();
    for (int i = 1; i < 5; i++) {
      movies.add(new MovieModel("description" + i, i, "title" + i));
    }

    TestSubscriber<IMovie> testSubscriber = TestSubscriber.create();
    mLocalService.saveMovies(movies)
        .flatMap(aVoid -> mLocalService.getDetailMovie(1))
        .subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();

    List<IMovie> movieList = testSubscriber.getOnNextEvents();
    assertThat(movieList, notNullValue());
    assertThat(movieList.size(), equalTo(1));

    IMovie movie = movieList.get(0);
    assertThat(movie, notNullValue());
    assertThat(movie.getId(), equalTo(1));
  }
}