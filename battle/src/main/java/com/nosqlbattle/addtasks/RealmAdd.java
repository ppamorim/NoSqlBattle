package com.nosqlbattle.addtasks;

import io.realm.Realm;

public class RealmAdd extends Add {

  private final Realm realm;

  public RealmAdd(AddListener addListener, Realm realm) {
    super(addListener);
    this.realm = realm;
  }

  @Override protected String add() {
    try {

      realm.copyToRealm()

      return objectMapper.writeValueAsString(response);
    } catch (Exception e) {
      return null;
    } finally {
      System.gc();
    }
  }
}
