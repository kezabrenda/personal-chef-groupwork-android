package com.example.personalchef.cuisines;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalchef.Adapter.CuisinesListViewAdapter;

import com.example.personalchef.R;
import com.example.personalchef.UI.ViewChefs;
import com.example.personalchef.databinding.ActivityCuisinesListBinding;

import java.util.ArrayList;

public class SeeCuisinesActivity extends AppCompatActivity {
    ActivityCuisinesListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCuisinesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] meals = {"African Cuisine", "Mediterranean Cuisine", "Asian Cuisine", "Other Cuisines"};
        int[] imageId = {R.drawable.african_cuisines, R.drawable.mediterranean, R.drawable.asian_cuisine, R.drawable.othercuisines};

        ArrayList<ViewCuisines> viewCuisinesArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            ViewCuisines viewCuisinesActivity = new ViewCuisines(meals[i], imageId[i]);
            viewCuisinesArrayList.add(viewCuisinesActivity);
        }

        CuisinesListViewAdapter cuisinesListViewAdapter = new CuisinesListViewAdapter(SeeCuisinesActivity.this, viewCuisinesArrayList);

        binding.listview2.setAdapter(cuisinesListViewAdapter);
        binding.listview2.setClickable(true);
        binding.listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SeeCuisinesActivity.this, ViewCuisinesActivity.class);
                intent.putExtra("meals", meals[position]);
                intent.putExtra("imageId", imageId[position]);
                startActivity(intent);
            }
        });

    }
}
