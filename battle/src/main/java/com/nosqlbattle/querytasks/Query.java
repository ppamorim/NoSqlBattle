/*
* Copyright (C) 2015 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
