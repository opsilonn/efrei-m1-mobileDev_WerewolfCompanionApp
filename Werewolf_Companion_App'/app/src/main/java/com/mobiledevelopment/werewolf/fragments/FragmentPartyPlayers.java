package com.mobiledevelopment.werewolf.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.util.AdapterPlayer;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyPlayers extends Fragment
{
    ActivityParty parentActivity;
    RecyclerView recyclerView;


    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyPlayers(final ActivityParty parentActivity)
    {
        this.parentActivity = parentActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_players, container, false);
    }


    @Override
    public void onResume()
    {
        super.onResume();


        // We get the Recycler View
        recyclerView = parentActivity.findViewById(R.id.PartyRvPlayer);

        // We set the widgets
        setSwitch();
        setRecyclerView();
    }


    /**
     * Sets the Switch managing whether or not to display crucial data about the {@link Player}
     */
    private void setSwitch()
    {
        Switch switchHide = parentActivity.findViewById(R.id.PartyPlayerSwitchHide);

        switchHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                // We change the value
                parentActivity.party.setDataHidden(isChecked);

                // We set the RecyclerView accordingly
                setRecyclerView();
            }
        });
    }


    /**
     * Sets the RecyclerView displaying the {@link Player} accordingly
     */
    private void setRecyclerView()
    {
        // We instantiate a RecyclerView Adapter
        AdapterPlayer adapter = new AdapterPlayer(parentActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }
}
