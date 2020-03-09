package com.mobiledevelopment.werewolf.model;

import android.content.res.Resources;

import androidx.annotation.StringRes;
import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.util.Util;
import java.io.Serializable;


/**
 * A enumeration describing the different Roles a {@link Player} can incarnate.
 * Implements the {@link Serializable} to allow the serialization of data.
 */
public enum Role implements Serializable
{
    Villager(
            R.string.role_villager,
            R.string.role_villager_description,
            R.drawable.im_villager,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_villager,
            false,
            false,
            false,
            PowerActivationTime.None,
            PowerActivationOrder.Villager),

    Werewolf(
            R.string.role_werewolf,
            R.string.role_werewolf_description,
            R.drawable.im_werewolf,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            false,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Werewolf),

    WhiteWolf(
            R.string.role_whitewolf,
            R.string.role_whitewolf_description,
            R.drawable.im_white_wolf,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Other,
            R.drawable.ic_werewolf,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.WhiteWolf),

    BigBadWolf(
            R.string.role_big_bad_wolf,
            R.string.role_big_bad_wolf_description,
            R.drawable.im_big_bad_wolf,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.BigBadWolf),

    InfectFatherOfWolves(
            R.string.role_infect_father_of_wolves,
            R.string.role_infect_father_of_wolves_description,
            R.drawable.im_infect_father_of_wolves,
            R.string.trigger_role_infect_father_of_wolves,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            true,
            false,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.InfectFatherOfWolves),

    Cupid(
            R.string.role_cupid,
            R.string.role_cupid_description,
            R.drawable.im_cupid,
            R.string.trigger_role_cupid,
            Team.Villager,
            R.drawable.ic_cupid,
            true,
            false,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.Cupid),

    Witch(
            R.string.role_witch,
            R.string.role_witch_description,
            R.drawable.im_witch,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_witch,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Witch),

    SoothSayer(
            R.string.role_soothsayer,
            R.string.role_soothsayer_description,
            R.drawable.im_soothsayer,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_soothsayer,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.SoothSayer),

    Hunter(
            R.string.role_hunter,
            R.string.role_hunter_description,
            R.drawable.im_hunter,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_hunter,
            true,
            false,
            false,
            PowerActivationTime.Death,
            PowerActivationOrder.Hunter),

    HonestMan(
            R.string.role_honest_man,
            R.string.role_honest_man_description,
            R.drawable.im_honest_man,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_honest_man,
            true,
            false,
            false,
            PowerActivationTime.Day,
            PowerActivationOrder.HonestMan),

    VillageIdiot(
            R.string.role_village_idiot,
            R.string.role_village_idiot_description,
            R.drawable.im_village_idiot,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_village_idiot,
            true,
            false,
            false,
            PowerActivationTime.Death,
            PowerActivationOrder.VillageIdiot),

    Crow(
            R.string.role_crow,
            R.string.role_crow_description,
            R.drawable.im_crow,
            R.string.trigger_role_crow,
            Team.Villager,
            R.drawable.ic_crow,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Crow),

    Weasel(
            R.string.role_weasel,
            R.string.role_weasel_description,
            R.drawable.im_weasel,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_weasel,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Weasel),

    SmallGirl(
            R.string.role_small_girl,
            R.string.role_small_girl_description,
            R.drawable.im_small_girl,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_small_girl,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.SmallGirl),

    Savior(
            R.string.role_savior,
            R.string.role_savior_description,
            R.drawable.im_savior,
            R.string.trigger_role_savior,
            Team.Villager,
            R.drawable.ic_savior,
            true,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Savior),

    Inquisitor(
            R.string.role_inquisitor,
            R.string.role_inquisitor_description,
            R.drawable.im_inquisitor,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_inquisitor,
            true,
            true,
            false,
            PowerActivationTime.Day,
            PowerActivationOrder.Inquisitor),

    StuttererJudge(
            R.string.role_stutterer_judge,
            R.string.role_stutterer_judge_description,
            R.drawable.im_stutterer_judge,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_stutterer_judge,
            true,
            false,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.StuttererJudge),

    Barber(
            R.string.role_barber,
            R.string.role_barber_description,
            R.drawable.im_barber,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Villager,
            R.drawable.ic_barber,
            true,
            false,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Barber),

    AbominableSectarian(
            R.string.role_abominable_sectarian,
            R.string.role_abominable_sectarian_description,
            R.drawable.im_abominable_sectarian,
            Util.HAS_NO_TRIGGER_MESSAGE,
            Team.Other,
            R.drawable.ic_abominable_sectarian,
            true,
            false,
            false,
            PowerActivationTime.None,
            PowerActivationOrder.AbominableSectarian),

    FlutePlayer(
            R.string.role_flute_player,
            R.string.role_flute_player_description,
            R.drawable.im_flute_player,
            R.string.trigger_role_flute_player,
            Team.Other,
            R.drawable.ic_flute_player,
            true,
            true,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.FlutePlayer);



    // Describing the fields
    @StringRes int nameRes;
    @StringRes int descriptionRes;
    @StringRes int imageRes;
    @StringRes int triggerRes;
    Team team;
    int icon;
    boolean isUnique;
    boolean isPowerReusable;
    boolean isTriggerPermanent;
    PowerActivationTime powerActivationTime;
    PowerActivationOrder powerActivationOrder;


