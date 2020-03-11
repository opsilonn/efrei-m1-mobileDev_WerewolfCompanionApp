package com.mobiledevelopment.werewolf.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;
import java.util.Objects;


public class DialogRole extends Dialog
{
    protected ImageView imageView;
    protected TextView textPlayer;
    protected TextView textName;
    protected TextView textDescription;

    public DialogRole(@NonNull Context context, Role role)
    {
        super(context);
        setContentView(R.layout.popup_role);

        // We get the widgets references
        imageView = findViewById(R.id.RoleImage);
        textPlayer = findViewById(R.id.RoleTextPlayer);
        textName = findViewById(R.id.RoleTextName);
        textDescription = findViewById(R.id.RoleTextDescription);

        imageView.setImageResource(role.getImageRes());


        // By default, this shall be INVISIBLE
        textPlayer.setVisibility(View.INVISIBLE);

        textName.setText(role.getName());
        textName.setTextColor(role.getTeam().getColor());
        textDescription.setText(role.getDescription());


        // We get the text that acts as a button, which when clicked, dismisses the PopUp
        TextView textDismiss = findViewById(R.id.RoleTextGoBack);
        textDismiss.setOnClickListener( v -> dismiss() );

        // We resize the Dialog
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
