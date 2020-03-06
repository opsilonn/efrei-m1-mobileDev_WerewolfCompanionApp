package com.mobiledevelopment.werewolf.dialogs;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;


/**
 * Dialog displayed when wanting data about a Player
 */
public class DialogRolePlayer extends DialogRole
{
    public DialogRolePlayer(@NonNull Context context, Role role, String playerName)
    {
        super(context, role);

        // we set the text as visible
        textPlayer.setVisibility(View.VISIBLE);

        // We get the placeholder
        String placeholder = context.getResources().getString(R.string.PlaceholderName);

        // We set the player name
        String content = context.getResources().getString(R.string.RoleTextPlayer);
        content = content.replace(placeholder, playerName);
        textPlayer.setText(content);
    }
}
