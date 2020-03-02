package com.mobiledevelopment.werewolf.model;

import java.io.Serializable;


/**
 * Class describing a player.
 * Implements the {@link Serializable} to allow the serialization of data.
 */
public class Player implements Serializable
{
    private String name;
    private Role role;
    private boolean isAlive;
    private boolean isDying;


    // CONSTRUCTORS
    /**
     * Partial Constructor of the class
     * @param name Name of the instance
     */
    public Player(String name)
    {
        this.name = name;
        role = null;
        isAlive = true;
        isDying= false;
    }

    /**
     * Complete Constructor of the class
     * @param name Name of the instance
     * @param role Role of the instance
     * @param isAlive Whether the instance is alive or not
     */
    public Player(String name, Role role, boolean isAlive, boolean isDying)
    {
        this.name = name;
        this.role = role;
        this.isAlive = isAlive;
        this.isDying = isDying;
    }


    // GETTER && SETTER

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean isAlive) { this.isAlive = isAlive; }
    public boolean isDying() { return isDying; }
    public void setDying(boolean isDying) { this.isDying = isDying; }
}
