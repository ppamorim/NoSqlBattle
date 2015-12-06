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
package com.nosqlbattle.addtasks;

import android.os.AsyncTask;
import java.util.concurrent.TimeUnit;

public abstract class Add extends AsyncTask<Void, Void, AddResult> {

  public interface AddListener {
    void onComplete(Add add, AddResult addResult);
  }

  private final AddListener addListener;

  protected Add(AddListener parseListener) {
    addListener = parseListener;
  }

  @Override  protected AddResult doInBackground(Void... params) {
    System.gc();
    long startTime = System.nanoTime();
    add();
    long endTime = System.nanoTime();
    long duration = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);
    return new AddResult(duration);
  }

  @Override
  protected void onPostExecute(AddResult addResult) {
    addListener.onComplete(this, addResult);
  }

  protected abstract String add();
}
