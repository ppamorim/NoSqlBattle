package com.nosqlbattle.querytasks;

import android.os.AsyncTask;
import java.util.concurrent.TimeUnit;

public abstract class Query extends AsyncTask<Void, Void, QueryResult> {

  public interface QueryListener {
    void onComplete(Query query, QueryResult queryResult);
  }

  private final QueryListener queryListener;

  protected Query(QueryListener queryListener) {
    this.queryListener = queryListener;
  }

  @Override protected QueryResult doInBackground(Void... params) {
      System.gc();
      long startTime = System.nanoTime();
      int objectCount = query();
      long endTime = System.nanoTime();
      long duration = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);

      return new QueryResult(duration, objectCount);
  }

  @Override protected void onPostExecute(QueryResult queryResult) {
    queryListener.onComplete(this, queryResult);
  }

  protected abstract int query();

}
