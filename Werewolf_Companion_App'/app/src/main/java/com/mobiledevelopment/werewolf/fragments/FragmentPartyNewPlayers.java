package com.mobiledevelopment.werewolf.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.mobiledevelopment.werewolf.activities.ActivityPartyNew;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Player;
import com.mobiledevelopment.werewolf.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyNewPlayers extends Fragment
{
    private ActivityPartyNew parentActivity;
    private ChipGroup chipGroup;
    private TextView textWarning;


    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyNewPlayers(final ActivityPartyNew parentActivity)
    {
        this.parentActivity = parentActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_new_players, container, false);
    }


    @Override
    public void onResume()
    {
        super.onResume();

        // Getting the widgets references
        final EditText editText = parentActivity.findViewById(R.id.PartyNewPlayerEditText);
        Button button = parentActivity.findViewById(R.id.PartyNewPlayerAdd);
        chipGroup = parentActivity.findViewById(R.id.PartyNewPlayerChipGroup);
        textWarning = parentActivity.findViewById(R.id.PartyNewPlayerWarning);

        // Setting the warning text (we need these several steps
        textWarning.setText(R.string.PartyNewPlayersWarning);
        String message = (String) textWarning.getText();
        message = message.replace("X", String.valueOf(Util.MINIMUM_PLAYER));
        textWarning.setText(message);


        // When the button is clicked : launches the Activity creating a Party
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We get the name entered in the Edit Text
                final String name = editText.getText().toString();

                // If the name is valid (not empty && not taken already)
                if( name.length() != 0 && !parentActivity.party.containsPlayer(name) )
                {
                    // The order is important :
                    // FIRST - We add the Player to the Party
                    parentActivity.party.addPlayer(name);

                    // SECOND - Adding the Chip
                    addChip(name);

                    // FINALLY - We reset the Edit Text
                    editText.setText("");
                }
            }
        });


        // Creating as many Chip as needed
        for (Player player : parentActivity.party.getPlayers())
        {
            addChip( player.getName() );
        }
    }




    /**
     * Adds a Chip to the layout (only GUI display, no data is modified)
     * @param name Name to display inside the Chip
     */
    private void addChip(final String name)
    {
        // Create a new Chip
        final Chip chip = new Chip(parentActivity);

        // Define its style
        ChipDrawable drawable = ChipDrawable.createFromAttributes(parentActivity, null, 0, R.style.Widget_MaterialComponents_Chip_Entry);
        chip.setChipDrawable(drawable);

        // We set some of its property
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setText(name);

        // We add a Listener
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We remove the Chip
                chipGroup.removeView(chip);

                // We remove the according Player from the Party
                parentActivity.party.removePlayer(name);

                // We set the warning's text if needed
                setWarningVisible();
            }
        });

        // We add the chip to the ChipGroup
        chipGroup.addView(chip);

        // We set the warning's text if needed
        setWarningVisible();
    }




    /**
     * Set the Warning visibility accordingly
     */
    private void setWarningVisible()
    {
        // We set its visibility accordingly
        int visibility = (parentActivity.party.numberOfPlayers < Util.MINIMUM_PLAYER) ? View.VISIBLE : View.INVISIBLE;
        textWarning.setVisibility(visibility);
    }
}