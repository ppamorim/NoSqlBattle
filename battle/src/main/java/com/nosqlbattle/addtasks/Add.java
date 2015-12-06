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
