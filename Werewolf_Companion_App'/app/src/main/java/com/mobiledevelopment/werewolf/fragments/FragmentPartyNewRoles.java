package com.mobiledevelopment.werewolf.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mobiledevelopment.werewolf.activities.ActivityPartyNew;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.CustomIntent;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.adapters.AdapterRole;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyNewRoles extends Fragment
{
    private ActivityPartyNew parentActivity;
    private TextView textNumberPlayersRoles;
    private RecyclerView recyclerView;

    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyNewRoles(final ActivityPartyNew parentActivity)
    {
        this.parentActivity = parentActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_party_new_roles, container, false);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onResume()
    {
        super.onResume();

        // We get the widgets references
        textNumberPlayersRoles = parentActivity.findViewById(R.id.partyNewTextRoleNumber);
        recyclerView = parentActivity.findViewById(R.id.RulesRecyclerView);

        setText();
        setRecyclerView();
    }


    /**
     * Sets the text of the number of Roles && Players
     */
    public void setText()
    {
        // We retrieve the values
        String numberRoles = String.valueOf(parentActivity.roles.size());
        String numberPlayers = String.valueOf(parentActivity.party.numberOfPlayers);

        // We format a message
        String message = numberRoles + " / " + numberPlayers;

        // We set the message inside the Text View
        textNumberPlayersRoles.setText(message);

        // We set the color accordingly
        if(parentActivity.asManyPlayersAsRoles())
        {
            textNumberPlayersRoles.setTextColor(getResources().getColor(R.color.colorValid));
        }
        else
        {
            textNumberPlayersRoles.setTextColor(getResources().getColor(R.color.colorInvalid));
        }
    }



    /**
     * Sets the RecyclerView
     */
    private void setRecyclerView()
    {
        // We get the Array of Roles to display
        Role[] roles = Util.getRolesAllByDefault();

        // We instantiate a RecyclerView Adapter (and we want to add or delete the Roles)
        AdapterRole adapter = new AdapterRole(parentActivity, CustomIntent.RV_ROLES_ADD, roles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }
}
