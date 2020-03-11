package com.mobiledevelopment.werewolf.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
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
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.dialogs.DialogRolePlayer;
import com.mobiledevelopment.werewolf.dialogs.DialogTrigger;
import com.mobiledevelopment.werewolf.model.Party;
import com.mobiledevelopment.werewolf.model.Player;


public class AdapterPlayer extends RecyclerView.Adapter<AdapterPlayer.MyViewHolder>
{
    private Context context;
    private Party party;
    private Player[] players;


    /**
     * Complete Constructor
     * @param context Context from where this RecyclerView is created
     */
    public AdapterPlayer(Context context, Party party)
    {
        this.context = context;
        this.party = party;

        players = new Player[party.numberOfPlayers];
        for (int i = 0; i < party.numberOfPlayers; i++)
        {
            players[i] = party.getPlayers().get(i);
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
        if(player.isAlive() && !party.isDataHidden())
        {
            // Display the button to PopUp
            holder.buttonPopUp.setVisibility(View.VISIBLE);

            // When clicking the Kill button :
            // Change the text
            holder.buttonPopUp.setOnClickListener(v ->
            {
                // We create the Dialog
                DialogTrigger dialogTrigger = new DialogTrigger(context, party, player);

                // We display it
                dialogTrigger.show();
            });




            // Display the button to Kill
            holder.buttonKill.setVisibility(View.VISIBLE);

            // When clicking the Kill button :
            // Change the text
            holder.buttonKill.setOnClickListener(v -> {
                // We set the player as dying (or not)
                player.setDying( !player.isDying() );

                // We set the text
                setTextName(holder, player);
            });
        }
        else
        {
            // Hide the button to PopUp
            holder.buttonPopUp.setVisibility(View.INVISIBLE);
            // Hide the button to Kill
            holder.buttonKill.setVisibility(View.INVISIBLE);
        }


        // When clicking the holder : PopUp displaying the role
        holder.mainLayout.setOnClickListener(view -> {
            // We create and set a dialog
            DialogRolePlayer dialogRolePlayer = new DialogRolePlayer(context, player.getRole(), player.getName());
            dialogRolePlayer.show();
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

        // If the player is dead : custom color + we can see the icon
        if( !player.isAlive() )
        {
            holder.name.setTextColor(context.getResources().getColor(R.color.colorDead));
            // Custom Icon
            holder.image.setImageResource(player.getRole().getIcon());
        }

        // If the data are Hidden :
        // Default Icon
        // Default color if alive
        // Display only the name
        // If it is not :
        if( !party.isDataHidden() )
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
                String placeholder = context.getResources().getString(R.string.PlaceholderName);

                // We get the message
                String content = context.getResources().getString(R.string.RowPlayerDefaultNameIsDying);

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
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        ImageButton buttonPopUp;
        ImageButton buttonKill;
        ConstraintLayout mainLayout;

        MyViewHolder(@NonNull View itemView)
        {
            // Calling the constructor
            super(itemView);

            // Getting the fields
            image = itemView.findViewById(R.id.PlayerImageView);
            name = itemView.findViewById(R.id.PlayerTextName);
            buttonPopUp = itemView.findViewById(R.id.PlayerButtonPopUp);
            buttonKill = itemView.findViewById(R.id.PlayerButtonKill);
            mainLayout = itemView.findViewById(R.id.rowPlayer);
        }
    }
}