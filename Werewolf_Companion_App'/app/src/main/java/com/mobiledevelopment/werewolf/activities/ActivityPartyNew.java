package com.mobiledevelopment.werewolf.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyNewLaunch;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyNewPlayers;
import com.mobiledevelopment.werewolf.fragments.FragmentPartyNewRoles;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Party;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.util.Util;
import java.util.ArrayList;


/**
 * Activity describing the creation of a {@link Party} :
 * - Create {@link Player}
 * - Select the {@link Role} to be played
 * - Launch {@link Party} the when ready (if some conditions are met)
 */
public class ActivityPartyNew extends AppCompatActivity
{
    public Party party;
    public ArrayList<Role> roles;

    // We declare 3 Fragments
    private FragmentPartyNewPlayers fragmentPlayers;
    public FragmentPartyNewRoles fragmentRoles;
    private FragmentPartyNewLaunch fragmentLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_new);

        // We initialize the structures
        party = new Party();
        roles = new ArrayList<>();

        // Receive Data : if not null, fill the previous structure with them
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey(Util.EXTRA_PARTY))
        {
            party = (Party) extras.getSerializable(Util.EXTRA_PARTY);
            for (Player player : party.getPlayers())
            {
                roles.add(player.getRole());
            }
        }


        // Initialize all the fragments
        fragmentPlayers = new FragmentPartyNewPlayers(this);
        fragmentRoles = new FragmentPartyNewRoles(this);
        fragmentLaunch = new FragmentPartyNewLaunch(this);


        // Get the Navbar
        BottomNavigationView navbar = findViewById(R.id.party_new_navbar);

        // Set the navbar to the first choice
        navbar.setSelectedItemId(R.id.party_new_navbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.party_new_frame, fragmentPlayers).commit();

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
                    case R.id.party_new_nav_players:
                        selectedFragment = fragmentPlayers;
                        break;

                    // Adds Roles
                    case R.id.party_new_nav_roles:
                        selectedFragment = fragmentRoles;
                        break;

                    case R.id.party_new_nav_launch:
                        selectedFragment = fragmentLaunch;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.party_new_frame, selectedFragment).commit();
                return true;
            }
        });
    }


    /**
     * If the Party contains the Minimum amount of Players required
     * @return If the Party contains the Minimum amount of Players required
     */
    public boolean hasEnoughPlayers() { return (party.numberOfPlayers >= Util.MINIMUM_PLAYER); }


    /**
     * If the Party contains as many Players as Roles
     * @return If the Party contains as many Players as Roles
     */
    public boolean asManyPlayersAsRoles () { return (party.numberOfPlayers == roles.size()); }


    /**
     * Returns the number of time a specific Role is selected
     * @param role Role of which we want to know the number of instance
     * @return The number of time a specific Role is selected
     */
    public int numberOfRolesInstance(Role role)
    {
        // We initialize a counter
        int cpt = 0;

        // Foreach Role currently selected
        for (Role r : roles)
        {
            if(r == role)
            {
                cpt++;
            }
        }

        // We return the counter
        return cpt;
    }

    public static class ActivityParty extends AppCompatActivity
    {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_party);
        }
    }
}
