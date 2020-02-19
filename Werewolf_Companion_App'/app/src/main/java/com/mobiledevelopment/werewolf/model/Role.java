package com.mobiledevelopment.werewolf.model;


import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.util.Util;

public enum Role
{
    Villager(
            "@string/ButtonToDelete",
            "hello there !",
            "@drawable/im_villager",
            Team.Villager,
            R.drawable.ic_villager,
            false,
            true,
            PowerActivationTime.None,
            PowerActivationOrder.Villager),

    Werewolf(
            "Werewolf",
            "ahoooouuu !",
            "@drawable/im_werewolf",
            Team.Werewolf,
            R.drawable.ic_werewolf,
            false,
            true,
            PowerActivationTime.Night,
            PowerActivationOrder.Werewolf),

    Cupid(
            "Cupid",
            "aim... loose !",
            "@drawable/im_cupid",
            Team.Villager,
            R.drawable.ic_villager,
            true,
            false,
            PowerActivationTime.Night,
            PowerActivationOrder.Cupid);


    // Describing the fields
    String name;
    String description;
    String imagePath;
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
     * @param name Name of the instance
     * @param description Description of the instance
     * @param imagePath Path leading to the image of the instance
     * @param team Team of the instance
     * @param icon Path leading to the icon of the instance
     * @param isPowerReusable Whether the power of the instance is reusable or not
     * @param isUnique Whether the power of the instance is unique (only one instance)
     * @param powerActivationTime At which day-time the power of the instance activates
     * @param powerActivationOrder Specific order index when the power of the instance activates
     */
    Role(
            String name,
            String description,
            String imagePath,
            Team team,
            int icon,
            boolean isPowerReusable,
            boolean isUnique,
            PowerActivationTime powerActivationTime,
            int powerActivationOrder)
    {
        this.name = name;
        this.description = description;
        this.imagePath = (imagePath != null) ? imagePath : Util.IMAGE_DEFAULT;
        this.team = team;
        this.icon = (icon != 0) ? icon : R.drawable.ic_villager;
        this.isUnique = isUnique;
        this.isPowerReusable = isPowerReusable;
        this.powerActivationTime = powerActivationTime;
        this.powerActivationOrder = powerActivationOrder;
    }


    // GETTER SETTER
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
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