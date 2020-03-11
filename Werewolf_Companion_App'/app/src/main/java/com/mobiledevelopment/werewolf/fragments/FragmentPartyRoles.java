package com.mobiledevelopment.werewolf.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.dialogs.DialogRoleDead;
import com.mobiledevelopment.werewolf.model.CustomIntent;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.adapters.AdapterRole;
import com.mobiledevelopment.werewolf.util.Util;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyRoles extends Fragment
{
    private ActivityParty parentActivity;
    private Button buttonTurn;
    private TextView textTurn;
    private RecyclerView recyclerView;
    private Role[] roles;


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

        // We get the widgets references
        buttonTurn = parentActivity.findViewById(R.id.PartyRoleTurnButton);
        textTurn = parentActivity.findViewById(R.id.PartyRoleTurnCounter);
        recyclerView = parentActivity.findViewById(R.id.PartyRvRoles);

        // We set the Widgets
        setButtonTurn();
        setWidgetsTexts();
        setRecyclerView();
    }


    /**
     * Sets the button that changes to the next turn
     */
    private void setButtonTurn()
    {
        // When clicked : launches the Activity a describing a game
        buttonTurn.setOnClickListener(v -> {
            // All dying Players... die.
            for (Player player : parentActivity.party.getPlayers())
            {
                if( player.isDying() )
                {
                    player.setAlive(false);
                    player.setDying(false);

                    // Create a Dialog if a player with a role that activates at death died
                    if(player.getRole().isActivatedAtDeath())
                    {
                        DialogRoleDead dialogRoleDead = new DialogRoleDead(parentActivity, player.getRole(), player.getName());
                        dialogRoleDead.show();
                    }
                }
            }

            // We change the time of day (may increment the turn, so that's cool !)
            parentActivity.party.changeTime();

            // AFTER the time change, we reset the widgets texts
            setWidgetsTexts();

            // THEN - we reset our data structure
            setRoles();

            // We reset the Recycler view
            setRecyclerView();
        });
    }


    /**
     * Set the TextView and the Button's texts
     */
    private void setWidgetsTexts()
    {
        // We get the placeholder and set some variables
        String placeholder = getResources().getString(R.string.PlaceholderName);
        String textText;
        String textButton;

        // We get the correct strings
        if(parentActivity.party.isNight())
        {
            textText = getResources().getString(R.string.PartyRoleTextNight);
            textButton = getResources().getString(R.string.PartyRoleButtonNight);
        }
        else
        {
            textText = getResources().getString(R.string.PartyRoleTextDay);
            textButton = getResources().getString(R.string.PartyRoleButtonDay);
        }
        textText = textText.replace(placeholder, String.valueOf(parentActivity.party.getCptTurns()));

        // We set the text
        textTurn.setText(textText);
        buttonTurn.setText(textButton);
    }


    /**
     * Sets the {@link Role} array and their boolean counterpart
     */
    public void setRoles()
    {
        // We create a list of Roles
        List<Role> r;

        // We order the roles (Alive, with no double) in activation order
        if(parentActivity.party.isNight())
        {
            r = parentActivity.party.getAllRolesNight();
        }
        else
        {
            r = parentActivity.party.getAllRolesDay();
        }

        // We get the Array of Roles to display
        roles = Util.getRolesByOrderFromList(r);
        parentActivity.roleHasPlayed = new boolean[roles.length];
        for(int i =0; i < roles.length; i++)
        {
            parentActivity.roleHasPlayed[i] = false;
        }
    }


    /**
     * Sets the RecyclerView displaying the {@link Role} in order
     */
    private void setRecyclerView()
    {
        // We instantiate a RecyclerView Adapter
        AdapterRole adapter = new AdapterRole(parentActivity, CustomIntent.RV_ROLE_HAS_PLAYED, roles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }
}