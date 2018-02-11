package com.example.srijith.fblithodemo;

/**
 * Created by Srijith on 21-11-2017.
 */

public class Feed {

  public enum FeedType {NEWS_FEED, PHOTO_FEED, AD_FEED}

  public int id;
  public FeedType type;
  public FeedData data;

  @Override
  public String toString() {
    return "Feed [ id: " + id + ", type: " + type + "]";
  }
}
