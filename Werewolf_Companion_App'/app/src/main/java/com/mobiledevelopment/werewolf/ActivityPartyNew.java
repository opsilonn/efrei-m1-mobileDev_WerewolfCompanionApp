package com.mobiledevelopment.werewolf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ActivityPartyNew extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_new);

        // Initialize all the components
        doStuff_Navbar();
    }


    private void doStuff_Navbar()
    {
        // Get the Navbar
        BottomNavigationView navbar = findViewById(R.id.party_new_navbar);

        // Set the navbar to the first choice
        navbar.setSelectedItemId(R.id.new_party_nav_players);

        // Adds the listener
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                boolean toReturn = false;

                // We get the selected item
                switch (menuItem.getItemId())
                {
                    // Adds Players
                    case R.id.new_party_nav_players:
                        // Creates a Fragment and a Transaction, and launches both
                        Fragment_NewParty_Players fragmentPlayers = new Fragment_NewParty_Players();
                        FragmentTransaction transactionPlayers = getSupportFragmentManager().beginTransaction();
                        transactionPlayers.replace(R.id.party_new_frame, fragmentPlayers);
                        transactionPlayers.commit();

                        // A fragment has been created, so we return true
                        toReturn = true;
                        break;

                    // Adds Roles
                    case R.id.new_party_nav_roles:
                        // Creates a Fragment and a Transaction, and launches both
                        Fragment_NewParty_Roles fragmentRoles = new Fragment_NewParty_Roles();
                        FragmentTransaction transactionRoles = getSupportFragmentManager().beginTransaction();
                        transactionRoles.replace(R.id.party_new_frame, fragmentRoles);
                        transactionRoles.commit();

                        // A fragment has been created, so we return true
                        toReturn = true;
                        break;

                    case R.id.new_party_nav_launch:
                        // Creates a Fragment and a Transaction, and launches both
                        Fragment_NewParty_Launch fragmentLaunch = new Fragment_NewParty_Launch();
                        FragmentTransaction transactionLaunch = getSupportFragmentManager().beginTransaction();
                        transactionLaunch.replace(R.id.party_new_frame, fragmentLaunch);
                        transactionLaunch.commit();

                        // A fragment has been created, so we return true
                        toReturn = true;
                        break;
                }

                // Otherwise : return false
                return toReturn;
            }
        });
    }
}
