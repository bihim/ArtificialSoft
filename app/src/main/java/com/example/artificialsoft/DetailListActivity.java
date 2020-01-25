package com.example.artificialsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.artificialsoft.MainActivity.DESIGNATION;
import static com.example.artificialsoft.MainActivity.ID;
import static com.example.artificialsoft.MainActivity.NAME;
import static com.example.artificialsoft.MainActivity.URL;
import static com.example.artificialsoft.MainActivity.USER;

public class DetailListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra(URL);
        String id = intent.getStringExtra(ID);
        String name = intent.getStringExtra(NAME);
        String user = intent.getStringExtra(USER);
        String designation = intent.getStringExtra(DESIGNATION);


        ImageView imageView = findViewById(R.id.details_image);
        TextView textViewID, textViewUser, textViewName, textViewDesignation;
        textViewID = findViewById(R.id.details_id);
        textViewUser = findViewById(R.id.details_user);
        textViewName = findViewById(R.id.details_name);
        textViewDesignation = findViewById(R.id.details_designation);

        Glide.with(this).load(imageUrl).into(imageView);
        textViewID.setText(id);
        textViewUser.setText(user);
        textViewName.setText(name);
        textViewDesignation.setText(designation);


    }
}
