package com.nosqlbattle.querytasks;

import com.nosqlbattle.model.UserRealm;
import io.realm.Realm;

public class RealmQuery extends Query {

  private final Realm realm;

  public RealmQuery(QueryListener queryListener, Realm realm) {
    super(queryListener);
    this.realm = realm;
  }

  @Override protected int query() {
    try {
      return realm.allObjects(UserRealm.class).size();
    } catch (Exception e) {
        return 0;
    } finally {
        System.gc();
    }
  }

}
