package mysquar.com.sample.movies.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import mysquar.com.sample.movies.data.apiws.IMovieApiWS;
import mysquar.com.sample.movies.data.apiws.model.ResultModel;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by phannguyen on 5/30/17.
 */

public class MovieApiWSTest {

  private MockWebServer mockWebServer;

  //@Inject MockWebServer mMockWebServer;
  IMovieApiWS service;

  @Before
  public void setUp() throws Exception {
    mockWebServer = new MockWebServer();
    service = new Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
        .create(IMovieApiWS.class);
  }

  @After
  public void tearDown() throws Exception {
    mockWebServer.shutdown();
  }

  @Test
  public void testGetMovies_success() throws Exception {
    enqueueResponse("movies.json");
    TestSubscriber<ResultModel> testSubscriber = TestSubscriber.create();
    service.geFavoriteTopMovies(1).subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();

    RecordedRequest request = mockWebServer.takeRequest();
    assertThat(request.getPath(), is("/top_rated?page=1"));

    ResultModel resultModel = testSubscriber.getOnNextEvents().get(0);
    assertThat(resultModel.getPage(), equalTo(1));
    assertThat(resultModel.getTotalPages(), equalTo(316));
    assertThat(resultModel.getTotalResults(), equalTo(6309));
  }

  private void enqueueResponse(String fileName) throws IOException {
    enqueueResponse(fileName, Collections.emptyMap());
  }

  private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
    InputStream inputStream = getClass().getClassLoader()
        .getResourceAsStream("api-response/" + fileName);
    BufferedSource source = Okio.buffer(Okio.source(inputStream));
    MockResponse mockResponse = new MockResponse();
    for (Map.Entry<String, String> header : headers.entrySet()) {
      mockResponse.addHeader(header.getKey(), header.getValue());
    }
    mockWebServer.enqueue(mockResponse
        .setBody(source.readString(StandardCharsets.UTF_8)));
  }
}