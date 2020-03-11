package com.mobiledevelopment.werewolf.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.activities.ActivityLoading;
import com.mobiledevelopment.werewolf.activities.ActivityPartyNew;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyNewLaunch extends Fragment
{
    private ActivityPartyNew parentActivity;

    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyNewLaunch(final ActivityPartyNew parentActivity)
    {
        this.parentActivity = parentActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_new_launch, container, false);
    }


    @Override
    public void onResume()
    {
        super.onResume();

        // We get the button
        Button button = parentActivity.findViewById(R.id.NewPartyButtonLaunch);

        // We set it enabled (or not) accordingly
        button.setEnabled( parentActivity.hasEnoughPlayers() && parentActivity.asManyPlayersAsRoles() );


        // When clicked : launches the Activity a describing a game
        button.setOnClickListener(v -> {
            Intent intent = new Intent(parentActivity.getBaseContext(), ActivityLoading.class);
            intent.putExtra(Util.EXTRA_PARTY, parentActivity.party);
            intent.putExtra(Util.EXTRA_ROLES, parentActivity.roles);
            startActivity(intent);
        });
    }
}