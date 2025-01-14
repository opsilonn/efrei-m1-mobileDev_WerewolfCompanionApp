package com.mobiledevelopment.werewolf.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class describing a Party of the game.
 * Implements the {@link Serializable} to allow the serialization of data.
 */
public class Party implements Serializable
{
    public int numberOfPlayers;
    private ArrayList<Player> players;
    private String partyID;
    private int cptTurns;
    private boolean dataHidden;
    private boolean isNight;
    private String[] triggers;
    private Boolean[] isTriggerPermanent;


    /**
     * Default Constructor
     */
    public Party()
    {
        numberOfPlayers = 0;
        players = new ArrayList<>();
        partyID = null;
        cptTurns = 1;
        dataHidden = false;
        isNight = true;
        triggers = new String[0];
        isTriggerPermanent = new Boolean[0];
    }


    /**
     * Complete Constructor
     * @param players List of Players of the instance
     * @param partyID ID of the party
     * @param cptTurns Number of Turns spent in the party
     * @param dataHidden Whether we display or not the crucial data of the {@link Player}
     * @param isNight Whether it is the night or not
     * @param triggers Array of triggers of the instance
     * @param isTriggerPermanent Whether the trigger is Permanent or not
     */
    public Party(ArrayList<Player> players, String partyID, int cptTurns, boolean dataHidden, boolean isNight, String[] triggers, Boolean[] isTriggerPermanent)
    {
        this.players = players;
        this.partyID = partyID;
        numberOfPlayers = players.size();
        this.cptTurns = cptTurns;
        this.dataHidden = dataHidden;
        this.isNight = isNight;
        this.triggers = triggers;
        this.isTriggerPermanent = isTriggerPermanent;
    }


    // Useful methods

    /**
     * Returns whether or not the instance contains a Player with a given name
     * @param name Name of the player we want to know if he's in the Party or not
     * @return Whether or not the instance contains a Player with a given name
     */
    public boolean containsPlayer(String name)
    {
        for (Player player : getPlayers())
        {
            if(player.getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Adds a Player to the Party
     * @param name Name of the Player we want to add
     */
    public void addPlayer(String name)
    {
        getPlayers().add( new Player(name) );
        numberOfPlayers++;
    }


    /**
     * Removes a Player to the Party
     * @param name Name of the Player we want to remove
     */
    public void removePlayer(String name)
    {
        // We initialize a variable
        Player playerToRemove = null;

        // We iterate through the Players
        for (Player player : getPlayers())
        {
            // If the name corresponds : we memorize him
            if(player.getName().equals(name))
            {
                playerToRemove = player;
            }
        }

        // If we found the player : we remove it
        if(playerToRemove != null)
        {
            getPlayers().remove(playerToRemove);
            numberOfPlayers--;
        }
    }


    /**
     * Returns all the {@link Role} (no doublon, only alive) that play at night
     * @return All the {@link Role} (no doublon, only alive) that play at night
     */
    public List<Role> getAllRolesDay()
    {
        // We create a list of Roles
        List<Role> roles = new ArrayList<>();

        // For each Player, we add its role if
        // he's alive
        // it's not in the list already
        // it's a NIGHT Role
        for (Player player : getPlayers())
        {
            Role currentRole = player.getRole();

            if(player.isAlive()
                    && !roles.contains(currentRole)
                    && currentRole.isActivatedAtDay())
            {
                roles.add(currentRole);
            }
        }

        // We return the List
        return roles;
    }


    /**
     * Returns all the {@link Role} (no doublon, only alive) that play at night
     * @return All the {@link Role} (no doublon, only alive) that play at night
     */
    public List<Role> getAllRolesNight()
    {
        // We create a list of Roles
        List<Role> roles = new ArrayList<>();

        // For each Player, we add its role if
        // he's alive
        // it's not in the list already
        // it's a NIGHT Role
        for (Player player : getPlayers())
        {
            Role currentRole = player.getRole();

            if(player.isAlive()
                && !roles.contains(currentRole)
                && currentRole.isActivatedAtNight())
            {
                roles.add(currentRole);
            }
        }

        // We return the List
        return roles;
    }



    /**
     * We change the time of day of the party.
     * If we transition from day to night, we increment the turn counter AND verify the triggers
     */
    public void changeTime()
    {
        isNight = !isNight;
        if(isNight)
        {
            this.cptTurns++;
            verifyTrigger();
        }
    }


    /**
     * Initializes all the trigger (for this instance and its players)
     * @param context Context from where we initialize
     */
    public void initializeTriggers(Context context)
    {
        List<String> triggerList = new ArrayList<>();
        List<Boolean> triggerPermanent = new ArrayList<>();

        // We create the trigger List
        for (Player player : players)
        {
            Role role = player.getRole();

            if(role.hasTriggerMessage())
            {
                triggerList.add( context.getResources().getString(role.triggerRes));
                triggerPermanent.add( role.isTriggerPermanent );
            }
        }

        // We convert it into an array
        this.triggers = triggerList.toArray(new String[0]);
        this.isTriggerPermanent = triggerPermanent.toArray(new Boolean[0]);


        // We set the trigger Array for each player
        for (Player player : getPlayers())
        {
            boolean[] triggersPlayer = new boolean[triggers.length];

            // Default value : false
            for(int i = 0; i < triggersPlayer.length; i++)
            {
                triggersPlayer[i] = false;
            }

            player.setTriggers(triggersPlayer);
        }
    }


    /**
     * Verify and change if needed the triggers of the players
     */
    private void verifyTrigger()
    {
        // For each Player
        for (Player player : getPlayers())
        {
            // For each Trigger
            for (int i = 0; i < getIsTriggerPermanent().length; i++)
            {
                // If it isn't permanent and set to true for the player :
                // Reset it to false
                if(!getIsTriggerPermanent()[i] && player.getTriggers()[i])
                {
                    player.getTriggers()[i] = false;
                }
            }
        }
    }

    // GETTER && SETTER
    public ArrayList<Player> getPlayers() { return players; }
    public void setPlayers(ArrayList<Player> players) { this.players = players; numberOfPlayers = players.size(); }
    public String getPartyID() { return partyID; }
    public void setPartyID(String partyID) { this.partyID = partyID; }
    public int getCptTurns() { return cptTurns; }
    public void setCptTurns(int cptTurns) { this.cptTurns = cptTurns; }
    public boolean isDataHidden() { return dataHidden; }
    public void setDataHidden(boolean dataHidden) { this.dataHidden = dataHidden; }
    public boolean isNight() { return isNight; }
    public void setNight(boolean isNight) { this.isNight = isNight; }
    public String[] getTriggers() { return triggers; }
    public void setTriggers(String[] triggers) { this.triggers =triggers; }
    public Boolean[] getIsTriggerPermanent() { return isTriggerPermanent; }
}