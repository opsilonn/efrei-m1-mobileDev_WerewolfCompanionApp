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

        // Setting up the different variables
        doStuff_RecyclerView();
    }


    /**
     * Sets up the RecyclerView so that it displays all the Roles
     */
    private void doStuff_RecyclerView()
    {
        // We get the Recycler View
        RecyclerView recyclerView = findViewById(R.id.RulesRecyclerView);

        // We instantiate a RecyclerView Adapter (for the ROLES, and false since we just want to display data)
        AdapterRole adapter = new AdapterRole(this, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
