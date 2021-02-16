package com.irrigation.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ControlActivity extends AppCompatActivity {
    Button on;
    Button off;

    Button mon;
    Button moff;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        mon = (Button) findViewById(R.id.mon);
        moff = (Button) findViewById(R.id.moff);






        on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");

                myRef.setValue(1);


            }

        });
        off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");

                myRef.setValue(0);
            }
        });


        mon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("MOTOR_STATUS");

                myRef.setValue(1);


            }

        });
        moff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("MOTOR_STATUS");

                myRef.setValue(0);
            }
        });
    }
}