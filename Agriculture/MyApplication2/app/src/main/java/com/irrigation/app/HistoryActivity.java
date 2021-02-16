package com.irrigation.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    DatabaseReference mref;
    DatabaseReference dref;
    private String moistureLevel;
    private RecyclerView recylerView;
    private CardViewAdapter adapter;
    private List<String> historyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyData = new ArrayList<>();
        updateRecycler();

        mref= FirebaseDatabase.getInstance().getReference("moisture_level");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(HistoryActivity.this, "inside " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                List<String> moistureLevels = new ArrayList<>();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    moistureLevels.add(postSnapshot.getValue().toString());
//                    moistureLevel = postSnapshot.getValue().toString();
//                    Log.d("History", "onDataChange: "+moistureLevel);
                }
                adapter = new CardViewAdapter(HistoryActivity.this, moistureLevels);
                recylerView.setAdapter(adapter);
//                adapter.updateList(moistureLevels);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    public void updateRecycler() {
        recylerView = findViewById(R.id.recycle_history);
       recylerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
//        recylerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new CardViewAdapter(this, historyData);
        recylerView.setAdapter(adapter);
    }

    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private List<String> data;
        private Context context;

        public void updateList(List<String> sosData) {
            this.data.clear();
            this.data.addAll(sosData);
        }

        public CardViewAdapter(Context context, List<String> cards) {
            this.context = context;
            this.data = cards;
        }

        @Override
        public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false);
            return new CardViewAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final CardViewAdapter.ViewHolder viewHolder, final int position) {
            viewHolder.parent_card.setBackgroundColor(Util.getMatColor(context,"100"));
            final String list = data.get(position);

            viewHolder.moisture.setText(list);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView moisture;
            LinearLayout parent_card;

            public ViewHolder(View itemView) {
                super(itemView);
                moisture = itemView.findViewById(R.id.moisture);
                parent_card = itemView.findViewById(R.id.parent_card);
            }


        }
    }
}
