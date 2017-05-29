package mysquar.com.sample.movies.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mysquar.com.sample.movies.domain.model.IMovie;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import mysquar.com.sample.movies.domain.usecase.GetDetailMovieUC;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by phannguyen on 5/29/17.
 */

public class DetailMovieTest {
    GetDetailMovieUC mUseCase;
    @Mock
    IApiMovieService apiMovieService;
    @Mock
    ILocalService localService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUseCase = new GetDetailMovieUC(localService,apiMovieService);
    }

    @After
    public void tearDown() {
        Mockito.reset(apiMovieService, localService);
        mUseCase = null;
    }


    @Test
    public void testGetDetailMovie_fromLocal_success() throws Exception{
        IMovie movie =  mock(IMovie.class);
        doReturn(100).when(movie).getId();
        doReturn(Observable.just(movie)).when(localService).getDetailMovie(anyInt());
        mUseCase.with(movie.getId()).buildUseCase().test()
                .assertValueCount(1)
                .assertValue(movie)
                .assertNoErrors()
                .assertCompleted();
        verify(localService,times(1)).getDetailMovie(eq(100));
        verify(apiMovieService,never()).getDetailMovie(anyInt());
    }

    @Test
    public void testGetDetailMovie_fromNetwork_success() throws Exception{
        IMovie movie =  mock(IMovie.class);
        doReturn(100).when(movie).getId();
        doReturn(Observable.just(null)).when(localService).getDetailMovie(anyInt());
        doReturn(Observable.just(movie)).when(apiMovieService).getDetailMovie(anyInt());
        doReturn(Observable.just(null)).when(localService).saveMovie(any(IMovie.class));
        mUseCase.with(movie.getId()).buildUseCase().test()
                .assertValueCount(1)
                .assertValue(movie)
                .assertNoErrors()
                .assertCompleted();
        verify(localService,times(1)).getDetailMovie(eq(100));
        verify(apiMovieService,times(1)).getDetailMovie(eq(100));
    }

}
