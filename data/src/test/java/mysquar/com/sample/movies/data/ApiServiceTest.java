package mysquar.com.sample.movies.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import mysquar.com.sample.movies.data.apiws.ApiMovieServiceImpl;
import mysquar.com.sample.movies.data.apiws.IMovieApiWS;
import mysquar.com.sample.movies.data.apiws.model.MovieModel;
import mysquar.com.sample.movies.data.apiws.model.ResultModel;
import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by phannguyen on 5/30/17.
 */

public class ApiServiceTest {

  IApiMovieService mApiMovieService;

  @Mock
  IMovieApiWS mMovieApiWS;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mApiMovieService = new ApiMovieServiceImpl(mMovieApiWS);
  }

  @After
  public void tearDown() {
    Mockito.reset(mMovieApiWS);
  }

  @Test
  public void testRetrieveMovies_success() throws Exception {
    MovieModel movie = mock(MovieModel.class);
    List<MovieModel> movies = Arrays.asList(movie, movie);
    ResultModel resultModel = mock(ResultModel.class);
    doReturn(1).when(resultModel).getPage();
    doReturn(movies).when(resultModel).getResults();
    doReturn(100).when(resultModel).getTotalPages();
    doReturn(2).when(resultModel).getTotalResults();
    doReturn(Observable.just(resultModel)).when(mMovieApiWS).geFavoriteTopMovies(anyInt());

    TestSubscriber<List<? extends IMovie>> testSubscriber = TestSubscriber.create();
    mApiMovieService.retrieveMoviesList().subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValueCount(1);
    testSubscriber.assertValue(movies);

    assertThat(testSubscriber.getOnNextEvents().size(), equalTo(1));

    List<? extends IMovie> imovies = testSubscriber.getOnNextEvents().get(0);

    assertThat(imovies.size(),equalTo(2));

  }

}
