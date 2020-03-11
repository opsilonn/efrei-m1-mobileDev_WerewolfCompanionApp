package com.mobiledevelopment.werewolf.util;


import androidx.annotation.StringRes;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;
import java.util.ArrayList;
import java.util.List;


public class Util
{
    public static final int IMAGE_LOGO = R.drawable.logo;
    public static final int IMAGE_DEFAULT = R.drawable.im_villager;

    public static final String EXTRA_PARTY = "extraParty";
    public static final String EXTRA_PLAYERS = "extraPlayers";
    public static final String EXTRA_ROLES = "extraRoles";

    public static final @StringRes int HAS_NO_TRIGGER_MESSAGE = 0;

    public static final @StringRes int PLACEHOLDER_NAME = R.string.PlaceholderName;


    public static final int MINIMUM_PLAYER = 4;



    /**
     * Gets all the {@link Role} as they are present in their Enum
     * @return all the {@link Role} as they are present in their Enum
     */
    public static Role[] getRolesAllByDefault()
    {
        return Role.values();
    }


    /**
     * Gets all the {@link Role} in order, according to an input List
     * @param roles List of all the {@link Role} we want to order by their {@link com.mobiledevelopment.werewolf.model.PowerActivationOrder}
     * @return
     */
    public static Role[] getRolesByOrderFromList(List<Role> roles)
    {
        // First - we delete all the roles that do not activate
        // We create a List
        List<Role> rolesPurified = new ArrayList<>();

        // We only keep the roles that activate
        for (Role role : roles)
        {
            if(!role.isNeverActivated())
            {
                rolesPurified.add(role);
            }
        }


        // Second - we order the Roles
        // We initialize the return array
        int size = rolesPurified.size();
        Role[] rolesToReturn = new Role[size];

        for(int i = 0; i < size; i++)
        {
            // We set the current role
            Role currentRole = rolesPurified.get(0);

            // We compare the roles
            for (Role r: rolesPurified)
            {
                if(currentRole.getPowerActivationOrder().compareTo( r.getPowerActivationOrder()) > 0)
                {
                    currentRole = r;
                }
            }

            // We set the value into the array
            rolesToReturn[i] = currentRole;
            // We delete him from the list
            rolesPurified.remove(currentRole);
        }

        return rolesToReturn;
    }
}