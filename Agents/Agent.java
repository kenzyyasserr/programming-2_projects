package Agents;
import enums.HealthStatus;


abstract class Agents
{
    // --------------------------- Attributes ----------------------------------
    private ColonyFacility currentLocation;
    private HealthStatus healthStatus;
    private String id;
    private boolean onMission;

    public Agents(ColonyFacility currentLocation, String id, boolean onMission)
    {
        this.currentLocation = currentLocation;
        this.healthStatus = HealthStatus.HEALTHY; // Default health status
        this.id = id;
        this.onMission = onMission;
    }



    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean canPerformMission(Mission mission);
    public abstract boolean isUsable();

    // ------------------------ Concrete Methods ---------------------------------------
    public String[] getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return currentLocation;}
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