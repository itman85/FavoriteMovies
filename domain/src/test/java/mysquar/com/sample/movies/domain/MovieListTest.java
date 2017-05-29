package mysquar.com.sample.movies.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;
import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        Mockito.reset(apiMovieService, localService);
        mUseCase = null;
    }

    @Test
    public void testGetMovies_onlyFromLocalService_success() throws Exception {
        //given
        IMovie movie = mock(IMovie.class);
        List<IMovie> mockList = Arrays.asList(movie, movie, movie);
        doReturn(Observable.just(mockList)).when(localService).getMovies();

        //when
        mUseCase.buildUseCase().test()

                //then
                .assertValueCount(1)
                .assertValue(mockList)
                .assertNoErrors()
                .assertCompleted();
        verify(apiMovieService,never()).retrieveMoviesList();
    }

    @Test
    public void testGetMovies_onlyFromLocalService_failed() throws Exception {
        //given
        doReturn(Observable.error(new IOException())).when(localService).getMovies();

        //when
        mUseCase.buildUseCase().test()

                //then
                .assertNoValues()
                .assertError(IOException.class)
                .assertNotCompleted();
    }

    @Test
    public void testGetMovies_fromNetworkToLocal_success() throws Exception {
        IMovie movie = mock(IMovie.class);
        List<IMovie> mockList = Arrays.asList(movie, movie, movie);
        doReturn(Observable.just(new ArrayList<IMovie>())).when(localService).getMovies();
        doReturn(Observable.just(mockList)).when(apiMovieService).retrieveMoviesList();
        doReturn(Observable.just(null)).when(localService).saveMovies(anyListOf(IMovie.class));

        mUseCase.buildUseCase().test()
                .assertValueCount(1)
                .assertValue(mockList)
                .assertNoErrors()
                .assertCompleted();

        verify(localService, times(1)).getMovies();
        verify(apiMovieService, times(1)).retrieveMoviesList();
        verify(localService, times(1)).saveMovies(eq(mockList));
    }



}
