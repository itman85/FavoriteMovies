package mysquar.com.sample.movies.domain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.usecase.RetrieveMoviesListUC;

/**
 * Created by phannguyen on 5/23/17.
 */
@Module
public class DomainModule {
    @Singleton
    @Provides
    public RetrieveMoviesListUC provideMoviesListUseCase(IApiMovieService apiService) {
        return new RetrieveMoviesListUC(apiService);
    }
}
