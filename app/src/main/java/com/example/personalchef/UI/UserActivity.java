package com.example.personalchef.UI;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalchef.Adapter.ListViewAdapter;
import com.example.personalchef.R;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] mealsNameList;
    ArrayList<MealsNames> arraylist = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Generate sample data

        mealsNameList = new String[]{"Soup", "Broth", "Blew",
                "Curry", "Roast", "Potpie", "Souffle", "Kebab",
                "Meatballs","Omelet","Stir-fry","rice","tossed salad","green salad"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (String s : mealsNameList) {
            MealsNames mealsNames = new MealsNames(s);
            // Binds all strings into an array
            arraylist.add(mealsNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter((ListAdapter) adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}