package com.mobiledevelopment.werewolf.model;

import java.io.Serializable;
import java.util.ArrayList;


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
    }


    /**
     * Complete Constructor
     * @param players List of Players of the instance
     * @param partyID ID of the party
     * @param cptTurns Number of Turns spent in the party
     * @param dataHidden Whether we display or not the crucial data of the {@link Player}
     */
    public Party(ArrayList<Player> players, String partyID, int cptTurns, boolean dataHidden)
    {
        this.players = players;
        this.partyID = partyID;
        numberOfPlayers = players.size();
        this.cptTurns = cptTurns;
        this.dataHidden = dataHidden;
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

    public void nextTurn() { this.cptTurns++; }

    // GETTER && SETTER
    public ArrayList<Player> getPlayers() { return players; }
    public void setPlayers(ArrayList<Player> players) { this.players = players; }
    public String getPartyID() { return partyID; }
    public void setPartyID(String partyID) { this.partyID = partyID; }
    public int getCptTurns() { return cptTurns; }
    public void setCptTurns(int cptTurns) { this.cptTurns = cptTurns; }
    public boolean isDataHidden() { return dataHidden; }
    public void setDataHidden(boolean dataHidden) { this.dataHidden = dataHidden; }
}