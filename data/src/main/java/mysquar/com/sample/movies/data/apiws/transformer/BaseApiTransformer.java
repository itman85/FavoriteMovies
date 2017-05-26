/*
 * Copyright(c) 2016 "Si Long <long.bkiter07@gmail.com>"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package mysquar.com.sample.movies.data.apiws.transformer;


import mysquar.com.sample.movies.data.apiws.BaseResponse;
import mysquar.com.sample.movies.data.apiws.exception.ApiException;
import rx.Observable;


/**
 * Created by SILONG on 5/2/16.
 */
public class BaseApiTransformer<T> implements rx.Observable.Transformer<BaseResponse<T>, T> {

  @Override
  public Observable<T> call(Observable<BaseResponse<T>> baseResponseObservable) {
    return baseResponseObservable.flatMap(tBaseResponse -> {
      switch (tBaseResponse.getCode()) {
        case 200://successful
          return Observable.just(tBaseResponse.getData());
        default:
          return Observable.error(new ApiException());
      }
    });
  }
}
