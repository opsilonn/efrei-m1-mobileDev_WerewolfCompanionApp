package com.mobiledevelopment.werewolf.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Party;
import com.mobiledevelopment.werewolf.model.Player;


public class DialogTrigger extends AlertDialog.Builder
{
    public DialogTrigger(@NonNull Context context, Party party, Player player)
    {
        super(context);


        // Manual list of things to add
        String[] triggers = party.getTriggers();

        // List representing the Player's triggers
        boolean[] triggersPlayer = player.getTriggers().clone();

        // We add E-VE-RY-THING
        setTitle(R.string.TriggerTitle).setMultiChoiceItems(triggers, triggersPlayer, (dialog, which, isChecked) -> triggersPlayer[which] = isChecked)
        // Do YES
        .setPositiveButton(R.string.TextApply, (dialog, id) ->
                player.setTriggers( triggersPlayer ))
        // Do NO
        .setNegativeButton(R.string.TextCancel, (dialog, id) ->
        {
        });
    }
}