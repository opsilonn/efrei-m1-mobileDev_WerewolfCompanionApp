package com.mobiledevelopment.werewolf.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyRoles extends Fragment
{
    ActivityParty parentActivity;

    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyRoles(final ActivityParty parentActivity)
    {
        this.parentActivity = parentActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_roles, container, false);
    }


    @Override
    public void onResume()
    {
        super.onResume();
    }
}
