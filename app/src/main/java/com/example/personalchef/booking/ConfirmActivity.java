package com.example.personalchef.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.personalchef.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmActivity extends AppCompatActivity {
    @BindView(R.id.firstName) EditText firstName;
    @BindView(R.id.secondName) EditText secondName;
    @BindView(R.id.meals) EditText meals;
    @BindView(R.id.addInfo) EditText addInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm);
        ButterKnife.bind(this);

        String userName = getIntent().getStringExtra("firstName");
        String userSurname = getIntent().getStringExtra("secondName");
        String meal = getIntent().getStringExtra("meals");
        String addInform = getIntent().getStringExtra("addInfo");

        firstName.setText(userName);
        secondName.setText(userSurname);
        meals.setText(meal);
        addInfo.setText(addInform);
    }
}