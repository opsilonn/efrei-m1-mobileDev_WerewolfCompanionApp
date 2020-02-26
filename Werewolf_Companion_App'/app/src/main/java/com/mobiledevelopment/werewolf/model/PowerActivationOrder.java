package com.mobiledevelopment.werewolf.model;

public class PowerActivationOrder
{
    // We create a default value for those whose power do not activate (or activates once they die)
    static final int NeverActivates = 0;

    static final int Villager  = NeverActivates;

    static final int Cupid  = 1;
    static final int Werewolf  = 2;
    static final int WhiteWolf  = 3;
    static final int BigBadWolf  = 3;
    static final int Crow  = 5;
    static final int Weasel  = 6;
}