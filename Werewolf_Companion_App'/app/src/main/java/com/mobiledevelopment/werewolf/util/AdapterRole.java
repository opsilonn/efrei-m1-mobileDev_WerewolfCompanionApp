package com.mobiledevelopment.werewolf.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mobiledevelopment.werewolf.activities.ActivityPartyNew;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;
import java.util.Objects;


public class AdapterRole extends RecyclerView.Adapter<AdapterRole.MyViewHolder>
{
    private Context context;
    private boolean setRoles;
    private Role[] roles;
    private int[] roleNumbers;
    private int MAX;


    /**
     * Complete Constructor
     * @param context Context from where this RecyclerView is created
     */
    public AdapterRole(Context context)
    {
        this.context = context;
        setRoles = (context.getClass() == ActivityPartyNew.class);
        roles = Role.values();

        // We only keep trace of the roles if we want to manage some (otherwise, it's useless)
        if(setRoles)
        {
            // We set the array accordingly
            roleNumbers = new int[roles.length];

            // We fill it with the number of roles selected
            for(int i = 0; i < roles.length; i++)
            {
                roleNumbers[i] = ((ActivityPartyNew) context).numberOfRolesInstance(roles[i]);
            }

            // We set the MAXIMUM value
            MAX = ((ActivityPartyNew) context).party.numberOfPlayers;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_role, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final @NonNull MyViewHolder holder, final int position)
    {
        // We get the current role
        final Role role = roles[position];

        // We display data accordingly
        holder.name.setTextColor(role.getTeam().getColor());
        holder.name.setText(role.getName());
        holder.image.setImageResource(role.getIcon());

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

                // We set the widgets (the text Player will remain hidden for the moment)
                imageView.setImageResource(role.getImageRes());
                textPlayer.setVisibility(View.INVISIBLE);
                textName.setText(role.getName());
                textName.setTextColor(role.getTeam().getColor());
                textDescription.setText(role.getDescription());


                // We get the text that acts as a button, which when clicked, dismisses the PopUp
                TextView textDismiss = dialog.findViewById(R.id.RoleTextGoBack);
                textDismiss.setOnClickListener(new View.OnClickListener()
                    {
                       @Override
                       public void onClick(View v)
                       {
                           dialog.dismiss();
                       }
                   }
                );

                // We resize the Dialog
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        // If we are setting the roles :
        // We add listeners to the buttons
        if(setRoles)
        {
            // First, we set the buttons enabled (or not) accordingly
            holder.buttonDelete.setEnabled(roleNumbers[position] != 0);
            holder.buttonAdd.setEnabled(roleNumbers[position] < MAX);


            // Second, we give a Listeners

            // We give a Listener to the DELETE button
            holder.buttonDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v)
                {
                    // We remove a Role from the list
                    ((ActivityPartyNew) context).roles.remove(role);

                    // We decrement the current counter
                    roleNumbers[position]--;

                    // We set the text displaying the current value
                    holder.textNumberOfRoles.setText( String.valueOf(roleNumbers[position]) );

                    // We set the text displaying the TOTAL number of Roles
                    ((ActivityPartyNew) context).fragmentRoles.setText();

                    // If we reached the minimum value :
                    if(roleNumbers[position] == 0)
                    {
                        holder.buttonDelete.setEnabled(false);
                    }

                    // If the role is unique and is now equal to O
                    // OR
                    // If the value reaches the (MAXIMUM value - 1)
                    if((roles[position].isUnique() && roleNumbers[position] == 0) || roleNumbers[position] == MAX - 1)
                    {
                        holder.buttonAdd.setEnabled(true);
                    }
                }
            });


            // We give a Listener to the ADD button
            holder.buttonAdd.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v)
                {
                    // We remove a Role from the list
                    ((ActivityPartyNew) context).roles.add(role);

                    // We increment the current counter
                    roleNumbers[position]++;

                    // We set the text displaying the current value
                    holder.textNumberOfRoles.setText( String.valueOf(roleNumbers[position]) );

                    // We set the text displaying the TOTAL number of Roles
                    ((ActivityPartyNew) context).fragmentRoles.setText();


                    // Setting the Buttons enabled (or not) accordingly

                    // If I have more than 1 Role
                    if(roleNumbers[position] != 0)
                    {
                        holder.buttonDelete.setEnabled(true);
                    }

                    // If the current Role is unique and already taken
                    // OR
                    // If I reach the maximum
                    if((roles[position].isUnique() && roleNumbers[position] == 1) || roleNumbers[position] == MAX)
                    {
                        holder.buttonAdd.setEnabled(false);
                    }
                }
            });


            // We set the text displaying the current value
            holder.textNumberOfRoles.setText( String.valueOf(roleNumbers[position]) );
        }
        // If we are NOT setting the roles :
        // We hide the following fields
        else
        {
            holder.buttonDelete.setVisibility(View.INVISIBLE);
            holder.buttonAdd.setVisibility(View.INVISIBLE);
            holder.textNumberOfRoles.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return roles.length;
    }




    /**
     * Sub-class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        ConstraintLayout mainLayout;

        // Fields for adding / removing Roles
        TextView textNumberOfRoles;
        Button buttonAdd;
        Button buttonDelete;


        public MyViewHolder(@NonNull View itemView)
        {
            // Calling the constructor
            super(itemView);

            // Getting the fields
            image = itemView.findViewById(R.id.RoleImageView);
            name = itemView.findViewById(R.id.RoleTextTitle);
            mainLayout = itemView.findViewById(R.id.rowRole);

            // Fields for adding / removing Roles
            textNumberOfRoles = itemView.findViewById(R.id.RoleTextNumberOfRoles);
            buttonAdd = itemView.findViewById(R.id.RoleButtonAdd);
            buttonDelete = itemView.findViewById(R.id.RoleButtonRemove);
        }
    }
}