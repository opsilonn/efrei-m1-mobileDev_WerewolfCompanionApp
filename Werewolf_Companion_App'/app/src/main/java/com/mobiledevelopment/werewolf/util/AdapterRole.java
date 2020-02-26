package com.mobiledevelopment.werewolf.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiledevelopment.werewolf.ActivityRole;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;


public class AdapterRole extends RecyclerView.Adapter<AdapterRole.MyViewHolder>
{
    private Context context;
    private boolean setRoles;
    private Role[] roles;
    public int[] roleNumbers;
    public int MAX = 4;

    /**
     * Complete Constructor
     * @param context Context where this RecyclerView is created
     * @param setRoles Whether the Adapter can manage the number of Roles of a party
     */
    public AdapterRole(Context context, boolean setRoles)
    {
        this.context = context;
        this.setRoles = setRoles;
        roles = Role.values();
        roleNumbers = new int[roles.length];
        for(int i = 0; i < roles.length; i++)
        {
            roleNumbers[i] = 0;
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
        holder.title.setTextColor(role.getTeam().getColor());
        holder.title.setText(role.getName());
        holder.image.setImageResource(role.getIcon());

        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ActivityRole.class);
                intent.putExtra("role", role);
                intent.putExtra("playerName", "");
                context.startActivity(intent);
            }
        });


        // If we are setting the roles :
        // We add listeners to the buttons
        if(setRoles)
        {
            // We give a Listener to the DELETE button
            holder.buttonDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v)
                {
                    // We decrement the current counter
                    roleNumbers[position]--;

                    // We set the text displaying the current value
                    holder.textNumberOfRoles.setText( String.valueOf(roleNumbers[position]) );


                    // If the value is too low :
                    // Disable the DELETE button
                    if(roleNumbers[position] == 0)
                    {
                        holder.buttonDelete.setEnabled(false);
                    }


                    // If the role is unique and is now equal to O
                    // OR
                    // If the value reaches the (MAXIMUM value - 1),
                    // Enable the ADD button
                    if((role.isUnique() && roleNumbers[position] == 0) || roleNumbers[position] == MAX - 1)
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
                    // We increment the current counter
                    roleNumbers[position]++;

                    // We set the text displaying the current value
                    holder.textNumberOfRoles.setText( String.valueOf(roleNumbers[position]) );


                    // If the role is Unique : only one instance max
                    // OR
                    // If the value is too high :
                    // Disable the ADD button
                    if(role.isUnique() || roleNumbers[position] == MAX)
                    {
                        System.out.println("is Unique : " + role.isUnique());
                        System.out.println("MAX is reached : " + (roleNumbers[position] == MAX));
                        holder.buttonAdd.setEnabled(false);
                    }


                    // If the value reaches the (MINIMUM value + 1 == 1),
                    // Enable the DELETE button
                    if(roleNumbers[position] == 1)
                    {
                        holder.buttonDelete.setEnabled(true);
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
        TextView title;
        ImageView image;
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
            title = itemView.findViewById(R.id.RoleTextTitle);
            image = itemView.findViewById(R.id.RoleImageView);
            mainLayout = itemView.findViewById(R.id.rowRole);

            // Fields for adding / removing Roles
            textNumberOfRoles = itemView.findViewById(R.id.RoleTextNumberOfRoles);
            buttonAdd = itemView.findViewById(R.id.RoleButtonAdd);
            buttonDelete = itemView.findViewById(R.id.RoleButtonRemove);
        }
    }
}