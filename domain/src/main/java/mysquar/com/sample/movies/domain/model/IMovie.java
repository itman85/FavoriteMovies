package mysquar.com.sample.movies.domain.model;

/**
 * Created by phannguyen on 5/23/17.
 */

public interface IMovie {
    String getTitle();
    String getDescription();
    int getId();
    String getPosterUrl();
}
