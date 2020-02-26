package com.mobiledevelopment.werewolf.model;


import androidx.annotation.StringRes;
import com.mobiledevelopment.werewolf.R;
import java.io.Serializable;


public enum Role implements Serializable
{
    Villager(
            R.string.role_villager,
            R.string.role_villager_description,
            R.drawable.im_villager,
            Team.Villager,
            R.drawable.ic_villager,
            false,
            false,
            PowerActivationTime.None,
            PowerActivationOrder.Villager),

    Werewolf(
            R.string.role_werewolf,
            R.string.role_werewolf_description,
            R.drawable.im_werewolf,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            false,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.Werewolf),

    WhiteWolf(
            R.string.role_whitewolf,
            R.string.role_whitewolf_description,
            R.drawable.im_white_wolf,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            true,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.WhiteWolf),

    BigBadWolf(
            R.string.role_big_bad_wolf,
            R.string.role_big_bad_wolf_description,
            R.drawable.im_big_bad_wolf,
            Team.Werewolf,
            R.drawable.ic_werewolf,
            true,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.BigBadWolf),

    Cupid(
            R.string.role_cupid,
            R.string.role_cupid_description,
            R.drawable.im_cupid,
            Team.Villager,
            R.drawable.ic_cupid,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Cupid),

    Weasel(
            R.string.role_weasel,
            R.string.role_weasel_description,
            R.drawable.im_weasel,
            Team.Villager,
            R.drawable.ic_weasel,
            true,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.Weasel),

    Crow(
            R.string.role_crow,
            R.string.role_crow_description,
            R.drawable.im_crow,
            Team.Villager,
            R.drawable.ic_crow,
            true,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.Crow);

    // Describing the fields
    @StringRes int nameRes;
    @StringRes int descriptionRes;
    @StringRes int imageRes;
    Team team;
    int icon;
    boolean isUnique;
    boolean isPowerReusable;
    PowerActivationTime powerActivationTime;
    int powerActivationOrder;


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
     * @param team Team of the instance
     * @param icon Path leading to the icon of the instance
     * @param isUnique Whether the power of the instance is unique (only one instance)
     * @param isPowerReusable Whether the power of the instance is reusable or not
     * @param powerActivationTime At which day-time the power of the instance activates
     * @param powerActivationOrder Specific order index when the power of the instance activates
     */
    Role(
            int nameRes,
            int descriptionRes,
            int imageRes,
            Team team,
            int icon,
            boolean isUnique,
            boolean isPowerReusable,
            PowerActivationTime powerActivationTime,
            int powerActivationOrder)
    {
        this.nameRes = nameRes;
        this.descriptionRes = descriptionRes;
        this.imageRes =  imageRes;
        this.team = team;
        this.icon = (icon != 0) ? icon : R.drawable.ic_villager;
        this.isUnique = isUnique;
        this.isPowerReusable = isPowerReusable;
        this.powerActivationTime = powerActivationTime;
        this.powerActivationOrder = powerActivationOrder;
    }


    // GETTER SETTER
    public int getName() {
        return nameRes;
    }
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
    public void setPowerActivationTime(int powerActivationOrder) { this.powerActivationOrder = powerActivationOrder; }
    public int getPowerActivationOrder() { return powerActivationOrder; }
    public void setPowerActivationOrder(int powerActivationOrder) { this.powerActivationOrder = powerActivationOrder; }
}