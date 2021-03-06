package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.personalchef.Authentication.LoginActivity;
import com.example.personalchef.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.chefButton) Button mChefButton;
    @BindView(R.id.bookingButton) Button mBookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mChefButton.setOnClickListener(this);
        mBookingButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mChefButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }else if(v == mBookingButton) {
            Intent intent = new Intent(MainActivity.this, UserdashboardActivity.class);
            startActivity(intent);
        }
    }

}