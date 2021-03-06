package com.example.personalchef.booking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.personalchef.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity2 extends AppCompatActivity {
    @BindView(R.id.bookingButton) Button bookingButton;
    @BindView(R.id.firstName) EditText firstName;
    @BindView(R.id.secondName) EditText secondName;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.meals) EditText meals;
    @BindView(R.id.nbrOfPpl) EditText nbrOfPpl;
    @BindView(R.id.address) EditText address;
    @BindView(R.id.addInfo) EditText addInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(BookingActivity2.this, new String[]{Manifest.permission.
                SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
                showDetails();
            }
        });
    }

    public void showDetails() {
        String username = firstName.getText().toString();
        String userSurname = secondName.getText().toString();
        String meal = meals.getText().toString();
        String addInform = addInfo.getText().toString();

        Intent intent = new Intent(BookingActivity2.this, ConfirmActivity.class);
        intent.putExtra("firstName", username);
        intent.putExtra("secondName", userSurname);
        intent.putExtra("meals", meal);
        intent.putExtra("addInfo", addInform);
        startActivity(intent);
    }

    public void sendSMS(){
        String message0 = firstName.getText().toString();
        String message1 = secondName.getText().toString();
        String message2 = meals.getText().toString();
        String message3 = addInfo.getText().toString();
        String message4 = address.getText().toString();
        String message = "You have been booked by " +message0 + " " + message1 + " to make "
                +message2 + " at " +message4 + " P.S. " +message3;

        /*String chefNumber = getIntent().getStringExtra("Cphone");
        System.out.println(chefNumber);*/

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage("0788519073",null, message, null, null);
    }
}
