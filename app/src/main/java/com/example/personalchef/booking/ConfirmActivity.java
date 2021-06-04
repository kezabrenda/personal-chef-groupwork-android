package com.example.personalchef.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.personalchef.Authentication.LoginActivity;
import com.example.personalchef.R;
import com.example.personalchef.UI.MainActivity;
import com.example.personalchef.UI.ViewChefsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmActivity extends AppCompatActivity {
    @BindView(R.id.firstName) TextView firstName;
    @BindView(R.id.secondName) TextView secondName;
    @BindView(R.id.meals) TextView meals;
    @BindView(R.id.addInfo) TextView addInfo;
    @BindView(R.id.doneButton) Button doneBtn;

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

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}