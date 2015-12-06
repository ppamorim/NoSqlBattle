package com.nosqlbattle.querytasks;

import com.nosqlbattle.model.UserPaper;
import io.paperdb.Paper;
import java.util.ArrayList;

public class PaperQuery extends Query {

  public PaperQuery(QueryListener queryListener) {
    super(queryListener);
  }

  @Override protected int query() {
    try {
       ArrayList<UserPaper> users = Paper.book().read("UserPaper");
      return users.size();
    } catch (Exception e) {
      return 0;
    } finally {
      System.gc();
    }
  }

}
