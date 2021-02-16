package com.irrigation.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button recentData = findViewById(R.id.buttonRecent);
        Button control = findViewById(R.id.buttonControl);
        Button history = findViewById(R.id.buttonHistory);

        Button logout = findViewById(R.id.buttonLogout);

        recentData.setOnClickListener(this);
        control.setOnClickListener(this);
        history.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRecent:
                Toast.makeText(this, "Recent data is clicked", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(DisplayActivity.this, RecentDataActivity.class);
                DisplayActivity.this.startActivity(myIntent);
                break;

            case R.id.buttonControl:
                Toast.makeText(this, "Control is clicked", Toast.LENGTH_SHORT).show();
                Intent myIntent1 = new Intent(DisplayActivity.this, ControlActivity.class);
                DisplayActivity.this.startActivity(myIntent1);
                break;

            case R.id.buttonHistory:
                Toast.makeText(this, "History is clicked", Toast.LENGTH_SHORT).show();
                Intent myIntent2 = new Intent(DisplayActivity.this, HistoryActivity.class);
                DisplayActivity.this.startActivity(myIntent2);
                break;

            case R.id.buttonLogout:
                Toast.makeText(this, "Logout is clicked", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(DisplayActivity.this, MainActivity.class);
                    startActivity(i);
                }
            };

        }


