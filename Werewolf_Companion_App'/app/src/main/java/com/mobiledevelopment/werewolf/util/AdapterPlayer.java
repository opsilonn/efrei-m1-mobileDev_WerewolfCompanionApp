package com.mobiledevelopment.werewolf.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        // We display data accordingly

        // If the data can be shown : change the Role's icon
        if( !context.party.isDataHidden() )
        {
            holder.image.setImageResource(player.getRole().getIcon());

            // If the player is alive : show his Team's color
            if( player.isAlive() )
            {
                holder.name.setTextColor(player.getRole().getTeam().getColor());
            }
        }
        // Otherwise, it is the default color and default Icon


        holder.name.setText(player.getName());

        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
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
     * Sub-class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            // Calling the constructor
            super(itemView);

            // Getting the fields
            image = itemView.findViewById(R.id.PlayerImageView);
            name = itemView.findViewById(R.id.PlayerTextName);
            mainLayout = itemView.findViewById(R.id.rowPlayer);
        }
    }
}