package com.mobiledevelopment.werewolf.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Party;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.util.Util;
import java.util.ArrayList;
import java.util.Random;


/**
 * Activity of transition between the activities {@link ActivityPartyNew} and {@link ActivityParty}
 * Receives a {@link Party} and a list of {@link Role}.
 * Gives to each {@link Player} of the {@link Party} a {@link Role}.
 * Whn it's all set, launches the Game.
 */
public class ActivityLoading extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        // Receive Data
        Bundle extras = getIntent().getExtras();
        Party party = (Party) extras.getSerializable(Util.EXTRA_PARTY);
        ArrayList<Role> roles = (ArrayList<Role>) extras.getSerializable(Util.EXTRA_ROLES);




        // Treat Data - A - Order by Names
        // We get the List of Players, not organized
        ArrayList<Player> playersNotOrdered = party.getPlayers();

        // We reset the list of Players in the Party
        party.setPlayers( new ArrayList<Player>() );

        // Order the players by name
        int numberOfPlayers = playersNotOrdered.size();

        for(int i = 0; i < numberOfPlayers; i++)
        {
            // We set a current Player at the index 0
            Player currentPlayer = playersNotOrdered.get(0);

            // We iterate through the Players to get the one with the "lowest" name
            for (Player player : playersNotOrdered)
            {
                int compare = player.getName().compareToIgnoreCase(currentPlayer.getName());
                if(compare < 0)
                {
                    currentPlayer = player;
                }
            }

            // We add the Player with the "lowest" name to the Party's list, and delete it from the disposable list
            party.getPlayers().add( currentPlayer );
            playersNotOrdered.remove(currentPlayer);
        }




        // Treat Data - B - Give each Player a Role
        Random rand = new Random();
        for (Player player : party.getPlayers())
        {
            int index = rand.nextInt(roles.size());
            int i = 0;
            Role roleToManage = null;

            for (Role role : roles)
            {
                if(i != index)
                {
                    i++;
                }
                else
                {
                    roleToManage = role;
                    break;
                }
            }

            player.setRole(roleToManage);
            roles.remove(roleToManage);
        }


        // Send Data
        Intent intent = new Intent(getBaseContext(), ActivityParty.class);
        intent.putExtra(Util.EXTRA_PARTY, party);
        startActivity(intent);
    }
}
