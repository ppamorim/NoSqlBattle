package com.nosqlbattle.querytasks;

public class QueryResult {

  public long runDuration;
  public int objectsQueried;

  public QueryResult(long runDuration, int objectsQueried) {
    this.runDuration = runDuration;
    this.objectsQueried = objectsQueried;
  }

}
