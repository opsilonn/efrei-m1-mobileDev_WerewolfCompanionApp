package com.mobiledevelopment.werewolf.model;

import android.graphics.Color;

/**
 * An Enumeration describing the different Teams to which a {@link Role} may belong to
 */
public enum Team
{
    Villager("Villager", Color.argb(255, 0, 112, 192)),
    Werewolf("Werewolf", Color.argb(255, 192, 0, 0)),
    Other("Non-affiliated", Color.argb(255, 0, 176, 80));


    // Describing the fields
    String name;
    int color;


    // Constructor
    /**
     *
     * @param name Name of the instance
     * @param color Color describing the instance
     */
    Team(String name, int color)
    {
        this.name = name;
        this.color = color;
    }


    // GETTER SETTER
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
}