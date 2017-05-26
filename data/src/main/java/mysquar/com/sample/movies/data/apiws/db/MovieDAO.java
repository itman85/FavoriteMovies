package mysquar.com.sample.movies.data.apiws.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;


/**
 * Created by phannguyen on 5/25/17.
 */
@Dao
public interface MovieDAO {
    @Query("select * from movies")
    List<EMovie> loadAllMovies();

    @Query("select * from movies where id = :id")
    EMovie loadMovieById(int id);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceMovies(List<EMovie>movies);


}
