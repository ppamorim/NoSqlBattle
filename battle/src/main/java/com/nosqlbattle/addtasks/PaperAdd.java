package com.nosqlbattle.addtasks;

import io.paperdb.Paper;

public class PaperAdd extends Add {

  public PaperAdd(AddListener addListener) {
    super(addListener);
  }

  @Override protected String add() {
    try {
      Paper.book().wr
      return gson.toJson(response);
    } catch (Exception e) {
      return null;
    } finally {
      System.gc();
    }
  }

}
