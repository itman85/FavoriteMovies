package mysquar.com.sample.movies.data.apiws.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import mysquar.com.sample.movies.data.apiws.model.MovieModel;
import mysquar.com.sample.movies.domain.model.IMovie;

/**
 * Created by phannguyen on 5/25/17.
 */
@Entity(tableName = "movies")
public class EMovie {
    @PrimaryKey
    private int id;

    private String title;
    private String description;

    public EMovie() {}

    @Ignore
    public EMovie(IMovie movieModel) {
        this.title = movieModel.getTitle();
        this.description = movieModel.getDescription();
        this.id = movieModel.getId();
    }
    @Ignore
    public EMovie(String description, int id, String title) {
        this.title = title;
        this.description = description;
        this.id = id;
    }


    public IMovie cast(){
        return new MovieModel(description,id,title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
