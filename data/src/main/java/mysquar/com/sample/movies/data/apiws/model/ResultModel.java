package mysquar.com.sample.movies.data.apiws.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phannguyen on 5/24/17.
 */

public class ResultModel {
  @SerializedName("page")
  @Expose
  public Integer page;
  @SerializedName("results")
  @Expose
  public List<MovieModel> results = new ArrayList<MovieModel>();
  @SerializedName("total_results")
  @Expose
  public Integer totalResults;
  @SerializedName("total_pages")
  @Expose
  public Integer totalPages;

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public List<MovieModel> getResults() {
    return results;
  }

  public void setResults(List<MovieModel> results) {
    this.results = results;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }
}
