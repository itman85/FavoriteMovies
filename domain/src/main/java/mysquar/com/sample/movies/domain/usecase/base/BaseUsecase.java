package mysquar.com.sample.movies.domain.usecase.base;


import rx.Observable;

/**
 * Created by phannguyen on 5/23/17.
 */

public interface BaseUsecase <T> {
    Observable<T> buildUseCase();
}
