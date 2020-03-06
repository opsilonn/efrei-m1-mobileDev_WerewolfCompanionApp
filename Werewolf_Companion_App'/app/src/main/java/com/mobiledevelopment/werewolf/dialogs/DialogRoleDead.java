package com.mobiledevelopment.werewolf.dialogs;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.model.Player;


/**
 * Class representing a {@link DialogRole} when a {@link Player} with a {@link Role} that activates at his death
 */
public class DialogRoleDead extends DialogRole
{
    public DialogRoleDead(@NonNull Context context, Role role, String playerName)
    {
        super(context, role);

        // we set the text as visible
        textPlayer.setVisibility(View.VISIBLE);

        // We get the placeholder
        String placeholder = context.getResources().getString(R.string.PlaceholderName);

        // We change the text displaying the player's name
        String contentPlayer = context.getResources().getString(R.string.RoleTextPlayed);
        contentPlayer = contentPlayer.replace(placeholder, playerName);
        textPlayer.setText(contentPlayer);

        // We change the title of the Dialog
        String contentTitle = context.getResources().getString(R.string.RolePlayerDead);
        contentTitle = contentTitle.replace(placeholder, context.getResources().getString(role.getName()));
        textName.setText(contentTitle);
    }
}
