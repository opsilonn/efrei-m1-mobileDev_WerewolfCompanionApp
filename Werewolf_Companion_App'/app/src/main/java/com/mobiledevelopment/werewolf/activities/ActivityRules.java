package com.mobiledevelopment.werewolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.mobiledevelopment.werewolf.util.AdapterRole;


public class ActivityRules extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);


        // We get the Recycler View
        RecyclerView recyclerView = findViewById(R.id.RulesRecyclerView);

        // We instantiate a RecyclerView Adapter
        AdapterRole adapter = new AdapterRole(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}