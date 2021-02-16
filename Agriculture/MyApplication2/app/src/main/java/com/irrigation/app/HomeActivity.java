package com.irrigation.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    Button on;
    Button off;

    TextView Moisture;
    DatabaseReference mref;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);

        Moisture= (TextView) findViewById(R.id.moist);
        mref= FirebaseDatabase.getInstance().getReference();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                status=dataSnapshot.child("Moisture").getValue().toString();
                Moisture.setText(status);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
    }
}