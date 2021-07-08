package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.personalchef.Constants;
import com.example.personalchef.R;
import com.example.personalchef.booking.BookingActivity;
import com.example.personalchef.booking.BookingActivity2;
import com.example.personalchef.booking.ConfirmActivity;
import com.example.personalchef.databinding.ActivityViewChefsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewChefsActivity extends AppCompatActivity {
    ActivityViewChefsBinding binding;
    Button bookButton;
    public String chefPhone;
    private DatabaseReference mChefNumberReference;
    private ValueEventListener mChefNumberReferenceListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mChefNumberReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CHEF_NUMBER);

        mChefNumberReferenceListener = mChefNumberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot chefPhoneSnapshot : dataSnapshot.getChildren()) {
                    String phone = chefPhoneSnapshot.getValue().toString();
                    Log.d("Phone number updated", "phone: " + phone);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        super.onCreate(savedInstanceState);
        binding = ActivityViewChefsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();


        if (intent != null) {
            String chefNames = intent.getStringExtra("name");
            String chefSpeciality = intent.getStringExtra("specialty");
            String hourlyRate = intent.getStringExtra("hourlyRate");
            chefPhone = intent.getStringExtra("phone");
            System.out.println(chefPhone);
            int imageId = intent.getIntExtra("imageId", R.drawable.chef002);

            saveChefNumberToFirebase(chefPhone);

            binding.chefNames.setText(chefNames);
            binding.chefSpecialty.setText(chefSpeciality);
            binding.hourlyRate.setText(hourlyRate);
            binding.phone.setText(chefPhone);
            binding.chefImage1.setImageResource(imageId);
        }
        bookButton = findViewById(R.id.bookButton);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("phone", chefPhone);
                startActivity(new Intent(getApplicationContext(), BookingActivity2.class));
            }
        });

    }
    public void saveChefNumberToFirebase(String phone) {
        mChefNumberReference.push().setValue(phone);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChefNumberReference.removeEventListener(mChefNumberReferenceListener);
    }
}