    // Constructor
    /**
     * Constructor of the class Role.
     *
     * If the Image and/or icon or not set, we give them default values, since a little effort is required to imported/search for the image/icon online.
     * For the other fields, I suppose they don't need such treatment.
     *
     * @param nameRes Name Resource of the instance
     * @param descriptionRes Description of the instance
     * @param imageRes Path leading to the image of the instance
     * @param triggerRes Path leading to the trigger message of the instance
     * @param team Team of the instance
     * @param icon Path leading to the icon of the instance
     * @param isUnique Whether the power of the instance is unique (only one instance)
     * @param isPowerReusable Whether the power of the instance is reusable or not
     * @param isPowerReusable Whether the trigger Message of the instance is permanent or not
     * @param powerActivationTime At which day-time the power of the instance activates
     * @param powerActivationOrder Specific order index when the power of the instance activates
     */
    Role(
            int nameRes,
            int descriptionRes,
            int imageRes,
            int triggerRes,
            Team team,
            int icon,
            boolean isUnique,
            boolean isPowerReusable,
            boolean isTriggerPermanent,
            PowerActivationTime powerActivationTime,
            PowerActivationOrder powerActivationOrder)
    {
        this.nameRes = nameRes;
        this.descriptionRes = descriptionRes;
        this.imageRes =  imageRes;
        this.triggerRes =  triggerRes;
        this.team = team;
        this.icon = (icon != 0) ? icon : R.drawable.ic_villager;
        this.isUnique = isUnique;
        this.isPowerReusable = isPowerReusable;
        this.isTriggerPermanent =  isTriggerPermanent;
        this.powerActivationTime = powerActivationTime;
        this.powerActivationOrder = powerActivationOrder;
    }


    /**
     * Returns whether a Role has a trigger message
     * @return Whether a Role has a trigger message
     */
    public boolean hasTriggerMessage()
    {
        return (triggerRes != Util.HAS_NO_TRIGGER_MESSAGE);
    }


    /**
     * Returns whether a Role is never activated
     * @return Whether a Role is never activated
     */
    public boolean isNeverActivated()
    {
        boolean afterBegin = ( getPowerActivationOrder().compareTo(PowerActivationOrder.BEGIN_NEVER_ACTIVATES) > 0);
        boolean beforeEnd = ( getPowerActivationOrder().compareTo(PowerActivationOrder.END_NEVER_ACTIVATES) < 0);

        return ( afterBegin && beforeEnd );
    }

    /**
     * Returns whether a Role activates when it dies
     * @return Whether a Role activates when it dies
     */
    public boolean isActivatedAtDeath()
    {
        boolean afterBegin = ( getPowerActivationOrder().compareTo(PowerActivationOrder.BEGIN_WHEN_ROLE_DIES) > 0);
        boolean beforeEnd = ( getPowerActivationOrder().compareTo(PowerActivationOrder.END_WHEN_ROLE_DIES) < 0);

        return ( afterBegin && beforeEnd );
    }

    /**
     * Returns whether a Role is activated at Night
     * @return Whether a Role is activated at Night
     */
    public boolean isActivatedAtNight()
    {
        boolean afterBegin = ( getPowerActivationOrder().compareTo(PowerActivationOrder.BEGIN_NIGHT) > 0);
        boolean beforeEnd = ( getPowerActivationOrder().compareTo(PowerActivationOrder.END_NIGHT) < 0);

        return ( afterBegin && beforeEnd );
    }

    /**
     * Returns whether a Role is activated at Day
     * @return Whether a Role is activated at Day
     */
    public boolean isActivatedAtDay()
    {
        boolean afterBegin = ( getPowerActivationOrder().compareTo(PowerActivationOrder.BEGIN_DAY) > 0);
        boolean beforeEnd = ( getPowerActivationOrder().compareTo(PowerActivationOrder.END_DAY) < 0);

        return ( afterBegin && beforeEnd );
    }

    // GETTER SETTER
    public int getName() { return nameRes; }
    public void setName(int nameRes) {
        this.nameRes = nameRes;
    }
    public int getDescription() {
        return descriptionRes;
    }
    public void setDescription(int descriptionRes) {
        this.descriptionRes = descriptionRes;
    }
    public int getImageRes() { return imageRes; }
    public void setImageRes(int imageRes) { this.imageRes = imageRes; }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public int getIcon() { return icon; }
    public void setIcon(int icon) { this.icon = icon; }
    public boolean isUnique() { return isUnique; }
    public void setUnique(boolean unique) { isUnique = unique; }
    public boolean isPowerReusable() {
        return isPowerReusable;
    }
    public void setPowerReusable(boolean powerReusable) {
        isPowerReusable = powerReusable;
    }
    public PowerActivationTime getPowerActivationTime() { return powerActivationTime; }
    public void setPowerActivationTime(PowerActivationTime powerActivationTime) { this.powerActivationTime = powerActivationTime; }
    public PowerActivationOrder getPowerActivationOrder() { return powerActivationOrder; }
    public void setPowerActivationOrder(PowerActivationOrder powerActivationOrder) { this.powerActivationOrder = powerActivationOrder; }
}