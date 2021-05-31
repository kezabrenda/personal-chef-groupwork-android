package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.personalchef.Adapter.ViewChefsListViewAdapter;
import com.example.personalchef.R;
import com.example.personalchef.databinding.ActivityClientViewChefsDashboardBinding;

import java.util.ArrayList;

public class ClientViewChefsDashboardActivity extends AppCompatActivity {
    ActivityClientViewChefsDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientViewChefsDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.chef001, R.drawable.chef002, R.drawable.chef003};
        String[] chefNames = {"Charity Mutoni", "Clema Ingabire", "Fred Mura"};
        String[] chefSpeciality = {"Mediterranean Cuisine", "American Cuisine", "American Cuisine"};
        String[] hourlyRate = {"10,000 Rwf per hour", "20,000 Rwf per hour", "15,000 Rwf per hour"};

        ArrayList<ViewChefs> viewChefsArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            ViewChefs viewChefsActivity = new ViewChefs(chefNames[i], chefSpeciality[i], hourlyRate[i], imageId[i]);
            viewChefsArrayList.add(viewChefsActivity);
        }

        ViewChefsListViewAdapter viewChefsListViewAdapter = new ViewChefsListViewAdapter(ClientViewChefsDashboardActivity.this, viewChefsArrayList);

        binding.listview.setAdapter(viewChefsListViewAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClientViewChefsDashboardActivity.this, ViewChefsActivity.class);
                intent.putExtra("name", chefNames[position]);
                intent.putExtra("specialty", chefSpeciality[position]);
                intent.putExtra("hourlyRate", hourlyRate[position]);
                intent.putExtra("imageId", imageId[position]);
                startActivity(intent);
            }
        });

    }
}