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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewChefsListViewAdapter extends ArrayAdapter<ViewChefs> {

    public ViewChefsListViewAdapter(Context context, ArrayList<ViewChefs> chefsArrayActivity) {
        super(context, R.layout.client_view_list_item, chefsArrayActivity);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewChefs viewChefsActivity = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_view_list_item,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.chefImage1);
        TextView chefNames = convertView.findViewById(R.id.chefNames);
        TextView chefSpeciality = convertView.findViewById(R.id.chefSpecialty);

        imageView.setImageResource(viewChefsActivity.imageId);
        chefNames.setText(viewChefsActivity.names);
        chefSpeciality.setText(viewChefsActivity.foodSpecialty);

        return convertView;
    }
}
