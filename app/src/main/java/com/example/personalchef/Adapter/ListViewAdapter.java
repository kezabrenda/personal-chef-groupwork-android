package com.example.personalchef.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.personalchef.UI.MealsNames;
import com.example.personalchef.R;
import com.example.personalchef.UI.UserActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<MealsNames> mealsNamesList = null;
    private ArrayList<MealsNames> arraylist;


    public ListViewAdapter(UserActivity context, ArrayList<MealsNames> mealsNamesList) {
        mContext = context;
        this.mealsNamesList = mealsNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<MealsNames>();
        this.arraylist.addAll(mealsNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return mealsNamesList.size();
    }

    @Override
    public MealsNames getItem(int position) {
        return mealsNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(mealsNamesList.get(position).getMealsName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mealsNamesList.clear();
        if (charText.length() == 0) {
            mealsNamesList.addAll(arraylist);
        } else {
            for (MealsNames wp : arraylist) {
                if (wp.getMealsName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mealsNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}