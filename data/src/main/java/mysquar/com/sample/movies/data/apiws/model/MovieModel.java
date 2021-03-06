package mysquar.com.sample.movies.data.apiws.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import mysquar.com.sample.movies.domain.model.IMovie;

/**
 * Created by phannguyen on 5/23/17.
 */

public class MovieModel implements IMovie {
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return overview;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPosterUrl() {
        return this.posterPath;
    }


    public MovieModel(String overview, String title,String posterPath) {
        this.overview = overview;
        this.title = title;
        this.posterPath = posterPath;
    }

    public MovieModel(String overview, Integer id, String title,String posterPath) {
        this.overview = overview;
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }
}
