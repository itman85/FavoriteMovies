package mysquar.com.sample.movies.data.apiws.di;

import com.google.gson.Gson;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mysquar.com.sample.movies.data.apiws.ApiMovieServiceImpl;
import mysquar.com.sample.movies.data.apiws.IMovieApiWS;
import mysquar.com.sample.movies.data.apiws.LocalServiceImpl;
import mysquar.com.sample.movies.data.apiws.db.AppDB;
import mysquar.com.sample.movies.domain.service.IApiMovieService;
import mysquar.com.sample.movies.domain.service.ILocalService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by phannguyen on 5/23/17.
 */
@Module
public class DataModule {
    @Provides
    @Singleton
    public IApiMovieService provideApiMovieService(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IMovieApiWS.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return new ApiMovieServiceImpl(retrofit.create(IMovieApiWS.class));
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                  HttpUrl url = request.newBuilder().header("Content-Type",
                      "application/json")
                      .addHeader("Accept","application/json").build().url().newBuilder().addQueryParameter(
                      "api_key",
                      IMovieApiWS.API_KEY
                  ).build();
                    /*HttpUrl url = request.url().newBuilder().addQueryParameter(
                            "api_key",
                            IMovieApiWS.API_KEY
                    ).build();*/
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public ILocalService provideLocalService(AppDB appDB){
        return  new LocalServiceImpl(appDB);
    }

    @Provides
    @Singleton
    public AppDB provideAppDB(Application app){
      return  Room.databaseBuilder(app.getApplicationContext(),
          AppDB.class, "movies_db").build();
    }
}
