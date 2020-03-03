package com.mobiledevelopment.werewolf.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Player;
import java.util.Objects;


public class AdapterPlayer extends RecyclerView.Adapter<AdapterPlayer.MyViewHolder>
{
    private ActivityParty context;
    private Player[] players;


    /**
     * Complete Constructor
     * @param context Context from where this RecyclerView is created
     */
    public AdapterPlayer(Context context)
    {
        this.context = (ActivityParty) context;

        players = new Player[this.context.party.numberOfPlayers];

        int i = 0;
        for (Player player : this.context.party.getPlayers())
        {
            players[i++] = player;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_player, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final @NonNull MyViewHolder holder, final int position)
    {
        // We get the current role
        final Player player = players[position];


        // We set the text
        setTextName(holder, player);



        // If the player is alive and we're allowed to show his data
        if(player.isAlive() && !context.party.isDataHidden())
        {
            // Display the button to Kill
            holder.buttonKill.setVisibility(View.VISIBLE);

            // When clicking the Kill button :
            // Change the text
            holder.buttonKill.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // We set the player as dying (or not)
                    player.setDying( !player.isDying() );

                    // We set the text
                    setTextName(holder, player);
                }
            });
        }
        else
        {
            // Hide the button to Kill
            holder.buttonKill.setVisibility(View.INVISIBLE);
        }


        // When clicking the row :
        // Display a PopUp describing the Player / Role
        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // We create and set a dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popup_role);

                // We get the widgets references
                ImageView imageView = dialog.findViewById(R.id.RoleImage);
                TextView textPlayer = dialog.findViewById(R.id.RoleTextPlayer);
                TextView textName = dialog.findViewById(R.id.RoleTextName);
                TextView textDescription = dialog.findViewById(R.id.RoleTextDescription);

                // We set the widgets
                // Image
                imageView.setImageResource(player.getRole().getImageRes());

                // Player
                textPlayer.setVisibility(View.VISIBLE);
                String playerName = player.getName() + " " + textPlayer.getText();
                textPlayer.setText(playerName);

                // Name of the Role
                textName.setText(player.getRole().getName());
                textName.setTextColor(player.getRole().getTeam().getColor());

                // Description of the Role
                textDescription.setText(player.getRole().getDescription());


                // We get the text that acts as a button, which when clicked, dismisses the PopUp
                TextView textDismiss = dialog.findViewById(R.id.RoleTextGoBack);
                textDismiss.setOnClickListener(new View.OnClickListener()
                                               {
                                                   @Override
                                                   public void onClick(View v)
                                                   { dialog.dismiss();
                                                   }
                                               }
                );

                // We resize the Dialog
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return players.length;
    }


    /**
     * Sets the text displaying the players name
     * @param holder Holder of the player's row widget
     * @param player Player of which we are displaying the data
     */
    private void setTextName(final @NonNull MyViewHolder holder, Player player)
    {
        SpannableString message = new SpannableString(player.getName());

        // If the player is dead : custom color
        if( !player.isAlive() )
        {
            holder.name.setTextColor(context.getResources().getColor(R.color.colorDead));
        }

        // If the data are Hidden :
        // Default Icon
        // Default color if alive
        // Display only the name
        // If it is not :
        if( !context.party.isDataHidden() )
        {
            // Custom Icon
            holder.image.setImageResource(player.getRole().getIcon());

            // Team color if alive
            if( player.isAlive() )
            {
                holder.name.setTextColor(player.getRole().getTeam().getColor());
            }

            // If the player is dying
            if(player.isDying())
            {
                // We get the placeholder
                holder.name.setText(R.string.PlaceholderName);
                String placeholder = (String) holder.name.getText();

                // We get the message
                holder.name.setText(R.string.RowPlayerDefaultNameIsDying);
                String content = (String) holder.name.getText();

                // We modify the message
                content = content.replace(placeholder, player.getName());

                // We set the text, and make it Italic
                message = new SpannableString(content);
                message.setSpan(new StyleSpan(Typeface.ITALIC), 0, message.length(), 0);
            }
        }


        // Set the name
        message.setSpan(new StyleSpan(Typeface.BOLD), 0, message.length(), 0);
        holder.name.setText(message);
    }



    /**
     * Sub-class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        ImageButton buttonKill;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            // Calling the constructor
            super(itemView);

            // Getting the fields
            image = itemView.findViewById(R.id.PlayerImageView);
            name = itemView.findViewById(R.id.PlayerTextName);
            buttonKill = itemView.findViewById(R.id.PlayerButtonKill);
            mainLayout = itemView.findViewById(R.id.rowPlayer);
        }
    }
}