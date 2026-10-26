package Agents;

abstract class Agents
{
    // --------------------------- Attributes ----------------------------------


    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean canPerformMission(Mission mission);
    public abstract boolean isUsable();

    // ------------------------ Concrete Methods ---------------------------------------
    public String getHealth()
    {

    }

    public Location getCurrentLocation()
    {

    }

    public void changeHealth(String status)
    {

    }

    public void setCurrentLocation(Location location)
    {

    }

    public boolean isAvailable()
    {

    }

    public String getId()
    {

    }

    public void setOnMission()
    {

    }
}