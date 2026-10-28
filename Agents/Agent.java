package Agents;

abstract class Agents
{
    // --------------------------- Attributes ----------------------------------
    private ColonyFacility currentLocation;
    private String[] healthStatus = {"healthy", "moderate", "unhealthy"};
    private String id;
    private boolean onMission;

    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean canPerformMission(Mission mission);
    public abstract boolean isUsable();

    // ------------------------ Concrete Methods ---------------------------------------
    public String[] getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return location;}
    public String getId() {return id;}

    public void setCurrentLocation(Location location) {currentLocation = location;}

    public void setOnMission(Mission mission) {Mission = mission;}

    public void changeHealth(String status)
    {

    }

    public boolean isAvailable()
    {

    }
}