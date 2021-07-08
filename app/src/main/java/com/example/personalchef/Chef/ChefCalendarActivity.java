package com.example.personalchef.Chef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.personalchef.Authentication.LoginActivity;
import com.example.personalchef.R;
import com.example.personalchef.UI.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChefCalendarActivity extends AppCompatActivity {
    @BindView(R.id.simpleDatePicker)
    DatePicker datePicker;
    @BindView(R.id.availableBtn)
    Button availableBtn;
    int day = datePicker.getDayOfMonth(); // get the selected day of the month
    int month = datePicker.getMonth(); // get the selected month
    int year = datePicker.getYear(); // get the selected year

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_calendar);
        ButterKnife.bind(this);

        availableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChefCalendarActivity.this, "Status:Available", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChefCalendarActivity.this, ChefDashboard2Activity.class));
            }
        });
    }

}