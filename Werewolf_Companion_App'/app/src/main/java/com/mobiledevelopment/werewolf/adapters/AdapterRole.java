package com.mobiledevelopment.werewolf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mobiledevelopment.werewolf.activities.ActivityPartyNew;
import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.CustomIntent;
import com.mobiledevelopment.werewolf.model.Role;
import com.mobiledevelopment.werewolf.dialogs.DialogRole;


public class AdapterRole extends RecyclerView.Adapter<AdapterRole.MyViewHolder>
{
    // By Default
    private Context context;
    private CustomIntent customIntent;
    private Role[] roles;

    // If adding Roles
    private int[] roleNumbers;
    private int MAX;


    /**
     * Complete Constructor
     * @param context Context from where this RecyclerView is created
     * @param customIntent Custom Intent for which we deploy the Recycler View
     * @param roles Array of {@link Role} to display
     */
    public AdapterRole(Context context, CustomIntent customIntent, Role[] roles)
    {
        this.context = context;
        this.customIntent = customIntent;
        this.roles = roles;

        switch(customIntent)
        {
            // Roles by default : display all the roles, with no additional interface
            case RV_ROLES_DEFAULT:
                break;

            // Roles to add : display the - and + buttons
            case RV_ROLES_ADD:
                    // We set the array accordingly
                    roleNumbers = new int[roles.length];

                    // We fill it with the number of roles selected
                    for(int i = 0; i < roles.length; i++)
                    {
                        roleNumbers[i] = ((ActivityPartyNew) context).numberOfRolesInstance(roles[i]);
                    }

                    // We set the MAXIMUM value
                    MAX = ((ActivityPartyNew) context).party.numberOfPlayers;
                break;

            // Only get unique references of all the roles played and alive
            case RV_ROLE_HAS_PLAYED:
                break;
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

        // When clicking the holder : PopUp displaying the role
        holder.mainLayout.setOnClickListener(view -> {
            final DialogRole dialogRole = new DialogRole(context, role);
            dialogRole.show();
        });


        // If we are setting the roles :
        // We add listeners to the buttons
        if(customIntent == CustomIntent.RV_ROLES_ADD)
        {
            // First, we set the buttons enabled (or not) accordingly
            holder.buttonDelete.setEnabled(roleNumbers[position] != 0);

            // If the role is unique, we can only add 1 role (active if value is 0)
            if(role.isUnique())
            {
                holder.buttonAdd.setEnabled(roleNumbers[position] == 0);
            }
            // Otherwise : active until the MAXIMUM is reached
            else
            {
                holder.buttonAdd.setEnabled(roleNumbers[position] < MAX);
            }


            // Second, we give a Listeners

            // We give a Listener to the DELETE button
            holder.buttonDelete.setOnClickListener(v -> {
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
            });


            // We give a Listener to the ADD button
            holder.buttonAdd.setOnClickListener(v -> {
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
            });


            // By default, we set the text displaying the current value
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

        if(customIntent == CustomIntent.RV_ROLE_HAS_PLAYED)
        {
            holder.checkBox.setChecked( ((ActivityParty) context).roleHasPlayed[position] );
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> ((ActivityParty) context).roleHasPlayed[position] = !((ActivityParty) context).roleHasPlayed[position]);
        }
        else
        {
            holder.checkBox.setVisibility(View.INVISIBLE);
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
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;
        ConstraintLayout mainLayout;

        // Fields for adding / removing Roles
        TextView textNumberOfRoles;
        Button buttonAdd;
        Button buttonDelete;

        // Fields for whether a role has played or not
        CheckBox checkBox;

        MyViewHolder(@NonNull View itemView)
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

            // Fields for whether a role has played or not
            checkBox = itemView.findViewById(R.id.RoleCheckBox);
        }
    }
}