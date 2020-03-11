package com.mobiledevelopment.werewolf.model;

import android.graphics.Color;

import androidx.annotation.StringRes;

import com.mobiledevelopment.werewolf.R;


/**
 * An Enumeration describing the different Teams to which a {@link Role} may belong to
 */
public enum Team
{
    Villager(R.string.team_villager, Color.argb(255, 0, 112, 192)),
    Werewolf(R.string.team_werewolf, Color.argb(255, 192, 0, 0)),
    Other(R.string.team_other, Color.argb(255, 0, 176, 80));


    // Describing the fields
    @StringRes int name;
    int color;


    // Constructor
    /**
     *
     * @param name Name of the instance
     * @param color Color describing the instance
     */
    Team(@StringRes int name, int color)
    {
        this.name = name;
        this.color = color;
    }


    // GETTER SETTER
    public @StringRes int getName() { return name; }
    public void setName(@StringRes int name) { this.name = name; }
    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
}