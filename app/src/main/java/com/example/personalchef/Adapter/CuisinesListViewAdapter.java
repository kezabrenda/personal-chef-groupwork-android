package com.example.personalchef.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personalchef.R;
import com.example.personalchef.UI.ViewChefs;
import com.example.personalchef.cuisines.ViewCuisines;

import java.util.ArrayList;

public class CuisinesListViewAdapter extends ArrayAdapter<ViewCuisines> {

    public CuisinesListViewAdapter(Context context, ArrayList<ViewCuisines> cuisinesArrayActivity) {
        super(context, R.layout.cuisine_view_list_item, cuisinesArrayActivity);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewCuisines viewCuisinesActivity = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cuisine_view_list_item,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.cuisineImage2);
        TextView meals = convertView.findViewById(R.id.cuisineType);

        meals.setText(viewCuisinesActivity.meals);
        imageView.setImageResource(viewCuisinesActivity.imageId);

        return convertView;
    }
}
