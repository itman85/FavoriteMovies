package com.mysquar.sample.movies.utils;

/**
 * Created by phannguyen on 6/1/17.
 */

public class RecycleScrollInfo {

  int pastVisiblesItems, visibleItemCount, totalItemCount;
  int state;

  public RecycleScrollInfo(int pastVisiblesItems, int visibleItemCount, int totalItemCount, int state) {
    this.pastVisiblesItems = pastVisiblesItems;
    this.visibleItemCount = visibleItemCount;
    this.totalItemCount = totalItemCount;
    this.state = state;
  }

  public int getVisibleItemCount() {
    return visibleItemCount;
  }

  public int getTotalItemCount() {
    return totalItemCount;
  }

  public int getState() {
    return state;
  }

  public int getPastVisiblesItems() {

    return pastVisiblesItems;
  }

  public String toString(){
    return pastVisiblesItems+ "-"+visibleItemCount+"-"+totalItemCount+"-"+state;
  }
}
