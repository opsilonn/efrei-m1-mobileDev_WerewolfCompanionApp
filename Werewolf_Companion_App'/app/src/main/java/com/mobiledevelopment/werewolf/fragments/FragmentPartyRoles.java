package com.mobiledevelopment.werewolf.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mobiledevelopment.werewolf.activities.ActivityLoading;
import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyRoles extends Fragment
{
    private ActivityParty parentActivity;
    private Button buttonTurn;
    private TextView textTurn;

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

        buttonTurn = parentActivity.findViewById(R.id.PartyRoleTurnButton);
        textTurn = parentActivity.findViewById(R.id.PartyRoleTurnCounter);

        setButtonTurn();
        setTextTurn();
    }


    /**
     * Sets the button that changes to the next turn
     */
    private void setButtonTurn()
    {
        // When clicked : launches the Activity a describing a game
        buttonTurn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // We add a turn
                parentActivity.party.nextTurn();

                // All dying Player... die.
                for (Player player : parentActivity.party.getPlayers())
                {
                    if( player.isDying() )
                    {
                        player.setAlive(false);
                        player.setDying(false);
                    }
                }

                // We reset the text displaying the turn
                setTextTurn();
            }
        });
    }


    /**
     * Sets the text displaying the current Turn
     */
    private void setTextTurn()
    {
        // We get the placeholder
        textTurn.setText(R.string.PlaceholderName);
        String placeholder = (String) textTurn.getText();

        // We get the content
        textTurn.setText(R.string.PartyRoleTurnCounter);
        String content = (String) textTurn.getText();

        // We modify the content
        content = content.replace(placeholder, String.valueOf(parentActivity.party.getCptTurns()));

        // We set the text
        textTurn.setText(content);
    }
}
