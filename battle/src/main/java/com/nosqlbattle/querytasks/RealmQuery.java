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
