package com.example.examprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewMenu);
        ArticleListAdapter listAdapter = new ArticleListAdapter(this);

//        mealArrayAdapter = new ArrayAdapter<Meal>(this, android.R.layout.simple_spinner_item, allMeals);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getApplicationContext(), ArticleInfoActivity.class);
        intent.putExtra("position", position);

        startActivity(intent);
    }
}
