package com.example.personalchef.booking;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalchef.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailBookingActivity extends AppCompatActivity {
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

        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");

                try {
                    startActivity(mailClient);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EmailBookingActivity.this, "your error message" , Toast.LENGTH_SHORT).show();
                }

               /* Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " +email.getText().toString()));
                System.out.println(intent);
                intent.putExtra(Intent.EXTRA_TEXT, firstName.getText().toString());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(EmailBookingActivity.this, "your error message" , Toast.LENGTH_SHORT).show();
                }
                */
            }
        });
    }
}
