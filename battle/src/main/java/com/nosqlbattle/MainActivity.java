package com.nosqlbattle;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.support.v7.app.AppCompatActivity;

import com.nosqlbattle.addtasks.Add;
import com.nosqlbattle.addtasks.PaperAdd;
import com.nosqlbattle.addtasks.RealmAdd;
import com.nosqlbattle.querytasks.PaperQuery;
import com.nosqlbattle.querytasks.Query;
import com.nosqlbattle.querytasks.Query.QueryListener;
import com.nosqlbattle.querytasks.QueryResult;

import com.nosqlbattle.addtasks.Add.AddListener;
import com.nosqlbattle.addtasks.AddResult;

import com.nosqlbattle.querytasks.RealmQuery;
import com.nosqlbattle.widget.BarChart;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a test app we wrote in 10 minutes. Please do not write code like this, kiddos.
 */
public class MainActivity extends AppCompatActivity  {

    private static final int ITERATIONS = 20;

    private BarChart mBarChart;

    private final QueryListener mParseListener = new QueryListener() {
      @Override public void onComplete(Query query, QueryResult queryResult) {
          addBarData(query, queryResult);
      }
    };

    private final AddListener mSerializeListener = new AddListener() {
      @Override public void onComplete(Add add, AddResult addResult) {
        addBarData(add, addResult);
      }
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mBarChart = (BarChart)findViewById(R.id.bar_chart);
      mBarChart.setColumnTitles(new String[] {"Realm", "paper"});

      findViewById(R.id.add_benchmark).setOnClickListener(new OnClickListener() {
        @Override public void onClick(View v) {
          performAddTests();
        }
      });

      findViewById(R.id.query_benchmark).setOnClickListener(new OnClickListener() {
        @Override public void onClick(View v) {
          performQueryTests();
        }
      });
    }

    private void performAddTests() {
        mBarChart.clear();
        mBarChart.setSections(new String[] {"Parse 60 items", "Parse 20 items", "Parse 7 items", "Parse 2 items"});

        List<Add> adds = new ArrayList<>();
        for (String jsonString : mJsonStringsToParse) {
            for (int iteration = 0; iteration < ITERATIONS; iteration++) {
              adds.add(new RealmAdd(mParseListener));
              adds.add(new PaperAdd(mParseListener));
            }
        }

        for (Add add : adds) {
          add.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
    }

    private void performQueryTests() {
      mBarChart.clear();
      mBarChart.setSections(new String[] {
          "Serialize 60 items", "Serialize 20 items", "Serialize 7 items", "Serialize 2 items"
      });

      Realm realm = Realm.getDefaultInstance();

      List<Query> queries = new ArrayList<>();
      for (Response response : mResponsesToSerialize) {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
          queries.add(new RealmQuery(mParseListener, realm));
          queries.add(new PaperQuery(mParseListener));
        }
      }

      for (Query query : queries) {
        query.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
      }
    }

    private void addBarData(Add add, AddResult addResult) {
        int section;
        switch (addResult.objectsParsed) {
            case 60:
                section = 0;
                break;
            case 20:
                section = 1;
                break;
            case 7:
                section = 2;
                break;
            case 2:
                section = 3;
                break;
            default:
                section = -1;
                break;
        }

        if (add instanceof RealmAdd) {
            mBarChart.addTiming(section, 0, addResult.runDuration / 1000f);
        } else if (add instanceof PaperAdd) {
            mBarChart.addTiming(section, 1, addResult.runDuration / 1000f);
        }
    }

    private void addBarData(Query query, QueryResult queryResult) {
      int section;
      switch (queryResult.objectsQueried) {
        case 60:
          section = 0;
          break;
        case 20:
          section = 1;
          break;
        case 7:
          section = 2;
          break;
        case 2:
          section = 3;
          break;
        default:
          section = -1;
          break;
      }

      if (query instanceof RealmQuery) {
          mBarChart.addTiming(section, 0, queryResult.runDuration / 1000f);
      } else if (query instanceof PaperQuery) {
          mBarChart.addTiming(section, 1, queryResult.runDuration / 1000f);
      }
    }

}
