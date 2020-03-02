package com.mobiledevelopment.werewolf.model;


/**
 * An enumeration describing the order in which the {@link Role} activate their powers
 */
public enum PowerActivationOrder
{
    BEGIN_NEVER_ACTIVATES,
    Villager,
    AbominableSectarian,
    END_NEVER_ACTIVATES,


    BEGIN_WHEN_ROLE_DIES,
    VillageIdiot,
    Hunter,
    END_WHEN_ROLE_DIES,


    BEGIN_NIGHT,
    StuttererJudge,
    Cupid,
    Savior,
    SmallGirl,
    Werewolf,
    WhiteWolf,
    BigBadWolf,
    InfectFatherOfWolves,
    FlutePlayer,
    Witch,
    SoothSayer,
    Crow,
    Barber,
    Weasel,
    END_NIGHT,

    BEGIN_DAY,
    Inquisitor,
    HonestMan,
    END_DAY
}