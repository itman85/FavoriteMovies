package mysquar.com.sample.movies.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;
import rx.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by phannguyen on 5/25/17.
 */

public class MovieListTest {
    RetrieveMoviesListUC mUseCase;
    @Mock
    IApiMovieService apiMovieService;
    @Mock
    ILocalService localService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUseCase = new RetrieveMoviesListUC(apiMovieService, localService);
    }

    @After
    public void tearDown() {
        Mockito.reset(apiMovieService,localService);
        mUseCase = null;
    }

    @Test
    public void test() throws Exception {
        IMovie movie = mock(IMovie.class);
        List<IMovie> mockList = Arrays.asList(movie, movie, movie);
        doReturn(Observable.just(mockList)).when(localService).getMovies();
        mUseCase.buildUseCase().test()
                .assertValueCount(1)
                .assertValue(mockList)
                .assertNoErrors()
                .assertCompleted();
    }
}
