package com.mobiledevelopment.werewolf.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.adapters.AdapterRole;
import com.mobiledevelopment.werewolf.model.CustomIntent;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.model.Team;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * {@link AppCompatActivity} that displays the rules of the game,
 * and the various {@link com.mobiledevelopment.werewolf.model.Role} a {@link com.mobiledevelopment.werewolf.model.Player} can incarnate
 */
public class ActivityRules extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);


        // We get the layout for the Teams
        LinearLayout layout = findViewById(R.id.RulesLayoutTeams);

        // We set the TextViews displaying the various teams
        for(Team team : Team.values())
        {
            // We create and set a textView
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(team.getName()));
            textView.setTextColor(team.getColor());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextSize(18);
            textView.setPadding(0, 10, 0, 10);

            // We insert the textView in the layout
            layout.addView(textView);
        }


        // We get the Recycler View
        RecyclerView recyclerView = findViewById(R.id.RulesRecyclerView);

        // We get the Array of Roles to display
        Role[] roles = Util.getRolesAllByDefault();

        // We instantiate a RecyclerView Adapter (and only see the default RecyclerView)
        AdapterRole adapter = new AdapterRole(this, CustomIntent.RV_ROLES_DEFAULT, roles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}