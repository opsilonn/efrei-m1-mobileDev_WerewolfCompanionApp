package com.mobiledevelopment.werewolf.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyLeave;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyPlayers;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyRoles;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Party;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * Activity describing the content of a {@link Party}
 */
public class ActivityParty extends AppCompatActivity
{
    public Party party;

    // We declare 3 Fragments
    private FragmentPartyPlayers fragmentPlayers;
    public FragmentPartyRoles fragmentRoles;
    private FragmentPartyLeave fragmentLaunch;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        // Receive Data
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        party = (Party) extras.getSerializable(Util.EXTRA_PARTY);

        // A Party begins with the data displayed
        assert party != null;
        party.setDataHidden(false);


        // Initialize all the fragments
        fragmentPlayers = new FragmentPartyPlayers(this);
        fragmentRoles = new FragmentPartyRoles(this);
        fragmentLaunch = new FragmentPartyLeave(this);


        // Get the Navbar
        BottomNavigationView navbar = findViewById(R.id.party_navbar);

        // Set the navbar to the first choice
        navbar.setSelectedItemId(R.id.party_nav_players);
        getSupportFragmentManager().beginTransaction().replace(R.id.party_frame, fragmentPlayers).commit();


        // Adds the listener
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                // We set a default fragment (here, the fragment about Players)
                Fragment selectedFragment = fragmentPlayers;

                // We get the selected item
                switch (menuItem.getItemId())
                {
                    // Adds Players
                    case R.id.party_nav_players:
                        selectedFragment = fragmentPlayers;
                        break;

                    // Adds Roles
                    case R.id.party_nav_roles:
                        selectedFragment = fragmentRoles;
                        break;

                    case R.id.party_nav_leave:
                        selectedFragment = fragmentLaunch;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.party_frame, selectedFragment).commit();
                return true;
            }
        });
    }
}